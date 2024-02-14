# Compiler 💻

## Objective 🎯

The objective of this task is to implement the **context-free grammar (CFG) left-recursion elimination** algorithm. Recall that a `CFG` is a quadruple **(V, Σ, R, S)** where **V** and **Σ** are disjoint alphabets (respectively, containing variables and terminals), **R ⊆ V × (V ∪ Σ)<sup>∗</sup>** is a set of rules, and **S ∈ V** is the start variable.

## Requirements 📋

To fulfill this task, adhere to the following requirements:
- **Assumptions**:
  - 🔍 The set **V** of variables consists of upper-case English letters.
  - 🔍 The start variable is the symbol **S**.
  - 🔍 The set **Σ** of terminals consists of lower-case English letters (except the letter **e**).
  - 🔍 The letter **“e”** represents **ε**.
  - 🔍 We only consider `CFGs` with no cycles and no **ε-rules**.
- **Implementation**:
  - 📌 Implement a class constructor `CfgLeftRecElim`, and two methods; `toString`, and `eliminateLeftRecursion`.
  - 📌 `CfgLeftRecElim`, a class constructor, takes one parameter which is a string description of a CFG and constructs a CFG instance. A string encoding a CFG is of the form **V#T#R**.
    - **V** is a string representation of the set of variables; a semicolon-separated sequence of upper-case English letters, starting with **S**.
    - **T** is a string representation of the set of terminals; a semicolon-separated sequence of alphabetically sorted lower-case English letters.
    - **R** is a string representation of the set of rules. **R** is a semicolon-separated sequence of pairs. Each pair represents a largest set of rules with the same left-hand side. Pairs are of the form **i/j** where **i** is a variable of **V** and **j** is a string representation of set of right-hand sides—a comma-separated sequence of strings. These pairs are sorted by the common left-hand side **i** based on the ordering of **V**.
- **Example**:
  - For example 🌟, consider the CFG _G1_ = ({S, T, L}, {a, b, c, d, i}, R, S), where **R** is given by the following productions.
    ```
    S → S c T i | L a | T i | b
    T → a S b | L a b S | i
    L → S d L | S i
    ```
    This CFG will have the following string encoding.
    ```
    S;T;L#a;b;c;d;i#S/ScTi,La,Ti,b;T/aSb,LabS,i;L/SdL,Si
    ```
- **toString**:
  - Returns a string representation of a CFG. This string representation is the same as the one used for the input to the constructor.
- **eliminateLeftRecursion**:
  - Eliminates left recursion in the constructed CFG where a newly-introduced variable, for the elimination of immediate left-recursion for variable **A**, is the string **A′**. The letter **e** denotes the empty string. Newly added rules appear in the order indicated in Slides 33 and 34 of Lecture 3.
  - For example 🌟, after invoking the method on _G1_, the string returned by `toString` is the following (split for readability)
    ```
    S;T;L;S';L'#a;b;c;d;i#S/LaS',TiS',bS';T/aSb,LabS,i;
    L/aSbiS'dLL',iiS'dLL',bS'dLL',aSbiS'iL',iiS'iL',bS'iL';S'/cTiS',e;
    L'/aS'dLL',abSiS'dLL',aS'iL',abSiS'iL',e
    ```

## Running the Program 🚀

- To run the program, provide a string description of the CFG to the `CfgLeftRecElim` constructor, then call the necessary methods as described above.
