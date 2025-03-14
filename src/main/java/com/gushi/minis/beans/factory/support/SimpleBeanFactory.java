package com.gushi.minis.beans.factory.support;

import com.gushi.minis.beans.*;
import com.gushi.minis.beans.factory.BeanFactory;
import com.gushi.minis.beans.factory.config.BeanDefinition;
import com.gushi.minis.beans.factory.config.ConstructorArgumentValue;
import com.gushi.minis.beans.factory.config.ConstructorArgumentValues;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/6 15:24
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private List<String> beanDefinitionNames = new ArrayList<>();
    private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);

    /**
     * 容器核心方法
     * @param beanName bean名称
     * @return
     * @throws BeansException
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        //尝试获取bean实例
        Object singleton = this.getSingleton(beanName);
        //如果此时没有这个Bean实例，则获取定义来创建实例
        if (singleton == null) {
            singleton = this.earlySingletonObjects.get(beanName);
            if (singleton == null) {
                BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
                if (beanDefinition == null) {
                    throw new BeansException("no bean definition");
                }
                singleton = createBean(beanDefinition);
                this.registerSingleton(beanName, singleton);
                //预留beanPostProcessor的位置
                // step 1: postProcessBeforeInitialization
                // step 2: afterPropertiesSet
                // step 3: init-method
                // step 4: postProcessAfterInitialization
            }
        }
        return singleton;
    }

    private Object createBean(BeanDefinition beanDefinition) {
        Class<?> clz = null;
        Object obj = doCreateBean(beanDefinition);
        this.earlySingletonObjects.put(beanDefinition.getId(), obj);
        try {
            clz = Class.forName(beanDefinition.getClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //处理属性
        handleProperties(beanDefinition, clz, obj);
        return obj;
    }

    //doCreateBean创建毛胚实例，仅仅调用构造方法，没有进行属性处理
    private Object doCreateBean(BeanDefinition beanDefinition) {
        Class<?> clz = null;
        Object obj = null;
        Constructor<?> con = null;
        try {
            clz = Class.forName(beanDefinition.getClassName());
            //处理构造器参数
            ConstructorArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();
            if (!constructorArgumentValues.isEmpty()) {
                int argumentCount = constructorArgumentValues.getArgumentCount();
                Class<?>[] paramTypes = new Class<?>[argumentCount];
                Object[] paramValues = new Object[argumentCount];
                //对每一个参数，分数据类型分别处理
                for (int i = 0; i < argumentCount; i++) {
                    ConstructorArgumentValue indexedArgumentValue = constructorArgumentValues.getIndexedArgumentValue(i);
                    if ("String".equals(indexedArgumentValue.getType()) || "java.lang.String".equals(indexedArgumentValue.getType())) {
                        paramTypes[i] = String.class;
                        paramValues[i] = indexedArgumentValue.getValue();
                    } else if ("Integer".equals(indexedArgumentValue.getType()) || "java.lang.Integer".equals(indexedArgumentValue.getType())) {
                        paramTypes[i] = Integer.class;
                        paramValues[i] = Integer.valueOf((String)indexedArgumentValue.getValue());
                    } else if ("int".equals(indexedArgumentValue.getType())) {
                        paramTypes[i] = int.class;
                        paramValues[i] = Integer.valueOf((String)indexedArgumentValue.getValue());
                    } else { //默认为String
                        paramTypes[i] = String.class;
                        paramValues[i] = indexedArgumentValue.getValue();
                    }
                }
                obj = clz.getDeclaredConstructor(paramTypes).newInstance(paramValues);
            } else {
                obj = clz.getConstructor().newInstance();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                 InstantiationException e) {
            e.printStackTrace();
//            throw new BeansException("create bean error: can not find matched constructor");
        }
        return obj;
    }

    private void handleProperties(BeanDefinition beanDefinition, Class<?> clz, Object obj) {
        System.out.println("handle properties for bean : " + beanDefinition.getId());
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        //存在属性
        if (!propertyValues.isEmpty()) {
            for (int i = 0; i < propertyValues.size(); i++) {
                PropertyValue propertyValue = propertyValues.getPropertyValueList().get(i);
                String pName = propertyValue.getName();
                Object pValue = propertyValue.getValue();
                String pType = propertyValue.getType();
                boolean isRef = propertyValue.isRef();
                Object pv = pValue;
                Class<?> paramType = null;
                if (!isRef) {
                    if ("String".equals(pType) || "java.lang.String".equals(pType)) {
                        paramType = String.class;
                    } else if ("Integer".equals(pType) || "java.lang.Integer".equals(pType)) {
                        paramType = Integer.class;
                    } else if ("int".equals(pType)) {
                        paramType = int.class;
                    } else {
                        paramType = String.class;
                    }
                } else {
                    try {
                        paramType = Class.forName(pType);
                        pv = getBean((String) pValue);
                    } catch (BeansException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

                //按照setXxx规范查找setter方法，调用setter方法设置属性
                String methodName = "set" + pName.substring(0,1).toUpperCase() + pName.substring(1);
                Method method = null;
                try {
                    method = clz.getDeclaredMethod(methodName, paramType);
                    method.invoke(obj, pv);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }

        }
    }

    /**
     * 在Spring中，Bean 是结合在一起同时创建完毕的。为了减少它内部的复杂性，Spring 对外提供了一个很重要的包装方法：refresh()。
     * 具体的包装方法也很简单，就是对所有的 Bean 调用了一次 getBean()，利用 getBean() 方法中的 createBean() 创建 Bean 实例，
     * 就可以只用一个方法把容器中所有的 Bean 的实例创建出来了。
     */
    public void refresh() {
        for (String beanDefinitionName : beanDefinitionNames) {
            try {
                getBean(beanDefinitionName);
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean containsBean(String name) {
        return this.containSingleton(name);
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
        this.beanDefinitionNames.add(name);
        if (!beanDefinition.isLazyInit()) {
            try {
                getBean(name);
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeBeanDefinition(String name) {
        this.beanDefinitionMap.remove(name);
        this.beanDefinitionNames.remove(name);
        this.removeSingleton(name);
    }

    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitionMap.containsKey(name);
    }

    public boolean isSingleton(String name) {
        return this.beanDefinitionMap.get(name).isSingleton();
    }

    public boolean isPrototype(String name) {
        return this.beanDefinitionMap.get(name).isPrototype();
    }

    public Class<?> getType(String name) {
        return this.beanDefinitionMap.get(name).getClass();
    }


}
