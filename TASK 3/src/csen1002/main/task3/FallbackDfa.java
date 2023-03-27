package csen1002.main.task3;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Write your info here
 * 
 * @name Mohamed Tarek
 * @id 49-12309
 * @labNumber 20
 */

public class FallbackDfa {

	Stack<String> stack = new Stack<String>();
	LinkedList<String> DFA = new LinkedList<String>();
	String startState;
	LinkedList<String> acceptStates = new LinkedList<String>();

	/**
	 * Constructs a Fallback DFA
	 * 
	 * @param fdfa A formatted string representation of the Fallback DFA. The string
	 *             representation follows the one in the task description
	 */
	public FallbackDfa(String fdfa) {
		// TODO Auto-generated constructor stub

		letsBreakItDown(fdfa);
	}

	/**
	 * @param input The string to simulate by the FDFA.
	 * 
	 * @return Returns a formatted string representation of the list of tokens. The
	 *         string representation follows the one in the task description
	 */
	public String run(String input) {

		String result = "";
		int R = 0;
		int L = 0;
		int hold = input.length() + 1;

		for (; R < input.length();) {
			if (R == L) {
				if (!stack.isEmpty())
					stack.clear();
				stack.push(startState);
				L++;
			}
			for (; L <= hold;) {
				String c = input.charAt(L - 1) + "";
				String s = stack.get(stack.size() - 1) + "";

				for (int i = 0; i < DFA.size(); i += 3) {
					if (s.compareTo(DFA.get(i)) >= 0) {

						if (s.equals(DFA.get(i)) && c.equals(DFA.get(i + 1))) {
							stack.push(DFA.get(i + 2));
							s = DFA.get(i + 2);
							L++;
							if (L < hold) {
								c = input.charAt(L - 1) + "";
								i = -3;
							} else {
								break;
							}
						}
					}
				}

				String Q = stack.get(stack.size() - 1);
				L = hold - 2;
				String topStack = stack.pop();
				while (R <= L) {

					if (isItAccept(topStack) == true) {
						for (int w = R; w <= L; w++) {
							result += input.charAt(w) + "";
						}
						result += "," + topStack + ";";
						L++;
						R = L;

						break;
					}

					else {
						topStack = stack.pop();
						L--;
					}
				}
				if (stack.isEmpty()) {
					for (int w = R; w < input.length(); w++) {

						result += input.charAt(w) + "";
					}
					result += "," + Q + ";";
					R = input.length();

				}
				break;

			}
		}
		return result.substring(0, result.length() - 1);
	}

	public void letsBreakItDown(String fdfa) {

		Boolean statesArrived = false;
		Boolean alphaArrived = false;
		Boolean dfaArrived = false;
		Boolean firstNodeArrived = false;

		for (int i = 0; i < fdfa.length(); i++) {

			if (statesArrived == false) {
				if ((fdfa.charAt(i + 1) + "").equals("#")) {
					statesArrived = true;
					i++;
				}

			} else if (statesArrived && alphaArrived == false) {

				if ((fdfa.charAt(i + 1) + "").equals("#")) {
					alphaArrived = true;
					i++;
				}

			} else if (statesArrived && alphaArrived && dfaArrived == false) {
				if (!(fdfa.charAt(i) + "").equals(";") && !(fdfa.charAt(i) + "").equals(",")) {
					String st = fdfa.charAt(i) + "";

					try {
						int secNo = Integer.parseInt(fdfa.charAt(i + 1) + "");
						st += secNo + "";
						i++;
					} catch (NumberFormatException e) {

					}

					DFA.add(st);
					if ((fdfa.charAt(i + 1) + "").equals("#")) {
						dfaArrived = true;
						i++;
					}

				}
			} else if (statesArrived && alphaArrived && dfaArrived && firstNodeArrived == false) {
				if (!(fdfa.charAt(i) + "").equals(";")) {
					String number = fdfa.charAt(i) + "";

					try {
						int secNo = Integer.parseInt(fdfa.charAt(i + 1) + "");
						number += secNo + "";
						i++;
					} catch (NumberFormatException e) {

					}

					startState = number;
					if ((fdfa.charAt(i + 1) + "").equals("#")) {
						firstNodeArrived = true;
						i++;
					}

				}
			} else {
				if (!(fdfa.charAt(i) + "").equals(";")) {
					String number = fdfa.charAt(i) + "";

					if (i + 1 < fdfa.length()) {
						try {

							int secNo = Integer.parseInt(fdfa.charAt(i + 1) + "");
							number += secNo + "";
							i++;
						} catch (NumberFormatException e) {

						}
					}

					acceptStates.add(number);

				}
			}
		}
	}

	public Boolean isItAccept(String node) {
		for (int i = 0; i < acceptStates.size(); i++) {
			if (acceptStates.get(i).equals(node))
				return true;
		}
		return false;
	}

}
