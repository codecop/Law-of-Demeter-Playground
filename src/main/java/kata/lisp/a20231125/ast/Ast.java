package kata.lisp.a20231125.ast;

/**
 * A node in the syntax tree.
 */
public interface Ast {

    void accept(AstVisitor visitor);

}
