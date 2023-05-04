package csen1002.main.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

/**
 * Write your info here
 * 
 * @name Mohamed Tarek
 * @id 49-12309
 * @labNumber 20
 */

public class NfaToDfa {

	Boolean deadArrived = false;
	Boolean gotMyStart = true;

	ArrayList<Integer> myNumbers = new ArrayList<Integer>();
	ArrayList<Character> myAlpha = new ArrayList<Character>();
	ArrayList<String> nfaList = new ArrayList<String>();
	ArrayList<Integer> acceptNode = new ArrayList<Integer>();
	int firstNode;

	LinkedList<State> Memory = new LinkedList<State>();
	LinkedList<ArrayList<Integer>> AllStates = new LinkedList<ArrayList<Integer>>();
	LinkedList<ArrayList<Integer>> finalStart = new LinkedList<ArrayList<Integer>>();
	LinkedList<ArrayList<Integer>> finalEnd = new LinkedList<ArrayList<Integer>>();
	Stack<ArrayList<Integer>> WAITTING = new Stack<ArrayList<Integer>>();

	/**
	 * Constructs a DFA corresponding to an NFA
	 * 
	 * @param input A formatted string representation of the NFA for which an
	 *              equivalent DFA is to be constructed. The string representation
	 *              follows the one in the task description
	 */
	public NfaToDfa(String input) {

		extractAllComp(input);
		ClosureTable eTable = new ClosureTable();
		eTable.build(nfaList, myNumbers);

		if (Memory.isEmpty()) {
			ArrayList<Integer> babyState = new ArrayList<Integer>();
			for (int c = 0; c < eTable.getMyTable().size(); c++) {
				if (eTable.getMyTable().get(c).getState() == firstNode) {
					babyState = eTable.getMyTable().get(c).getItsClosure();
				}
			}
			addToFinalStart(babyState);
			WAITTING.add(babyState);
		}

		while (!WAITTING.isEmpty()) {
			ArrayList<Integer> node = WAITTING.pop();

			for (int i = 0; i < myAlpha.size(); i++) {
				Boolean foundAlph = false;
				Boolean foundAccept = false;
				Boolean foundFisrt = false;

				ArrayList<Integer> secondNode = new ArrayList<Integer>();

				for (int j = 0; j < node.size(); j++) {
					for (int k = 0; k < nfaList.size(); k += 3) {

						if (node.get(j) >= Integer.parseInt(nfaList.get(k))) {

							if (node.get(j) == Integer.parseInt(nfaList.get(k))
									&& myAlpha.get(i).equals(nfaList.get(k + 1).charAt(0))) {
								foundAlph = true;
								secondNode.add(Integer.parseInt(nfaList.get(k + 2)));

								if (node.get(j) == firstNode) {
									foundFisrt = true;
								}
							}

						} else {
							break;
						}

					}

				}

				// no alpha(i) for this node
				if (foundAlph == false) {
					createDeadState();
					ArrayList<Integer> dead = new ArrayList<Integer>();
					dead.add(-1);
					State n = new State(node, myAlpha.get(i), dead);

					for (int f = 0; f < node.size(); f++) {
						for (int g = 0; g < acceptNode.size(); g++) {
							if (node.get(f) == acceptNode.get(g)) {
								foundAccept = true;
								break;
							}
						}
					}
					n.setItsAccept(foundAccept);
					n.setItsStart(foundFisrt);
					Memory.add(n);
				}
				// found alpha(i) for this node
				else {
					ArrayList<Integer> result = new ArrayList<Integer>();
					for (int l = 0; l < secondNode.size(); l++) {

						result.addAll(eTable.getMyClosureOfState(secondNode.get(l)));

						// remove duplicate
						Set<Integer> temp = new LinkedHashSet<>();
						temp.addAll(result);
						result.clear();
						result.addAll(temp);

					}
					// add the state to the memory
					Collections.sort(result);
					Collections.sort(node);
					State s = new State(node, myAlpha.get(i), result);

					for (int f = 0; f < node.size(); f++) {
						for (int g = 0; g < acceptNode.size(); g++) {
							if (node.get(f) == acceptNode.get(g)) {
								foundAccept = true;
								break;
							}
						}
					}
					s.setItsStart(foundFisrt);
					s.setItsAccept(foundAccept);
					Memory.add(s);

					// add this node that doesn't have routes for all alpha to WAITTING
					Boolean alreadyfound = false;
					for (int q = 0; q < Memory.size(); q++) {
						if (node.equals(result)) {
							alreadyfound = true;
							break;
						} else if (Memory.get(q).getStartState().equals(result)) {
							alreadyfound = true;
							break;
						}

					}
					if (alreadyfound == false) {
						Boolean trial = false;
						for (int p = 0; p < WAITTING.size(); p++) {
							if (WAITTING.get(p).equals(result))
								trial = true;
						}
						if (trial == false)
							WAITTING.push(result);
					}

				}
			}

		}

		Collections.sort(Memory);
	}

