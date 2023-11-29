package kata.lisp.a20231125;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import kata.lisp.a20231125.eval.Result;

class MainTest {

    @Test
    void evaluateSingleFileWithAddition() throws IOException {
        Result actual = new Main().runFile("src/test/resources/single_addition.scm");
        assertEquals("6 (ResultType N of class java.lang.Integer)", actual.toString());
    }

    @Test
    // @Disabled("Guiding Test")
    void evaluateSqrt() throws IOException {
        Result actual = new Main().runFile("src/test/resources/math_sqrt.scm");
        assertEquals("1.4142135623730951 (ResultType F of class java.lang.Double)", actual.toString());
    }

}
