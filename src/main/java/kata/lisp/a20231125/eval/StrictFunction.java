package kata.lisp.a20231125.eval;

public abstract class StrictFunction extends Function {

    public StrictFunction(String name) {
        super(name);
    }

    @Override
    public Result apply(LazyResult[] lazyArguments, Variables variables) {
        Results arguments = evalArgumentsFirst(lazyArguments);
        return apply(arguments);
    }

    private Result apply(Results arguments) {
        Result error = arguments.firstErrorInArguments();
        if (error != null) {
            return error;
        }

        error = arguments.typeMismatchWith(this);
        if (error != null) {
            return error;
        }

        Object[] values = arguments.toValues();
        return apply(values);
    }

    private Results evalArgumentsFirst(LazyResult[] lazyArguments) {
        Result[] arguments = new Result[lazyArguments.length];
        for (int i = 0; i < lazyArguments.length; i++) {
            arguments[i] = lazyArguments[i].get();
        }
        return new Results(arguments);
    }

    public abstract boolean matchesArgumentType(int i, ResultType parameterType);

    protected abstract Result apply(Object[] arguments);

}
