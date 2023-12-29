package testclass;

import annotations.Inject;
import annotations.Value;

public class JImpl2 implements J{
    private E e;
    private int integer;
    private String string;

    @Inject
    public JImpl2(E e,
                 @Value(value = "n2", min = 10, max = 20) int integer,
                 @Value(value = "name2", min = 8, max = 12) String string) {
        this.e = e;
        this.integer = integer;
        this.string = string;
    }


    @Override
    public E getE() {
        return e;
    }

    @Override
    public int getInt() {
        return this.integer;
    }

    @Override
    public String getString() {
        return this.string;
    }
}
