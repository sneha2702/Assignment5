
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;

class XMLDownloadTask extends SwingWorker<String, Object> {
	// String to hold the URL
	String holdURL;
	// object reference to the XMLDownloadPanel
	XMLDownloadPanel holdRef;
	//parameterized constructor
	public XMLDownloadTask(String holdURL, XMLDownloadPanel holdRef) {
		super();
		this.holdURL = holdURL;
		this.holdRef = holdRef;
	}
	public String getHoldURL() {
		return holdURL;
	}

	public void setHoldURL(String holdURL) {

	}
	//doInBackground() method  Downloading XML from a Web Page and Parsing XML Using SAX
	public String doInBackground() throws ProtocolException, IOException {
		String xmlString;
		HttpURLConnection connection = null;
		String song = "";

		try { 	
			URL url = new URL(holdURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				StringBuilder xmlResponse = new StringBuilder();
				BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String strLine;
				while ((strLine = input.readLine()) != null) {
					xmlResponse.append(strLine);
				}
				xmlString = xmlResponse.toString();
				input.close();
				// create SAX obj and use it to parse the XML
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser saxParser = factory.newSAXParser();
				//object referece to Album handler class
				AlbumHandler hand = new AlbumHandler();
				//pass xml string to Album handler
				saxParser.parse(new InputSource(new ByteArrayInputStream(xmlString.getBytes("utf-8"))), hand);
				//process the string and return output string
				song = process(hand.getOutput());
			}
		} catch (MalformedURLException e) {
			// Handle MalformedURLException
		} catch (IOException e) {
			// Handle IOException
		} catch (Exception e) {
			// Handle any other exceptions thrown by the code that
			// processes xmlString
		} finally {
			// close connection
			if (connection != null) {
				connection.disconnect();
			}
		}
		return song;

	}
	
	protected String process(ArrayList<Album> arrayList) {
		// Loop through results
		StringBuilder songs = new StringBuilder();
		for(int i = 0; i < arrayList.size(); i ++) {
			// format and add to the displayed text area
			songs.append(arrayList.get(i).getAlbumName() + " ; " + arrayList.get(i).getArtistName() + " ; " + arrayList.get(i).getGenre() + "\n");
        }
		
		return songs.toString();
	} 
}
