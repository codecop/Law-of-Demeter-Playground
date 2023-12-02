package kata.lisp.a20231125.ast;

import java.util.List;

/**
 * A node of an expression, starting with a symbol and a list of arguments.
 */
public final class ExpressionAst extends MultipleValueAst {

    public ExpressionAst(List<Ast> expressions) {
        super(expressions);
    }

    @Override
    public void accept(AstVisitor visitor) {
        Ast[] children = getChildren(); // LoD_O.1 or LoD_Cw.6
        visitor.visitExpression(children); // LoD_O.2
    }

    @Override
    public String toString() {
        return "Expression" + super.toString(); // LoD_Cw.6
    }

}

// LoD review OK
