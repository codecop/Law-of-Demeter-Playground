package kata.lisp.a20231125;

public class Parser {

    public Ast parse(String token) {
        if (token.matches("0|-?[1-9]\\d*")) {
            int value = Integer.parseInt(token);
            return new NumberAst(value);
        }
        if (token.matches("\"[^\"]*\"")) {
            String value = token.substring(1, token.length() - 1);
            return new StringAst(value);
        }
        if (token.matches("#f|#t")) {
            boolean value = token.equals("#t");
            return new BooleanAst(value);
        }
        return null;
    }

}
