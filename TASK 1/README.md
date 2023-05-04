# Compiler 💻

## Objective 🎯
For this task, you need to implement Thompson's construction algorithm for converting a regular expression to an equivalent non-deterministic finite automaton (NFA). You can find the description of Thompson's construction in Chapter 3 of the textbook and on the Wikipedia page https://en.wikipedia.org/wiki/Thompson's_construction.

## Requirements 📋
- 📌 We assume the following for simplicity:
  - The alphabet Σ of the regular expression is always a subset of the Latin alphabet, excluding "**e**".
  - Regular expressions do not include **∅**.
  - The empty string ε is represented by "**e**".
  - The ◦ operator is represented by "**.**" and the **∪** operator is represented by "**|**".
  - Regular expressions are represented in postfix notation.
  - States of the resulting NFA are represented by numbers.
  - For a postfix regular expression R, states introduced by the NFA equivalent to a prefix of R are **smaller** (as numbers) than states introduced by the NFA equivalent to longer prefixes of R. For operators (such as union and *) which introduce a start and an accept state, the **start state** is smaller (as a number) than the **accept state**.
  - Following Thompson's construction, concatenation involves **merging** the accept state of the first (left) NFA and the start 
    state of the second (right) NFA; the resulting merged state is the accept state of the first NFA.


## Project Details 🔍
- You should implement a class constructor `RegExToNfa` and a method `toString`.
- `RegExToNfa` takes one parameter which is a string of the form **A#R**. **A** is a string representation of an alphabet **Σ**, a semicolon-separated sequence of alphabetically sorted symbols, and **R** is a postfix regular expression over **Σ**.
- `RegExToNfa` constructs the NFA to **R** as per Thompson’s construction.
- `toString` returns a string describing the NFA resulting from Thompson’s construction. A string describing the NFA resulting from Thompson’s construction is of the form **Q#A#T#I#F**.
- 📌 **Q** is a string representation of the set of states; a semicolon-separated sequence of sorted integer literals.
- 📌 **A** is a string representation of the input alphabet; a semicolon-separated sequence of alphabetically sorted symbols
- 📌 **T** is a string representation of the transition function. **T** is a semicolon-separated sequence of triples. Each triple 
     is a string representing a single transition; a comma separated sequence **i, a, j** where **i** is a state of **Q**, **a** a symbol of **A** or **e**, and **j** a state of **Q** representing a transition from **i** to **j** on input **a**. These triples are sorted by the source state `i`, then (if the same state has more than one outgoing transition) by the input **a**, and then (if multiple triples share the same source state and input, due to non-determinism) by the destination state **j**.
- 📌 **I** is an integer literal representing the initial state.
- 📌 **F** is a string representation of the set of accept states; a semicolon-separated sequence of sorted integer literals.
- 📌 For example, `toString`, being invoked on a `RegExToNfa` object representing the regular expression `a;b#ab|`, should return the string `0;1;;2;3;4;5#a;b#0,a,1;1,e,5;2,b,3;3,e,5;4,e,0;4,e,2#4#5`