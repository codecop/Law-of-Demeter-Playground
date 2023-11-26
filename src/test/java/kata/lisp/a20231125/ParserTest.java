package kata.lisp.a20231125;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;

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
            assertEquals(new ExpressionAst(new SymbolAst("list"), Collections.emptyList()), ast);
        }

        @Test
        void parseTwoElementTokens() {
            Ast ast = parser.parse(Tokens.tokensOf("(", "list", "1", ")"));
            assertEquals(new ExpressionAst(new SymbolAst("list"), Arrays.asList(new NumberAst(1))), ast);
        }

    }

    @Nested
    class NestedToken {

        @Test
        void nestedTokens() {
            Ast ast = parser.parse(Tokens.tokensOf("(", "(", "list", ")", ")"));
            ExpressionAst subExpression = new ExpressionAst(new SymbolAst("list"), Collections.emptyList());
            assertEquals(new ExpressionAst(subExpression, Collections.emptyList()), ast);
        }

    }

    private Token tokenOf(String value) {
        return new Token(value);
    }

}
