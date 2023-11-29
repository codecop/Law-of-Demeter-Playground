package kata.lisp.a20231125;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LexerTest {

    Lexer lexer = new Lexer();

    @Test
    void lexSingleToken() {
        Tokens tokens = lexer.tokenise("1");

        assertEquals(1, tokens.size());
        assertEquals(new Token("1"), tokens.next());
    }

    @Test
    void twoTokensByBlank() {
        Tokens tokens = lexer.tokenise("1 2");

        assertEquals(2, tokens.size());
        assertEquals(new Token("1"), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token("2"), tokens.next());
    }

    @Test
    void twoTokensByMoreWhitespace() {
        Tokens tokens = lexer.tokenise("\n\n1   2 \t");
        
        assertEquals(2, tokens.size());
        assertEquals(new Token("1"), tokens.next());
        tokens.consumeToken();
        assertEquals(new Token("2"), tokens.next());
    }

}
