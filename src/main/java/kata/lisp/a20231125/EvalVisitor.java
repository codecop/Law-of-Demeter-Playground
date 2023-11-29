package kata.lisp.a20231125;

import java.util.Arrays;

public class EvalVisitor {

    private final Functions context;

    public EvalVisitor(Functions context) {
        this.context = context;
    }

    public Result visitBoolean(Boolean value) {
        return new Result(value, ResultType.BOOLEAN);
    }

    public Result visitNumber(Integer value) {
        return new Result(value, ResultType.NUMBER);
    }

    public Result visitString(String value) {
        return new Result(value, ResultType.STRING);
    }

    public Result visitProgram(Ast[] children) {
        Result lastResult = new Result("Empty Program", ResultType.ERROR);
        for (Ast ast : children) {
            lastResult = ast.accept(this);
        }
        return lastResult;
    }

    public Result visitExpression(Ast[] expressions) {
        if (expressions[0] instanceof SymbolAst) {
            return evalAsFunction(expressions, context);
        }
        throw new IllegalStateException();
    }

    private Result evalAsFunction(Ast[] expressions, Functions context) {
        SymbolAst symbol = (SymbolAst) expressions[0];
        Ast[] arguments = Arrays.copyOfRange(expressions, 1, expressions.length);
        return evalAsFunction(symbol, arguments, context);
    }

    private Result evalAsFunction(SymbolAst symbol, Ast[] arguments, Functions context) {
        return symbol.evalAsFunction(arguments, context);
    }

}
