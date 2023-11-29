package kata.lisp.a20231125;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public Tokens tokenise(String code) {
        List<String> pieces = new ArrayList<>();

        int lastStart = 0;
        for (int current = 0; current < code.length(); current++) {
            char ch = code.charAt(current);
            if (Character.isWhitespace(ch)) {
                if (lastStart < current) {
                    pieces.add(code.substring(lastStart, current));
                }
                lastStart = current + 1;
            }
        }
        if (lastStart < code.length()) {
            pieces.add(code.substring(lastStart, code.length()));
        }

        return Tokens.tokensOf(pieces.toArray(new String[0]));
    }

}
