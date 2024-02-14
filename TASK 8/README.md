# Compiler 💻

## Objective 🎯

The objective of this task is to implement a lexical analyzer using ANTLR (www.antlr.org). The tokenizer should split the input string over a subset of a pseudo programming language into lexemes and produce an output token for each lexeme.

## Requirements 📋

To fulfill this task, adhere to the following requirements:

- **Token Types**:
  - **IF**: `if`, `If`, `iF`, `IF`
  - **ELSE**: `else,...,ELSE`
  - **COMP**: `>`, `<`, `>=`, `<=`, `==`, `!=`
  - **ID**: any letter or `_`, optionally followed by letters, digits, or `_`
  - **NUM**: a decimal numeral consisting of:
    - a whole-number part consisting of at least one digit
    - an optional decimal part consisting of `.` followed by at least one digit
    - an optional exponent part consisting of a case-insensitive `e` followed by an optional sign (`+` or `-`) followed by at least one digit
  - **LIT**: zero or more ASCII characters enclosed in double quotes, excluding `\` and `"`, where each must be preceded by a `\`
  - **LP**: `(`
  - **RP**: `)`
  - unenclosed spaces should be **recognized and discarded**

- **Lexer Rules**:
  - Write a lexer rule for each token type described above. These rules should recognize all strings matching the description and only these strings.

- **Output Format**:
  - The method `tokenStream` should use the ANTLR grammar to return a semicolon-separated sequence of tokens, where each token is in the form `"lex,q"`, where `lex` is the lexeme and `q` is the token type.

- **Example 🌟**:
  - Running the lexical analyzer implementing the tokenizer on the string:
    ```
    if (x > 10) printf("Yes") else printf("No")
    ```
    should output the following (split for readability):
    ```
    if,IF;(,LP;x,ID;>,COMP;10,NUM;),RP;printf,ID;(,LP;"Yes",LIT;),RP;else,ELSE;printf,ID;(,LP;"No",LIT;),RP
    ```

## Running the Program 🚀

- To run the program, use the provided ANTLR grammar and call the `tokenStream` method as described above.
