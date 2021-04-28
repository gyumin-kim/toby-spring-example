package learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public int calcSum(final String filepath) throws IOException {
        LineCallback sumCallback = new LineCallback() {
            @Override
            public int doSomethingWithLine(final String line, final int value) {
                return value + Integer.parseInt(line);
            }
        };
        return lineReadTemplate(filepath, sumCallback, 0);
    }

    public int calcMultiply(final String filepath) throws IOException {
        LineCallback multiplyCallback = new LineCallback() {
            @Override
            public int doSomethingWithLine(final String line, final int value) {
                return value * Integer.parseInt(line);
            }
        };
        return lineReadTemplate(filepath, multiplyCallback, 1);
    }

    public int lineReadTemplate(final String filepath, final LineCallback callback, final int initVal) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));
            int res = initVal;
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
