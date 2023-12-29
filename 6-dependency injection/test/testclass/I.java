package testclass;

import annotations.Inject;

public class I {
    private E e;
    private F f;

    public I(E e) {
        this.e = e;
    }

    public I(F f) {
        this.f = f;
    }

    @Inject
    public I(E e, F f) {
        this.e = e;
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
        return String.format("I{e=%s,f=%s}", e, f);
    }
}
