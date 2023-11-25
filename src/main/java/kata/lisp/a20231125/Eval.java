package kata.lisp.a20231125;

public class Eval {

    public Result eval(Ast ast) {
        Context context = new EmptyContext();
        return ast.eval(context);
    }

}
