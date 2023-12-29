package testclass;

import annotations.Value;

public class FEnhanced extends F {
    @Value(value = "n1", min = 10, max = 20)
    protected int number1;
    @Value(value = "n2", min = 10, max = 20)
    protected int number2;
    @Value(value = "name1", max = 5)
    protected String name1;

    @Value(value = "name2", max = 10)
    protected String name2;

    @Override
    public int calculateNumbers() {
        return number1 + number2;
    }

    @Override
    public String appendTwoStrings() {
        return name1 + name2;
    }

    @Override
    public String toString() {
        return "FEnhanced";
    }
}
