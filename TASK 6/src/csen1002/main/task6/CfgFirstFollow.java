package csen1002.main.task6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Write your info here
 * 
 * @name Mohamed Tarek
 * @id 49-12309
 * @labNumber 20
 */

public class CfgFirstFollow {

	HashMap<String, ArrayList<String>> myRules = new LinkedHashMap<String, ArrayList<String>>();
	ArrayList<String> myVariables = new ArrayList<String>();
	String myTerminals = "";
	HashMap<String, String> myFirst = new LinkedHashMap<String, String>();
	ArrayList<String> myEpsilonCount = new ArrayList<String>();
	HashMap<String, String> myFollow = new LinkedHashMap<String, String>();
	HashMap<String, ArrayList<String>> followOfFollow = new HashMap<String, ArrayList<String>>();
	HashMap<String, ArrayList<String>> firstOfFirst = new HashMap<String, ArrayList<String>>();
	HashSet<String> noEpsilon = new HashSet<String>();

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	public CfgFirstFollow(String cfg) {

		// Eliminate the epsilon and unit rule
		CfgEpsUnitElim EpsUnitElim = new CfgEpsUnitElim(cfg);
		EpsUnitElim.eliminateEpsilonRules();
		myEpsilonCount = EpsUnitElim.dircEpsilonCont;

		// skip the variables and terminals parts
		int flage = 0;
		int cfgstart = 0;
		for (int i = 0; i < cfg.length(); i++) {

			if ((cfg.charAt(i) + "").compareTo("A") >= 0 && (cfg.charAt(i) + "").compareTo("Z") <= 0) {
				if ((cfg.charAt(i + 1) + "").compareTo("'") == 0) {
					myVariables.add(cfg.charAt(i) + "'");
				} else {
					myVariables.add(cfg.charAt(i) + "");
				}

			}

			if ((cfg.charAt(i) + "").compareTo("a") >= 0 && (cfg.charAt(i) + "").compareTo("z") <= 0) {
				myTerminals += cfg.charAt(i) + ";";
			}

			if ((cfg.charAt(i) + "").equals("#"))
				flage++;
			if (flage == 2) {
				cfgstart = i + 1;
				break;
			}
		}

		myTerminals = myTerminals.substring(0, myTerminals.length() - 1);

		// got array of string that contains the sets and the first sets contain the
		// variables of the sets

		String input = cfg.substring(cfgstart, cfg.length());
		String[] parts = input.split(";");

		for (int i = 0; i < parts.length; i++) {

			// The first subPart is the key
			String[] subParts = parts[i].split("/");
			String Key = subParts[0];

			// the second subPart is its sets
			ArrayList<String> sets = new ArrayList<String>(Arrays.asList(subParts[1].split(",")));

			// add the hash map
			myRules.put(Key, sets);
		}
	}

