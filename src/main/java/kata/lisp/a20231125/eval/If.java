package kata.lisp.a20231125.eval;

class If extends Function {

    public If() {
        super("if", new ExactNumberOfArguments(3), new AllSameTypeOfArguments(ResultType.BOOLEAN));
    }

    @Override
    public Result apply(LazyResults arguments, Variables variables) {
        Result condition = arguments.evalArgument(0); // LoD_O.2
        if (!matchesArgumentType(0, condition)) { // LoD_O.1
            return Result.error(errorMatchingArgumentType(0, condition)); // named constructor, LoD_O.1 
        }

        // LoD Violation (cheat by using a private method)
        return evalCond(condition, arguments); // LoD_O.1
    }

    private Result evalCond(Result condition, LazyResults arguments) {

        if (condition.isError()) { // LoD_O.2 <-> LoD Violation (cheat by using a private method)
            return condition;
        }

        if ((Boolean) condition.value()) { // LoD_O.2 <-> LoD Violation (cheat by using a private method)
            return arguments.evalArgument(1); // LoD_O.2
        }

        return arguments.evalArgument(2); // LoD_O.2
    }

}

// LoD review Violation (cheat by using a private method)
