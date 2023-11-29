package kata.lisp.a20231125;

/**
 * A node in the syntax tree containing a symbol (name).
 */
public class SymbolAst extends SingleValueAst<String> {

    private final String symbol;

    public SymbolAst(String value) {
        super(value);
        this.symbol = value;
    }

    @Override
    protected ResultType getValueType() {
        throw new IllegalStateException("Cannot eval symbol " + symbol + " on its own");
    }

    public Result evalAsFunction(Ast[] arguments, Context context) {
        Result[] tempResults = evalArguments(arguments, context);
        return context.applyFunction(symbol, tempResults);
    }

    private Result[] evalArguments(Ast[] arguments, Context context) {
        Result[] tempResults = new Result[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            tempResults[i] = arguments[i].eval(context);
        }
        return tempResults;
    }

}
