package dao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactoryImpl implements DaoFactory {
    private static DaoFactoryImpl instance;
    private Properties prop;

    private DaoFactoryImpl() {
        prop = new Properties();
        try (InputStream in = new BufferedInputStream(new FileInputStream("src/resources/properties"))) {
            prop.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized DaoFactoryImpl getInstance() {
        if (instance == null) {
            instance = new DaoFactoryImpl();
        }
        return instance;
    }

    @Override
    public ComputerDao createComputerDao() {
        return createDao(prop.getProperty("computerDao"));
    }

    @Override
    public StaffDao createStaffDao() {
        return createDao(prop.getProperty("staffDao"));
    }

    private <T> T createDao(String className) {
        try {
            Class<?> clz = Class.forName(className);
            return (T) clz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
