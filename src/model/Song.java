/*
 * @Class
 * 		Song.java
 * @Purpose
 * 		This class defines object Song. It holds 6 values inside the object which are
 * 		file name, title of song, artist of song, length of song, times played and date.
 * 		It contains appropriate getters and setters, and is able to check current date
 * 		with local date and reset values if necessary.
 * @Programmer YANG HONG
 * @Programmer Haozhe Xu
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Song implements Serializable{

	private String fileName;
	private String title;
	private String artist;
	private int songLength;
	private int timesPlayed;
	private LocalDate currentDate;
	
	public Song(String fileName, String title, String artist, int songLength){
		this.fileName    = fileName;
		this.title 	     = title;
		this.artist      = artist;
		this.songLength  = songLength;
		this.timesPlayed = 0;
		this.currentDate = LocalDate.now();
	}
	
	// ---------- getter ----------
	public String getFileName(){
		return fileName;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getArtist(){
		return artist;
	}
	
	public int getSongLength(){
		return songLength;
	}
	
	public int getTimesPlayed(){
		return timesPlayed;
	}
	
	public String getSongLengthInFormat(){
		int mins = songLength / 60;
		int secs = songLength % 60;
		
		return String.format("%01d:%02d", mins, secs);
	}
	
	
	// ---------- setter ----------
	public void increaseTimesPLayed(){
		timesPlayed++;
	}
	
	private void resetTimesPlayed(){
		timesPlayed = 0;
	}
	
	private void updateCurrentDate(){
		currentDate = LocalDate.now();
	}
	
	private void checkDate(){
		if (currentDate.compareTo(LocalDate.now()) != 0){
			updateCurrentDate();
			resetTimesPlayed();
		}
	}

	public boolean isPlayable(){
		checkDate();
		return (timesPlayed < 3);
	}
	
	// for testing purposes
	public void minusDateBy(int day){
		currentDate = currentDate.minusDays(day);
	}
}
