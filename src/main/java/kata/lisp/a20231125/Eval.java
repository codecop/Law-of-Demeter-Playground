package kata.lisp.a20231125;

public class Eval {

    public Result eval(Ast ast) {
        if (ast instanceof NumberAst) {
            return new NumberResult(((NumberAst) ast).value);
        }
        return null;
    }

}
