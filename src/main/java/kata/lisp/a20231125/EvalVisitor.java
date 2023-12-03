package kata.lisp.a20231125;

import java.util.Arrays;

import kata.lisp.a20231125.ast.Ast;
import kata.lisp.a20231125.ast.AstVisitor;
import kata.lisp.a20231125.ast.MultipleValueAst;
import kata.lisp.a20231125.ast.SingleValueAst;
import kata.lisp.a20231125.ast.SymbolAst;
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
    public void visitSymbol(String symbolName) {
        result = variables.get(symbolName); // LoD_O.4
    }

    @Override
    public void visitExpression(Ast[] expressions) {
        if (expressions[0] instanceof SymbolAst) {
            SymbolAst symbol = (SymbolAst) expressions[0];
            Ast[] arguments = Arrays.copyOfRange(expressions, 1, expressions.length);

            // LoD Violation 1 (cheat by using a private method)
            visitFunctionExpression(symbol, arguments); // LoD_O.1
            return;
        }
        throw new IllegalArgumentException("Can only evaluate function expressions");
    }

    private void visitFunctionExpression(SymbolAst symbol, Ast[] arguments) {
        String symbolName = symbol.getSymbol(); // LoD_O.2 <-> LoD Violation 1 (cheat by using a private method)
        LazyResults results = new LazyResults(toLazyResults(arguments)); // LoD_O.1
        result = functions.applyFunction(symbolName, results, variables); // LoD_O.4
    }

    private LazyResult[] toLazyResults(Ast[] arguments) {
        int size = arguments.length;
        LazyResult[] results = new LazyResult[size];
        for (int i = 0; i < size; i++) {
            results[i] = lazyEval(arguments[i]); // LoD_O.1
        }
        return results;
    }

    private LazyResult lazyEval(Ast ast) {
        return new LazyResult() {

            @Override
            public Result get() {
                return eval(ast); // LoD_O.1
            }

            @Override
            public Result asSymbol() {
                if (ast instanceof SymbolAst) {
                    String symbol = ((SymbolAst) ast).getSymbol(); // LoD_O.2
                    return new Result(symbol, ResultType.SYMBOL);
                }
                return Result.error("Not a symbol " + ast);
            }

            @Override
            public LazyResult[] asList() {
                if (ast instanceof MultipleValueAst) {
                    Ast[] children = ((MultipleValueAst) ast).getChildren(); // LoD_O.2
                    return toLazyResults(children); // LoD_O.1
                } else if (ast instanceof SingleValueAst) {
                    return toLazyResults(new Ast[] { ast }); // LoD_O.1
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
            statement.accept(this); // LoD_O.2
        }
    }

}

// LoD review OK
