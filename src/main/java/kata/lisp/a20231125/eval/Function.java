package kata.lisp.a20231125.eval;

public abstract class Function {

    private final String name;
    private final NumberOfArguments numberOfArguments;
    private final TypeOfArguments typeOfArguments;

    public Function(String name, NumberOfArguments numberOfArguments, TypeOfArguments typeOfArguments) {
        this.name = name;
        this.numberOfArguments = numberOfArguments;
        this.typeOfArguments = typeOfArguments;
    }

    public String getName() {
        return name;
    }

    private boolean matchesArgumentNumber(int parameterCount) {
        return numberOfArguments.matches(parameterCount); // LoD_O.4
    }

    private String errorMatchingArgumentNumber(int parameterCount) {
        return "Too few/many arguments to function " + toString() + // LoD_O.1
                ", expected " + numberOfArguments.display() + // LoD_O.4
                ", got " + parameterCount;
    }

    public boolean matchesArgumentType(int index, Result parameter) {
        return typeOfArguments == null || typeOfArguments.matches(index, parameter.type()); // LoD_O.4, LoD_O.2
    }

    public String errorMatchingArgumentType(int index, Result parameter) {
        return "Type mismatch of " + (index + 1) + ". argument to function " + toString() + // LoD_O.1
                ", expected " + typeOfArguments.expectedType(index) + // LoD_O.4
                ", got " + parameter.type(); // LoD_O.2
    }

    public Result applyFunction(LazyResults results, Variables variables) {
        int size = results.size(); // LoD_O.2
        if (!matchesArgumentNumber(size)) { // LoD_O.1
            return Result.error(errorMatchingArgumentNumber(size)); // LoD_O.1
        }

        return apply(results, variables); // LoD_O.1
    }

    protected abstract Result apply(LazyResults arguments, Variables variables);

    @Override
    public String toString() {
        return name;
    }

}

// LoD review OK
