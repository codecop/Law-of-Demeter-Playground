package kata.lisp.a20231125.eval;

public abstract class StrictFunction extends Function {

    public StrictFunction(String name, NumberOfArguments numberOfArguments, TypeOfArguments typeOfArguments) {
        super(name, numberOfArguments, typeOfArguments);
    }

    @Override
    public Result apply(LazyResults lazyArguments, Variables variables) {
        Results arguments = lazyArguments.evalArguments();
        return apply(arguments);
    }

    private Result apply(Results arguments) {
        Result error = arguments.firstError();
        if (error != null) {
            return error;
        }

        error = arguments.typeMismatchWith(this);
        if (error != null) {
            return error;
        }

        Object[] values = arguments.values();
        return apply(values);
    }

    protected abstract Result apply(Object[] arguments);

}
