package kata.lisp.a20231125.eval;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VariablesTest {

    @Test
    void missingValue() {
        Variables v = new Variables();

        Result result = v.get("missing");

        assertEquals(ResultType.ERROR, result.type());
    }

    @Test
    void stackFrame() {
        Variables v = new Variables();
        v.push();
        v.add("a", new Result(1, ResultType.NUMBER));

        Result result = v.get("a");
        assertEquals(ResultType.NUMBER, result.type());

        v.pop();

        Result result2 = v.get("a");
        assertEquals(ResultType.ERROR, result2.type());
    }

}
