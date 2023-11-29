package kata.lisp.a20231125;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class EvalTest {

    Eval eval = new Eval();

    @Test
    void evalNumber() {
        Result result = eval.eval(astOf("1"));
        assertEquals(1, result.value());
        assertEquals(Result.Type.NUMBER, result.type());
    }

    @Test
    void evalAddition() {
        Result result = eval.eval(astOf("(", "+", "1", "2", ")"));
        assertEquals(3, result.value());
        assertEquals(Result.Type.NUMBER, result.type());
    }

    @Test
    void evalTopLevel() {
        Result result = eval.eval(astOf("1", "2"));
        assertEquals(2, result.value());
        assertEquals(Result.Type.NUMBER, result.type());
    }

    @Nested
    class ErrorHandling {

        @Test
        void evalWrongType() {
            Result result = eval.eval(astOf("(", "+", "#t", "#f", ")"));
            assertEquals("Type mismatch of 1. argument: expected ResultType N of class java.lang.Integer, "
                    + "got true (ResultType B of class java.lang.Boolean)", result.value());
            assertEquals(Result.Type.ERROR, result.type());
        }

        @Test
        void evalMissingFunction() {
            Result result = eval.eval(astOf("(", "not-existing", "#t", ")"));
            assertEquals("Unknown symbol not-existing", result.value());
            assertEquals(Result.Type.ERROR, result.type());
        }

        @Test
        void errorStopsEval() {
            Result result = eval.eval(astOf("(", "+", "1", "(", "not-existing", "1", "2", ")", ")"));
            assertEquals("Unknown symbol not-existing", result.value());
            assertEquals(Result.Type.ERROR, result.type());
        }

    }

    private Ast astOf(String token) {
        return new Parser().parse(new Token(token));
    }

    private Ast astOf(String... tokens) {
        return new Parser().parse(Tokens.tokensOf(tokens));
    }

}
