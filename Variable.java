import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Variable {
    private String name;
    private String type;
    private boolean isFinal;
    private boolean hasValue;

    public Variable(String name, String type, boolean isFinal){
        this.name = name;
        this.type = type;
        this.isFinal = isFinal;
    }

    public String getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public boolean isValidCond(){
        if (!hasValue)
            return false;
        Pattern validCond = Pattern.compile("boolean|int|double");
        Matcher matches = validCond.matcher(type);
        return matches.find();
    }

}
