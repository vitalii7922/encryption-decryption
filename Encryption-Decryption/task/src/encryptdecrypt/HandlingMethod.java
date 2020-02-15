package encryptdecrypt;

interface HandlingMethod {
    public void handleData();
    public String getHandledData();
}

abstract class EncryptionDecryption  implements HandlingMethod{

    private String data;
    private int key;
    private boolean encryption;

    public String getData() {
        return data;
    }

    public int getKey() {
        return key;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public void handleData() {
        if (encryption){
            encryptionMethod();
        } else {
            decryptionMethod();
        }
    }

    @Override
    public String getHandledData() {
        return data;
    }

    abstract void encryptionMethod();

    abstract void decryptionMethod();

    EncryptionDecryption(String data, int key, boolean encryption){
        this.data = data;
        this.encryption = encryption;
        this.key = key;
    }
}

class ShiftEnDeAlgorithm extends EncryptionDecryption{

    private String algorithmEnDe(String data, int key){
        StringBuilder res = new StringBuilder();
        for (char currentCh : data.toCharArray()){
            char ch =  Character.toLowerCase(currentCh);
            ch -= 97;
            if (ch >= 0 && ch <= 25 ){
                int offset = key;
                if (ch + offset < 0){
                    offset += 26;
                }
                if (ch + offset > 25) {
                    offset -= 26;
                }
                currentCh += offset;
                //Log.appendToFile("shift.txt", String.format("CurrentCh=%s ch = %d, offset=%d, key=%d\n", currentCh, (int) ch, offset, key));
            }

            res.append((char) (currentCh));
        }
        /*Log.appendToFile("log.txt", "Shift algorithm\n");
        Log.appendToFile("log.txt","data= "+ data + " key=" + key + "\n");
        Log.appendToFile("log.txt","result= "+ res.toString() + "\n");*/
        return res.toString();
    }

    @Override
    void encryptionMethod() {
        setData(algorithmEnDe(getData(), getKey()));
    }

    @Override
    void decryptionMethod() {
        setData(algorithmEnDe(getData(), - getKey()));
    }

    ShiftEnDeAlgorithm(String data, int key, boolean encryption){
        super(data, key % 26, encryption);
         // this algorithm working with just 26 chars from 'A' to 'Z'
    }
}

class UnicodeEnDeAlgorithm extends EncryptionDecryption{

    private String algorithmEnDe(String data, int key){
        StringBuilder res = new StringBuilder();
        for (char currentCh : data.toCharArray()){
            res.append((char) (currentCh +  key));
        }
        //Log.appendToFile("log.txt", "Unicode algorithm\n");
        //Log.appendToFile("log.txt","data= "+ data + " key=" + key + "\n");
        //Log.appendToFile("log.txt","result= "+ res.toString() + "\n");
        return res.toString();
    }

    @Override
    void encryptionMethod() {
        setData(algorithmEnDe(getData(), getKey()));
    }

    @Override
    void decryptionMethod() {
        setData(algorithmEnDe(getData(), - getKey()));
    }

    UnicodeEnDeAlgorithm(String data, int key, boolean encryption){
        super(data, key, encryption);
    }
}


