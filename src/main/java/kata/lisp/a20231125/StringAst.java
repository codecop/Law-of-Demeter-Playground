package kata.lisp.a20231125;

import java.util.Objects;

public class StringAst implements Ast {

    private final String value;

    public StringAst(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof StringAst)) {
            return false;
        }
        StringAst that = (StringAst) other;
        return this.value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
