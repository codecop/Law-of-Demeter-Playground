package kata.lisp.a20231125.eval;

public abstract class StrictFunction extends Function {

    private final TypeOfArguments typeOfArguments;

    public StrictFunction(String name, NumberOfArguments numberOfArguments, TypeOfArguments typeOfArguments) {
        super(name, numberOfArguments);
        this.typeOfArguments = typeOfArguments;
    }

    @Override
    public Result apply(LazyResults lazyArguments, Variables variables) {
        Results arguments = lazyArguments.evalArguments();
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

    public boolean matchesArgumentType(int i, ResultType parameterType) {
        return typeOfArguments.matches(i, parameterType);
    }

    protected abstract Result apply(Object[] arguments);

}
