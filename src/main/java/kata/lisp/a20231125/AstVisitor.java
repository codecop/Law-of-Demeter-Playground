package kata.lisp.a20231125;

public interface AstVisitor {

    void visitBoolean(Boolean value);

    void visitNumber(Integer value);

    void visitString(String value);

    void visitExpression(Ast[] expressions);

    void visitProgram(Ast[] children);

}
