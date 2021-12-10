package test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TestDataSource implements Iterator<TestData> {

    private final String resourcePath;
    private int index = 0;

    public TestDataSource(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public boolean hasNext() {
        return getNextInputFileResource() != null;
    }

    @Override
    public TestData next() {
        List<String> input = Collections.emptyList(), output = Collections.emptyList();

        try {
            input = readFile(getNextInputFileResource());
            output = readFile(getNextOutputFileResource());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        index++;
        return new TestData(input, output);
    }

    private URL getNextInputFileResource() {
        return getClass().getClassLoader().getResource(resourcePath + File.separator + "test." + index + ".in");
    }

    private URL getNextOutputFileResource() {
        return getClass().getClassLoader().getResource(resourcePath + File.separator + "test." + index + ".out");
    }

    private List<String> readFile(URL resource) throws IOException, URISyntaxException {
        final Path path = Paths.get(resource.toURI());
        return Files.readAllLines(path);
    }
}
