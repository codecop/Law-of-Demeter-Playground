package kata.lisp.a20231125;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        Object output = new Main().run(args[0]);
        System.out.println(output);
    }

    private final Lexer lexer = new Lexer();
    private final Parser parser = new Parser();
    private final Eval eval = new Eval();

    public Result run(String fileName) throws IOException {
        String code = Files.readString(Paths.get(fileName));
        Tokens tokens = lexer.tokenise(code);
        Ast topLevelAst = parser.parse(tokens);
        return eval.eval(topLevelAst);
    }
}
