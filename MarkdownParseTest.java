import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class MarkdownParseTest {
    @Test
    public void testEmpty() throws IOException {
        assertLinks(List.of(), "testCases/empty.md");
    }

    @Test
    public void testExtraSpace() throws IOException {
        assertLinks(List.of(), "testCases/extraSpace.md");
    }

    @Test
    public void testEscape() throws IOException {
        assertLinks(List.of("https://somethingelse.com"), "testCases/escape.md");
    }

    @Test
    public void testJustEscape() throws IOException {
        assertLinks(List.of(), "testCases/justEscape.md");
    }

    @Test
    public void testImage() throws IOException {
        assertLinks(List.of(), "testCases/image.md");
    }

    @Test
    public void testJustBrackets() throws IOException {
        assertLinks(List.of(), "testCases/justBrackets.md");
    }

    @Test
    public void testJustParentheses() throws IOException {
        assertLinks(List.of(), "testCases/justParentheses.md");
    }

    @Test
    public void testMultiline() throws IOException {
        assertLinks(List.of("https://isthisfound.com"), "testCases/multiline.md");
    }

    @Test
    public void testLastLine() throws IOException {
        assertLinks(List.of("last line link should be found"), "testCases/lastLine.md");
    }

    @Test
    public void testFail(){
        assertEquals(2, 1+3); //should fail
    }

    @Test
    public void testSnippet1() throws IOException{
        ArrayList<String> result = new ArrayList<String>();
        result.add("'google.com");
        result.add("google.com");
        result.add("ucsd.edu");

        
	    String contents = Files.readString(Paths.get("./snippet-1.md"));
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        assertEquals(result, links);
    }

    @Test
    public void testSnippet2() throws IOException{
        ArrayList<String> result = new ArrayList<String>();
        result.add("a.com");
        result.add("a.com(())");
        result.add("example.com");

        
	    String contents = Files.readString(Paths.get("./snippet-2.md"));
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        assertEquals(result, links);
    }

    @Test
    public void testSnippet3() throws IOException{
        ArrayList<String> result = new ArrayList<String>();
        result.add("https://ucsd-cse15l-w22.github.io/");

        
	    String contents = Files.readString(Path.of("./snippet-3.md"));
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        assertEquals(result, links);
    }

    public static void assertLinks(List<String> expectedLinks, String fileName) throws IOException {
        Path filePath = Path.of(fileName);
        String contents = Files.readString(filePath);
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        assertEquals(expectedLinks, links);
    }
}
