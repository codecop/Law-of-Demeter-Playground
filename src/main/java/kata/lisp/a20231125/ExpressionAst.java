package kata.lisp.a20231125;

import java.util.List;
import java.util.Objects;

public class ExpressionAst implements Ast {

    private final Ast symbol;
    private final List<Ast> arguments;

    public ExpressionAst(Ast symbol, List<Ast> arguments) {
        Objects.requireNonNull(symbol);
        this.symbol = symbol;
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        ExpressionAst that = (ExpressionAst) other;
        return this.symbol.equals(that.symbol) && this.arguments.equals(that.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol) ^ Objects.hash(arguments);
    }

    @Override
    public String toString() {
        return "Ast(" + symbol + ": " + arguments + ")";
    }

    @Override
    public Result eval(Context context) {
        SymbolAst symbol2 = (SymbolAst) symbol;
        Function function = symbol2.getFunction(context); //x
        Result[] rs = new Result[arguments.size()];
        for (int i = 0; i < arguments.size(); i++) {
            rs[i] = arguments.get(i).eval(context); //x
        }
        return function.execute(rs); //x
    }

}
