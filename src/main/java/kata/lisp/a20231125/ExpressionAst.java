package kata.lisp.a20231125;

import java.util.Arrays;
import java.util.List;

/**
 * A node of an expression, starting with a symbol and a list of arguments.
 */
public final class ExpressionAst extends MultipleValueAst {

    public ExpressionAst(List<Ast> expressions) {
        super(expressions);
    }

    @Override
    public Result eval(Functions context) {
        Ast[] expressions = getChildren();
        if (expressions[0] instanceof SymbolAst) {
            return evalAsFunction(expressions, context);
        }
        throw new IllegalStateException();
    }

    private Result evalAsFunction(Ast[] expressions, Functions context) {
        SymbolAst symbol = (SymbolAst) expressions[0];
        Ast[] arguments = Arrays.copyOfRange(expressions, 1, expressions.length);
        return evalAsFunction(symbol, arguments, context);
    }

    private Result evalAsFunction(SymbolAst symbol, Ast[] arguments, Functions context) {
        return symbol.evalAsFunction(arguments, context);
    }

    @Override
    public void accept(EvalVisitor visitor) {
        visitor.visitExpression(getChildren());
    }
    
}
