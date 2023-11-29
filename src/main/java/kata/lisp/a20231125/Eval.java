package kata.lisp.a20231125;

public class Eval {

    public Result eval(Ast ast) {
        Functions context = prepareContext();
        return ast.eval(context);
    }

    private Functions prepareContext() {
        Functions context = new Functions();
        BasicFunctions.add(context);
        return context;
    }

}
