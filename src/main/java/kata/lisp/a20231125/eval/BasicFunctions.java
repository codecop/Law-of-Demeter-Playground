package kata.lisp.a20231125.eval;

public class BasicFunctions {

    public static void addTo(Functions functions) {
        functions.addFunctionNamed(new IntegerAddition());
        functions.addFunctionNamed(new IntegerSquareRoot());
        functions.addFunctionNamed(new StringAppend());
        functions.addFunctionNamed(new Error());
        functions.addFunctionNamed(new If());
        functions.addFunctionNamed(new Let());
        functions.addFunctionNamed(new Define());
    }

}

class IntegerAddition extends StrictFunction {

    public IntegerAddition() {
        super("+");
    }

    @Override
    public boolean matchesArgumentNumber(int count) {
        return count >= 1;
    }

    @Override
    public boolean matchesArgumentType(int i, ResultType type) {
        return ResultType.NUMBER == type;
    }

    @Override
    public Result apply(Object[] arguments) {
        int sum = 0;
        for (int i = 0; i < arguments.length; i++) {
            sum += (Integer) arguments[i];
        }
        return new Result(sum, ResultType.NUMBER);
    }

}

class IntegerSquareRoot extends StrictFunction {

    public IntegerSquareRoot() {
        super("sqrt");
    }

    @Override
    public boolean matchesArgumentNumber(int count) {
        return count == 1;
    }

    @Override
    public boolean matchesArgumentType(int i, ResultType type) {
        return ResultType.NUMBER == type;
    }

    @Override
    public Result apply(Object[] arguments) {
        Integer value = (Integer) arguments[0];
        return new Result(Math.sqrt(value), ResultType.FLOAT);
    }

}

class StringAppend extends StrictFunction {

    public StringAppend() {
        super("string-append");
    }

    @Override
    public boolean matchesArgumentNumber(int count) {
        return count >= 1;
    }

    @Override
    public boolean matchesArgumentType(int i, ResultType type) {
        return ResultType.STRING == type;
    }

    @Override
    public Result apply(Object[] arguments) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < arguments.length; i++) {
            buf.append((String) arguments[i]);
        }
        return new Result(buf.toString(), ResultType.STRING);
    }

}

class Error extends StrictFunction {

    public Error() {
        super("error");
    }

    @Override
    public boolean matchesArgumentNumber(int count) {
        return count == 1;
    }

    @Override
    public boolean matchesArgumentType(int i, ResultType type) {
        return ResultType.STRING == type;
    }

    @Override
    public Result apply(Object[] arguments) {
        return new Result(arguments[0], ResultType.ERROR);
    }

}

class If extends Function {

    public If() {
        super("if");
    }

    @Override
    public boolean matchesArgumentNumber(int count) {
        return count == 3;
    }

    @Override
    public Result apply(LazyResults arguments, Variables variables) {
        Result condition = getOfTypeOrError(arguments.evalArgument(0), ResultType.BOOLEAN, 0);
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

    private Result getOfTypeOrError(Result result, ResultType resultType, int index) {
        if (result.type() != resultType) {
            return result.causesTypeMismatchAtPosition(index);
        }
        return result;
    }

}

class Define extends Function {

    public Define() {
        super("define");
    }

    @Override
    public boolean matchesArgumentNumber(int count) {
        return count == 2;
    }

    @Override
    public Result apply(LazyResults arguments, Variables variables) {
        Result r = arguments.asSymbol(0);
        if (r.isError()) {
            return r;
        }
        String symbol = (String) r.value();
        Result value = arguments.evalArgument(1);
        variables.add(symbol, value);
        return new Result("<undefined>", ResultType.UNDEFINED);
    }

}
