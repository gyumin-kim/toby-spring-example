package learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public int calcSum(final String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        int sum = 0;
        String line;
        while ((line = br.readLine()) != null) {
            sum += Integer.parseInt(line);
        }

        br.close();
        return sum;
    }
}
