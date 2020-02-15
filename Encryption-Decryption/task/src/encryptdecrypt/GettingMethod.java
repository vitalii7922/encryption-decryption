package encryptdecrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

interface GettingMethod {
    String loadData() throws IOException;
}

class GetDataFromCommandLine implements GettingMethod {

    private CommandLine commandLine = null;

    @Override
    public String loadData() {
        return commandLine.getValue("data","");
    }

    GetDataFromCommandLine(CommandLine commandLine) {
        this.commandLine = commandLine;
    }
}

class GetDataFromFile implements GettingMethod {
    String fileName;

    @Override
    public String loadData() throws IOException {

        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            Scanner scanner = new Scanner(fis);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()){
                sb.append(scanner.nextLine());
            }
            scanner.close();
            return sb.toString();
        } catch(IOException e){
            throw new IOException(e.getMessage());
        }
    }

    GetDataFromFile(CommandLine commandLine){
        fileName = commandLine.getValue("-in",null);
    }

}