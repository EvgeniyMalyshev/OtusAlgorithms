import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortTest {

    private static final File INITIAL_FILE = new File("src/test/resources/binary/initial");
    private static final File EXPECTED_FILE = new File("src/test/resources/binary/expected");

    @TempDir
    File tmpDir;

    private File resultFile;

    @BeforeEach
    void setUp() throws IOException {
        resultFile = new File(tmpDir, "result");
        Files.copy(INITIAL_FILE.toPath(), resultFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void sort() throws IOException {
        MergeSort.sort(resultFile);
        assertArrayEquals(FileUtils.readFileToByteArray(resultFile), FileUtils.readFileToByteArray(EXPECTED_FILE));
    }
}
