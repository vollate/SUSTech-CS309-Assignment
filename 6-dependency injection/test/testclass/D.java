package testclass;

import annotations.Inject;
import annotations.Value;

public class D {
    @Value(value = "n2")
    private int age;
    @Value(value = "d2")
    private double money;
    @Value(value = "trueValue")
    private boolean isStudent;
    @Value(value = "name1")
    private String name;

    public int getAge() {
        return age;
    }

    public double getMoney() {
        return money;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public String getName() {
        return name;
    }
}
