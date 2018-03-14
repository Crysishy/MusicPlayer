/*
 * @Class
 * 		JukeboxGUI.java
 * @Purpose
 * 		This class is the GUI interface of the program Jukebox. It is responsible for logging in
 * 		Account for users; selecting songs for users; playing songs for users; signing out.
 * 		Update: Music overlap issue solved.
 * @Programmer YANG HONG
 * @Programmer Haozhe Xu
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.Jukebox;
import model.Song;
import model.SongList;


@SuppressWarnings("serial")
public class JukeboxGUI extends JFrame implements Observer {

	//private JPanel playlistPan = new JPanel();
	private JPanel loginPan = new JPanel();
	//private JPanel tablePan = new JPanel();
	private JPanel fourByFour = new JPanel();
	
	private JLabel labelAccount = new JLabel("Account Name ", SwingConstants.RIGHT);
	private JLabel labelPassword = new JLabel("Password ", SwingConstants.RIGHT);
	private JLabel status = new JLabel("Status: ", SwingConstants.RIGHT);
	private JLabel statusInfo = new JLabel("Login first");
	private JLabel tableInfo = new JLabel("Select a song from this Jukebox");
	private JLabel listInfo = new JLabel("Play List (song at top is playing)");
	
	private JButton play = new JButton("PLAY");
	private JButton login = new JButton("Login");
	private JButton signout = new JButton("Sign out");
	
	private JTextField textAccount = new JTextField();
	private JPasswordField textPassword = new JPasswordField();
	
	private ButtonListener buttonListener = new ButtonListener();
	private WindowClosingListener windowClosingListener = new WindowClosingListener();
	
	private TableModel songlistTableModel = SongList.getSongList();
	private JTable songlistTable = new JTable(songlistTableModel);
	private JScrollPane tableScrollPan = new JScrollPane(songlistTable);
	
	private DefaultListModel<String> playlistListModel = new DefaultListModel<String>();
	private JList<String> playlistList = new JList<String>(playlistListModel);
	private JScrollPane listScrollPan = new JScrollPane(playlistList);

	private Jukebox jukebox;

	public static void main(String[] args) {
		JukeboxGUI g = new JukeboxGUI();
		g.setVisible(true);
	}

	public JukeboxGUI() {
		jukebox = new Jukebox();
		readDataFromDisk();
		CreateBoard();
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(songlistTableModel);
		songlistTable.setRowSorter(sorter);
		jukebox.getPlayList().addObserver(this);
		addListeners();
	}

	private void CreateBoard() {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setSize(1060, 700);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		listInfo.setFont(new Font("Arial", Font.BOLD, 14));
		listInfo.setSize(400, 20);
		listInfo.setLocation(20, 20);
		this.add(listInfo);
		
		listScrollPan.setSize(400, 400);
		listScrollPan.setLocation(20, 40);
		this.add(listScrollPan);
		
		play.setSize(400, 50);
		play.setLocation(0, 0);
		loginPan.add(play);
		
		fourByFour.setSize(400, 150);
		fourByFour.setLocation(0, 50);
		fourByFour.setBackground(Color.white);
		fourByFour.setLayout(new GridLayout(4, 2));
		fourByFour.add(labelAccount);
		fourByFour.add(textAccount);
		fourByFour.add(labelPassword);
		fourByFour.add(textPassword);
		fourByFour.add(signout);
		fourByFour.add(login);
		fourByFour.add(status);
		fourByFour.add(statusInfo);
		loginPan.add(fourByFour);
		
		loginPan.setSize(400, 200);
		loginPan.setLocation(20, 460);
		loginPan.setLayout(null);
		this.add(loginPan);
		
		tableInfo.setFont(new Font("Arial", Font.BOLD, 14));
		tableInfo.setSize(600, 20);
		tableInfo.setLocation(440, 20);
		this.add(tableInfo);
		
		tableScrollPan.setSize(600, 620);
		tableScrollPan.setLocation(440, 40);
		this.add(tableScrollPan);
	}

	
	private void addListeners() {
		login.addActionListener(buttonListener);
		signout.addActionListener(buttonListener);
		play.addActionListener(buttonListener);
		this.addWindowListener(windowClosingListener);
	}

	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton buttonClicked = (JButton) e.getSource();

			if (buttonClicked == login) {
				// get name and pass
				String name = textAccount.getText();
				char[] pass = textPassword.getPassword();
				String password = "";
				for (char onePass : pass)
					password += onePass;
				password.trim();

				// check login
				if (!jukebox.login(name, password)) {
					JOptionPane.showMessageDialog(null, "Invalid Account Name or Password!");
					textAccount.setText("");
					textPassword.setText("");
					return;
				}
				statusInfo.setText(jukebox.getCurrentAccountSongsPlayed() + " played, " 
								 + jukebox.getCurrentAccountTimeRemaining());
			}
			
			if (buttonClicked == signout){
				if (jukebox.logout()){
					JOptionPane.showMessageDialog(null, "You have successfully signed out");
					statusInfo.setText("Login first");
					textAccount.setText("");
					textPassword.setText("");
				}
			}
			
			if (buttonClicked == play){
				if (!jukebox.isLoggedIn()){
					JOptionPane.showMessageDialog(null, "Please login first");
					return;
				}
				
				int selectedRow = songlistTable.getSelectedRow();
				if (selectedRow < 0)
					JOptionPane.showMessageDialog(null, "Please select a song");
				else{
					int convertedRow = songlistTable.convertRowIndexToModel(selectedRow);
					String status = jukebox.getPlayableStatus(
									jukebox.getSongList().getSongByTitle(
									(String)songlistTableModel.getValueAt(convertedRow, 0)));
					
					if (!status.equals("playing")){
						JOptionPane.showMessageDialog(null, status);
						return;
					}
					statusInfo.setText(jukebox.getCurrentAccountSongsPlayed() + " played, " 
							 		 + jukebox.getCurrentAccountTimeRemaining());
				}
			}
		}
	}
	
	private void updatePlayList() {
		playlistListModel.clear();
		ArrayList<Song> list = jukebox.getPlayList().getCopyOfPlayList();
		for (int i = 0; i < list.size(); i++){
			Song song = list.get(i);
			playlistListModel.addElement(song.getSongLengthInFormat() + " "
									   + song.getTitle() + " by "
									   + song.getArtist());
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		updatePlayList();
	}
	
	private void readDataFromDisk(){
		int option = JOptionPane.showConfirmDialog(null, "Start with previous saved data?");
		if (option == JOptionPane.YES_OPTION){
			if (!jukebox.readDataFromDisk())
				JOptionPane.showMessageDialog(null, "Error: unable reading data from disk\n"
													+ "Data loading failed");
			updatePlayList();
		}
	}
	
	private class WindowClosingListener extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			int option = JOptionPane.showConfirmDialog(null, "Save data?");
			if (option == JOptionPane.YES_OPTION){
				if (jukebox.saveDataToDisk())
					System.exit(0);
				else{
					JOptionPane.showMessageDialog(null, "Error: unable writing data to disk\n"
														+ "Data saving failed");
					System.exit(1);
				}
			}
			if (option == JOptionPane.NO_OPTION)
				System.exit(0);
		}
	}
	
}