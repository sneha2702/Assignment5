
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class XMLDownloader extends JFrame implements ActionListener {

	//mainpanel for the application
	JPanel mainPanel = new JPanel(new BorderLayout());
	//declaration: XMLDownloadPanel object
	XMLDownloadPanel xmlObj = new XMLDownloadPanel();
	//constructor to initialize title
	private XMLDownloader(String title) {
		super(title);
	}
	//createandshowGUI	method to initialize interface
	private void createAndShowGUI() {
		//calling create menu method
		createMenu();
		initComponents();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setVisible(true);

	}
	//init components method to initialize panel
	private void initComponents() {
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		//XMLDownloadpanel object Pane added to the center
		mainPanel.add(xmlObj, BorderLayout.CENTER);
		add(mainPanel, BorderLayout.CENTER);
	}
	//createmenu method tocreate menu
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		//create menu for rssType
		JMenu menu = new JMenu("Type");
		//setMnemonic for Type
		menu.setMnemonic(KeyEvent.VK_T);
		//Tooltip for Typemenu
		menu.setToolTipText("Select RSS type");
		menuBar.add(menu);
		//button group for Type
		ButtonGroup buttonGroup = new ButtonGroup();

		JRadioButtonMenuItem typeMenuItem = new JRadioButtonMenuItem("New Music");
		//setAccelerator for new music
		typeMenuItem.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_M, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		buttonGroup.add(typeMenuItem);
		typeMenuItem.setSelected(true);
		typeMenuItem.addActionListener(this);
		menu.add(typeMenuItem);

		typeMenuItem = new JRadioButtonMenuItem("Recent Releases");
		//setAccelerator for recent releases
		typeMenuItem.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		buttonGroup.add(typeMenuItem);
		typeMenuItem.addActionListener(this);
		menu.add(typeMenuItem);

		typeMenuItem = new JRadioButtonMenuItem("Top Albums");
		//setAccelerator for top albums
		typeMenuItem.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		buttonGroup.add(typeMenuItem);
		typeMenuItem.addActionListener(this);
		menu.add(typeMenuItem);
		//create menu for Limit
		menu = new JMenu("Limit");
		//setMnemonic for Limit
		menu.setMnemonic(KeyEvent.VK_L);
		//Tooltip for Limit
		menu.setToolTipText("number of songs to be displayed");
		menuBar.add(menu);
		//Button group for Limit
		ButtonGroup buttonGroup1 = new ButtonGroup();

		JRadioButtonMenuItem limitMenuItem = new JRadioButtonMenuItem("10");
		//setAccelerator for limit-10
		limitMenuItem.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_1, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		buttonGroup1.add(limitMenuItem);
		limitMenuItem.setSelected(true);
		limitMenuItem.addActionListener(this);
		menu.add(limitMenuItem);

		limitMenuItem = new JRadioButtonMenuItem("25");
		//setAccelerator for limit-25
		limitMenuItem.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_2, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		buttonGroup1.add(limitMenuItem);
		limitMenuItem.addActionListener(this);
		menu.add(limitMenuItem);

		limitMenuItem = new JRadioButtonMenuItem("50");
		//setAccelerator for limit-50
		limitMenuItem.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_3, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		buttonGroup1.add(limitMenuItem);
		limitMenuItem.addActionListener(this);
		menu.add(limitMenuItem);

		limitMenuItem = new JRadioButtonMenuItem("100");
		//setAccelerator for limit-100
		limitMenuItem.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_4, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		buttonGroup1.add(limitMenuItem);
		limitMenuItem.addActionListener(this);
		menu.add(limitMenuItem);

		//create menu for Limit
		menu = new JMenu("Explicit");
		//setMnemonic for Explicit
		menu.setMnemonic(KeyEvent.VK_E);
		menu.setToolTipText("Select explicit or not");
		menuBar.add(menu);
		//Button group for explicit Y/N
		ButtonGroup buttonGroup2 = new ButtonGroup();

		JRadioButtonMenuItem explicitMenuItem = new JRadioButtonMenuItem("YES");
		//setAccelerator for explicit yes
		explicitMenuItem.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		buttonGroup2.add(explicitMenuItem);
		explicitMenuItem.setSelected(true);
		explicitMenuItem.addActionListener(this);
		menu.add(explicitMenuItem);

		explicitMenuItem = new JRadioButtonMenuItem("NO");
		//setAccelerator for explicit yes
		explicitMenuItem.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		buttonGroup2.add(explicitMenuItem);
		explicitMenuItem.addActionListener(this);
		menu.add(explicitMenuItem);

		setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//actionPerformed for each menuitem thet sets the value
		if (e.getActionCommand().equals("Top Albums")) {
			xmlObj.setRssType("top-albums");
		} else if (e.getActionCommand().equals("Recent Releases")) {
			xmlObj.setRssType("recent-releases");
		}
		
		if (e.getActionCommand().equals("10")) {
			xmlObj.setLimit(10);
		} else if (e.getActionCommand().equals("25")) {
			xmlObj.setLimit(25);
		} else if (e.getActionCommand().equals("50")) {
			xmlObj.setLimit(50);
		} else if (e.getActionCommand().equals("100")) {
			xmlObj.setLimit(100);
		}
		
		if (e.getActionCommand().equals("YES")) {
			xmlObj.setExplicit(true);
		} else if (e.getActionCommand().equals("NO")) {
			xmlObj.setExplicit(false);
		}

	}
	//main method
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException
				| ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		EventQueue.invokeLater(() -> {
			XMLDownloader mainFrame = new XMLDownloader("iTunes Store Album");
			mainFrame.createAndShowGUI();

		});

	}
}
