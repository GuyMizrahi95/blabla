import java.util.Iterator;

/**
 * This class represents an end of line verifier, that checks if given line or action includes an ;
 * at the end of it.
 */
public class EndOfLineVerifier implements Verifier{

    /** A regex pattern to describe the presence of ; at the end of a line or action. */
    private final String pattern = ".*,;$";

    /** A boolean value to state if verifier is activated (if pattern is found at the end of given line) */
    private boolean isActivated = false;

    /**
     * Checks if given line matches the demands.
     * @param line - line to be checked.
     * @return true if line matches, false otherwise.
     */
    public boolean isActivated(String line){
        if (line.matches(pattern)){
            isActivated = true;
            return true;
        }
        return false;
    }

    /**
     * A method that does relevant action.
     * @param lines - lines to perform action on.
     * @return true upon success, false upon failure.
     */
    public boolean doAction(Iterator<String> lines){
        return isActivated;
    }
}
