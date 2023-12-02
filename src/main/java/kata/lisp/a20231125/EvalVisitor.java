package kata.lisp.a20231125;

import java.util.Arrays;

import kata.lisp.a20231125.ast.Ast;
import kata.lisp.a20231125.ast.AstVisitor;
import kata.lisp.a20231125.ast.MultipleValueAst;
import kata.lisp.a20231125.ast.SingleValueAst;
import kata.lisp.a20231125.ast.SymbolAst;
import kata.lisp.a20231125.eval.Function;
import kata.lisp.a20231125.eval.Functions;
import kata.lisp.a20231125.eval.LazyResult;
import kata.lisp.a20231125.eval.LazyResults;
import kata.lisp.a20231125.eval.Result;
import kata.lisp.a20231125.eval.ResultType;
import kata.lisp.a20231125.eval.Variables;

public class EvalVisitor implements AstVisitor {

    private final Functions functions;
    private final Variables variables;

    private Result result;

    public EvalVisitor(Functions functions, Variables variables) {
        this.functions = functions;
        this.variables = variables;
    }

    public Result eval(Ast ast) {
        ast.accept(this); // LoD_O.2
        return result;
    }

    @Override
    public void visitBoolean(Boolean value) {
        result = new Result(value, ResultType.BOOLEAN);
    }

    @Override
    public void visitNumber(Integer value) {
        result = new Result(value, ResultType.NUMBER);
    }

    @Override
    public void visitString(String value) {
        result = new Result(value, ResultType.STRING);
    }

    @Override
    public void visitSymbol(String value) {
        Result symbol = new Result(value, ResultType.SYMBOL);
        result = variables.get((String) symbol.value()); // LoD_O.3, LoD_O.4
    }

    @Override
    public void visitExpression(Ast[] expressions) {
        if (expressions[0] instanceof SymbolAst) {
            visitSymbolExpression(expressions); // LoD_O.1
            return;
        }
        throw new IllegalArgumentException("Can only evaluate function expressions");
    }

    private void visitSymbolExpression(Ast[] expressions) {
        SymbolAst symbol = (SymbolAst) expressions[0];
        Ast[] arguments = Arrays.copyOfRange(expressions, 1, expressions.length);
        visitSymbolExpression(symbol, arguments); // LoD_O.1
    }

    private void visitSymbolExpression(SymbolAst symbol, Ast[] arguments) {
        // TODO LoD cheats starting here
        applyFunction(symbol.getSymbol(), arguments); // LoD_O.2
    }

    private void applyFunction(String functionName, Ast[] arguments) {
        Function function = functions.getFunctionNamed(functionName); // LoD_O.4
        if (function == null) {
            result = Result.error("Unknown function symbol " + functionName);
            return;
        }

        applyFunction(function, arguments);
    }

    private void applyFunction(Function function, Ast[] arguments) {
        int size = arguments.length;
        if (!function.matchesArgumentNumber(size)) {
            result = Result.error(function.errorMatchingArgumentNumber(size));
            return;
        }

        LazyResult[] results = toLazyResults(arguments);
        result = function.apply(new LazyResults(results), variables);
    }

    private LazyResult[] toLazyResults(Ast[] arguments) {
        LazyResult[] results = new LazyResult[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            results[i] = lazyEval(arguments[i]);
        }
        return results;
    }

    private LazyResult lazyEval(Ast ast) {
        return new LazyResult() {

            @Override
            public Result get() {
                return eval(ast);
            }

            @Override
            public Result asSymbol() {
                if (ast instanceof SymbolAst) {
                    return new Result(((SymbolAst) ast).getSymbol(), ResultType.SYMBOL);
                }
                return Result.error("Not a symbol " + ast);
            }

            @Override
            public LazyResult[] asList() {
                if (ast instanceof MultipleValueAst) {
                    return toLazyResults(((MultipleValueAst) ast).getChildren());
                } else if (ast instanceof SingleValueAst) {
                    return toLazyResults(new Ast[] { ast });
                } else {
                    throw new IllegalStateException("Unsupported type of ast " + ast);
                }
            }
        };
    }

    @Override
    public void visitProgram(Ast[] program) {
        result = Result.error("Empty Program");
        for (Ast statement : program) {
            statement.accept(this);
        }
    }

}
