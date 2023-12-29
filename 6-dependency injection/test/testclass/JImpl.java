package testclass;

import annotations.Inject;
import annotations.Value;

public class JImpl implements J {

    private E e;
    private int integer;
    private String string;

    @Inject
    public JImpl(E e,
                 @Value(value = "n3", min = 10, max = 20) int integer,
                 @Value(value = "name3", min = 8, max = 12) String string) {
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
