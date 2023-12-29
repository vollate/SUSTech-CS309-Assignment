package factory;

import annotations.Inject;
import annotations.Value;

import java.io.FileInputStream;
import java.lang.reflect.*;
import java.util.Properties;

public class BeanFactoryImpl implements factory.BeanFactory {
    private final Properties nameProperties = new Properties();
    private final Properties valueProperties = new Properties();

    @Override
    public void loadInjectProperties(String path) {
        try {
            FileInputStream in = new FileInputStream(path);
            nameProperties.load(in);
            in.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void loadValueProperties(String path) {
        try {
            FileInputStream in = new FileInputStream(path);
            valueProperties.load(in);
            in.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override

    public <T> T createInstance(Class<T> clazz) {
        try {
            String implClassName = nameProperties.getProperty(clazz.getName());
            T instance;
            Class<?> implClass = implClassName == null ? Class.forName(clazz.getName()) : Class.forName(implClassName);
            Constructor<?> m = implClass.getDeclaredConstructors()[0];
            if (m.getParameterCount() != 0) {
                Class<?>[] parameterTypes = m.getParameterTypes();
                Parameter[] param = m.getParameters();
                Object[] parameters = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    injectParametersValue(parameterTypes, param, parameters, i);
                }
                instance = (T) m.newInstance(parameters);
            } else instance = (T) implClass.getDeclaredConstructor().newInstance();
            return getT((Class<T>) (implClassName == null ? clazz : implClass), instance);

        } catch (Exception e) {
            System.err.println("Failed to create instance for " + clazz.getName() + ": " + e);
            return null;
        }
    }

    private void injectParametersValue(Class<?>[] parameterTypes, Parameter[] param, Object[] parameters, int i) {
        if (param[i].isAnnotationPresent(Value.class)) {
            String propertyKey = param[i].getAnnotation(Value.class).value();
            String propertyValue = valueProperties.getProperty(propertyKey);
            int min = param[i].getAnnotation(Value.class).min();
            int max = param[i].getAnnotation(Value.class).max();
            if (Integer.TYPE.equals(parameterTypes[i]) || Integer.class.equals(parameterTypes[i])) {
                int tmp = Integer.parseInt(propertyValue);
                if (tmp < min || tmp > max) parameters[i] = 0;
                else parameters[i] = tmp;
            } else if (Double.TYPE.equals(parameterTypes[i]) || Double.class.equals(parameterTypes[i])) {
                double tmp = Double.parseDouble(propertyValue);
                if (tmp < min || tmp > max) parameters[i] = 0;
                else parameters[i] = tmp;
            } else if (Boolean.TYPE.equals(parameterTypes[i]) || Boolean.class.equals(parameterTypes[i])) {
                parameters[i] = Boolean.parseBoolean(propertyValue);
            } else {
                if (propertyValue.length() > max || propertyValue.length() < min)
                    parameters[i] = "default_value";
                else parameters[i] = propertyValue;
            }
        } else parameters[i] = createInstance(parameterTypes[i]);
    }

    private <T> T getT(Class<T> clazz, T instance) throws IllegalAccessException, InvocationTargetException {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                Class<?> fieldType = field.getType();
                Object fieldInstance = createInstance(fieldType);
                field.setAccessible(true);
                field.set(instance, fieldInstance);
            } else if (field.isAnnotationPresent(Value.class)) {
                String value = valueProperties.getProperty(field.getAnnotation(Value.class).value());
                if (value == null) {
                    throw new RuntimeException("No value for field " + field.getName());
                }
                field.setAccessible(true);
                field.set(instance, convertToFieldType(value, field));
            }
        }
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inject.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Parameter[] param = method.getParameters();
                Object[] parameters = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; ++i) {
                    injectParametersValue(parameterTypes, param, parameters, i);
                }
                method.setAccessible(true);
                method.invoke(instance, parameters);
            }
        }
        return instance;
    }

    private Object convertToFieldType(String value, Field field) {
        Class<?> fieldType = field.getType();
        int min = field.getAnnotation(Value.class).min();
        int max = field.getAnnotation(Value.class).max();
        if (Integer.TYPE.equals(fieldType) || Integer.class.equals(fieldType)) {
            int tmp = Integer.parseInt(value);
            if (tmp < min || tmp > max) return 0;
            return tmp;
        } else if (Double.TYPE.equals(fieldType) || Double.class.equals(fieldType)) {
            double tmp = Double.parseDouble(value);
            if (tmp < min || tmp > max) return 0;
            return tmp;
        } else if (Boolean.TYPE.equals(fieldType) || Boolean.class.equals(fieldType)) {
            return Boolean.parseBoolean(value);
        }
        if (value.length() > max || value.length() < min) return "default_value";
        return value;
    }

}
