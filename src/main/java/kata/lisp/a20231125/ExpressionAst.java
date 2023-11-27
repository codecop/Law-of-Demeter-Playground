package kata.lisp.a20231125;

import java.util.List;
import java.util.Objects;

public class ExpressionAst implements Ast {

    private final SymbolAst symbol;
    private final List<Ast> remaining;

    public ExpressionAst(SymbolAst symbol, List<Ast> arguments) {
        Objects.requireNonNull(symbol);
        this.symbol = symbol;
        this.remaining = arguments;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        ExpressionAst that = (ExpressionAst) other;
        return this.symbol.equals(that.symbol) && this.remaining.equals(that.remaining);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol) ^ Objects.hash(remaining);
    }

    @Override
    public String toString() {
        return "Ast(" + symbol + ": " + remaining + ")";
    }

    @Override
    public Result eval(Context context) {
        Ast[] arguments = remaining.toArray(new Ast[remaining.size()]);
        return symbol.evalFunction(arguments, context);
    }

}
