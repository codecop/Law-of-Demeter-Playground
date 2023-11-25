package kata.lisp.a20231125;

import java.util.Objects;

public class SymbolAst implements Ast {

    private final String value;

    public SymbolAst(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SymbolAst)) {
            return false;
        }
        SymbolAst that = (SymbolAst) other;
        return this.value.equals(that.value);
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
