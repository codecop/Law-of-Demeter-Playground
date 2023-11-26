package kata.lisp.a20231125;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void evalWrongType() {
        Result result = eval.eval(astOf("(", "+", "#t", "#f", ")"));
        assertEquals(
                "Type mismatch of 1. argument: expected class java.lang.Integer, got true (class java.lang.Boolean)",
                result.value());
        assertEquals(Result.Type.ERROR, result.type());
    }

    private Ast astOf(String token) {
        return new Parser().parse(new Token(token));
    }

    private Ast astOf(String... tokens) {
        return new Parser().parse(Tokens.tokensOf(tokens));
    }

}
