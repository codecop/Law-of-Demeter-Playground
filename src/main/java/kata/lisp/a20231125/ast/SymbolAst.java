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
        String value = getValue(); // LoD_O.1 or LoD_Cw.6
        visitor.visitSymbol(value); // LoD_O.2
    }

    public String getSymbol() {
        return getValue(); // LoD_O.1 or LoD_Cw.6
    }

}

// LoD review OK
