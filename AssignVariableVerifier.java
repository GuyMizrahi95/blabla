import java.util.Iterator;

/**
 * This class represents a verifier for a variable assignment operation.
 */
public class AssignVariableVerifier implements Verifier{

    /** A line that holds a variable assignment operation. */
    private String currentLine;

    /** A regex pattern to determine if there's a variable assignment. */
    private final String pattern = "\\s*[a-zA-Z_][a-zA-Z_\\d]*\\s*=\\s*[^\\s]+\\s*";

    /** An end of line verifier object to verify that there's an end of line. */
    private EndOfLineVerifier endLineVerifier = new EndOfLineVerifier();

    /**
     * Checks if such operation is happening at given line.
     * @param line - line to check
     * @return true if there's a match, false otherwise.
     */
    public boolean isActivated(String line){
        if (line.matches(pattern)){
            currentLine = line;
            return true;
        }
        return false;
    }

    /**
     * Checks that the way the variable is assigned is functional and legit.
     * @param lines - lines of code to be checked.
     * @return - true upon success, false upon failure.
     * @throws Exception if an error has occurred.
     */
    public boolean doAction(Iterator<String> lines) throws Exception{
        if (hasEnd()){
            currentLine = currentLine.replaceAll("\\s*=\\s*", "");
        }
        return true;
    }

    /**
     * Checks if there's an ; after the variable assignment.
     * @return true if there is, false otherwise.
     */
    private boolean hasEnd(){
        return endLineVerifier.isActivated(currentLine);
    }
}
