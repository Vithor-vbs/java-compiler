import java.io.*;
import java_cup.runtime.Symbol;

public class ExemploJFlexCup {

    public static void main(String[] args) {
        try {           
            
            /*Comandos (no prompt):
            java -jar jflex-full-1.8.2.jar scanner.flex
            java -jar java-cup-11b.jar -parser Parser -symbols Tokens parser.cup
            */
            
            Runtime r = Runtime.getRuntime();
            Process p;            
            //posicionando na pasta src e chamar o flex por linha de comando
            // ================ vai gerar a classe Scanner.java ========================
//            p = r.exec(new String[]{"java", "-jar", "..\\jflex-full-1.8.2.jar", "..\\scanner.flex"}, null, new File("src\\"));
//            System.out.println(p.waitFor());//se ok, a saída será 0*/

            //================== vai gerar as classes Parser.java e Tokens.java ======================
//            p = r.exec(new String[]{
//                    "java", "-jar", "..\\java-cup-11b.jar",
//                    "-parser", "Parser",
//                    "-symbols", "Tokens",
//                    "..\\parser.cup"
//            }, null, new File("src\\"));
//
//            BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//            BufferedReader inputReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//
//            String line;
//            // Log errors
//            System.err.println("Errors:");
//            while ((line = errorReader.readLine()) != null) {
//                System.err.println(line);
//            }
//
//            // Log outputs
//            System.out.println("Output:");
//            while ((line = inputReader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            System.out.println("Exit Code: " + p.waitFor());

//
            Scanner scanner = new Scanner(new FileReader("entrada.txt"));
            System.out.println("Análise Léxica: Lista de Tokens:");
            Symbol s = scanner.next_token();
            while(s.sym != Tokens.EOF){
                System.out.printf("<%d, %s>\n", s.sym, s.value);
                s = scanner.next_token();
            }

            //criando o parser passando o scanner
            scanner = new Scanner(new FileReader("entrada.txt"));
            Parser parser = new Parser(scanner);

            // Realiza a análise sintática e constrói a árvore
            Symbol parseResult = parser.parse();
            SyntaxTreeNode root = (SyntaxTreeNode) parseResult.value;

            // Imprime a árvore sintática
            System.out.println("Árvore Sintática:");
            root.printTree("");
        }
        catch(Exception e) { System.out.println(e.getMessage());}
    }

}
