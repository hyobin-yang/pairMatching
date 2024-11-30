package pairmatching.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private FileReader() {
    }

    public static List<String> readFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            List<String> fileBody = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                fileBody.add(line);
            }
            return fileBody;
        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 파일을 읽어들일 수 없습니다.");
        }
    }
}
