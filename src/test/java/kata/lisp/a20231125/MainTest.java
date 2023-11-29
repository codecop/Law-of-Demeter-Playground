package kata.lisp.a20231125;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    // @Disabled("Guiding Test")
    void evaluateSingleFile() throws IOException {
        Result actual = new Main().run("src/test/resources/single_addition.scm");
        assertEquals("5 (ResultType N of class java.lang.Integer)", actual.toString());
    }

}
