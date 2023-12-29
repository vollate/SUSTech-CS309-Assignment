import annotations.Inject;
import annotations.Value;
import democlass.AA;
import democlass.BB;
import democlass.CC;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Properties;

public class Demo {
    public static Properties loadProp(String path) {
        Properties p = new Properties();
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(path));
            p.load(in);
            return p;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Properties injectProp = loadProp("properties/private-inject.properties");
        Properties valueProp = loadProp("properties/private-value.properties");

        try {
            BB bObject = BB.class.getDeclaredConstructor().newInstance();
            CC cObject = CC.class.getDeclaredConstructor().newInstance();
            Constructor<?> constructor = null;
            for (Constructor<?> c : AA.class.getDeclaredConstructors()) {
                if (c.getAnnotation(Inject.class) != null) {
                    constructor = c;
                    break;
                }
            }
            Parameter[] parameters = constructor.getParameters();
            for (Parameter p : parameters) {
                System.out.printf("Type = %s\n", p.getType());
            }

            Object[] objects = new Object[parameters.length];
            Object parameterObject = null;
            for (Parameter p : parameters) {
                if (p.getAnnotation(Value.class) != null) {
                    System.out.println("The type of parameter:" + p.getType().getName());
                    Value valueAnnotation = p.getAnnotation(Value.class);
                    System.out.println("Annotation Name = " + valueAnnotation.value());
                    System.out.println("Annotation Value = " + valueProp.getProperty(valueAnnotation.value()));
                    if (p.getType() == boolean.class) {
                        parameterObject = Boolean.parseBoolean(valueProp.getProperty(valueAnnotation.value()));
                    }
                    if (p.getType() == int.class) {
                        parameterObject = Integer.parseInt(valueProp.getProperty(valueAnnotation.value()));
                    }
                    if (p.getType() == double.class) {
                        parameterObject = Double.parseDouble(valueProp.getProperty(valueAnnotation.value()));
                    }
                    if (p.getType() == String.class) {
                        parameterObject = valueProp.getProperty(valueAnnotation.value());
                    }
                }
            }
            objects[0] = bObject;
            objects[1] = cObject;
            objects[2] = parameterObject;
            AA aObject = (AA) constructor.newInstance(objects);
            System.out.println(aObject);

            Field field = aObject.getClass().getDeclaredField("field");
            if (field.getAnnotation(Value.class) != null) {
                Value valueAnnotation = field.getAnnotation(Value.class);
                if (field.getType() == int.class) {
                    field.setAccessible(true);
                    field.set(aObject, Integer.parseInt(valueProp.getProperty(valueAnnotation.value())));
                    field.setAccessible(false);
                }
                //todo: similary way to inject other type of field
            }
            System.out.println(aObject);


        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

    }
}
