
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class conditions implements Verifier {
	private static int insideLoop = 0;
	HashMap<String, Variable> vars;
	String[] aaa = {"A", "v", "s", "aa"};
	String[] types = {"boolean", "String", "int", "double"};


	@Override
	public boolean isActivated(String line) {
		return false;
	}

	@Override
	public boolean doAction(Iterator<String> lines) throws Exception {
		String line = " ";
		boolean found = false;
		line = lines.next();
		Pattern validStruct = Pattern.compile("if[(]|while[(]");
		Matcher matcher = validStruct.matcher(line);
		if (matcher.find()) {
			if (matcher.start() == 0) {
				found = true;
				line = matcher.replaceAll("");
			}
		}
		if (!found) {
			return false;
		}
		line = line.replaceAll(" ", "");
		Pattern validTail = Pattern.compile("[)][{]$");
		matcher = validTail.matcher(line);
		if (matcher.find()) {
			line = matcher.replaceFirst("");
		} else {
			return false;
		}
		line = line.replace("){", "");
		return checkCondition(line);

	}

	public boolean checkCondition(String line) {
		/*to remove*/
		vars = new HashMap<String, Variable>();
		for (int i = 0; i < aaa.length; i++) {
			Variable v = new Variable(aaa[i], types[i]);
			vars.put(v.getName(), v);
		}
		/*end of remove*/
		Pattern orAnd = Pattern.compile("[|]{2}|[&]{2}"); //in order to check when it || or &&
		if (line.endsWith("||") || line.startsWith("||") || line.endsWith("&&") || line.startsWith("&&")) {
			return false; //check if a line starting ot ending with invalid syntax
		}
		String valid = "true|false|-?\\d+.\\d+|-?\\d"; //check valid conds
		Pattern validPat = Pattern.compile(valid);
		String[] lineParts; //we will insert here all of the conditions
		lineParts = line.split(String.valueOf(orAnd)); //split our line by || and &&
		for (String cond : lineParts) { //check each condition apart
			cond = cond.trim();  //remove spaces

			Matcher m = validPat.matcher(cond); //check if it's match the regular conditions
			if (m.matches()) { //if so - move to the next condition(if exist
				continue;
			}
			if (vars.containsKey(cond)) { //
				Variable v = vars.get(cond);
				if (!v.isValidCond()) {
					return false;
				}
				continue;
			}
			return false;
		}
		return true;

	}

}
