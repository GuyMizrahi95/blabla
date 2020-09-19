import java.util.Iterator;

/**
 * This class represents an end of block verifier, that checks if there's an end of an opened block.
 */
public class CloseBlockVerifier implements Verifier{

    /** A regex pattern to determine if there's any appearance of a closing block. */
    private final String pattern = "\\s*\\}\\s*";

    /** A boolean value to determine if verifier is activated. */
    private boolean isActivated = false;

    /**
     * Checks if verifier should be activated.
     * @param line
     * @return
     */
    public boolean isActivated(String line) {
        if (line.matches(pattern)){
            isActivated = true;
            return true;
        }
        return false;
    }

    /**
     * Performs relevant action to
     * @param dataGatherer - object of data to update.
     * @throws Exception if an error has occurred.
     */
    public void doAction(DataGatherer dataGatherer) throws Exception{
        dataGatherer.removeBlock();
    }
}
