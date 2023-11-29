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
        visitor.visitExpression(getChildren());
    }

    @Override
    public String toString() {
        return "Expression" + super.toString();
    }

}
