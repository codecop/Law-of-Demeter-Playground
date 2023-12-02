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

    public String errorMatchingArgumentType(int index, Result argument) {
        return "Type mismatch of " + (index + 1) + ". argument to function " + toString() + // LoD_O.1
                ", expected " + typeOfArguments.get(index) + // LoD_O.4
                ", got " + argument.type(); // LoD_O.2
    }

    protected abstract Result apply(Object[] arguments);

}
