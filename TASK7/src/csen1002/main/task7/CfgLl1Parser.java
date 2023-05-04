package csen1002.main.task7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Write your info here
 * 
 * @name Mohamed Tarek
 * @id 49-12309
 * @labNumber 20
 */

public class CfgLl1Parser {

	ArrayList<String> myVariables = new ArrayList<String>();
	ArrayList<String> myTerminals = new ArrayList<String>();
	HashMap<String, ArrayList<String>> myRules = new LinkedHashMap<String, ArrayList<String>>();
	HashMap<String, ArrayList<String>> myFirst = new LinkedHashMap<String, ArrayList<String>>();
	HashMap<String, ArrayList<String>> myFollow = new LinkedHashMap<String, ArrayList<String>>();
	HashMap<String, String> parsingTable = new LinkedHashMap<String, String>();
	ArrayList<String> myParser = new ArrayList<String>();

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG, the First sets of
	 *            each right-hand side, and the Follow sets of each variable. The
	 *            string representation follows the one in the task description
	 */
	public CfgLl1Parser(String input) {

		// skip the variables and terminals parts
		boolean flage = false;
		int skip = 0;
		int inputstart = 0;
		int inputend = 0;
		int firststart = 0;
		int firstend = 0;
		int followstart = 0;
		int followend = input.length();
		for (int i = 0; i < input.length(); i++) {

			if ((input.charAt(i) + "").compareTo("A") >= 0 && (input.charAt(i) + "").compareTo("Z") <= 0 && skip < 2) {
				myVariables.add(input.charAt(i) + "");
			}

			if ((input.charAt(i) + "").compareTo("a") >= 0 && (input.charAt(i) + "").compareTo("z") <= 0 && skip < 2) {
				myTerminals.add(input.charAt(i) + "");
			}

			if ((input.charAt(i) + "").equals("#")) {
				if (skip != 0)
					flage = true;
				skip++;
			}
			if (flage == true && inputstart == 0) {
				inputstart = i + 1;
				flage = false;

			} else if (flage == true && inputend == 0) {
				inputend = i - 1;
				firststart = i + 1;
				flage = false;
			} else if (flage == true && firstend == 0) {
				firstend = i - 1;
				followstart = i + 1;
				flage = false;
			}
		}

		// got array of string that contains the sets and the first sets contain the
		// variables of the sets

		String t1 = input.substring(inputstart, inputend + 1);
		String[] parts1 = t1.split(";");

		for (int i = 0; i < parts1.length; i++) {

			// The first subPart is the key
			String[] subParts = parts1[i].split("/");
			String Key = subParts[0];

			// the second subPart is its sets
			ArrayList<String> sets = new ArrayList<String>(Arrays.asList(subParts[1].split(",")));

			// add the hash map
			myRules.put(Key, sets);
		}

		// get the FIRST list and add it
		String t2 = input.substring(firststart, firstend + 1);
		String[] parts2 = t2.split(";");

		for (int i = 0; i < parts2.length; i++) {

			// The first subPart is the key
			String[] subParts = parts2[i].split("/");
			String Key = subParts[0];

			// the second subPart is its sets
			ArrayList<String> sets = new ArrayList<String>(Arrays.asList(subParts[1].split(",")));

			// add the hash map
			myFirst.put(Key, sets);
		}

		// get the FOLLOW list and add it
		String t3 = input.substring(followstart, followend);
		String[] parts3 = t3.split(";");

		for (int i = 0; i < parts3.length; i++) {

			// The first subPart is the key
			String[] subParts = parts3[i].split("/");
			String Key = subParts[0];

			String[] tempArray = subParts[1].split("");

			// the second subPart is its sets
			ArrayList<String> sets = new ArrayList<String>(Arrays.asList(tempArray));

			// add the hash map
			myFollow.put(Key, sets);
		}
	}

	/**
	 * @param input The string to be parsed by the LL(1) CFG.
	 * 
	 * @return A string encoding a left-most derivation.
	 */
	public String parse(String input) {
		genParsingTable();
		letsParse(input);
		String result = "";
		String x = "";
		for (String s : myParser) {
			if (s.contains("$"))
				x = s.replace("$", "");
			else
				x = s;
			result += x + ";";
		}
		result = result.substring(0, result.length() - 1);

		return result;
	}

