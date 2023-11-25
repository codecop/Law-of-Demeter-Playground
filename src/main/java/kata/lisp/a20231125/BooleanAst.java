package kata.lisp.a20231125;

import java.util.Objects;

public class BooleanAst {

    private final boolean value;

    public BooleanAst(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BooleanAst)) {
            return false;
        }
        BooleanAst that = (BooleanAst) other;
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Ast(" + value + ")";
    }

}
