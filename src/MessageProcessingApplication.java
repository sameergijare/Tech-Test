import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 *
 * The main application class that processes the messages.
 * 
 *
 */
public class MessageProcessingApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sale sale = new Sale();
		BufferedReader inputFile = null;
        // Read inputs from messages file
        try {
            String line;
            //inputFile = new BufferedReader(new FileReader("C:\\Workspace\\MessageProcessor\\src\\messages.txt"));
            URL url = sale.getClass().getResource("messages.txt");
            inputFile = new BufferedReader(new FileReader(url.getPath()));
            while((line = inputFile.readLine()) != null) {
                // process message for each sale notification
                sale.processNotification(line);

                // Call the report
                // @note: report only generates after every 10th iteration and stops on 50th iteration and pauses for
                // 2 seconds.
                sale.log.report();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }finally{
        	try {
				inputFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

	//}

}
