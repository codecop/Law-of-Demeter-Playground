package kata.lisp.a20231125;

public class Eval {

    public Result eval(Ast ast) {
        Context context = prepareContext();
        return ast.eval(context);
    }

    private Context prepareContext() {
        Context context = new Context();
        context.addFunctionNamed(new Add());
        return context;
    }

}
