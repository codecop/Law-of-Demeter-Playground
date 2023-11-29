package kata.lisp.a20231125;

import java.util.List;
import java.util.Objects;

/**
 * A node in the syntax tree with multiple child nodes.
 */
public abstract class MultipleValueAst implements Ast {

    private final List<Ast> expressions;

    public MultipleValueAst(List<Ast> expressions) {
        Objects.requireNonNull(expressions);
        this.expressions = expressions;
    }

    // a getter
    protected Ast[] getChildren() {
        return expressions.toArray(new Ast[expressions.size()]);
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

}
