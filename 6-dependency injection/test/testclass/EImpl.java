package testclass;

import annotations.Inject;
import annotations.Value;

public class EImpl implements E {
    @Inject
    private C c;

    public C getC() {
        return c;
    }

    @Override
    public String toString() {
        return String.format("EImpl{c=%s}", c);
    }
}
