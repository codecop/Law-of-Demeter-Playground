package kata.lisp.a20231125;

/**
 * A node in the syntax tree.
 */
public interface Ast {

    Result eval(Context context);
}
