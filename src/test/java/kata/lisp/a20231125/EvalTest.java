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

    private Ast astOf(String token) {
        return new Parser().parse(new Token(token));
    }

    private Ast astOf(String... tokens) {
        return new Parser().parse(Tokens.tokensOf(tokens));
    }

}
