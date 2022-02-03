import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

// This is a method using unit testing with integration testing.
// See more: https://revdebug.com/blog/unit-tests-vs-integration-tests
public class MarkdownParseTestOtherMethod {
    // We're doing unit testing here. We want to make sure getLinks works
    // exactly as we expect it to.
    @Test
    public void testEmpty() {
        assertEquals(List.of(), MarkdownParse.getLinks(""));
    }

    @Test
    public void testExtraSpace() {
        assertEquals(List.of(), MarkdownParse.getLinks("[not a link]extra space(shouldn't be found)"));
    }

    @Test
    public void testImages() {
        assertEquals(List.of(), MarkdownParse.getLinks("![image text](image url)"));
    }

    @Test
    public void testExpected() {
        // ArrayList<String> list = new ArrayList<>();
        // list.add("google.com");
        // Same thing as:
        // List.of("google.com");

        assertEquals(List.of("google.com"), MarkdownParse.getLinks("[google](google.com)"));
    }

    // Testing the main method to ensure it can read files and calls getLinks
    // correctly. Once we know the method calls getLinks, we can test getLinks
    // without having to go through the the main method, which makes testing
    // simpler.
    // This would be considered an integration test, because we're making sure
    // the main method *integrates* with getLinks.
    @Test
    public void testMain() throws IOException {
        ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        MarkdownParse.main(new String[] { "testCases/mainMethodTest.md" });

        String standardOutput = myOut.toString();
        assertEquals("[google.com]\n", standardOutput);
    }
}
