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

    public boolean matchesArgumentNumber(int parameterCount) {
        return numberOfArguments.matches(parameterCount); // LoD_O.4
    }

    public String errorMatchingArgumentNumber(int parameterCount) {
        return "Too few/many arguments to function " + toString() + // LoD_O.1
                ", expected " + numberOfArguments.display() + // // LoD_O.4
                ", got " + parameterCount;
    }

    public boolean matchesArgumentType(int index, ResultType parameterType) {
        return typeOfArguments == null || typeOfArguments.matches(index, parameterType); // LoD_O.4
    }

    public String errorMatchingArgumentType(int index, ResultType parameterType) {
        return "Type mismatch of " + (index + 1) + ". argument to function " + toString() + // LoD_O.1
                ", expected " + typeOfArguments.expectedType(index) + // LoD_O.4
                ", got " + parameterType; // LoD_O.2
    }

    public abstract Result apply(LazyResults arguments, Variables variables);

    @Override
    public String toString() {
        return name;
    }

}

// LoD review OK
