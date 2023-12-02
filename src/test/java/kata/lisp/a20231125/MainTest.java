package kata.lisp.a20231125;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import kata.lisp.a20231125.eval.Result;
import kata.lisp.a20231125.eval.ResultType;

class MainTest {

    Main main = new Main();

    @Test
    void evaluateSingleFileWithAddition() throws IOException {
        Result actual = main.runFile("src/test/resources/single_addition.scm");
        assertEquals("6 (ResultType N of class java.lang.Integer)", actual.toString());
    }

    @Test
    void evaluateSqrt() throws IOException {
        Result actual = main.runFile("src/test/resources/math_sqrt.scm");
        assertEquals("1.4142135623730951 (ResultType F of class java.lang.Double)", actual.toString());
    }

    @Test
    void stringFunction() {
        Result actual = main.run("( string-append \"a\" \" \" \"b\")");
        assertEquals("a b", actual.value());
        assertEquals(ResultType.STRING, actual.type());
    }

    @Test
    void ifFunction() {
        Result actual = main.run("(if #t (+ 1 2) (error \"should not eval this\"))");
        assertEquals(3, actual.value());
        assertEquals(ResultType.NUMBER, actual.type());
    }

    @Test
    void globalData() {
        Result actual = main.run("(define name \"Peter\") (string-append \"Hello \" name)");
        assertEquals("Hello Peter", actual.value());
        assertEquals(ResultType.STRING, actual.type());
    }

    @Test
    void defineErrorHandling() {
        Result actual = main.run("(define 1 \"Peter\")");
        assertEquals("Not a symbol Ast(1)", actual.value());
        assertEquals(ResultType.ERROR, actual.type());
    }

    // TODO Test: single quote (') indicates literal data; it suppresses evaluation. 
}
