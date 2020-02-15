package encryptdecrypt;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //parsing command line
        if (args.length == 0){
            System.out.println("Empty command line. Please enter correct command line.");
            System.out.println("Enter -? for help");
        }

        CommandLine commandLine = new CommandLine(args);
        try {
            commandLine.parse();
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }

        //Log.appendToFile("log.txt", "New Test\n");
        //Log.appendToFile("log.txt",commandLine.getArgsHM().toString() + "\n");

        //Get data block
        Data data = new Data();

        //Choose method - from command line or from file
        if (commandLine.containsKey("-in")) {
            data.setGettingMethod(new GetDataFromFile(commandLine));
        } else {
            data.setGettingMethod(new GetDataFromCommandLine(commandLine));
        }

        //getting data with particular method
        try {
            data.loadData();
        } catch (IOException e){
            System.out.println(e.getMessage());
            return;
        }
        //Log.appendToFile("log.txt","Loaded data:\n");
        //Log.appendToFile("log.txt",data.getData() + "\n");

        //Handling data block
        String alg = commandLine.getValue("-alg", "shift");
        int key = Integer.parseInt(commandLine.getValue("-key", "0"));
        boolean encrypt;
        String mode = commandLine.getValue("-mode","enc");
        switch (mode){
            case "enc":
                encrypt = true;
                break;
            case "dec":
                encrypt = false;
                break;
            default:
                System.out.println("Unknown value of argument -mode=" + mode);
                return;
        }
        //Choose method encoding or decoding data
        switch (alg){
            case "shift":
                data.setHandler(new ShiftEnDeAlgorithm(data.getData(), key, encrypt));
                break;
            case "unicode":
                data.setHandler(new UnicodeEnDeAlgorithm(data.getData(), key, encrypt));
                break;
            default:
                System.out.println("Unknown value of argument -arg=" + alg);
                return;
        }
        //Handling data
        data.handleData();

        //Log.appendToFile("log.txt","Handled data:\n");
        //Log.appendToFile("log.txt",data.getData() + "\n");

        // Save handled data block

        // choose method
        String out = commandLine.getValue("-out",null);
        if (out != null){
            data.setSaver(new SaveToFile(out, data.getData()));
        } else {
            data.setSaver(new PrintToConsole(data.getData()));
        }

        // save data to file or print in the concole.
        try {
            data.saveData();
        } catch (IOException e){
            System.out.println("IOExceprion saving Data");
            System.out.println(e.getMessage());
            return;
        }
    }
}
