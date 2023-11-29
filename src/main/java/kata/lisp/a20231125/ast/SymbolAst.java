package kata.lisp.a20231125.ast;

/**
 * A node in the syntax tree containing a symbol (name).
 */
public class SymbolAst extends SingleValueAst<String> {

    public SymbolAst(String value) {
        super(value);
    }

    @Override
    public void accept(AstVisitor visitor) {
        visitor.visitSymbol(value);
    }

    public String getSymbol() {
        return value;
    }

}