	public void createDeadState() {
		if (deadArrived == false) {

			ArrayList<Integer> badOne = new ArrayList<Integer>();
			badOne.add(-1);

			for (int i = 0; i < myAlpha.size(); i++) {
				State deadState = new State(badOne, myAlpha.get(i), badOne);
				Memory.add(deadState);
			}
			deadArrived = true;

		}

	}

	public void addToFinalEnd(ArrayList<Integer> n) {
		Boolean found = false;
		Collections.sort(n);
		for (int i = 0; i < finalEnd.size(); i++) {
			Collections.sort(finalEnd.get(i));
			if (finalEnd.get(i).equals(n))
				found = true;
		}
		if (found == false)
			finalEnd.add(n);

	}

	public void addToFinalStart(ArrayList<Integer> n) {
		Boolean found = false;
		Collections.sort(n);
		for (int i = 0; i < finalStart.size(); i++) {
			Collections.sort(finalStart.get(i));
			if (finalStart.get(i).equals(n))
				found = true;
		}
		if (found == false)
			finalStart.add(n);

	}

	public void addToAllStates(ArrayList<Integer> n) {
		Boolean found = false;
		Collections.sort(n);
		for (int i = 0; i < AllStates.size(); i++) {
			Collections.sort(AllStates.get(i));
			if (AllStates.get(i).equals(n))
				found = true;
		}
		if (found == false)
			AllStates.add(n);

	}

	public void extractAllComp(String input) {

		Boolean statesArrived = false;
		Boolean alphaArrived = false;
		Boolean nfaArrived = false;
		Boolean firstNodeArrived = false;

		for (int i = 0; i < input.length(); i++) {

			if (statesArrived == false) {
				if (!(input.charAt(i) + "").equals(";")) {
					String number = input.charAt(i) + "";

					try {
						int secNo = Integer.parseInt(input.charAt(i + 1) + "");
						number += secNo + "";
						i++;
					} catch (NumberFormatException e) {

					}
					myNumbers.add(Integer.parseInt(number));
					if ((input.charAt(i + 1) + "").equals("#")) {
						statesArrived = true;
						i++;
					}

				}
			} else if (statesArrived && alphaArrived == false) {

				if (!(input.charAt(i) + "").equals(";")) {
					char cond = input.charAt(i);

					myAlpha.add(cond);
					if ((input.charAt(i + 1) + "").equals("#")) {
						alphaArrived = true;
						i++;
					}

				}
			} else if (statesArrived && alphaArrived && nfaArrived == false) {
				if (!(input.charAt(i) + "").equals(";") && !(input.charAt(i) + "").equals(",")) {
					String st = input.charAt(i) + "";

					try {
						int secNo = Integer.parseInt(input.charAt(i + 1) + "");
						st += secNo + "";
						i++;
					} catch (NumberFormatException e) {

					}

					nfaList.add(st);
					if ((input.charAt(i + 1) + "").equals("#")) {
						nfaArrived = true;
						i++;
					}

				}
			} else if (statesArrived && alphaArrived && nfaArrived && firstNodeArrived == false) {
				if (!(input.charAt(i) + "").equals(";")) {
					String number = input.charAt(i) + "";

					try {
						int secNo = Integer.parseInt(input.charAt(i + 1) + "");
						number += secNo + "";
						i++;
					} catch (NumberFormatException e) {

					}

					firstNode = Integer.parseInt(number);
					if ((input.charAt(i + 1) + "").equals("#")) {
						firstNodeArrived = true;
						i++;
					}

				}
			} else {
				if (!(input.charAt(i) + "").equals(";")) {
					String number = input.charAt(i) + "";

					if (i + 1 < input.length()) {
						try {

							int secNo = Integer.parseInt(input.charAt(i + 1) + "");
							number += secNo + "";
							i++;
						} catch (NumberFormatException e) {

						}
					}

					acceptNode.add(Integer.parseInt(number));

				}
			}
		}
	}

