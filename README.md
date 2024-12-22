# Instruções de Compilação e Execução do Scanner, Parser e Solução Integrada

Este README explica como compilar e executar o scanner, o parser e a solução integrada utilizando JFlex e CUP. O processo envolve três etapas principais: a compilação do scanner, a compilação do parser e a execução integrada, que envolve a análise léxica e sintática de um código de exemplo.

---

## 1. Compilação do Scanner

### Passo 1: Preparar o Scanner
O scanner será gerado a partir do arquivo de especificação do JFlex (geralmente chamado `scanner.flex`). Para compilar o scanner, siga os passos abaixo:

1. **Descomente as linhas 18 e 19** do script `ExemploJflexCup.java`:
   ```java
   // p = r.exec(new String[]{"java", "-jar", "..\\jflex-full-1.8.2.jar", "..\\scanner.flex"}, null, new File("src\\"));
   // System.out.println(p.waitFor()); // Se ok, a saída será 0
Isso irá gerar a classe Scanner a partir da especificação JFlex.


2. **Executar o Script**:
- Após descomentar as linhas acima, execute o script `ExemploJflexCup.java` para gerar o scanner.

## 2. Compilação do Parser
------------------------

### Passo 1: Preparar o Parser

O parser será gerado a partir do arquivo de especificação do CUP (geralmente chamado `parser.cup`). Para compilar o parser, siga os passos abaixo:

1. **Descomente as linhas 22 a 27** do script `ExemploJflexCup.java`:

    ```java
    // p = r.exec(new String[]{
    //         "java", "-jar", "..\\java-cup-11b.jar",
    //         "-parser", "Parser",
    //         "-symbols", "Tokens",
    //         "..\\parser.cup"
    // }, null, new File("src\\"));
    ```

2. Isso irá gerar o parser a partir da especificação do CUP.

---

### OBS: Ativar Debugger (opcional)

Se você deseja ativar o debugger para capturar erros durante a execução, **descomente as linhas 29 a 45** do script `ExemploJflexCup.java`:

```java
// BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
// BufferedReader inputReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//
// String line;
// // Log de erros
// System.err.println("Errors:");
// while ((line = errorReader.readLine()) != null) {
//     System.err.println(line);
// }
//
// // Log de saídas
// System.out.println("Output:");
// while ((line = inputReader.readLine()) != null) {
//     System.out.println(line);
// }
//
// System.out.println("Exit Code: " + p.waitFor());
```



# Guia de Execução do Scanner, Parser e Solução Integrada

Este guia explica como executar o scanner, o parser e obter a solução integrada a partir de um arquivo de entrada.

---

## 3. Execução do Scanner, Parser e Solução Integrada

### Passo 1: Executar o Scanner

1. **Execute as linhas 48 até 66** do script `ExemploJflexCup.java`:

    ```java
    Scanner scanner = new Scanner(new FileReader("entrada.txt"));
    System.out.println("Análise Léxica: Lista de Tokens:");
    Symbol s = scanner.next_token();
    while(s.sym != Tokens.EOF){
        System.out.printf("<%d, %s>\n", s.sym, s.value);
        s = scanner.next_token();
    }
    ```

2. O código irá realizar a análise léxica do arquivo de entrada (`entrada.txt`) e imprimir a lista de tokens no terminal.

---

### Passo 2: Executar o Parser

1. **Criar o parser e realizar a análise sintática**:

    ```java
    // Criando o parser, passando o scanner
    scanner = new Scanner(new FileReader("entrada.txt"));
    Parser parser = new Parser(scanner);

    // Realiza a análise sintática e constrói a árvore
    Symbol parseResult = parser.parse();
    SyntaxTreeNode root = (SyntaxTreeNode) parseResult.value;

    // Imprime a árvore sintática
    System.out.println("Árvore Sintática:");
    root.printTree("");
    ```

2. O parser será executado e a árvore sintática será exibida no terminal.

### Exemplo de Saída no Terminal
   Após executar os passos acima, você verá a seguinte saída no terminal, que inclui a análise léxica, o resultado do código e a árvore sintática, respectivamente, para o arquivo `entrada.txt`:
   
```java
<23, x>
<7, =>
<21, 10>
<2, ;>
<23, y>
<7, =>
<8, (>
<23, x>
<3, +>
<21, 5>
<9, )>
<5, *>
<21, 2>
<2, ;>
<13, if>
<8, (>
<23, x>
<20, >>
<21, 5>
<9, )>
<10, {>
<23, z>
<7, =>
<23, x>
<5, *>
<21, 2>
<2, ;>
<11, }>
<14, else>
<10, {>
<23, z>
<7, =>
<23, x>
<6, />
<21, 2>
<2, ;>
<11, }>
<15, while>
<8, (>
<23, z>
<19, <>
<21, 50>
<9, )>
<10, {>
<23, z>
<7, =>
<23, z>
<3, +>
<21, 1>
<2, ;>
<11, }>
<16, return>
<23, z>
<2, ;>
Resultado: 50.0
Árvore Sintática:
Programa
  Programa
    Programa
      Programa
        Programa
          Atribuicao
            x
            10
        Atribuicao
          y
          *
            ()
              +
                x
                5
            2
      If-Else
        >
          x
          5
        Bloco
          Programa
            Atribuicao
              z
              *
                x
                2
        Bloco
          Programa
            Atribuicao
              z
              /
                x
                2
    While
      <
        z
        50
      Bloco
        Programa
          Atribuicao
            z
            +
              z
              1
  Return
    z


```