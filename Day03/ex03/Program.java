package ex03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Program {

    private static volatile Integer index = -1;

    public static synchronized int getIndex() {
        index++;
        return index;
    }

    private static void ThreadsManage(List<String> urlList, int threadsCount) throws InterruptedException {

        Thread[] threads = new Thread[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(new FileDownloader(urlList, i));
            threads[i].start();
        }
        for (int i = 0; i < threadsCount; i++) {
            threads[i].join();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final String filename = System.getProperty("user.dir") + "/files_urls.txt";
        if (!args[0].startsWith("--threadsCount="))
            throw new RuntimeException("Wrong argument");
        final int threadsCount = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<String> urlList = new ArrayList<>();
        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;
            line = line.substring(line.indexOf(' ') + 1);
            urlList.add(line);
        }
        reader.close();
        ThreadsManage(urlList, threadsCount);
    }
}