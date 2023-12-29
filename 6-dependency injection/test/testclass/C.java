package testclass;

import annotations.Inject;

public class C {
    @Inject
    private A a;
    @Inject
    private B b;

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    @Override
    public String toString() {
        return String.format("C[a=%s,b=%s]", a, b);
    }
}
