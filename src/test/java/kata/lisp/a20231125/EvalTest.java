package kata.lisp.a20231125;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EvalTest {

    Eval eval = new Eval();

    @Test
    void evalNumber() {
        Result result = eval.eval(astOf("1"));
        assertTrue(result instanceof NumberResult);
        assertEquals(1, ((NumberResult) result).value());
    }

    private Ast astOf(String token) {
        return new Parser().parse(new Token(token));
    }

}
