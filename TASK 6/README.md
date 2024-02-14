# Compiler 💻

## Objective 🎯

For this task, you will implement the algorithms computing the functions **First** and **Follow** for the variables of a given `context-free grammar (CFG)`. Recall that a CFG is a quadruple **(V, Σ, R, S)** where **V** and **Σ** are disjoint alphabets (respectively, containing variables and terminals), **R ⊆ V ×(V ∪ Σ)<sup>*</sup>** is a set of rules, and **S ∈ V** is the start variable.

## Requirements 📋

To fulfill this task, adhere to the following requirements:

- **Assumptions**:
  - 🔍 The set **V** of variables consists of upper-case English letters.
  - 🔍 The start variable is the symbol **S**.
  - 🔍 The set **Σ** of terminals consists of lower-case English letters (except the letter **e**).
  - 🔍 The letter **e** represents **ε**.

- **Implementation**:
  - 📌 Implement a class constructor `CfgFirstFollow`, and two methods; `first` and `follow`.
  - 📌 `CfgFirstFollow`, a class constructor, takes one parameter which is a string description of a CFG and constructs a CFG instance. A string encoding a CFG is of the form `V#T#R`.
    - **V** is a string representation of the set of variables; a semicolon-separated sequence of upper-case English letters, starting with **S**.
    - **T** is a string representation of the set of terminals; a semicolon-separated sequence of alphabetically sorted lower-case English letters.
    - **R** is a string representation of the set of rules. **R** is a semicolon-separated sequence of pairs. Each pair represents a largest set of rules with the same left-hand side. Pairs are of the form i/j where i is a variable of **V** and j is a string representation of set of right-hand sides—a comma-separated sequence of strings. These pairs are sorted by the common left-hand side i based on the ordering of **V**.

- **Example**:
  - For example 🌟, consider the CFG _G1_ = ({S, T, L}, {a, b, c, d, i}, R, S), where R is given by the following productions.
    ```
    S → ScT | T
    T → aSb | iaLb | ε
    L → SdL | S
    ```
    This CFG will have the following string encoding.
    ```
    S;T;L#a;b;c;d;i#S/ScT,T;T/aSb,iaLb,e;L/SdL,S
    ```

- **Output Format**:
  - The output of each of `first` and `follow` is a semi-colon-separated sequence of items, where each item is a /-separated pair. The first element of each pair is a variable of the grammar and the second element is a string representing the `First` or, respectively, the `Follow` set of that variable. The symbols in these strings should appear in alphabetical order. (`$` always appears first.) The items themselves should appear in the order in which their respective variables appear in the input CFG.
  - For example, the result of calling `first` on _G1_ may have the following form:
    ```
    S/acei; T/aei; L/acdei
    ```
  - Similarly, the result of calling `follow` on _G1_ may be as follows:
    ```
    S/$bcd; T/$bcd; L/b
    ```

## Running the Program 🚀

- To run the program, provide a string description of the CFG to the `CfgFirstFollow` constructor, then call the necessary methods as described above.
