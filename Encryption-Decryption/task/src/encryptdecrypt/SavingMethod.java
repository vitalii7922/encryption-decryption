package encryptdecrypt;

import java.io.FileWriter;
import java.io.IOException;

interface SavingMethod {
    void saveData() throws IOException;
}

class SaveToFile implements SavingMethod{
    private String fileName;
    private String data;

    @Override
    public void saveData() throws IOException{
        try (FileWriter fw = new FileWriter(fileName)){
            fw.write(data);
        } catch (IOException e ){
            throw e;
        }
    }

    SaveToFile(String fileName, String data){
        this.data = data;
        this.fileName = fileName;
    }
}

class PrintToConsole implements SavingMethod{
    private String data;

    @Override
    public void saveData() throws IOException{
        System.out.println(data);
    }

    PrintToConsole(String data){
        this.data = data;
    }
}