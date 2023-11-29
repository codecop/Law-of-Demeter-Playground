package kata.lisp.a20231125;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import kata.lisp.a20231125.ast.Ast;
import kata.lisp.a20231125.ast.BooleanAst;
import kata.lisp.a20231125.ast.ExpressionAst;
import kata.lisp.a20231125.ast.NumberAst;
import kata.lisp.a20231125.ast.ProgramAst;
import kata.lisp.a20231125.ast.StringAst;
import kata.lisp.a20231125.ast.SymbolAst;

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

        // TODO features in AST
        // * character with 'a
        // * double numbers

    }

    @Nested
    class GroupedToken {

        @Test
        void parseTokens() {
            Ast ast = parser.parse(Tokens.tokensOf("(", "list", ")"));
            assertEquals(new ExpressionAst(Collections.singletonList(new SymbolAst("list"))), ast);
        }

        @Test
        void parseTwoElementTokens() {
            Ast ast = parser.parse(Tokens.tokensOf("(", "list", "1", ")"));
            assertEquals(new ExpressionAst(Arrays.asList(new SymbolAst("list"), new NumberAst(1))), ast);
        }

    }

    @Nested
    class NestedToken {

        @Test
        void nestedTokens() {
            Ast ast = parser.parse(Tokens.tokensOf("(", "list", "(", "list", ")", ")"));
            ExpressionAst subExpression = new ExpressionAst(Collections.singletonList(new SymbolAst("list")));
            assertEquals(new ExpressionAst(Arrays.asList(new SymbolAst("list"), subExpression)), ast);
        }

    }

    @Nested
    class MultipleTopLevelToken {

        @Test
        void parseTokens() {
            Ast ast = parser.parse(Tokens.tokensOf("1", "2"));
            assertEquals(new ProgramAst(Arrays.asList(new NumberAst(1), new NumberAst(2))), ast);
        }

    }

    private Token tokenOf(String value) {
        return new Token(value);
    }

}
