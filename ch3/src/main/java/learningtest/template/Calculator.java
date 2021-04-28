package learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public int calcSum(final String filepath) throws IOException {
        LineCallback<Integer> sumCallback = new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(final String line, final Integer value) {
                return value + Integer.parseInt(line);
            }
        };
        return lineReadTemplate(filepath, sumCallback, 0);
    }

    public int calcMultiply(final String filepath) throws IOException {
        LineCallback<Integer> multiplyCallback = new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(final String line, final Integer value) {
                return value * Integer.parseInt(line);
            }
        };
        return lineReadTemplate(filepath, multiplyCallback, 1);
    }

    public <T> T lineReadTemplate(final String filepath, final LineCallback<T> callback, final T initVal) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));
            T res = initVal;
            String line;
            while ((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
