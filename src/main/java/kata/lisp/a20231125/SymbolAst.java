package kata.lisp.a20231125;

public class SymbolAst extends AbstractAst<String> {

    public SymbolAst(String value) {
        super(value);
    }

    @Override
    public Result eval() {
        throw new SyntaxError("Cannot eval symbol on its own");
    }

}