	public void genParsingTable() {

		for (String variable : myVariables) {
			for (String terminal : myFirst.get(variable)) {

				ArrayList<String> rules = myRules.get(variable);

				for (String rule : rules) {

					// add this rule if its first is terminal and this rule produce the
					// M[variable,terminal]
					if (rule.charAt(0) >= 'a' && rule.charAt(0) <= 'z' && rule.charAt(0) == terminal.charAt(0)
							&& rule.charAt(0) != 'e') {
						String key = variable + terminal;
						parsingTable.put(key, rule);
					}
					// add this rule if its first is variable and this rule produce the
					// M[variable,terminal]
					else if (rule.charAt(0) >= 'A' && rule.charAt(0) <= 'Z') {
						String first = getFirst(rule);

						if (first.equals(terminal)) {
							String key = variable + terminal;
							parsingTable.put(key, rule);
						}
					}

				}

				// if the first of the variable contain epsilon add in all its follow epsilon
				if (terminal.equals("e")) {
					ArrayList<String> followList = myFollow.get(variable);
					for (String f : followList) {
						parsingTable.put(variable + f, "e");
					}
				}
			}

		}
	}

	public void letsParse(String input) {
		int counter = 0;
		input += "$";

		myParser.add("S");
		String stack = "S$";
		for (int i = 0; i < input.length();) {
			char letter = input.charAt(i);
			char c = stack.charAt(0);

			// terminal hits
			if (c == letter && c != '$') {

				// replace the variable by the new rule

				String temp = myParser.get(myParser.size() - 1);
				String temp2 = temp.substring(0, counter) + stack;

				if (!temp.equals(temp2))
					myParser.add(temp.substring(0, counter) + stack);

				counter++;
				stack = stack.substring(1);
				i++;
			}

			// found variable in stack
			else if (c >= 'A' && c <= 'Z' && letter != '$') {

				String rule = parsingTable.get(String.valueOf(c) + String.valueOf(letter));

				// Handle the rule that have more than one terminal
				if (rule == null) {
					for (String key : parsingTable.keySet()) {
						if (key.length() > 2 && key.charAt(0) == c) {
							if (key.contains((letter + ""))) {
								rule = parsingTable.get(key);
								break;
							}
						}
					}

					// parsing table doesn't have this rule even after check for rule that have more
					// than one terminal
					if (rule == null) {
						myParser.add("ERROR");
						break;
					}
				}

				if (rule.equals("e")) {

					// replace the variable by epsilon
					String temp = myParser.get(myParser.size() - 1);

					for (int a = 0; a < temp.length(); a++) {
						if (temp.charAt(a) >= 'A' && temp.charAt(a) <= 'Z') {
							stack = stack.substring(1);
							myParser.add(temp.substring(0, a) + stack);
							break;
						}
					}

				}

				else {
					stack = rule + stack.substring(1);
					String temp = myParser.get(myParser.size() - 1);
					myParser.add(temp.substring(0, counter) + stack);

				}
			}

			// input finished but the stack is not empty yet
			else if (c != letter && letter == '$') {
				myParser.add("ERROR");
				break;
			}

			else if (c == '$' && letter != c) {
				myParser.add("ERROR");
				break;
			}

			// found two terminals not equal
			else if (c != letter && (letter >= 'a' && letter <= 'z') && (c >= 'a' && c <= 'z')) {
				myParser.add("ERROR");
				break;
			}

			// if the stack and the input are identical
			else if (letter == c && letter == '$') {
				break;
			}
		}

	}

	public String getFirst(String rule) {
		String result = "";

		if (rule.charAt(0) >= 'a' && rule.charAt(0) <= 'z') {
			result += rule.charAt(0);
		} else {
			ArrayList<String> firstList = myFirst.get(rule.charAt(0) + "");

			for (String c : firstList) {
				if (c.compareTo("a") >= 0 && c.compareTo("z") <= 0 && c.compareTo("e") != 0) {
					result += c;
				} else if (c.compareTo("e") == 0) {
					result += getFirst(rule.substring(1, rule.length()));
				}
			}
		}
		char[] chars = result.toCharArray();
		Arrays.sort(chars);
		result = new String(chars);

		return result;

	}

	
	
//	public static void main(String[] args) {
//		String input = "S;Z;R;O;Y#b;k;l;m;q;s;t#S/sS,lR;Z/bR,OlOY,sRlY;R/m,tOl,e;O/mYs,kRl,e;Y/sS,lZqY#S/s,l;Z/b,klm,s;R/m,t,e;O/m,k,e;Y/s,l#S/$qs;Z/q;R/$lqs;O/l;Y/qs";
//		CfgLl1Parser x = new CfgLl1Parser(input);
//		System.out.println(x.myVariables.toString());
//		System.out.println(x.myTerminals.toString());
//		System.out.println("the rules " + x.myRules.toString());
//		System.out.println("the first " + x.myFirst.toString());
//		System.out.println("the follow " + x.myFollow.toString());
//		x.genParsingTable();
//		System.out.println("parsing table " + x.parsingTable.toString());
//		System.out.println("S;lR;ltOl;ltmYsl;ltmlZqYsl;ltmlOlOYqYsl;ltmllOYqYsl;ltmllmYsYqYsl;ERROR");
//		System.out.println(x.parse("ltmllmm"));
//
//	}

}
