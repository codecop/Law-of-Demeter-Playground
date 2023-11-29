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

}
