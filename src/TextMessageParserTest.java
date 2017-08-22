

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * A Sample junit test case for testing Text Message Parser.
 * @author sgijare
 *
 */
public class TextMessageParserTest {
	TextMessageParser messageParser = null;
	BufferedReader inputFile = null;


	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		messageParser = new TextMessageParser();
		//inputFile = new BufferedReader(new FileReader("C:\\Workspace\\MessageProcessor\\src\\messages.txt"));
		URL url = messageParser.getClass().getResource("messages.txt");
        inputFile = new BufferedReader(new FileReader(url.getPath()));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		inputFile.close();
	}

	/**
	 * Test method for {@link TextMessageParser#parseMessage(java.lang.String)}.
	 * @throws IOException 
	 */
	@Test
	public void testParseMessage() throws IOException {

		//fail("Not yet implemented");
		String message;
		while((message = inputFile.readLine()) != null) {
			    //System.out.println(message);
				boolean flag = messageParser.parseMessage(message);
				//System.out.println(flag);
				assertNotNull(flag);
			//}
		}
		
	}

}
