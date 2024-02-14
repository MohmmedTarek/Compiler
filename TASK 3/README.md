# Compiler 💻

## Objective 🎯

The objective of this task is to implement **a fallback deterministic finite automaton with actions (FDFA)** abstract data type. An FDFA is defined by a sextuple **(Q, Σ, δ, q0, F, A)** where:
- **Q** is a nonempty, finite set of states.
- **Σ** is a non-empty, finite set of symbols (an alphabet).
- **δ : Q×Σ −→ Q** is the transition function.
- **q0 ∈ Q** is the start state.
- **F ⊆ Q** is the set of accept states.
- **A** is a function that maps every state in **Q** to an action.


## Requirements 📋

To fulfill this task, adhere to the following requirements:
- **Assumptions**: 
  - 🔍 The set of states **Q** is always of the form **{0, . . . , n}** for some **n ∈ ℕ**.
  - 🔍 The alphabet **Σ** is always a subset of the Latin alphabet, not including **ε**.
  - 🔍 **q0 ∈/ F**.
  - 🔍 **A(q)** is the action which appends the token **"lex,q"** to a list, where **lex** is as indicated in Lecture 2 of CSEN1003, and **q** is the state name.
- **Implementation**:
  - 📌 Implement a class constructor `FallbackDfa` and a method `run`.
  - 📌 The class constructor `FallbackDfa` takes one parameter which is a string description of an FDFA and constructs an FDFA instance as per the description.
  - 📌 A string describing an FDFA follows the format `Q#A#T#I#F` where:
    - **Q** is a string representation of the set of states; a semicolon-separated sequence of sorted integer literals.
    - **A** is a string representation of the input alphabet; a semicolon-separated sequence of alphabetically sorted symbols.
    - **T** is a string representation of the transition function. **T** is a semicolon-separated sequence of triples. Each triple is a string representing a single transition; a comma-separated sequence i, a, j where i is a state of **Q**, **a** a symbol of **A**, and j a state of **Q** representing a transition from i to j on input a. These triples are sorted by the source state i and then by the input a.
    - **I** is an integer literal representing the initial state.
    - **F** is a string representation of the set of accept states; a semicolon-separated sequence of sorted integer literals.
    - Note that the function **A** is not encoded in the string representation since it is fixed for all `FDFA` as indicated in the simplifying assumptions above.
- **Example**:
  - For example 🌟, the following string represents the FDFA whose state diagram appears in the figure below.
    `0;1;2;3#a;b#0,a,0;0,b,1;1,a,2;1,b,1;2,a,0;2,b,3;3,a,3;3,b,3#0#1;2`

    <br>
  
  <img src="https://github.com/MohmmedTarek/Compiler/blob/main/FIGURES/TASK3_FIG1.jpg" alt="FDFA State Diagram" width="600">



## Running the Program 🚀

- The `run` method simulates the operation of the constructed FDFA on a given string and returns a semicolon-separated sequence of tokens.
- For instance, running the above FDFA on the string `baababb` produces the output `baaba, 2; bb, 1`.
