package kata.lisp.a20231125;

import java.util.function.Supplier;

import kata.lisp.a20231125.eval.Functions;
import kata.lisp.a20231125.eval.Result;
import kata.lisp.a20231125.eval.ResultType;
import kata.lisp.a20231125.eval.StrictFunction;

public class BasicFunctions {

    public static void add(Functions functions) {
        functions.addFunctionNamed(new IntegerAddition());
        functions.addFunctionNamed(new IntegerSquareRoot());
        functions.addFunctionNamed(new StringAppend());
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
    public Result execute(Object[] arguments) {
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
    public Result execute(Object[] arguments) {
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
    public Result execute(Object[] arguments) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < arguments.length; i++) {
            buf.append((String) arguments[i]);
        }
        return new Result(buf.toString(), ResultType.STRING);
    }

}
