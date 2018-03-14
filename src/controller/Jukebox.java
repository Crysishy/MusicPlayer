/*
 * @Class
 * 		Jukebox.java
 * @Purpose
 * 		This class is the main component of the program. It has access to all
 * 		other classes and is able to change state of each of them. The responsibility
 * 		of this class is to determine various state of the program and pass
 * 		boolean parameter to the program GUI.
 * @Programmer YANG HONG
 * @Programmer Haozhe Xu
 */
package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import model.Account;
import model.AccountCollections;
import model.PlayList;
import model.Song;
import model.SongList;
import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class Jukebox {

	private AccountCollections accountlist;
	private Account currentAccount;
	private SongList songlist;
	private PlayList playlist;
	private String songDir;
	private String saveDir;
	private String accountlistData;
	private String songlistData;
	private String playlistData;
	
	public Jukebox(){
		accountlist = AccountCollections.getAccountList();
		currentAccount = null;
		songlist = SongList.getSongList();
		playlist = new PlayList();
		songDir = "./songfiles/";
		saveDir = "./JukeboxDataStorage/";
		accountlistData = saveDir + "accountlist.save";
		songlistData = saveDir + "songlist.save";
		playlistData = saveDir + "playlist.save";
		playing();
	}
	
	public SongList getSongList(){
		return songlist;
	}
	
	public PlayList getPlayList(){
		return playlist;
	}
	
	public int getCurrentAccountSongsPlayed(){
		return currentAccount.getSongsPLayed();
	}
	
	public String getCurrentAccountTimeRemaining(){
		return currentAccount.getTimeRemaining();
	}
	
	public boolean isLoggedIn(){
		if (currentAccount != null)
			return true;
		
		return false;
	}
	
	public boolean login(String username, String password){
		if (accountlist.hasAccount(username)){
			if (accountlist.getAccountByName(username).isValidUser(username, password)){
				currentAccount = accountlist.getAccountByName(username);
				return true;
			}
		}
		return false;
	}
	
	public boolean logout(){
		if (currentAccount != null){
			currentAccount = null;
			return true;
		}
		return false;
	}
	
	public String getPlayableStatus(Song currentSong) {
		// check account
		// 1. number of times the user played songs
		// 2. total credit left in the user's account
		if (!currentAccount.canPlay(currentSong)) {
			if (currentAccount.getSongsPLayed() == 3)
				return "You(" + currentAccount.getUserName()
							  + ") have played 3 songs today.";
			if (currentAccount.getSecondsRemaining() < currentSong.getSongLength())
				return "You(" + currentAccount.getUserName() 
							  + ") don't have enough time to play this song.";
		}

		// check song
		// number of times the song has been played
		if (!currentSong.isPlayable()) {
			return "[" + currentSong.getTitle() + "]" + " has been played 3 times today.";
		}

		if (playlist.peek() == null) {
			currentSong.increaseTimesPLayed();
			currentAccount.increaseSongsPlayed();
			currentAccount.decreaseListeningTime(currentSong.getSongLength());
			playlist.add(currentSong);
			playing();
		} else {
			currentSong.increaseTimesPLayed();
			currentAccount.increaseSongsPlayed();
			currentAccount.decreaseListeningTime(currentSong.getSongLength());
			playlist.add(currentSong);
		}
		return "playing";
	}
	
	WaitingForSongToEnd waitForSongEnd = new WaitingForSongToEnd();

	private class WaitingForSongToEnd implements EndOfSongListener {
		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			System.out.println("\nFinished " 
								+ eosEvent.fileName() + ", " 
								+ eosEvent.finishedDate() + ", "
								+ eosEvent.finishedTime());
			ending();
		}
	}
	
	private void playing() {
		if (playlist.peek() == null)
			return;
		SongPlayer.playFile(waitForSongEnd, songDir + playlist.peek().getFileName());
	}
	
	private void ending() {
		playlist.remove();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		playing();
	}
	
	public boolean readDataFromDisk() {
		try {
			FileInputStream file = new FileInputStream(accountlistData);
			ObjectInputStream stream = new ObjectInputStream(file);
			accountlist = (AccountCollections) stream.readObject();
			stream.close();

			file = new FileInputStream(songlistData);
			stream = new ObjectInputStream(file);
			songlist = (SongList) stream.readObject();
			stream.close();

			file = new FileInputStream(playlistData);
			stream = new ObjectInputStream(file);
			playlist = (PlayList) stream.readObject();
			stream.close();
			
			playing();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean saveDataToDisk() {
		try {
			FileOutputStream file = new FileOutputStream(accountlistData);
			ObjectOutputStream stream = new ObjectOutputStream(file);
			stream.writeObject(accountlist);
			stream.close();

			file = new FileOutputStream(songlistData);
			stream = new ObjectOutputStream(file);
			stream.writeObject(songlist);
			stream.close();

			file = new FileOutputStream(playlistData);
			stream = new ObjectOutputStream(file);
			stream.writeObject(playlist);
			stream.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}