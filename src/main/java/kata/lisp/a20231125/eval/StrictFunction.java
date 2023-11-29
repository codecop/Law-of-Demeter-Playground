package kata.lisp.a20231125.eval;

public abstract class StrictFunction extends Function {

    public StrictFunction(String name) {
        super(name);
    }

    @Override
    public Result apply(LazyResult[] lazyArguments) {
        Results arguments = evalArgumentsFirst(lazyArguments);

        Result error = arguments.firstErrorInArguments();
        if (error != null) {
            return error;
        }

        error = arguments.numberMismatchWith(this);
        if (error != null) {
            return error;
        }

        error = arguments.typeMismatchWith(this);
        if (error != null) {
            return error;
        }

        Object[] values = arguments.toValues();
        return execute(values);
    }

    private Results evalArgumentsFirst(LazyResult[] lazyArguments) {
        Result[] arguments = new Result[lazyArguments.length];
        for (int i = 0; i < lazyArguments.length; i++) {
            arguments[i] = lazyArguments[i].eval();
        }
        return new Results(arguments);
    }

    public abstract boolean matchesArgumentNumber(int parameterCount);

    public abstract boolean matchesArgumentType(int i, ResultType parameterType);

    protected abstract Result execute(Object[] arguments);

}
