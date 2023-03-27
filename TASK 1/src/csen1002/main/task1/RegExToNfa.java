package csen1002.main.task1;

import java.util.LinkedList;
import java.util.Stack;
import java.util.Collections;



public class RegExToNfa {

	Stack<GroupOfStates> memory = new Stack<>();
	int maxState = -1;
	String alph = "";
	Boolean accessFlg = false;

	/**
	 * Constructs an NFA corresponding to a regular expression based on Thompson's
	 * construction
	 * 
	 * @param input The alphabet and the regular expression in postfix notation for
	 *              which the NFA is to be constructed
	 */
	public RegExToNfa(String input) {
		for (int i = 0; i < input.length(); i++) {

			if (i != 0 && input.charAt(i - 1) == '#')
				accessFlg = true;
			else if (accessFlg == false)
				alph += input.charAt(i);

			if (accessFlg) {
				if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z') {

					State newState = new State(maxState + 1, input.charAt(i), maxState + 2);
					GroupOfStates newGroup = new GroupOfStates(maxState + 1, maxState + 2);
					newGroup.addState(newState);

					memory.push(newGroup);
					maxState += 2;

				} else if (input.charAt(i) == '*') {
					memory.push(star(memory.pop()));
					maxState += 2;
				} else if (input.charAt(i) == '|') {
					GroupOfStates exp2 = memory.pop();
					GroupOfStates exp1 = memory.pop();
					memory.push(or(exp1, exp2));
					maxState += 2;
				} else if (input.charAt(i) == '.') {
					GroupOfStates exp2 = memory.pop();
					GroupOfStates exp1 = memory.pop();
					memory.push(conc(exp1, exp2));
				}

			}
		}
		

	}

	@Override
	public String toString() {

		GroupOfStates GP = memory.pop();
		GP.sortMyList();

		LinkedList<Integer> numList = new LinkedList<Integer>();
		String num = "";
		String first = "" + GP.getStartGroupOfStates();
		String last = "" + GP.getEndGroupOfStates();
		String NFA = "";

		for (int i = 0; i < GP.size(); i++) {

			NFA += GP.getIndex(i).getStartState() + ",";
			if (!numList.contains(GP.getIndex(i).getStartState())) {
				numList.add(GP.getIndex(i).getStartState());
			}
			NFA += GP.getIndex(i).getcond() + ",";

			NFA += GP.getIndex(i).getEndState() + ";";
			if (!numList.contains(GP.getIndex(i).getEndState())) {
				numList.add(GP.getIndex(i).getEndState());
			}

		}

		Collections.sort(numList);
		for (int n : numList) {
			num += n + ";";
		}

		String result = num.substring(0, num.length() - 1) + "#" + alph + NFA.substring(0, NFA.length() - 1) + "#"
				+ first + "#" + last;

		return result;
	}

	public GroupOfStates star(GroupOfStates expresion) {

		// create new start and accept states
		State newExpSt = new State(expresion.getEndGroupOfStates() + 1, 'e', expresion.getStartGroupOfStates());
		State newExpEd = new State(expresion.getEndGroupOfStates(), 'e', expresion.getEndGroupOfStates() + 2);
		State newExpRep = new State(expresion.getEndGroupOfStates(), 'e', expresion.getStartGroupOfStates());
		State newExpEmt = new State(expresion.getEndGroupOfStates() + 1, 'e', expresion.getEndGroupOfStates() + 2);

		// update the start and accept states variables pointers
		expresion.setStartGroupOfStates(expresion.getEndGroupOfStates() + 1);
		expresion.setEndGroupOfStates(expresion.getEndGroupOfStates() + 2);

		// add those states to the NFA
		expresion.addState(newExpSt);
		expresion.addState(newExpEd);
		expresion.addState(newExpRep);
		expresion.addState(newExpEmt);

		return expresion;

	}

	public GroupOfStates or(GroupOfStates expLeft, GroupOfStates expRight) {
		int newStart;
		if (expLeft.getEndGroupOfStates() < expRight.getEndGroupOfStates()) {
			newStart = expRight.getEndGroupOfStates();
		} else {
			newStart = expLeft.getEndGroupOfStates();
		}
		GroupOfStates G1 = new GroupOfStates(newStart + 1, expLeft.getStartGroupOfStates());
		State S1 = new State(newStart + 1, 'e', expLeft.getStartGroupOfStates());
		G1.addState(S1);

		GroupOfStates G2 = new GroupOfStates(newStart + 1, expRight.getStartGroupOfStates());
		State S2 = new State(newStart + 1, 'e', expRight.getStartGroupOfStates());
		G2.addState(S2);

		GroupOfStates G3 = new GroupOfStates(expLeft.getEndGroupOfStates(), newStart + 2);
		State S3 = new State(expLeft.getEndGroupOfStates(), 'e', newStart + 2);
		G3.addState(S3);

		GroupOfStates G4 = new GroupOfStates(expRight.getEndGroupOfStates(), newStart + 2);
		State S4 = new State(expRight.getEndGroupOfStates(), 'e', newStart + 2);
		G4.addState(S4);

		expLeft.mergeGP(expRight);
		expLeft.mergeGP(G1);
		expLeft.mergeGP(G2);
		expLeft.mergeGP(G3);
		expLeft.mergeGP(G4);
		expLeft.setStartGroupOfStates(newStart + 1);
		expLeft.setEndGroupOfStates(newStart + 2);

		return expLeft;
	}

	public GroupOfStates conc(GroupOfStates expLeft, GroupOfStates expRight) {
		if (expLeft.getEndGroupOfStates() < expRight.getStartGroupOfStates()) {

			expRight.searchAndRep(expLeft.getEndGroupOfStates());

			expLeft.mergeGP(expRight);

			expLeft.setEndGroupOfStates(expRight.getEndGroupOfStates());

			return expLeft;
		} else {
			expLeft.searchAndRep(expRight.getEndGroupOfStates());

			expRight.mergeGP(expLeft);

			expRight.setEndGroupOfStates(expLeft.getEndGroupOfStates());

			return expRight;
		}

	}


}

