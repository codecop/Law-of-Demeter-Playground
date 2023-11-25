package kata.lisp.a20231125;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParserTest {

    Parser parser = new Parser();

    @Nested
    class SingleToken {

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

        @Test
        void parseBoolean() {
            Ast ast = parser.parse("#t");
            assertEquals(new BooleanAst(true), ast);
        }

        @Test
        void parseSymbol() {
            Ast ast = parser.parse("+");
            assertEquals(new SymbolAst("+"), ast);
        }

    }

    @Nested
    class MultipleToken {

        @Test
        void parseTokens() {
            Ast ast = parser.parse(new Tokens("(", "list", ")"));
            assertEquals(new ListAst(Arrays.asList(new SymbolAst("list"))), ast);
        }

        @Test
        void parseTwoElementTokens() {
            Ast ast = parser.parse(new Tokens("(", "list", "1", ")"));
            assertEquals(new ListAst(Arrays.asList(new SymbolAst("list"), new NumberAst(1))), ast);
        }

    }

}
