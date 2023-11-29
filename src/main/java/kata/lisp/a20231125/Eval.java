package kata.lisp.a20231125;

public class Eval {

    public Result eval(Ast ast) {
        Functions context = prepareContext();
        // (1) return ast.eval(context);
        EvalVisitor visitor = new EvalVisitor(context);
        return ast.accept(visitor);
    }

    private Functions prepareContext() {
        Functions context = new Functions();
        BasicFunctions.add(context);
        return context;
    }

}
