package kata.lisp.a20231125.token;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TokensTest {

    @Test
    void toStringShowsCurrent() {
        Tokens tokens = Tokens.tokensOf("(", "list", ")");
        assertEquals("Tokens: ( <= current, list, )", tokens.toString());

        tokens.consumeToken();
        assertEquals("Tokens: (, list <= current, )", tokens.toString());

        tokens.consumeToken();
        
        tokens.consumeToken();
        assertEquals("Tokens: (, list, ), <= END", tokens.toString());
    }

}
