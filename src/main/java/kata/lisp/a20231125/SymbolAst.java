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
        return context.getFunctionNamed(value);
    }

}
