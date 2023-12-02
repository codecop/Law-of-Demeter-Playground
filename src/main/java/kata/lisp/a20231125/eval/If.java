package kata.lisp.a20231125.eval;

class If extends Function {

    private final TypeOfArguments typeOfArguments;

    public If() {
        super("if", new ExactNumberOfArguments(3));
        typeOfArguments = new AllSameTypeOfArguments(ResultType.BOOLEAN);
    }

    @Override
    public Result apply(LazyResults arguments, Variables variables) {
        Result condition = arguments.evalArgument(0);
        if (!matchesArgumentType(0, condition.type())) {
            return Result.error(errorMatchingArgumentType(0, condition));
        }
        return evalCond(condition, arguments);
    }

    private Result evalCond(Result condition, LazyResults arguments) {
        if (condition.isError()) {
            return condition;
        }

        if ((Boolean) condition.value()) {
            return arguments.evalArgument(1);
        }

        return arguments.evalArgument(2);
    }

    // TODO duplication
    public boolean matchesArgumentType(int i, ResultType parameterType) {
        return typeOfArguments.matches(i, parameterType);
    }

    public String errorMatchingArgumentType(int index, Result argument) {
        return "Type mismatch of " + (index + 1) + ". argument to function " + toString() + // LoD_O.1
                ", expected " + typeOfArguments.get(index) + // LoD_O.4
                ", got " + argument.type(); // LoD_O.2
    }

}
