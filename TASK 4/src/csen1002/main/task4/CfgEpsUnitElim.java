package csen1002.main.task4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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

public class CfgEpsUnitElim {

	HashMap<String, ArrayList<String>> myList = new HashMap<>();
	ArrayList<String> dircEpsilonCont = new ArrayList<String>();
	ArrayList<String> MyVariables = new ArrayList<String>();
	String myTerminals = "";

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
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

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {

		for (Map.Entry<String, ArrayList<String>> entry : myList.entrySet()) {
			Collections.sort(entry.getValue(), new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					return s1.compareTo(s2);
				}
			});
		}

		String finalCFG = "";
		String finalVar = "";
		for (int i = 0; i < MyVariables.size(); i++) {
			finalVar += MyVariables.get(i) + ";";
			ArrayList<String> temp = myList.get(MyVariables.get(i));
			finalCFG += MyVariables.get(i) + "/";
			for (int j = 0; j < temp.size(); j++) {
				finalCFG += temp.get(j) + ",";
			}
			finalCFG = finalCFG.substring(0, finalCFG.length() - 1) + ";";
		}
		finalCFG = finalCFG.substring(0, finalCFG.length() - 1);
		finalVar = finalVar.substring(0, finalVar.length() - 1);

		String result = finalVar + "#" + myTerminals + "#" + finalCFG;
		return result;
	}

	/**
	 * Eliminates Epsilon Rules from the grammar
	 */
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

	/**
	 * Eliminates Unit Rules from the grammar
	 */
	public void eliminateUnitRules() {

		Boolean updated = true;

		while (updated == true) {
			updated = false;
			for (Map.Entry<String, ArrayList<String>> entry : myList.entrySet()) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).length() == 1
							&& !(entry.getValue().get(i).charAt(0) + "").equals("e")) {

						if (Character.isUpperCase(entry.getValue().get(i).charAt(0))) {
							String Key = entry.getKey();
							ArrayList<String> newList = myList.get(entry.getValue().get(i));
							entry.getValue().remove(i);

							ArrayList<String> conc = myList.get(Key);
							conc.addAll(newList);

							// remove duplicate sets
							Set<String> tempSet = new LinkedHashSet<>(conc);
							ArrayList<String> tempList = new ArrayList<>(tempSet);

							myList.put(Key, tempList);
							deleteMyVariableLoop();
							updated = true;
							
						}
					}
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

	public void deleteMyVariableLoop() {
		for (int i = 0; i < MyVariables.size(); i++) {

			ArrayList<String> subParts = myList.get(MyVariables.get(i));

			if (subParts.contains(MyVariables.get(i))) {
				subParts.remove(MyVariables.get(i));
				myList.put(MyVariables.get(i), subParts);
			}
		}
	}

//	public static void main(String[] args) {
//		CfgEpsUnitElim x = new CfgEpsUnitElim(
//				"S;M;P;G;B;I;X#b;j;o;p#S/oPBbM,pMXBp;M/GjMX,I,oMB;P/B,G,ISIbI,M,XPo,jIbI;G/SSBI,o,pISM;B/GGIS,MMIBb,P,SGjG,j;I/BpPo,M;X/GX,MPP");
//
//		x.eliminateUnitRules();
//		for (String key : x.myList.keySet()) {
//			System.out.print(key + ": ");
//			// loop through the ArrayList for each key
//			for (String value : x.myList.get(key)) {
//				System.out.print(value + " | ");
//			}
//			System.out.println();
//		}
//		System.out.println(x.MyVariables.toString());
//		System.out.println(x.myTerminals);
//		System.out.println(x.toString());
//	}
}
