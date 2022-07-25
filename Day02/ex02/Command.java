package ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Command {

    private static long getFileSize(File file) {

        long length = 0;
        try {
            if (file.isFile())
                return file.length();
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    length += files[i].length();
                } else {
                    length += getFileSize(files[i]);
                }
            }
        }
        catch (Exception ignored) {}
        return length;
    }

    private static void showInfoLs(File file) {
        System.out.print(file.getName());
        System.out.print(" ");
        System.out.print(getFileSize(file) / 1024);
        System.out.print(" ");
        System.out.println("KB");
    }

    public static void ls (Path dir) throws IOException {
        Files.list(dir).forEach(path -> showInfoLs(path.toFile()));
    }

    private static void PointPointSlashParse(String command, String folder) {
        while (command.startsWith("../")) {
            command = command.substring(3);
            try {
                folder = folder.substring(0, folder.lastIndexOf('/'));
            }
            catch (IndexOutOfBoundsException e) {
                folder = ".";
                command = "";
            }
        }
    }

    public static Path cd (String[] command, Path dir) {
        String folder = dir.toString();
        if (command[1].startsWith("/")) {
            folder = command[1];
            Path newDir = Paths.get(folder).normalize().toAbsolutePath();
            System.out.println(newDir.toAbsolutePath());
            return newDir;
        }
        if (command[1].startsWith("../"))
            PointPointSlashParse(command[1], folder);
        Path newDir = Paths.get(folder + "/" + command[1]).normalize().
                toAbsolutePath();
        if (!Files.exists(newDir))
            throw new RuntimeException("No such file or directory!");
        if (newDir.toFile().isFile())
            throw new RuntimeException("Not a directory!");
        System.out.println(newDir);
        return newDir;
    }

    private static void DoublePointParse(String dest) {
        try {
            dest = dest.substring(0, dest.lastIndexOf('/'));
        }
        catch (IndexOutOfBoundsException e) {
            dest = ".";
        }
    }

    public static void mv(String[] command, Path dir) throws IOException {
        if (command.length < 2)
            throw new RuntimeException("Command mv have uncorrect number of argument");
        String from = new String(dir.toString());
        String to = new String(dir.toString());
        if (command[1].startsWith("..")) {
            DoublePointParse(from);
        }
        if (command[2].startsWith("..")) {
            DoublePointParse(to);
        }
        if (command[1].startsWith("../")) {
            PointPointSlashParse(command[1], from);
        }
        if (command[2].startsWith("../")) {
            PointPointSlashParse(command[2], to);
        }
        Path pathFrom = Paths.get(from + "/" + command[1]).normalize();
        Path pathTo = Paths.get(to + "/" + command[2]).normalize();
        if (pathTo.toFile().isDirectory()) {
            String filename = pathFrom.toString();
            int index = filename.lastIndexOf('/');
            if (index == -1)
                index = 0;
            filename = filename.substring(index);
            to = pathTo.toString() + '/' + filename;
            pathTo = Paths.get(to);
        }
        System.out.println(pathFrom);
        System.out.println(pathTo);
        Files.move(pathFrom, pathTo, REPLACE_EXISTING);
    }
}
