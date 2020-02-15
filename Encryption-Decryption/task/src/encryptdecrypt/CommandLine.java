package encryptdecrypt;

import java.util.HashMap;


class CommandLine {
    private String[] args = null;

    private HashMap<String, String> argsHM;

    public CommandLine(String[] args){
        this.args = args;
    }

    public void parse() throws IllegalArgumentException{

        this.argsHM = new HashMap<>();
        boolean lastArg = false;
        String argument = "";

        for (String current: args){
            current = current.trim();
            if (current.length() == 0) {continue;} // not sure if  commLine will be -arg1 "" arg2
            //current is argument if length > 1, starts with '-', second char is not digit.
            if (current.length() > 1 && current.charAt(0) == '-' && !Character.isDigit(current.charAt(1))) {
                if (lastArg) {
                    // Previous current was an argument without value.
                    argsHM.put(argument, null);
                    // null because HashMap.getOrDefault check with null;
                } else {
                    lastArg = true;
                }
                argument = current;// save current argument
            } else {
                if (lastArg){
                    argsHM.put(argument,current);
                    lastArg = false;
                } else {
                    throw new IllegalArgumentException("Command line exception. Value without key " + current);
                }
            }
        }
    }

    public HashMap<String, String> getArgsHM() {
        return argsHM;
    }

    public String getValue(String arg, String defaultValue){
        return argsHM.getOrDefault(arg, defaultValue);
    }

    public boolean containsKey(String key){
        return argsHM.containsKey(key);
    }

}