	/**
	 * Calculates the First Set of each variable in the CFG.
	 * 
	 * @return A string representation of the First of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String first() {

		// loop on all variables to get its FIRST
		for (int i = 0; i < myVariables.size(); i++) {

			String var = myVariables.get(i);

			// get the FIRST of this variable and sort it
			String firstList = firstHelper(myRules.get(var), var);

			char[] tempList = firstList.toCharArray();
			Arrays.sort(tempList);
			firstList = new String(tempList);

			// add the FIRST list to its variable
			myFirst.put(var, firstList);

		}

		firstOfFirst();

		// remove the extra "epsilon" from its non-terminals
		for (String var : myVariables) {
			if (!myEpsilonCount.contains(var)) {
				String first = myFirst.get(var);
				first = first.replace("e", "");
				myFirst.put(var, first);
			}
		}

		// print the FIRST LIST
		String finalFirst = "";

		for (int i = 0; i < myVariables.size(); i++) {
			if (!myVariables.get(i).contains("'"))
				finalFirst += myVariables.get(i) + "/" + myFirst.get(myVariables.get(i)) + ";";
		}

		finalFirst = finalFirst.substring(0, finalFirst.length() - 1);
		return finalFirst;

	}

	/**
	 * Calculates the Follow Set of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String follow() {
		first();

		// loop on all variables to get its FOLLOW
		for (int i = 0; i < myVariables.size(); i++) {

			String var = myVariables.get(i);

			if (!var.contains("'")) {
				// get the FOLLOW of this variable and sort it
				String followList = followHelper(var);

				if (i == 0)
					followList += "$";

				char[] tempList = followList.toCharArray();
				Arrays.sort(tempList);
				followList = new String(tempList);

				// add the FOLLOW list to its variable
				myFollow.put(var, followList);
			}
		}

		followOfFollow();

		// print the FOLLOW LIST
		String finalFollow = "";

		for (int i = 0; i < myVariables.size(); i++) {
			if (!myVariables.get(i).contains("'"))
				finalFollow += myVariables.get(i) + "/" + myFollow.get(myVariables.get(i)) + ";";
		}

		finalFollow = finalFollow.substring(0, finalFollow.length() - 1);
		return finalFollow;
	}

	public String firstHelper(ArrayList<String> rules, String myVariable) {

		String result = "";

		// loop on the rule of this variable and get its FIRST
		for (int i = 0; i < rules.size(); i++) {
			String var = rules.get(i);

			// for any terminal its FIRST is the same
			if (var.charAt(0) >= 'a' && var.charAt(0) <= 'z') {
				if (!result.contains(String.valueOf(var.charAt(0))))
					result += var.charAt(0) + "";
			}

			// the FIRST is variable
			else if (var.charAt(0) >= 'A' && var.charAt(0) <= 'Z') {

				if (myFirst.containsKey(myVariable)) {
					String temp = myFirst.get(myVariable);

					// Add only non duplicate terminals
					for (Character c : temp.toCharArray()) {
						if (!result.contains(String.valueOf(c))) {
							result += c;
						}
					}
				}
				ArrayList<String> boxTemp = new ArrayList<String>();
				if (firstOfFirst.containsKey(myVariable)) {
					boxTemp.addAll(firstOfFirst.get(myVariable));
				}
				boxTemp.add(var.charAt(0) + "");

				firstOfFirst.put(myVariable, boxTemp);

				// add the "epsilon" to its non-terminals
				if (myEpsilonCount.contains(var.charAt(0) + "") && rules.get(i).length() > 1) {
					ArrayList<String> box = new ArrayList<String>();
					String newRule = rules.get(i).substring(1, rules.get(i).length());
					box.add(newRule);
					String temp = firstHelper(box, myVariable);

					// eliminate the extra epsilon if terminal is found
					if (newRule.charAt(0) >= 'a' && newRule.charAt(0) <= 'z')
						noEpsilon.add(myVariable);

					// Add only non duplicate terminals
					for (Character c : temp.toCharArray()) {
						if (!result.contains(String.valueOf(c))) {
							result += c;
						}
					}
				}

			}
		}

		return result;
	}

	public void firstOfFirst() {

		boolean change = true;

		while (change) {
			change = false;
			for (Map.Entry<String, ArrayList<String>> entry : firstOfFirst.entrySet()) {

				// Add the FIRST set to this variable
				for (int i = 0; i < entry.getValue().size(); i++) {

					String rule = myFirst.get(entry.getValue().get(i));
					String acceptRule = myFirst.get(entry.getKey());

					if (rule != null) {

						// Add only non duplicate terminals
						for (Character c : rule.toCharArray()) {
							if (!acceptRule.contains(String.valueOf(c))) {
								acceptRule += c;
								change = true;
							}
						}

						// Sort the terminals
						char[] acceptRuleList = acceptRule.toCharArray();
						Arrays.sort(acceptRuleList);
						acceptRule = new String(acceptRuleList);

						myFirst.put(entry.getKey(), acceptRule);
					}
				}
			}

		}

	}

	public String followHelper(String myVariable) {

		String result = "";

		for (Map.Entry<String, ArrayList<String>> entry : myRules.entrySet()) {

			// loop on each rule to find myVariable and get its FOLLOW
			for (int i = 0; i < entry.getValue().size(); i++) {

				String rule = entry.getValue().get(i);
				if (rule.contains(myVariable)) {

					ArrayList<Integer> pos = getMyVarPos(myVariable, rule);

					for (int j = 0; j < pos.size(); j++) {

						// The FOLLOW of myVariable is the FIRST of the second letter
						if (pos.get(j) != rule.length() - 1) {

							String nextLetter = rule.charAt(pos.get(j) + 1) + "";

							// the FIRST of nextLetter is terminal
							if (nextLetter.charAt(0) >= 'a' && nextLetter.charAt(0) <= 'z') {

								// add terminals to the result if it doesn't contain it
								for (Character c : nextLetter.toCharArray()) {
									if (!result.contains(String.valueOf(c)))
										result += c + "";
								}
							}

							// the FIRST of nextLetter is non-terminal
							else if (nextLetter.charAt(0) >= 'A' && nextLetter.charAt(0) <= 'Z') {

								String firstSet = myFirst.get(nextLetter);

								// the FIRST set contain epsilon so the FOLLOW of myVariable is equal to the
								// FOLLOW of next
								if (firstSet.contains("e")) {
									String newRule = rule.substring(0, pos.get(j) + 2);
									String temp = "";
									if (firstSet.length() - 1 > 1) {

										newRule += rule.substring(pos.get(j) + 2, rule.length());
										temp = followHandel(entry.getKey(), myVariable, newRule, pos.get(j));

									}
									firstSet = firstSet.replace("e", "");
									temp += firstSet;

									// add terminals to the result if it doesn't contain it
									for (Character c : temp.toCharArray()) {
										if (!result.contains(String.valueOf(c)))
											result += c + "";
									}

								}

								// the FIRST set doesn't contain epsilon so add it to the FOLLOW of myVariable
								else {

									// add terminals to the result if it doesn't contain it
									for (Character c : firstSet.toCharArray()) {
										if (!result.contains(String.valueOf(c)))
											result += c + "";
									}
								}

							}

						}

						// The FOLLOW of myVariable is the FOLLOW of the variable of the production
						else if (pos.get(j) == rule.length() - 1 && myVariable.compareTo(entry.getKey()) != 0) {

							// Add the FOLLOW set to the FOLLOW of the variable to handle any update in the
							// set later
							ArrayList<String> temp = new ArrayList<String>();
							if (followOfFollow.get(myVariable) != null)
								temp = followOfFollow.get(myVariable);

							temp.add(entry.getKey());

							HashSet<String> TempCont = new HashSet<>(temp);
							temp = new ArrayList<>(TempCont);

							followOfFollow.put(myVariable, temp);
							if (myFollow.containsKey(entry.getKey())) {

								String acceptRule = myFollow.get(entry.getKey());

								for (Character c : acceptRule.toCharArray()) {
									if (!result.contains(String.valueOf(c)))
										result += c;
								}

							}
						}
					}
				}
			}
		}
		return result;
	}

	public String followHandel(String myKey, String var, String rule, int pos) {
		char letter = rule.charAt(pos);
		if ((letter + "").equals(var)) {
			if (pos + 1 < rule.length()) {
				letter = rule.charAt(pos + 1);
			}
		}

		String result = "";

		if (letter >= 'A' && letter <= 'Z') {

			String firstSet = myFirst.get(letter + "");

			// FOLLOW of next have epsilon
			if (firstSet.contains("e")) {

				// The FOLLOW set of the variable equal to the FOLLOW set of the entry
				if (pos == rule.length() - 1) {
					ArrayList<String> temp = new ArrayList<String>();
					if (followOfFollow.get(var) != null)
						temp = followOfFollow.get(var);

					temp.add(myKey);
					HashSet<String> TempCont = new HashSet<>(temp);
					temp = new ArrayList<>(TempCont);

					followOfFollow.put(var, temp);
				}
				// add the FIRST set without epsilon and recursion get the rest FOLLOW set
				else {
					firstSet = firstSet.replace("e", "");
					result += firstSet;
					rule = rule.substring(0, pos) + rule.substring(pos + 1, rule.length());
					result += followHandel(myKey, var, rule, pos);
				}

			}
			// the FIRST set doesn't contain epsilon so add it to the FOLLOW of myVariable
			else {
				result += firstSet;
			}

		}
		// the FIRST set is terminal
		else if (letter >= 'a' && letter <= 'z') {
			result += letter;
		}

		return result;
	}

	public void followOfFollow() {

		boolean change = true;

		while (change) {
			change = false;
			for (Map.Entry<String, ArrayList<String>> entry : followOfFollow.entrySet()) {

				// Add the FOLLOW set to this variable
				for (int i = 0; i < entry.getValue().size(); i++) {

					String rule = myFollow.get(entry.getValue().get(i));
					String acceptRule = myFollow.get(entry.getKey());

					// Add only non duplicate characters
					for (Character c : rule.toCharArray()) {
						if (!acceptRule.contains(String.valueOf(c))) {
							acceptRule += c;
							change = true;
						}
					}

					// Sort the terminals
					char[] acceptRuleList = acceptRule.toCharArray();
					Arrays.sort(acceptRuleList);
					acceptRule = new String(acceptRuleList);

					myFollow.put(entry.getKey(), acceptRule);
				}
			}

		}

	}

	public ArrayList<Integer> getMyVarPos(String var, String rule) {

		ArrayList<Integer> result = new ArrayList<Integer>();
		int len = rule.length();

		for (int i = 0; i < len; i++) {
			char letter = rule.charAt(i);

			if (letter == var.charAt(0)) {
				if (i + 1 == len || i + 1 < len) {
					result.add(i);
				}
			}

		}

		return result;
	}

	public static void main(String[] args) {
		CfgFirstFollow x = new CfgFirstFollow(
				"S;W;B;E;T;V;D#k;p;q;t;w#S/W,pTTtS,qT;W/S,W,e,qD,qETt;B/B,E,ESkVD,TpD,WDED,e,wSpD;E/DwED,S,SBk,VpWtB,W,qTqSD,wST;T/pDk,qWqT,tS;V/S,TDSD,TVpW,WTp,WVTWt,kDVW,q;D/DBSVD,Dw,tTt");
		x.follow();
	}
}

class CfgEpsUnitElim {

	HashMap<String, ArrayList<String>> myList = new HashMap<>();
	ArrayList<String> dircEpsilonCont = new ArrayList<String>();
	ArrayList<String> MyVariables = new ArrayList<String>();
	String myTerminals = "";

	public CfgEpsUnitElim(String cfg) {

		// skip the variables and terminals parts
		int flage = 0;
		int cfgstart = 0;
		for (int i = 0; i < cfg.length(); i++) {

			if ((cfg.charAt(i) + "").compareTo("A") >= 0 && (cfg.charAt(i) + "").compareTo("Z") <= 0) {
				MyVariables.add(cfg.charAt(i) + "");
			}

			if ((cfg.charAt(i) + "").compareTo("a") >= 0 && (cfg.charAt(i) + "").compareTo("z") <= 0) {
				myTerminals += cfg.charAt(i) + ";";
			}

			if ((cfg.charAt(i) + "").equals("#"))
				flage++;
			if (flage == 2) {
				cfgstart = i + 1;
				break;
			}
		}

		myTerminals = myTerminals.substring(0, myTerminals.length() - 1);

		// got array of string that contains the sets and the first sets contain the
		// variables of the sets

		String input = cfg.substring(cfgstart, cfg.length());
		String[] parts = input.split(";");

		for (int i = 0; i < parts.length; i++) {

			// The first subPart is the key
			String[] subParts = parts[i].split("/");
			String Key = subParts[0];

			// the second subPart is its sets
			ArrayList<String> sets = new ArrayList<String>(Arrays.asList(subParts[1].split(",")));

			// add the hash map
			myList.put(Key, sets);
		}

	}

	public void eliminateEpsilonRules() {
		getDircEpsilonCont();

		for (int i = 0; i < dircEpsilonCont.size(); i++) {
			String var = dircEpsilonCont.get(i);

			deleteMyEpsilon(var);

			for (Map.Entry<String, ArrayList<String>> entry : myList.entrySet()) {

				for (int j = 0; j < entry.getValue().size(); j++) {

					// means the first set contain my var so we will add to it "e"
					if (entry.getValue().get(j).indexOf(var.charAt(0)) != -1) {

						// remove the letter by "e" and concatenate the string
						if (entry.getValue().get(j).length() > 1) {

							String temp = entry.getValue().get(j);
							Boolean foundVar = false;
							for (int x = 0; x < temp.length(); x++) {

								if ((temp.charAt(x) + "").equals(var)) {
									foundVar = true;
									StringBuilder sb = new StringBuilder(temp);
									sb.deleteCharAt(x);
									entry.getValue().add(sb.toString());
								}
							}
							// add terminal if it contain more than one variable equal to var
							if (foundVar == true) {
								StringBuilder sb = new StringBuilder(temp);
								for (int t = 0; t < sb.length(); t++) {
									if ((sb.charAt(t) + "").equals(var)) {
										sb.deleteCharAt(t);
									}
								}
								entry.getValue().add(sb.toString());

							}
						} else {
							setDircEpsilonCont(entry.getKey());
						}
					}

					// remove duplicate sets
					Set<String> tempSet = new LinkedHashSet<>(entry.getValue());
					ArrayList<String> tempList = new ArrayList<>(tempSet);
					entry.setValue(tempList);

				}
			}
		}
	}

	public void getDircEpsilonCont() {
		for (String key : myList.keySet()) {

			for (int i = 0; i < myList.get(key).size(); i++) {
				if (myList.get(key).get(i).equals("e")) {
					dircEpsilonCont.add(key);
				}
			}
		}
	}

	public void setDircEpsilonCont(String value) {

		Boolean found = false;
		for (int i = 0; i < dircEpsilonCont.size(); i++) {
			if (dircEpsilonCont.get(i).equals(value)) {
				found = true;
				break;
			}
		}
		if (found == false)
			dircEpsilonCont.add(value);
	}

	public void deleteMyEpsilon(String var) {
		ArrayList<String> subParts = myList.get(var);

		if (subParts.contains("e")) {
			subParts.remove("e");
			myList.put(var, subParts);
		}

	}
}
