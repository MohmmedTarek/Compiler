# Compiler 💻

## Objective 🎯

The objective of this task is to implement an **LL(1) parser** using pushdown automata (PDA) and predictive parsing tables. Given an input context-free grammar **G = (V, Σ, R, S)**, along with the **First** and **Follow** sets for all rules, you need to:
1. Construct the predictive parsing table for **G**.
2. Construct the PDA equivalent to **G**.
3. Implement an **LL(1) parser** for **G** which makes use of the table and the PDA to direct its decisions. Given an input string **w**, the parser should signal an error if **w ∉ L(G)** and produce a derivation of **w** from **S** if **w ∈ L(G)**.

## Requirements 📋

To fulfill this task, adhere to the following requirements:

- **Assumptions**:
  - 🔍 The set **V** of variables consists of upper-case English letters.
  - 🔍 The start variable is the symbol **S**.
  - 🔍 The set **Σ** of terminals consists of lower-case English letters (except the letter **e**).
  - 🔍 The letter **e** represents **ε**.
  - 🔍 We only consider **LL(1)** context-free grammars.

- **Implementation**:
  - 📌 Implement a class constructor `CfgLl1Parser` and a method `parse`.
  - 📌 `CfgLl1Parser`, a class constructor, takes one parameter which is a string description of a CFG, together with **First** and **Follow** sets for its rules, and constructs a CFG instance. A string encoding a CFG is of the form `V#T#R#I#O`.
    - **V** is a string representation of the set of variables; a semicolon-separated sequence of upper-case English letters, starting with **S**.
    - **T** is a string representation of the set of terminals; a semicolon-separated sequence of alphabetically sorted lower-case English letters.
    - **R** is a string representation of the set of rules. **R** is a semicolon-separated sequence of pairs. Each pair represents a largest set of rules with the same left-hand side. Pairs are of the form i/j where i is a variable of **V** and j is a string representation of set of right-hand sides—a comma-separated sequence of strings. These pairs are sorted by the common left-hand side i based on the ordering of **V**.
    - **I** is a string representation of the **First** set of each rule. **I** is a semicolon-separated sequence of pairs. Pairs are of the form i/j where i is a variable of **V** and j is the string representation of the **First** sets of each right-hand side of a rule for i—a comma-separated sequence of strings. These sets appear in the same order of the corresponding rules and are concatenations of the symbols making up the represented set. These pairs are sorted by the common left-hand side i based on the ordering of **V**.
    - **O** is a string representation of the **Follow** set of each variable. **O** is a semicolon-separated sequence of pairs. Pairs are of the form i/j where i is a variable of **V** and j is the string representation of the **Follow** set of i. These sets are encoded by concatenations of the symbols making up the represented set. These pairs are sorted by the common left-hand side i based on the ordering of **V**.

- **Example**:
  - For example 🌟, consider the CFG _G1_ = ({S, T}, {a, c, i}, R, S), where R is given by the following productions.
    ```
    S → iST | ε
    T → cS | a
    ```
    This CFG will have the following string encoding.
    ```
    S;T#a;c;i#S/iST,e;T/cS,a#S/i,e;T/$ca
    ```
  
- **parse**:
  - Takes an input string **w** and returns a string encoding a left-most derivation of **w** in **G**; in case **w ∉ L(G)**, this derivation ends with “ERROR.” The parse method should construct a PDA equivalent to **G** and use the PDA together with the **LL(1)** parsing table to reach its decision. Note that we will be testing parse using only **LL(1)** grammars.
  - A string encoding a derivation is a semicolon-separated sequence of items. Each item is a sentential form representing a step in the derivation. The first item is **S**. If **w ∈ L(G)** the last item is **w**; otherwise, it is **ERROR**.
  - For example, given _G1_, on input string **iiac**, `parse` should return the following string.
    ```
    S;iST;iiSTT;iiTT;iiaT;iiacS;iiac
    ```
    On the other hand, on input string **iia**, `parse` should return the following.
    ```
    S;iST;iiSTT;iiTT;iiaT;ERROR
    ```

## Running the Program 🚀

- To run the program, provide a string description of the CFG to the `CfgLl1Parser` constructor, then call the necessary methods as described above.
