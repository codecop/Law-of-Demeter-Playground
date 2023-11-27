package kata.lisp.a20231125;

import java.util.List;
import java.util.Objects;

public abstract class MultipleValueAst implements Ast {

    private final List<Ast> expressions;

    public MultipleValueAst(List<Ast> expressions) {
        this.expressions = expressions;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        MultipleValueAst that = (MultipleValueAst) other;
        return this.expressions.equals(that.expressions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expressions);
    }

    @Override
    public String toString() {
        return "Ast(" + expressions + ")";
    }

    protected Ast[] asArray() {
        return expressions.toArray(new Ast[expressions.size()]);
    }

}
