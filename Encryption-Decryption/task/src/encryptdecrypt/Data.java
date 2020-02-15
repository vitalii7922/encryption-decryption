package encryptdecrypt;

import java.io.IOException;

class Data {

    private String data = null;

    private GettingMethod gettingMethod;

    private HandlingMethod handler;

    private SavingMethod saver;

    public void setHandler(HandlingMethod handler) {
        this.handler = handler;
    }

    public void setGettingMethod(GettingMethod gettingMethod) {
        this.gettingMethod = gettingMethod;
    }

    public void setSaver(SavingMethod saver){
        this.saver = saver;
    }

    public void loadData() throws IOException {
        data = gettingMethod.loadData();
    }

    public void handleData(){
        //may be handler.handleData and getHandledData need to refactoring to one method String handleData()...
        handler.handleData();
        data = handler.getHandledData();
    }

    public void saveData() throws IOException{
        try {
            saver.saveData();
        } catch (IOException e){
            throw e;
        }
    }

    public String getData(){
        return this.data;
    }
}
