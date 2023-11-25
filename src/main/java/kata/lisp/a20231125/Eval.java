package kata.lisp.a20231125;

public class Eval {

    public Result eval(Ast ast) {
        Context context = new EmptyContext();
        context.addFunctionNamed(new Add());
        return ast.eval(context);
    }

}

class Add implements Function {

    @Override
    public String getName() {
        return "+";
    }

    @Override
    public Class<?> getArgumentType() {
        return Integer.class;
    }

    @Override
    public Result execute(Result[] arguments) {
        int s = 0;
        for (Result r : arguments) {
            s += (Integer) r.value();
        }
        return new Result(s, Result.Type.NUMBER);
    }

}
