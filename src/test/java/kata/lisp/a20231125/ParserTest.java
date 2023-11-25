package kata.lisp.a20231125;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ParserTest {

    Parser parser = new Parser();

    @Test
    void parseNumber() {
        Ast ast = parser.parse("1");
        assertEquals(new NumberAst(1), ast);
    }

    @Test
    void parseString() {
        Ast ast = parser.parse("\"abc\"");
        assertEquals(new StringAst("abc"), ast);
    }

}
