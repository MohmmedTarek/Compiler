# Compiler 💻

## Objective 🎯

The objective of this task is to use ANTLR (www.antlr.org) to implement a **Syntax Directed Definition (SDD)** for a **context-free grammar (CFG)** generating the language `a*c*b*`.

## Requirements 📋

To fulfill this task, adhere to the following requirements:

- **SDD Rules**:
  - Use ANTLR to implement the following SDD rules for the CFG:
    ```
    S → ACB       C.l = A.2n; C.u = A.3n
                  C.ilf = 0; C._iuf_ = 1
                  S.check = C.slf * C.suf * equals(A.n, B.n)

    A → aA₁       A.n = A₁.n + 1; A.2n = A₁.2n * 2; A.3n = A₁.3n * 3

    A → ε         A.n = 0; A.2n = 1; A.3n = 1

    B → bB₁       B.n = B₁.n + 1

    B → ε         B.n = 0

    C → cC₁       C₁.l = C.l; C₁.u = C.u
                  C₁.ilf = C.ilf; C₁.iuf = C.iuf
                  C.m = C₁.m + 1
                  C.slf = C₁.slf + equals(C.l, C.m)
                  C.suf = C₁.suf - equals(C.u, C₁.m)

    C → ε         C.m = 0; C.slf = C.ilf; C.suf = C.iuf
    ```
  - The start variable **S** has an attribute **check** that evaluates to 1 if the generated string is of the form **a<sup>n</sup>c<sup>m</sup>b<sup>n</sup>** with **2<sup>n</sup> ≤ m ≤ 3<sup>n</sup>**, for some **n ≥ 0**, and **0** otherwise.
  - Only assignments, additions, multiplications, and equality checks are allowed as operations on attributes.
  - The provided method `sCheckValue` should return the value of **S.check** for a given input string.

## Running the Program 🚀

- Use the provided ANTLR grammar and method `sCheckValue` as described above to test your implementation.
