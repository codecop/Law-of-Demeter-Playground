package kata.lisp.a20231125;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import kata.lisp.a20231125.ast.Ast;
import kata.lisp.a20231125.eval.Result;
import kata.lisp.a20231125.eval.ResultType;
import kata.lisp.a20231125.eval.Variables;
import kata.lisp.a20231125.token.Token;
import kata.lisp.a20231125.token.Tokens;

class EvalTest {

    Eval eval = new Eval();

    @Test
    void evalNumber() {
        Result result = eval.eval(astOf("1"));
        assertEquals(1, result.value());
        assertEquals(ResultType.NUMBER, result.type());
    }

    @Test
    void evalAddition() {
        Result result = eval.eval(astOf("(", "+", "1", "2", ")"));
        assertEquals(3, result.value());
        assertEquals(ResultType.NUMBER, result.type());
    }

    @Test
    void evalTopLevel() {
        Result result = eval.eval(astOf("1", "2"));
        assertEquals(2, result.value());
        assertEquals(ResultType.NUMBER, result.type());
    }

    @Test
    void stringFunction() {
        Result result = eval.eval(astOf("(", "string-append", "\"a\"", "\"b\"", ")"));
        assertEquals("ab", result.value());
        assertEquals(ResultType.STRING, result.type());
    }

    @Nested
    class UsingVariables {

        @Test
        void missingVariable() {
            Result result = eval.eval(astOf("a"));

            assertEquals("Unknown symbol a", result.value());
            assertEquals(ResultType.ERROR, result.type());
        }

        @Test
        void useVariable() {
            Ast ast = astOf("a");
            Variables variables = new Variables();
            variables.add("a", new Result(1, ResultType.NUMBER));

            Result result = eval.evalUsing(ast, null, variables);

            assertEquals(1, result.value());
            assertEquals(ResultType.NUMBER, result.type());
        }

        @Test
        @Disabled("need variable first")
        void letFunction() {
            Result result = eval.eval(astOf("(", "let", "(", "(", "a", "1", ")", ")", "a", ")"));
            assertEquals(1, result.value());
            assertEquals(ResultType.NUMBER, result.type());
        }

    }

    @Nested
    class ErrorHandling {

        @Test
        void evalWrongType() {
            Result result = eval.eval(astOf("(", "+", "#t", "#f", ")"));
            assertEquals("Type mismatch of 1. argument: got true (ResultType B of class java.lang.Boolean)",
                    result.value());
            assertEquals(ResultType.ERROR, result.type());
        }

        @Test
        void evalMissingFunction() {
            Result result = eval.eval(astOf("(", "not-existing", "#t", ")"));
            assertEquals("Unknown symbol not-existing", result.value());
            assertEquals(ResultType.ERROR, result.type());
        }

        @Test
        void errorStopsEval() {
            Result result = eval.eval(astOf("(", "+", "1", "(", "not-existing", "1", "2", ")", ")"));
            assertEquals("Unknown symbol not-existing", result.value());
            assertEquals(ResultType.ERROR, result.type());
        }

        @Test
        void tooManyArguments() {
            Result result = eval.eval(astOf("(", "sqrt", "2", "3", ")"));
            assertEquals("Too many arguments to sqrt, got 2", result.value());
            assertEquals(ResultType.ERROR, result.type());
        }

        @Test
        void tooFewArguments() {
            Result result = eval.eval(astOf("(", "+", ")"));
            assertEquals("Too many arguments to +, got 0", result.value());
            assertEquals(ResultType.ERROR, result.type());
        }

    }

    private Ast astOf(String token) {
        return new Parser().parse(new Token(token));
    }

    private Ast astOf(String... tokens) {
        return new Parser().parse(Tokens.tokensOf(tokens));
    }

}
