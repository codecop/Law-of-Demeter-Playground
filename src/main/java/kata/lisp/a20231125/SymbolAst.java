package kata.lisp.a20231125;

public class SymbolAst extends SingleValueAst<String> {

    public SymbolAst(String value) {
        super(value);
    }

    @Override
    public Result eval(Context context) {
        throw new SyntaxError("Cannot eval symbol on its own");
    }

    public Function getFunction(Context context) {
        Function function = context.getFunctionNamed(value);
        if (function == null) {
            throw new FunctionLookupError(value);
        }
        return function;
    }

}

class FunctionLookupError extends RuntimeException {

    public FunctionLookupError(String name) {
        super(name);
    }

}
