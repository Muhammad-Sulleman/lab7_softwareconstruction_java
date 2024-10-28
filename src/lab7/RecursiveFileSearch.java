package lab7;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecursiveFileSearch {
    public static void main(String[] args) {
        System.out.println("Arguments received:");
        for (String arg : args) {
            System.out.println(arg);
        }

        if (args.length < 2) {
            System.out.println("Usage: java RecursiveFileSearch <directory> <filename1> [<filename2> ...]");
            return;
        }

        String directoryPath = args[0];
        List<String> fileNames = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            fileNames.add(args[i].toLowerCase());
        }

        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Invalid directory specified.");
            return;
        }

        for (String fileName : fileNames) {
            int count = searchFile(directory, fileName, true); // true for case-insensitive search
            if (count > 0) {
                System.out.println("Found " + fileName + " " + count + " time(s).");
            } else {
                System.out.println("File " + fileName + " not found.");
            }
        }
    }

    private static int searchFile(File directory, String fileName, boolean caseInsensitive) {
        int count = 0;
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    count += searchFile(file, fileName, caseInsensitive);
                } else {
                    String fileNameToCheck = caseInsensitive ? file.getName().toLowerCase() : file.getName();
                    if (fileNameToCheck.equals(fileName)) {
                        System.out.println("File found at: " + file.getAbsolutePath());
                        count++;
                    }
                }
            }
        }
        return count;
    }
}




 