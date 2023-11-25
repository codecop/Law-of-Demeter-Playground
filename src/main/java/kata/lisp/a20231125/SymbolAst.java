package kata.lisp.a20231125;

public class SymbolAst extends AbstractAst<String> {

    public SymbolAst(String value) {
        super(value);
    }

    @Override
    public Result eval() {
        return new Result(value, Result.Type.SYMBOL);
    }

}
