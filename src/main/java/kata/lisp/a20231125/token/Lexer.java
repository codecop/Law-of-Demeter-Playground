package kata.lisp.a20231125.token;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final List<String> chunks = new ArrayList<>();
    private final CharacterStream stream;

    public Lexer(String code) {
        this.stream = new CharacterStream(code);
    }

    public Tokens tokenise() {
        stream.markBegin(); // LoD_O.4
        while (stream.notFinished()) { // LoD_O.4

            if (isIgnoredSeparator()) { // LoD_O.1
                addPreviousChunk(); // LoD_O.1
                ignoreCurrent(); // LoD_O.1
                stream.markBegin(); // LoD_O.4

            } else if (isBraces()) { // LoD_O.1
                addPreviousChunk(); // LoD_O.1
                addCurrent(); // LoD_O.1
                stream.markBegin(); // LoD_O.4

            } else if (isQuote()) { // LoD_O.1
                addPreviousChunk(); // LoD_O.1
                addUpToQuote(); // LoD_O.1
                stream.markBegin(); // LoD_O.4

            } else {
                stream.next(); // LoD_O.4
            }

        }
        addPreviousChunk(); // LoD_O.1

        String[] target = new String[chunks.size()]; // LoD_O.4
        String[] tokens = chunks.toArray(target); // LoD_O.4
        return Tokens.tokensOf(tokens); // constructor
    }

    private boolean isIgnoredSeparator() {
        char c = stream.peek(); // LoD_O.4
        return Character.isWhitespace(c);
    }

    private void addPreviousChunk() {
        if (stream.hasChunk()) { // LoD_O.4
            String chunk = stream.getChunk(); // LoD_O.4
            chunks.add(chunk); // LoD_O.4
        }
    }

    private void ignoreCurrent() {
        stream.next(); // LoD_O.4
    }

    private boolean isBraces() {
        char c = stream.peek(); // LoD_O.4
        return c == '(' || c == ')' || c == '{' || c == '}' || c == '[' || c == ']';
    }

    private void addCurrent() {
        stream.markBegin(); // LoD_O.4
        stream.next(); // LoD_O.4
        addPreviousChunk(); // LoD_O.1
    }

    private boolean isQuote() {
        return stream.peek() == '"'; // LoD_O.4
    }

    private void addUpToQuote() {
        stream.markBegin(); // LoD_O.4
        stream.next(); // LoD_O.4
        while (!isQuote() && stream.notFinished()) { // LoD_O.1, LoD_O.4
            stream.next(); // LoD_O.4
        }
        stream.next(); // LoD_O.4
        addPreviousChunk(); // LoD_O.1
    }

}

// LoD review OK
