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
            Ast ast = parser.parse(tokenOf("1"));
            assertEquals(new NumberAst(1), ast);
        }

        @Test
        void parseString() {
            Ast ast = parser.parse(tokenOf("\"abc\""));
            assertEquals(new StringAst("abc"), ast);
        }

        @Test
        void parseBoolean() {
            Ast ast = parser.parse(tokenOf("#t"));
            assertEquals(new BooleanAst(true), ast);
        }

        @Test
        void parseSymbol() {
            Ast ast = parser.parse(tokenOf("+"));
            assertEquals(new SymbolAst("+"), ast);
        }

    }

    @Nested
    class MultipleToken {

        @Test
        void parseTokens() {
            Ast ast = parser.parse(Tokens.tokensOf("(", "list", ")"));
            assertEquals(new ListAst(Arrays.asList(new SymbolAst("list"))), ast);
        }

        @Test
        void parseTwoElementTokens() {
            Ast ast = parser.parse(Tokens.tokensOf("(", "list", "1", ")"));
            assertEquals(new ListAst(Arrays.asList(new SymbolAst("list"), new NumberAst(1))), ast);
        }

    }

    @Nested
    class NestedToken {

        @Test
        void nestedTokens() {
            Ast ast = parser.parse(Tokens.tokensOf("(", "(", "list", ")", ")"));
            assertEquals(new ListAst(Arrays.asList(new ListAst(Arrays.asList(new SymbolAst("list"))))), ast);
        }

    }

    private Token tokenOf(String value) {
        return new Token(value);
    }

}
