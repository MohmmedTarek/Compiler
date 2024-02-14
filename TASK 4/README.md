# Compiler 💻

## Objective 🎯

The objective of this task is to implement the algorithms for **eliminating epsilon and unit rules** from a given `context-free grammar (CFG)`. Recall that a CFG is a quadruple **(V, Σ, R, S)** where **V** and **Σ** are disjoint alphabets (respectively, containing variables and terminals),<br>
**R ⊆ V × (V ∪ Σ)<sup>∗</sup>** is a set of rules, and **S ∈ V** is the start variable.

## Requirements 📋

To fulfill this task, adhere to the following requirements:
- **Assumptions**:
  - 🔍 The set **V** of variables consists of upper-case English letters.
  - 🔍 The start variable is the symbol **S**.
  - 🔍 The set **Σ** of terminals consists of lower-case English letters (except the letter **e**).
  - 🔍 The letter **“e”** represents **ε**.
  - 🔍 **ε ∉ L(G)**.
- **Implementation**:
  - 📌 Implement a class constructor `CfgEpsUnitElim`, and three methods; `toString`, `eliminateEpsilonRules`, and `eliminateUnitRules`.
  - 📌 `CfgEpsUnitElim`, a class constructor, takes one parameter which is a string description of a CFG and constructs a CFG instance. A string encoding a CFG is of the form `V#T#R`.
    - **V** is a string representation of the set of variables; a semicolon-separated sequence of upper-case English letters, starting with **S**.
    - **T** is a string representation of the set of terminals; a semicolon-separated sequence of alphabetically sorted lower-case English letters.
    - **R** is a string representation of the set of rules. **R** is a semicolon-separated sequence of pairs. Each pair represents a largest set of rules with the same left-hand side. Pairs are of the form i/j where i is a variable of **V** and j is a string representation of set of right-hand sides—a comma-separated sequence of lexicographically sorted strings. These pairs are sorted by the common left-hand side i based on the ordering of **V**.
- **Example**:
  - For example 🌟, consider the CFG _G1_ = ({S, A, B, C}, {a, b, c, d, x}, R, S), where R is given by the following productions.
    ```
    S → aAb | xB
    A → Bc | C | c | d
    B → CACA | ε
    C → A | b | ε
    ```
    This CFG will have the following string encoding.
    ```
    S;A;B;C#a;b;c;d;x#S/aAb,xB;A/Bc,C,c,d;B/CACA,e;C/A,b,e
    ```
- **toString**:
  - Returns a string representation of a CFG. This string representation is the same as the one used for the input to the constructor.
- **eliminateEpsilonRules**:
  - Eliminates epsilon rules from the constructed CFG using the classical algorithm.
  - For example 🌟, after invoking the method on _G1_, the string returned by toString is the following (split for readability)
    ```
    S;A;B;C#a;b;c;d;x#S/aAb,ab,x,xB;A/Bc,C,c,d;
    B/A,AA,AC,ACA,C,CA,CAA,CAC,CACA,CC,CCA;C/A,b
    ```
- **eliminateUnitRules**:
  - Eliminates unit rules from the constructed CFG using the classical algorithm.
  - For example 🌟, after invoking the method on _G1_, the string returned by toString is the following
    ```
    S;A;B;C#a;b;c;d;x#S/aAb,xB;A/Bc,b,c,d,e;B/CACA,e;C/Bc,b,c,d,e
    ```
- Additionally, the above two methods can be called sequentially. Thus the result of invoking `toString` after invoking `eliminateEpsilonRules` then `eliminateUnitRules` returns the following (split for readability)
  ```
  S;A;B;C#a;b;c;d;x#S/aAb,ab,x,xB;A,Bc,b,c,d;
  B/AA,AC,ACA,Bc,CA,CAA,CAC,CACA,CC,CCA,b,c,d;C/Bc,b,c,d
  ```

## Running the Program 🚀

- To run the program, provide a string description of the CFG to the `CfgEpsUnitElim` constructor, then call the necessary methods as described above.
