import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSVFileReader {
    public static String[] readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path)).split("\n");
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом по пути: " + path + ". Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
