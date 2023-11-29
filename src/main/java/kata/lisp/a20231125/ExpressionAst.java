package kata.lisp.a20231125;

import java.util.List;
import java.util.Objects;

/**
 * A node of an expression, starting with a symbol and a list of arguments.
 */
public class ExpressionAst extends MultipleValueAst {

    private final SymbolAst symbol;

    public ExpressionAst(SymbolAst symbol, List<Ast> arguments) {
        super(arguments);
        Objects.requireNonNull(symbol);
        this.symbol = symbol;
    }

    @Override
    public Result eval(Context context) {
        Ast[] arguments = getChildren();
        return symbol.evalAsFunction(arguments, context);
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }
        ExpressionAst that = (ExpressionAst) other;
        return this.symbol.equals(that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol) ^ super.hashCode();
    }

    @Override
    public String toString() {
        return "ExpressionAst(" + symbol + ": " + super.toString() + ")";
    }

}
