import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents a data gatherer, which obtains and keeps data throughout the scanning of a file,
 * counting the amount of blocks found, saved variables and functions.
 */
public class DataGatherer {
    private HashMap<String, Variable> variables;
    private int numOfOpenedBlocks;
    private List<String> functions;

    /**
     * Constructor for DataGatherer object.
     */
    public DataGatherer(){
        variables = new HashMap<>();
        numOfOpenedBlocks = 0;
        functions = new ArrayList<String>();
    }

    /**
     * Upon call, adds a block.
     */
    public void addBlock(){
        numOfOpenedBlocks++;
    }

    /**
     * Upon call, removes a block.
     */
    public void removeBlock(){
        numOfOpenedBlocks--;
    }

    /**
     * Adds a function to the list of functions.
     * @param name - name of new function.
     * @throws ExistingException - in case that such function already exists - throws mentioned exception.
     */
    public void addFunction(String name) throws ExistingException{
        if (functionExists(name)){
            throw new ExistingException("Such function already exists!");
        }
        functions.add(name);
    }

    /**
     * Removes desired function.
     * @param name - name of function to remove.
     * @throws ExistingException - in case such function does not exist - throws mentioned exception.
     */
    public void removeFunction(String name) throws ExistingException{
        if (functionExists(name)){
            functions.remove(name);
            return;
        }
        throw new ExistingException("Such function is not defined");
    }

    /**
     * Checks if function exists.
     * @param name - name of function.
     * @return true if function exists, false otherwise.
     */
    public boolean functionExists(String name){
        for (String function : functions){
            if (function.equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a variable to the hashmap.
     * @param variable - variable to add.
     * @throws Exception - in case such value was already assigned but with different type, throws
     * mentioned exception.
     */
    public void addVariable(Variable variable) throws Exception{
        if (variableExists(variable)){
            if (isSameType(variable.getName(),variable)){
                variables.put(variable.getName(),variable);
                return;
            }
            throw new Exception("A value with a different type is assigned!");
        }
        variables.put(variable.getName(), variable);
    }

    /**
     * Checks if given variable has the same type of a variable with given name that already exists.
     * @param variable - variable to be checked.
     * @return true if both share same type, false otherwise.
     */
    private boolean isSameType(String variableName, Variable variable){
        return variables.get(variableName).getType().equals(variable.getType());
    }

    /**
     * Checks if variable is already declared\assigned.
     * @param variable - variable to check.
     * @return true if variable exists, false otherwise.
     */
    public boolean variableExists(Variable variable){
        return variables.containsKey(variable.getName());
    }

    /**
     * @return - A map of variables declared so far.
     */
    public HashMap<String, Variable> getVariables(){
        return variables;
    }
}
