
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

import javax.swing.*;

public class XMLDownloadPanel extends JPanel implements ActionListener {
	//declaring getAlbums button
	public JButton getAlbums = new JButton("Get Albums");
	//declaring textArea for display
	public JTextArea textArea = new JTextArea(20, 80);
	//variable for XMLDownloadTask class
	XMLDownloadTask xmlDownload;
	//initialize Sting to store rssType
	String rssType="new-music";
	//initialize int limit to store limit to be displayed
	int limit = 10;
	// boolean variable to indicate whether or not explicit albums are allowed.
	boolean explicit = true;
	//declaration of timers for displaying elapsed time
	public long start;
	public long end;
	//Jlabel elapsedTime
	public JLabel elapsedTime=new JLabel("Elapsed Time:");
	//default constructor to initialize pane for getalbums button and text Area
	public XMLDownloadPanel() {
		JPanel xmlPanel = new JPanel(new BorderLayout());
		JPanel flowLayout = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
		flowLayout.add(getAlbums);
		flowLayout.add(elapsedTime);
		xmlPanel.add(flowLayout, BorderLayout.NORTH);
		xmlPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        textArea.setEditable(false);
		add(xmlPanel);
		getAlbums.addActionListener(this);
	}

	//getter and setters for rssType,Limit,explicit,textArea
	public String getRssType() {
		return rssType;
	}

	public void setRssType(String rssType) {
		this.rssType = rssType;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isExplicit() {
		return explicit;
	}

	public void setExplicit(boolean explicit) {
		this.explicit = explicit;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	//declaring download class method
	public void download(){
		//checks foe explicit boolean value and passes the appropriate string
		String explicitCond = (isExplicit() == true)?"explicit":"non-explicit";
		//URL string for the requested fetch.
        String holdURL = "https://rss.itunes.apple.com/api/v1/us/itunes-music/"+rssType+"/all/"+limit+"/"+explicitCond+".atom";
        //XMLDownloadTask and pass it the URL string and a reference
        xmlDownload = new XMLDownloadTask(holdURL, new XMLDownloadPanel());
        try {
        	//calling the method from XMLDownloadTask class  (Downloading XML from a Web Page and Parsing XML Using SAX)
            String song = xmlDownload.doInBackground();
            //setting text to text area
            textArea.setText(song);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //calling execute method
        xmlDownload.execute();
        xmlDownload.addPropertyChangeListener(this::propertyChange);
    }
	//Property changed for getalbums
    private void propertyChange(PropertyChangeEvent event) {
        switch (event.getPropertyName()) {
            case "state":
                switch ((SwingWorker.StateValue) event.getNewValue()) {
                    case DONE:
                        end=System.nanoTime();
                        long elapsed=((end-start)/1000000);
                        Double finaltime= ((double)elapsed)/1000;
                        elapsedTime.setText("Elapsed Time: "+finaltime);
                        getAlbums.setEnabled(true);
                        xmlDownload = null;
                        break;
                    case STARTED:
                        start=System.nanoTime();
                        getAlbums.setEnabled(false);
                        break;
					case PENDING:
						getAlbums.setEnabled(false);
						break;
                }
                break;
        }
    }

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == getAlbums) {
			textArea.setText("");
			//calling download method
			download();
		}
	}
}
