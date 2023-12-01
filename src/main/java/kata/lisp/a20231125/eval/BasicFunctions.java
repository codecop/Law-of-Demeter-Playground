package kata.lisp.a20231125.eval;

public class BasicFunctions {

    public static void addTo(Functions functions) {
        functions.addFunctionNamed(new IntegerAddition());
        functions.addFunctionNamed(new IntegerSquareRoot());
        functions.addFunctionNamed(new StringAppend());
        functions.addFunctionNamed(new Error());
        functions.addFunctionNamed(new If());
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
    public Result apply(LazyResult[] arguments, Variables variables) {
        Result condition = arguments[0].get();
        if (condition.type() != ResultType.BOOLEAN) {
            return condition.causesTypeMismatchAtPosition(0);
        }

        if ((Boolean) condition.value()) {
            return arguments[1].get();
        }

        return arguments[2].get();
    }

}
