package kata.lisp.a20231125;

public class Eval {

    public Result eval(Ast ast) {
        Functions context = prepareContext();
        return ast.eval(context);
    }

    private Functions prepareContext() {
        Functions context = new Functions();
        context.addFunctionNamed(new IntegerAddition());
        context.addFunctionNamed(new IntegerSquareRoot());
        return context;
    }

}
