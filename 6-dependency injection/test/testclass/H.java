package testclass;

import annotations.Inject;
import annotations.Value;

public class H {
    private E e;
    private F f;

    private String name;

    private int value;

    @Inject
    public void setE(E e) {
        this.e = e;
    }

    @Inject
    public void setOthers(F f, @Value(value = "name1") String name, @Value(value = "n3") int value) {
        this.f = f;
        this.name = name;
        this.value = value;
    }

    public E getE() {
        return e;
    }

    public F getF() {
        return f;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("H{e=%s,f=%s,name=%s,value=%d}", e, f, name, value);
    }
}
