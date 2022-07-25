package ex03;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileDownloader implements Runnable {

    private List<String> strList;
    private int numThread;

    public FileDownloader(List<String> strList, int numThread) {
        this.strList = strList;
        this.numThread = numThread;
    }

    private static String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.'));
    }

    private static void DownloadFile(String strUrl, int index) throws IOException {
        URL url = new URL(strUrl);
        String filename = System.getProperty("user.dir") + "/file" + index + getExtension(strUrl);
        Files.createFile(Paths.get(filename));
        try (InputStream in = url.openStream();
             BufferedInputStream bis = new BufferedInputStream(in);
             FileOutputStream fos = new FileOutputStream(filename)) {
            byte[] data = new byte[1024];
            int count;
            while ((count = bis.read(data, 0, 1024)) != -1) {
                fos.write(data, 0, count);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            int index = Program.getIndex();
            if (index >= strList.size())
                break;
            System.out.println("Thread-" + (numThread + 1) + " start download file number " + (index + 1));
            try {
                DownloadFile(strList.get(index), index);
            } catch (IOException e) {
                System.out.println("Problem with downloading " + e.getMessage());
            }
        }
    }
}
