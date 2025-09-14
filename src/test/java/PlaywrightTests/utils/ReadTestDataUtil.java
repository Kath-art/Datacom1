package PlaywrightTests.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadTestDataUtil {

    public static void readTestDataFile (String resourcePath) {
        InputStream input = ReadTestDataUtil.class.getResourceAsStream(resourcePath);

        if (input == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) !=null) {
                reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
