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

    @Override
    public void accept(EvalVisitor visitor) {
        throw new IllegalStateException("Cannot eval symbol " + symbol + " on its own");
        // return visitor.visitSymbol(symbol);
    }

//    public Result evalAsFunction(Ast[] arguments, Functions context) {
//        Results tempResults = evalArguments(arguments, context);
//        return context.applyFunction(symbol, tempResults);
//    }
//
//    private Results evalArguments(Ast[] arguments, Functions context) {
//        Result[] tempResults = new Result[arguments.length];
//        for (int i = 0; i < arguments.length; i++) {
//            tempResults[i] = arguments[i].eval(context);
//        }
//        return new Results(tempResults);
//    }

    public Result evalAsFunction(Ast[] arguments, Functions context) {
        return context.applyFunction(symbol, arguments);
    }

}
