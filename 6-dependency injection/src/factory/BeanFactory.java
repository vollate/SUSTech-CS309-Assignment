package factory;

public interface BeanFactory {
    void loadInjectProperties(String path);
    void loadValueProperties(String path);
    <T> T createInstance(Class<T> clazz);
}