	/**
	 * @return Returns a formatted string representation of the DFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {

		String dfa = "";
		String Alpha = "";
		String myStates = "";
		String first = "";
		String myAcc = "";

		for (int i = 0; i < Memory.size(); i++) {
			addToAllStates(Memory.get(i).getStartState());

			if (Memory.get(i).getItsAccept())
				addToFinalEnd(Memory.get(i).getStartState());
		}

		// get the start state of the DFA
		for (int z = 0; z < finalStart.size(); z++) {
			for (int e = 0; e < finalStart.get(z).size(); e++) {
				first += finalStart.get(z).get(e) + "/";
			}
			first = first.substring(0, first.length() - 1) + ";";
		}
		first = first.substring(0, first.length() - 1);

		// get the accept states of the DFA
		for (int z = 0; z < finalEnd.size(); z++) {
			for (int e = 0; e < finalEnd.get(z).size(); e++) {
				myAcc += finalEnd.get(z).get(e) + "/";
			}
			myAcc = myAcc.substring(0, myAcc.length() - 1) + ";";

		}

		// get the alpha of the DFA
		for (int z = 0; z < myAlpha.size(); z++) {
			Alpha += myAlpha.get(z) + ";";
		}
		Alpha = Alpha.substring(0, Alpha.length() - 1);

		// get the states numbers of the DFA
		for (int z = 0; z < AllStates.size(); z++) {
			for (int e = 0; e < AllStates.get(z).size(); e++) {
				myStates += AllStates.get(z).get(e) + "/";
			}
			myStates = myStates.substring(0, myStates.length() - 1) + ";";
		}
		myStates = myStates.substring(0, myStates.length() - 1);

		// get the DFA
		for (int z = 0; z < Memory.size(); z++) {
			dfa += Memory.get(z).Display();
		}
		dfa = dfa.substring(0, dfa.length() - 1);

		String result = myStates + "#" + Alpha + "#" + dfa + "#" + first + "#" + myAcc;
		result = result.substring(0, result.length() - 1);

		return result;
	}
}

class State implements Comparable<State> {
	private ArrayList<Integer> startState = new ArrayList<Integer>();
	private char cond;
	private ArrayList<Integer> endState = new ArrayList<Integer>();
	private Boolean itsAccept;
	private Boolean itsStart;

	public State(ArrayList<Integer> startState, char cond, ArrayList<Integer> endState) {
		this.startState = startState;
		this.cond = cond;
		this.endState = endState;
		this.setItsAccept(false);
		this.setItsAccept(false);
		Collections.sort(this.startState);
		Collections.sort(this.endState);
	}

	public ArrayList<Integer> getStartState() {
		return startState;
	}

	public char getCond() {
		return this.cond;
	}

	public ArrayList<Integer> getEndState() {
		return endState;
	}

	@Override
	public int compareTo(State babyState) {

		int len = 0;
		if (this.startState.size() < babyState.startState.size())
			len = this.startState.size();
		else
			len = babyState.startState.size();

		// same elements but different size
		for (int i = 0; i < len; i++) {
			if (this.startState.get(i) - babyState.startState.get(i) != 0) {
				return this.startState.get(i) - babyState.startState.get(i);// Different --> "safe side" return the
																			// smallest one
			}
		}

		// Different size for the first elements
		if (this.startState.size() - babyState.startState.size() != 0) {
			return this.startState.size() - babyState.startState.size(); // Different --> "safe side" return the
																			// smallest one
		}
		// the first elements are the same
		else if (this.startState.equals(babyState.startState)) {
			String myCond = "" + this.cond;
			String babyCond = "" + babyState.cond;
			if (myCond.compareTo(babyCond) != 0) {
				return myCond.compareTo(babyCond); // Different --> "safe side" return the smallest one
			} else

			// the second elements are the same
			if (this.endState.size() < babyState.endState.size())
				len = this.endState.size();
			else
				len = babyState.endState.size();

			for (int i = 0; i < len; i++) {
				if (this.endState.get(i) - babyState.endState.get(i) != 0) {
					return this.endState.get(i) - babyState.endState.get(i);// Different --> "safe side" return the
																			// smallest one
				}
			}

			// Different size for the last elements
			if (this.endState.size() - babyState.endState.size() != 0) {
				return this.endState.size() - babyState.endState.size(); // return the smallest one
			}

		}

		return 0;
	}

	public Boolean getItsAccept() {
		return itsAccept;
	}

	public void setItsAccept(Boolean itsAccept) {
		this.itsAccept = itsAccept;
	}

	public Boolean getItsStart() {
		return itsStart;
	}

	public void setItsStart(Boolean itsStart) {
		this.itsStart = itsStart;
	}

	public String Display() {
		String f = "";
		for (int i = 0; i < this.startState.size(); i++) {
			f += this.startState.get(i) + "";
			if (this.startState.size() != i + 1) {
				f += "/";
			} else {
				f += ",";
			}
		}
		f += this.cond + ",";

		for (int i = 0; i < this.endState.size(); i++) {
			f += this.endState.get(i) + "";
			if (this.endState.size() != i + 1) {
				f += "/";
			} else {
				f += ";";
			}
		}
		return f;
	}

}

class ClosureTable {

	private ArrayList<ClosureState> myTable = new ArrayList<ClosureState>();

	public ArrayList<ClosureState> build(ArrayList<String> nfa, ArrayList<Integer> nfaStates) {
		Boolean edit = true;

		// create e-closure for each state to itself
		for (int i = 0; i < nfaStates.size(); i++) {
			ArrayList<Integer> ints = new ArrayList<Integer>();
			ints.add(nfaStates.get(i));
			ClosureState cs = new ClosureState(nfaStates.get(i), ints);
			myTable.add(cs);
		}

		// add for direct e-closure for each state
		for (int i = 0; i < nfa.size(); i += 3) {

			if (nfa.get(i + 1).equals("e")) {
				newClosureToState(Integer.parseInt("" + nfa.get(i)), Integer.parseInt("" + nfa.get(i + 2)));

			}
		}
		// union each state with its closure
		while (edit == true) {
			edit = false;
			// for state
			for (int i = 0; i < myTable.size(); i++) {

				// for e-closure
				for (int j = 0; j < myTable.get(i).getItsClosure().size(); j++) {
					int loc = myTable.get(i).getItsClosure().get(j);
					if (loc != myTable.get(i).getState()) {
						ArrayList<Integer> unionClosure = getMyClosureOfState(loc);

						for (int k = 0; k < unionClosure.size(); k++) {
							if (!myTable.get(i).getItsClosure().contains(unionClosure.get(k))) {
								myTable.get(i).addCS(unionClosure.get(k));
								edit = true;
							}

						}
					}
				}
			}
		}

		return myTable;

	}

	public void newClosureToState(int state, int closure) {

		for (int i = 0; i < myTable.size(); i++) {
			if (myTable.get(i).getState() == state) {
				myTable.get(i).addCS(closure);
			}
		}
	}

	public ArrayList<Integer> getMyClosureOfState(int state) {

		for (int i = 0; i < myTable.size(); i++) {
			if (myTable.get(i).getState() == state)
				return myTable.get(i).getItsClosure();
		}
		return null;

	}

	public ArrayList<ClosureState> getMyTable() {
		return this.myTable;
	}
}

class ClosureState {

	private int state;
	private ArrayList<Integer> itsClosure = new ArrayList<Integer>();

	public ClosureState(int state, ArrayList<Integer> itsClosure) {
		this.itsClosure = itsClosure;
		this.state = state;
		Collections.sort(itsClosure);
	}

	public ArrayList<Integer> getItsClosure() {
		return itsClosure;
	}

	public void addCS(int value) {
		itsClosure.add(value);
		Collections.sort(itsClosure);
	}

	public int getState() {
		return this.state;
	}

}
