import junitx.framework.FileAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.razuvaev_dd.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

class JUnitTests {

    @Test
    void test1() {
        String jsonInputFile = Objects.requireNonNull(JUnitTests.class.getClassLoader().getResource("test1.json")).getPath();
        String jsonExpectedFile = Objects.requireNonNull(JUnitTests.class.getClassLoader().getResource("expectedResult1.json")).getPath();

        Main.main(new String[]{"--input-json", jsonInputFile, "--output-json", "result.json"});

        FileAssert.assertEquals(new File("result.json"), new File(jsonExpectedFile));
    }

    @Test
    void test2() {
        String jsonInputFile = Objects.requireNonNull(JUnitTests.class.getClassLoader().getResource("test2.json")).getPath();
        String jsonExpectedFile = Objects.requireNonNull(JUnitTests.class.getClassLoader().getResource("expectedResult2.json")).getPath();

        Main.main(new String[]{"--input-json", jsonInputFile, "--output-json", "result.json"});

        FileAssert.assertEquals(new File("result.json"), new File(jsonExpectedFile));
    }

    @Test
    void test3() {
        String jsonInputFile = Objects.requireNonNull(JUnitTests.class.getClassLoader().getResource("test3.json")).getPath();
        String jsonExpectedFile = Objects.requireNonNull(JUnitTests.class.getClassLoader().getResource("expectedResult3.json")).getPath();

        Main.main(new String[]{"--input-json", jsonInputFile, "--output-json", "result.json"});

        FileAssert.assertEquals(new File("result.json"), new File(jsonExpectedFile));
    }

    @AfterEach
    void clean() throws IOException {
        Files.deleteIfExists(Paths.get("result.json"));
    }
}
