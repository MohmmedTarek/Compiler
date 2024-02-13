# Compiler 💻

**Objective** 🚀

The objective of this task is to implement a fallback deterministic finite automaton with actions (FDFA) abstract data type. An FDFA is defined by a sextuple (Q, Σ, δ, q0, F, A), where:
- Q is a nonempty, finite set of states.
- Σ is a non-empty, finite set of symbols (an alphabet).
- δ : Q×Σ −→ Q is the transition function.
- q0 ∈ Q is the start state.
- F ⊆ Q is the set of accept states.
- A is a function that maps every state in Q to an action.

Refer to the slides of Lecture 2 of CSEN1003 for more detailed information about the operation of FDFA.

**Requirements** 📋

To fulfill this task, adhere to the following requirements:
- **Assumptions**: 
  - The set of states Q is always of the form {0, . . . , n}, for some n ∈ ℕ.
  - The alphabet Σ is always a subset of the Latin alphabet, not including ε.
  - q0 ∈/ F.
  - A(q) is the action which appends the token "lex,q" to a list, where lex is as indicated in Lecture 2 of CSEN1003, and q is the state name.
- **Implementation**:
  - Implement a class constructor `FallbackDfa` and a method `run`.
  - The class constructor `FallbackDfa` takes one parameter which is a string description of an FDFA and constructs an FDFA instance as per the description.
  - A string describing an FDFA follows the format Q#A#T #I#F, where:
    - Q is a string representation of the set of states; a semicolon-separated sequence of sorted integer literals.
    - A is a string representation of the input alphabet; a semicolon-separated sequence of alphabetically sorted symbols.
    - T is a string representation of the transition function. T is a semicolon-separated sequence of triples. Each triple is a string representing a single transition; a comma-separated sequence i, a, j where i is a state of Q, a a symbol of A, and j a state of Q representing a transition from i to j on input a. These triples are sorted by the source state i and then by the input a.
    - I is an integer literal representing the initial state.
    - F is a string representation of the set of accept states; a semicolon-separated sequence of sorted integer literals.
    - Note that the function A is not encoded in the string representation since it is fixed for all FDFA as indicated in the simplifying assumptions above.
- **Example**:
  - For example, the following string represents the FDFA whose state diagram appears in the figure below.
    ```
    0;1;2;3#a;b#0,a,0;0,b,1;1,a,2;1,b,1;2,a,0;2,b,3;3,a,3;3,b,3#0#1;2
    ```

**Running the Program**🏃‍♂️

- The `run` method simulates the operation of the constructed FDFA on a given string and returns a semicolon-separated sequence of tokens.
- For instance, running the above FDFA on the string `baababb` produces the output `baaba, 2; bb, 1`.

✨ Happy coding! ✨



















## Objective 🎯

- For this task, you need to implement the **classical algorithm** for constructing a **deterministic finite automaton (DFA)** equivalent to a given **non-deterministic finite automaton (NFA) 🤖**. 

- Recall that an NFA is a quintuple (**Q, Σ, δ, q0, F**): **Q** is a non-empty, finite set of states; **Σ** is non-empty, finite set of symbols (an alphabet); **δ : Q × (Σ ∪ {ε}) −→ P(Q)** is the transition function; **q0 ∈ Q** is the start state; and **F ⊆ Q** is the set of accept states 🤔. 

- Given a description of an NFA, you need to construct an equivalent DFA 🛠️.


## Requirements 📝
- We make the following assumptions for simplicity.
  - 📌 The alphabet **Σ** is always a subset of the Latin alphabet, not including **“e”**.
  - 📌 The letter **“e”** represents **ε**.
  - 📌 The set of NFA states **Q** is always of the form **{0, . . . , n}**, for some **n ∈ N**.
- You should implement a class constructor NfaToDfa and a method toString.

- NfaToDfa takes one parameter which is a string description of an NFA and constructs an equivalent DFA. A string describing an NFA is of the form **Q#A#T#I#F**.
- 📌 **Q** is a string representation of the set of states; a semicolon-separated sequence of sorted integer literals.
- 📌 **A** is a string representation of the input alphabet; a semicolon-separated sequence of alphabetically sorted symbols.
- 📌 **T** is a string representation of the transition function. **T** is a semicolon-separated sequence of triples. Each triple 
     is a string representing a single transition; a commaseparated sequence **i**, **a**, **j** where **i** is a state of **Q**, a symbol of **A** or **e**, and **j** a state of Q representing a transition from **i** to **j** on input a. These triples are sorted by the source state i, then (if the same state has more than one outgoing transition) by the input a, and then (if multiple triples share the same source state and input, due to non-determinism) by the destination state j.
- 📌 **I** is an integer literal representing the initial state.
- 📌 **F** is a string representation of the set of accept states; a semicolon-separated sequence of sorted integer literals.

- 📌 For example 🌟, the NFA for which the state diagram appears below may have the following string representation.🧵
     `0;1;2;3#a;b#0,a,0;0,b,0;0,b,1;1,a,2;1,e,2;2,b,3;3,a,3;3,b,3#0#3`

  `toString` returns a string representation of the constructed DFA. A string representation of a DFA returned by `toString` is similar to that of an NFA—a string of the form **Q#A#T#I#F**.
- 📌 However, states of such DFA are sets of states of the original NFA. Hence, only the representation of states in the string 
      encoding of DFA is different from that of NFA. A DFA state is represented as a /-separated sequence of numerals, with the numerals representing NFA states.
- 📌 These sequences are sorted by their first numerals (assuming the natural order of numerals). Two sequences starting with the same numeral are sorted according to the order of their respective suffixes resulting from dropping the first numeral. The empty sequence precedes any sequence.
- 📌 A DFA state corresponding the empty set of NFA states is represented by -1.
- 📌 Thus, following the classical construction, the following is a (split for readability) string representing a DFA equivalent  
     to the NFA in the above figure.    
     `0;0/1/2;0/1/2/3;0/2;0/2/3;0/3#a;b#0,a,0;0,b,0/1/2;0/1/2,a,0/2;0/1/2,b,0/1/2/3;0/1/2/3,a,0/2/3;0/1/2/3,b,0/1/2/3;0/2,a,0;0/2,b,0/1/2/3;0/2/3,a,0/3;0/2/3,b,0/1/2/3;0/3,a,0/3;0/3,b,0/1/2/3#0#0/1/2/3;0/2/3;0/3`
