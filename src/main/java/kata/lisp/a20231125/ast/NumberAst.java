package kata.lisp.a20231125.ast;

public final class NumberAst extends SingleValueAst<Integer> {

    public NumberAst(Integer value) {
        super(value);
    }

    @Override
    public void accept(AstVisitor visitor) {
        Integer value = getValue(); // LoD_O.1 or LoD_Cw.6
        visitor.visitNumber(value); // LoD_O.2 
    }

}

// LoD review OK
