import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a variable, that includes a created variable's name, type, whether it's final or 
 * not and if it has an assigned value.
 */
public class Variable {
    private String name;
    private String type;
    private boolean isFinal;
    private boolean hasValue;

    /**
     * Creates a new Variable object
     * @param name - name of variable.
     * @param type - type of variable.
     * @param isFinal - determines if variable is final or not.
     */
    public Variable(String name, String type, boolean isFinal){
        this.name = name;
        this.type = type;
        this.isFinal = isFinal;
    }

    /**
     * @return - type of variable.
     */
    public String getType(){
        return this.type;
    }

    /**
     * @return - name of variable.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Checks if variable is of a legitimate type.
     * @return true if it is, false otherwise.
     */
    public boolean isValidCond(){
        if (!hasValue)
            return false;
        Pattern validCond = Pattern.compile("boolean|int|double");
        Matcher matches = validCond.matcher(type);
        return matches.find();
    }
}
