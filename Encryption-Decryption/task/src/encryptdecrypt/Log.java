package encryptdecrypt;

import java.io.FileWriter;
import java.io.IOException;

public class Log{

    private FileWriter fileWriter;
    private String fileName = "log.txt";
    private boolean append = false;

    static void appendToFile(String fileName, String message) {
        try (FileWriter fileWriter = new FileWriter(fileName,true)){
            fileWriter.write(message);
        } catch (IOException e){
            System.out.println("Error logging file: " + e);
        }
    }

    static void toNewFile(String fileName, String message) {
        try (FileWriter fileWriter = new FileWriter(fileName,false)){
            fileWriter.write(message);
        } catch (IOException e){
            System.out.println("Error logging file: " + e);
        }
    }

    static void toConsole(String message){
        System.out.println(message);
    }

    private void createFileWriter(){
        try (FileWriter fileWriter = new FileWriter(this.fileName,this.append)){
            this.fileWriter = fileWriter;
        } catch (IOException e){
            System.out.println("Error open file: " + e);
        }
    }

    public Log (){
        createFileWriter();
    }

    public Log(String fileName){
        this.fileName = fileName;
        createFileWriter();
    }

    public Log(String fileName, boolean append){
        this.append = append;
        this.fileName = fileName;
        createFileWriter();
    }

    public String getFileName() {
        return fileName;
    }

    public void println(String message){
        try {
            this.fileWriter.write(message);
        } catch (IOException e){
            System.out.println("Error write to file: " + e);
        }
    }
}
