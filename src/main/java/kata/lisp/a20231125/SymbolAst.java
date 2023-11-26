package kata.lisp.a20231125;

public class SymbolAst extends SingleValueAst<String> {

    public SymbolAst(String value) {
        super(value);
    }

    @Override
    public Result eval(Context context) {
        throw new IllegalStateException("Cannot eval symbol on its own");
    }

    public Result evalFunction(Ast[] arguments, Context context) {
        Result[] tempResults = evalArguments(arguments, context);
        return context.applyFunction(value, tempResults);
    }

    private Result[] evalArguments(Ast[] arguments, Context context) {
        Result[] tempResults = new Result[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            tempResults[i] = arguments[i].eval(context);
        }
        return tempResults;
    }

}
