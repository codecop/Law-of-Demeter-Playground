package kata.lisp.a20231125.ast;

import java.util.List;
import java.util.Objects;

/**
 * A node in the syntax tree with multiple child nodes.
 */
public abstract class MultipleValueAst implements Ast {

    private final List<Ast> expressions;

    public MultipleValueAst(List<Ast> expressions) {
        Objects.requireNonNull(expressions); // ignore
        this.expressions = expressions;
    }

    // a getter
    public Ast[] getChildren() {
        Ast[] target = new Ast[expressions.size()]; // LoD_O.4
        return expressions.toArray(target); // LoD_O.4
    }

    @Override
    public boolean equals(Object other) {
        if (!this.getClass().isInstance(other)) { // LoD_O.4
            return false;
        }

        // PMD conform code, but uses LoD_C
        MultipleValueAst that = this.getClass().cast(other); // LoD_O.4
        return this.expressions.equals(that.expressions); // LoD_O.4, LoD_Cs.1
    }

    @Override
    public int hashCode() {
        return expressions.hashCode(); // LoD_O.4
    }

    @Override
    public String toString() {
        return "Ast(" + expressions.toString() + ")"; // LoD_O.4
    }

}

// LoD review OK
