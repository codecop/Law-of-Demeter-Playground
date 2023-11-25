package kata.lisp.a20231125;

import java.util.Objects;

public class NumberAst implements Ast {

    private final int value;

    public NumberAst(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof NumberAst)) {
            return false;
        }
        NumberAst that = (NumberAst) other;
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
