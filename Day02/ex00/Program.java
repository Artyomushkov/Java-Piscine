package ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {

        final int MAXBYTES = 9;
        final String OUTPUTFILE = "result.txt";
        final String INPUTFILE = System.getProperty("user.dir") + "/ex00/signatures.txt";

        SignatureFileAnalyzer fileParser = new SignatureFileAnalyzer
                (INPUTFILE);
        Map<String, String> signBase = fileParser.getBase();
        Scanner sc = new Scanner(System.in);
        FileOutputStream fos = new FileOutputStream(OUTPUTFILE);
        while (true) {
            String fileCheck = sc.next();
            if (fileCheck.equals("42")) {
                break;
            }
            FileInputStream fis = new FileInputStream(fileCheck);
            String fileBegin = new String();
            int i = 0;
            while (i < MAXBYTES) {
                int b = fis.read();
                fileBegin += String.format("%02X", b);
                fileBegin += " ";
                i++;
            }
            boolean ifFound = false;
            for (Map.Entry<String, String> entry : signBase.entrySet()) {
                if (fileBegin.startsWith(entry.getValue())) {
                    System.out.println("PROCESSED");
                    fos.write(entry.getKey().getBytes());
                    fos.write('\n');
                    ifFound = true;
                    break ;
                }
            }
            if (!ifFound) {
                System.out.println("UNDEFINED");
            }
            fis.close();
        }
        fos.close();
    }
}
