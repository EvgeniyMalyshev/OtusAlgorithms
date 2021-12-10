package test;

import java.util.List;

public class TestData {

    private final List<String> input;

    private final List<String> output;

    public TestData(List<String> input, List<String> output) {
        this.input = input;
        this.output = output;
    }

    public List<String> getInput() {
        return input;
    }

    public List<String> getOutput() {
        return output;
    }
}
