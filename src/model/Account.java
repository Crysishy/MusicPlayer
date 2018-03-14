/*
 * @Class
 * 		Account.java
 * @Purpose
 * 		This class defines Object Account. It holds 5 values inside the object:
 * 		user's name; password; user's remaining length credit(seconds left); user's
 * 		remaining time credit(times left); local date. It is able to return these 
 * 		values, set and reset them if necessary. It'll check if current date fits
 * 		local date just in case it'll renew user's credit. It is able to check if
 * 		a user is logged in with valid account.	
 * @Programmer YANG HONG
 * @Programmer Haozhe Xu
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Account implements Serializable{

	private String username;
	private String password;
	private int secondsRemaining;
	private int songsPlayed;
	private LocalDate currentDate;
	
	public Account(String user, String pass){
		username = user;
		password = pass;
		secondsRemaining = 90000;
		songsPlayed = 0;
		currentDate = LocalDate.now();
	}
	
	// ---------- setter ----------
	public void increaseSongsPlayed(){
		songsPlayed++;
	}
	
	public void decreaseListeningTime(int second){
		secondsRemaining -= second;
	}
	
	private void resetEverything(){
		currentDate = LocalDate.now();
		secondsRemaining = 90000;
		songsPlayed = 0;	
	}
	
	// for testing purposes
	public void plusCurrentDateBy(int day){
		currentDate = currentDate.plusDays(day);
	}
	
	// ---------- getter ----------
	public String getUserName(){
		return username;
	}
	
	public boolean isValidUser(String user, String pass){
		if(username.equals(user) && password.equals(pass))
			return true;
		return false;
	}
	
	public int getSongsPLayed(){
		return songsPlayed;
	}
	
	public int getSecondsRemaining(){
		return secondsRemaining;
	}
	
	public String getTimeRemaining(){
		int temp = secondsRemaining / 60;
		int secs = secondsRemaining % 60;
		int hour = temp / 60;
		int mins = temp % 60;
		
		return String.format("%02d:%02d:%02d", hour, mins, secs);
	}
	
	private void checkDate(){
		if (currentDate.compareTo(LocalDate.now()) != 0){
			resetEverything();
		}
	}
	
	public boolean canPlay(Song song){
		checkDate();
		return ((secondsRemaining >= song.getSongLength()) && (songsPlayed < 3));		
	}
}