class State implements Comparable<State> {

	private int startState;
	private int endState;
	private char cond;

	State(int startState, char cond, int endState) {
		this.startState = startState;
		this.cond = cond;
		this.endState = endState;
	}

	public int getStartState() {
		return startState;
	}

	public int getEndState() {
		return endState;
	}

	public char getcond() {
		return cond;
	}

	public void setStartState(int startState) {
		this.startState = startState;
	}

	public void Display() {
		System.out.println(startState + " ," + cond + " ," + endState);
	}

	@Override
	public int compareTo(State babyState) {

		if (this.startState - babyState.startState != 0)
			return this.startState - babyState.startState; // Different --> "safe side" return the smallest one

		else {
			// the first elements are the same
			String myCond = "" + this.cond;
			String babyCond = "" + babyState.cond;

			if (myCond.compareTo(babyCond) != 0)
				return myCond.compareTo(babyCond); // Different --> "safe side" return the smallest one
			else 
				// the second elements are the same
				return this.endState - babyState.endState; // return the smallest one

		}
	}
}

class GroupOfStates {
	private int startGroupOfStates;
	private int endGroupOfStates;
	private LinkedList<State> NFA_list = new LinkedList<State>();

	GroupOfStates(int startGroupOfStates, int endGroupOfStates) {
		this.setStartGroupOfStates(startGroupOfStates);
		this.setEndGroupOfStates(endGroupOfStates);
	}

	public void addState(State state) {
		NFA_list.add(state);
	}

	public void searchAndRep(int newS) {
		for (int i = 0; i < NFA_list.size(); i++) {
			if (NFA_list.get(i).getStartState() == startGroupOfStates) {
				NFA_list.get(i).setStartState(newS);
			}
		}
	}

	public void mergeGP(GroupOfStates list) {
		for (int i = 0; i < list.size(); i++) {
			NFA_list.add(list.getIndex(i));
		}
	}

	public int size() {
		return NFA_list.size();
	}

	public State getIndex(int i) {
		return NFA_list.get(i);
	}

	public void sortMyList() {
		Collections.sort(NFA_list);
	}

	// ** getter and setter for the private construction of the class **
	public int getStartGroupOfStates() {
		return startGroupOfStates;
	}

	public void setStartGroupOfStates(int startGroupOfStates) {
		this.startGroupOfStates = startGroupOfStates;
	}

	public int getEndGroupOfStates() {
		return endGroupOfStates;
	}

	public void setEndGroupOfStates(int endGroupOfStates) {
		this.endGroupOfStates = endGroupOfStates;
	}

}
