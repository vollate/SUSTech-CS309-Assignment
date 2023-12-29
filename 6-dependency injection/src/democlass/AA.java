package democlass;

import annotations.Inject;
import annotations.Value;

public class AA {
    @Value(value = "n1")
    private int field;

    private boolean isOk;

    private BB bb;
    private CC cc;

    public AA(BB bb, CC cc) {
        this.bb = bb;
        this.cc = cc;
    }

    @Inject
    public AA(BB bb, CC cc, @Value(value = "falseValue") boolean isOk) {
        this.bb = bb;
        this.cc = cc;
        this.isOk = isOk;
    }

    public AA(int field) {
    }

    public int getField() {
        return field;
    }

    @Override
    public String toString() {
        return String.format("AA{field=%d,isOK=%s,bb=%s,cc=%s}", this.field, this.isOk, this.bb, this.cc);
    }
}
