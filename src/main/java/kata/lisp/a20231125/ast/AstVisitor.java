package kata.lisp.a20231125.ast;

public interface AstVisitor {

    void visitBoolean(Boolean value);

    void visitNumber(Integer value);

    void visitString(String value);

    void visitSymbol(String value);

    void visitExpression(Ast[] expressions);

    void visitProgram(Ast[] program);

}

// LoD review OK
