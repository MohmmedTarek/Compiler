package csen1002.main.task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Write your info here
 * 
 * @name Mohamed Tarek
 * @id 49-12309
 * @labNumber 20
 */

public class CfgLeftRecElim {

	HashMap<String, ArrayList<String>> myRules = new LinkedHashMap<String, ArrayList<String>>();
	ArrayList<String> myVariables = new ArrayList<String>();
	HashMap<String, ArrayList<String>> myPrime = new LinkedHashMap<String, ArrayList<String>>();
	String myTerminals = "";

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	public CfgLeftRecElim(String cfg) {

		// skip the variables and terminals parts
		int flage = 0;
		int cfgstart = 0;
		for (int i = 0; i < cfg.length(); i++) {

			if ((cfg.charAt(i) + "").compareTo("A") >= 0 && (cfg.charAt(i) + "").compareTo("Z") <= 0) {
				myVariables.add(cfg.charAt(i) + "");
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
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {

		String finalRules = "";
		String finalVar = "";

		for (int i = 0; i < myVariables.size(); i++) {
			finalVar += myVariables.get(i) + ";";
			ArrayList<String> temp = myRules.get(myVariables.get(i));
			finalRules += myVariables.get(i) + "/";
			for (int j = 0; j < temp.size(); j++) {
				finalRules += temp.get(j) + ",";
			}
			finalRules = finalRules.substring(0, finalRules.length() - 1) + ";";
		}

		for (Map.Entry<String, ArrayList<String>> entry : myPrime.entrySet()) {
			finalVar += entry.getKey() + ";";
			ArrayList<String> temp = entry.getValue();
			finalRules += entry.getKey() + "/";
			for (int j = 0; j < temp.size(); j++) {
				finalRules += temp.get(j) + ",";
			}
			finalRules = finalRules.substring(0, finalRules.length() - 1) + ";";

		}

		finalRules = finalRules.substring(0, finalRules.length() - 1);
		finalVar = finalVar.substring(0, finalVar.length() - 1);

		String result = finalVar + "#" + myTerminals + "#" + finalRules;
		return result;

	}

	/**
	 * Eliminates Left Recursion from the grammar
	 */
	public void eliminateLeftRecursion() {

		for (Map.Entry<String, ArrayList<String>> entry : myRules.entrySet()) {
			elimIndirectLeftRec(entry.getKey(), getMyVarInd(entry.getKey()));

			ArrayList<String> alphaRules = new ArrayList<String>();
			ArrayList<String> petaRules = new ArrayList<String>();

			for (int i = 0; i < entry.getValue().size(); i++) {
				if ((entry.getValue().get(i).charAt(0) + "").equals(entry.getKey())) {

					String rule = entry.getValue().remove(i);
					i--;
					rule = rule.substring(1) + entry.getKey() + "'";
					alphaRules.add(rule);
				} else {
					petaRules.add(entry.getValue().get(i) + entry.getKey() + "'");

				}
			}
			if (!alphaRules.isEmpty()) {
				entry.getValue().clear();
				entry.getValue().addAll(petaRules);

				alphaRules.add("e");
				myPrime.put(entry.getKey() + "'", alphaRules);

			}

		}

	}

	public void elimIndirectLeftRec(String key, int keyIndex) {

		for (int i = 0; i < keyIndex; i++) {
			for (int j = 0; j < myRules.get(key).size(); j++) {

				// if rule start with previous variable
				if ((myRules.get(key).get(j).charAt(0) + "").equals(myVariables.get(i))) {

					ArrayList<String> firstHalfRule = new ArrayList<String>();
					firstHalfRule.addAll(myRules.get(myVariables.get(i)));
					String secondHalfRule = myRules.get(key).get(j).substring(1);

					// Concatenate both rules
					for (int k = 0; k < firstHalfRule.size(); k++) {
						firstHalfRule.set(k, firstHalfRule.get(k).concat(secondHalfRule));
					}

					// first remove the old rule
					myRules.get(key).remove(j);

					// Second add the new rules at the same position
					myRules.get(key).addAll(j, firstHalfRule);
					i = -1;
					j = -1;
					break;
				}
			}
		}
	}

	public int getMyVarInd(String key) {
		for (int i = 0; i < myVariables.size(); i++) {
			if (myVariables.get(i).equals(key))
				return i;
		}
		return -1;
	}


}
