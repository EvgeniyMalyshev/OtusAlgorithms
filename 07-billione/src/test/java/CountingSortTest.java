import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CountingSortTest {

    private static final File INITIAL_FILE = new File("src/test/resources/binary/initial");
    private static final File EXPECTED_FILE = new File("src/test/resources/binary/expected");

    @TempDir
    File tmpDir;

    private File resultFile;

    @BeforeEach
    void setUp() {
        resultFile = new File(tmpDir, "result");
    }

    @Test
    void sort() throws IOException {
        CountingSort.sort(INITIAL_FILE, resultFile);

        assertArrayEquals(FileUtils.readFileToByteArray(resultFile), FileUtils.readFileToByteArray(EXPECTED_FILE));
    }
}
