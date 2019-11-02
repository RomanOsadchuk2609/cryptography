package main.util;

import java.io.*;

public class FileReader {
    private FileReader() {
    }

    /**
     * Reads files and retrieve its content
     *
     * @param pathToFile - file location
     * @return content of file
     */
    public static String readFile(String pathToFile) {
        String fileAsString = CryptographyConstants.EMPTY_STRING;
        try (InputStream is = new FileInputStream(pathToFile);
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
}
