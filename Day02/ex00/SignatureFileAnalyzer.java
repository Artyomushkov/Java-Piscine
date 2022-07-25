package ex00;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignatureFileAnalyzer {
    private Map<String, String> signBase;

    public SignatureFileAnalyzer(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        signBase = new HashMap<>();
        byte[] buf = new byte[2048];
        int bytes = fis.read(buf);
        String data = new String(buf, 0, bytes);
        String [] strArr = data.split("\n");
        for (int i = 0; i < strArr.length; i++) {
            int commaPos = strArr[i].indexOf(',');
            String key = strArr[i].substring(0, commaPos);
            String value = strArr[i].substring(commaPos + 2);
            signBase.put(key, value);
        }
        fis.close();
    }

    public Map<String, String> getBase() {
        return signBase;
    }
}
