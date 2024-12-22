import java_cup.runtime.Symbol;

/*
Directivas:
public: classe publica
cup: compatibilidade com CUP
full: estende o alfabeto com todos os valores de 8 bits
linha: adicionar a variável int yyline, para indicar a linha do lexema
char: adicionar a variável int yychar, para indicar o índice do primeiro caractere do lexema
ignorecase: validar, independentemente de a letra ser maiúscula ou minúscula
eofval: especifica um valor de retorno no final do arquivo
*/

%%

%class Scanner
%cup
%full
%line
%char
%eofval{
    return new Symbol(Tokens.EOF, "Fim do arquivo");
%eofval}

digito              = [0-9]
letra               = [a-zA-Z]
id                  = {letra}({letra}|{digito}|"_")*
espaco              = [ \t\r\n\f]
comentario_single   = "//".*
comentario_multi    = "/\\*([^*]|\\*+[^*/])*\\*+/"

%%

{espaco}            { /* Ignorar espaços em branco */ }
{comentario_single} { /* Ignorar comentários de linha única */ }
{comentario_multi}  { /* Ignorar comentários de múltiplas linhas */ }

"if"                { return new Symbol(Tokens.IF, yytext()); }
"else"              { return new Symbol(Tokens.ELSE, yytext()); }
"while"             { return new Symbol(Tokens.WHILE, yytext()); }
"return"            { return new Symbol(Tokens.RETURN, yytext()); }

"=="                { return new Symbol(Tokens.IGUAL_IGUAL, yytext()); }
"!="                { return new Symbol(Tokens.DIFERENTE, yytext()); }
"<"                 { return new Symbol(Tokens.MENOR, yytext()); }
">"                 { return new Symbol(Tokens.MAIOR, yytext()); }

"+"                 { return new Symbol(Tokens.MAIS, yytext()); }
"-"                 { return new Symbol(Tokens.MENOS, yytext()); }
"*"                 { return new Symbol(Tokens.VEZES, yytext()); }
"/"                 { return new Symbol(Tokens.DIV, yytext()); }

"="                 { return new Symbol(Tokens.ATRIB, yytext()); }

"("                 { return new Symbol(Tokens.LPAREN, yytext()); }
")"                 { return new Symbol(Tokens.RPAREN, yytext()); }
"{"                 { return new Symbol(Tokens.LCURLY, yytext()); }
"}"                 { return new Symbol(Tokens.RCURLY, yytext()); }
";"                 { return new Symbol(Tokens.SEMI, yytext()); }
","                 { return new Symbol(Tokens.VIRGULA, yytext()); }

{digito}+           { return new Symbol(Tokens.NUMERO, Integer.parseInt(yytext())); }
{digito}+"."{digito}* { return new Symbol(Tokens.PONTO_FLUTUANTE, Double.parseDouble(yytext())); }
{id}                { return new Symbol(Tokens.ID, yytext()); }

.                   {
    System.err.println("Caracter ilegal na linha " + yyline + ", coluna " + yychar + ": " + yytext());
    return null;
}
