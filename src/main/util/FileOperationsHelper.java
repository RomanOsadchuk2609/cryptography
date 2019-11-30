package main.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Dedicated class for file operations
 */
public class FileOperationsHelper {
    private FileOperationsHelper() {
    }

    /**
     * Reads files and retrieve its content
     *
     * @return file content
     */
    public static String readFile(String pathToFile) {
        return readFile(new File(pathToFile));
    }

    /**
     * Reads files and retrieve its content
     *
     * @return file content
     */
    public static String readFile(File file) {
        String fileAsString = CryptographyConstants.EMPTY_STRING;
        try (InputStream is = new FileInputStream(file);
             BufferedReader buf = new BufferedReader(new InputStreamReader(is))) {
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            fileAsString = sb.toString();
            return fileAsString;
        } catch (IOException e) {
            e.printStackTrace();
            return fileAsString;
        }
    }

    /**
     * Writes text to file
     */
    public static void writeToFile(String pathToFile, String text) {
        File file = new File(pathToFile);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes text to file
     */
    public static void writeToFile(File file, String text) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
