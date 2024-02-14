# ANTLR SDD Implementation 🧠

## Objective 🎯

The objective of this task is to use ANTLR (www.antlr.org) to implement a Syntax Directed Definition (SDD) for a context-free grammar (CFG) generating the language \(a^*c^*b^*\).

## Requirements 📋

To fulfill this task, adhere to the following requirements:

- **SDD Rules**:
  - Use ANTLR to implement the following SDD rules for the CFG:
    ```
    S → ACB
    C.l = A.2n; C.u = A.3n
    C.ilf = 0; C.iuf = 1
    S.check = C.slf * C.suf * equals(A.n, B.n)

    A → aA1
    A.n = A1.n + 1; A.2n = A1.2n * 2; A.3n = A1.3n * 3
    A → ε
    A.n = 0; A.2n = 1; A.3n = 1

    B → bB1
    B.n = B1.n + 1
    B → ε
    B.n = 0

    C → cC1
    C1.l = C.l; C1.u = C.u
    C1.ilf = C.ilf; C1.iuf = C.iuf
    C.m = C1.m + 1
    C.slf = C1.slf + equals(C.l, C.m)
    C.suf = C1.suf - equals(C.u, C1.m)
    C → ε
    C.m = 0; C.slf = C.ilf; C.suf = C.iuf
    ```
  - The start variable \(S\) has an attribute \(check\) that evaluates to 1 if the generated string is of the form \(a^nc^mb^n\) with \(2^n ≤ m ≤ 3^n\), for some \(n ≥ 0\), and 0 otherwise.
  - Only assignments, additions, multiplications, and equality checks are allowed as operations on attributes.
  - The provided method `sCheckValue` should return the value of \(S.check\) for a given input string.

- **Implementation Details**:
  - Your implementation should be done within the template file uploaded to the CMS.
  - You are not allowed to change the grammar name, the rule name "s", or attribute "check".
  - You can write as many helper parser and lexer rules within the same grammar file if needed.
  - Public test cases have been provided on the CMS for you to test your implementation.
  - Ensure that the public test cases run correctly without modification before coming to the lab for evaluation.
  - A Java file is provided to easily test your grammar with custom strings in addition to the public test cases.
  - Private test cases will be uploaded before your session and will have the same structure as the public test cases.

## Running the Program 🚀

- Use the provided ANTLR grammar and method `sCheckValue` as described above to test your implementation.
