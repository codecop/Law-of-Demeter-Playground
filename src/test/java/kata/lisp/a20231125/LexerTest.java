package kata.lisp.a20231125;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LexerTest {

    @Test
    void lexSingleToken() {
        Lexer lexer = new Lexer("1");

        Tokens tokens = lexer.tokenise();

        assertEquals(1, tokens.size());
        assertEquals(new Token("1"), tokens.next());
    }

    @Test
    void twoTokensByBlank() {
        Lexer lexer = new Lexer("1 2");

        Tokens tokens = lexer.tokenise();

        assertEquals(2, tokens.size());
        assertEquals(new Token("1"), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token("2"), tokens.next());
    }

    @Test
    void twoTokensByMoreWhitespace() {
        Lexer lexer = new Lexer("\n\n1   2 \t");

        Tokens tokens = lexer.tokenise();

        assertEquals(2, tokens.size());
        assertEquals(new Token("1"), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token("2"), tokens.next());
    }

    @Test
    void tokenWithBracket() {
        Lexer lexer = new Lexer("(1)");

        Tokens tokens = lexer.tokenise();

        assertEquals(new Token("("), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token("1"), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token(")"), tokens.next());
    }

    @Test
    void nestedTokenWithBrackets() {
        Lexer lexer = new Lexer("(1(2)3)");

        Tokens tokens = lexer.tokenise();

        assertEquals(new Token("("), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token("1"), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token("("), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token("2"), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token(")"), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token("3"), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token(")"), tokens.next());
    }

    @Test
    void nestedTokenWithBracketsAndWhitespace() {
        Lexer lexer = new Lexer("\n( 1   (2) 3 \t)");

        Tokens tokens = lexer.tokenise();

        assertEquals(new Token("("), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token("1"), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token("("), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token("2"), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token(")"), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token("3"), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token(")"), tokens.next());
    }

}
