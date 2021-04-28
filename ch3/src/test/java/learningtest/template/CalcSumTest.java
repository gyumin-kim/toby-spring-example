package learningtest.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class CalcSumTest {

    private Calculator calculator;
    private String numFilepath;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        numFilepath = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    void sumOfNumbers() throws IOException {
        int sum = calculator.calcSum(this.numFilepath);
        assertThat(sum).isEqualTo(10);
    }

    @Test
    void multiplyOfNumbers() throws IOException {
        int multiply = calculator.calcMultiply(this.numFilepath);
        assertThat(multiply).isEqualTo(24);
    }
}
