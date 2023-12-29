package testclass;

import annotations.Inject;

public class G {
    @Inject
    private E e;

    private F f;

    @Inject
    public G(F f) {
        this.f = f;
    }

    public E getE() {
        return e;
    }

    public F getF() {
        return f;
    }

    @Override
    public String toString() {
        return String.format("G{e=%s,f=%s}", e, f);
    }
}
