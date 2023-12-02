package kata.lisp.a20231125.eval;

class If extends Function {

    public If() {
        super("if", new ExactNumberOfArguments(3), new AllSameTypeOfArguments(ResultType.BOOLEAN));
    }

    @Override
    public Result apply(LazyResults arguments, Variables variables) {
        Result condition = arguments.evalArgument(0);
        if (!matchesArgumentType(0, condition.type())) { // TODO LoD
            return Result.error(errorMatchingArgumentType(0, condition.type()));
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

}
