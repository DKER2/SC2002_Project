package src.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 Represents a cineplex that have multiple cinema.
 @author SE4G4
 @version 1.0
 @since 2022-11-09
*/
public class Cineplex implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Cineplex Name
	 */
	private String cineplexName;

	/**
	 * ArrayList of Cinemas
	 */
	private ArrayList<Cinema> cinemas;
	
	/**
	 * Constructor for Cineplex Object
	 * @param cineplexName Cineplex Name
	 * @param cinemasList ArrayList of cinemas
	 */
	public Cineplex(String cineplexName,ArrayList<Cinema> cinemasList) {
		this.cineplexName = cineplexName;
		this.cinemas = cinemasList;
	}

	/**
	 * Gets Cineplex name
	 * @return cineplex name
	 */
	public String getCineplexName()
	{
		return cineplexName;
	}

	/**
	 * Gets ArrayList of cinemas
	 * @return ArrayList of cinemas
	 */
	public ArrayList<Cinema> getCinema() 
	{
		return cinemas;
	}
	
	/**
	 * Sets cineplex name
	 * @param name New cineplex name
	 */
	public void setCineplexName(String name) 
	{
		cineplexName = name;
	}

	/**
	 * Sets ciname list
	 * @param newCinemas New cinema list
	 */
	public void setCinemaList(ArrayList<Cinema> newCinemas) 
	{
		cinemas = newCinemas;
	}

	/**
	 * Add show time to a cinema in a cineplex
	 * @param cinemaCode cinemaCode to add showtime
	 * @param showTime show time to add 
	 */
	public void addShowTime(String cinemaCode, ShowTime showTime){
		for(int i=0; i<cinemas.size(); i++){
			if(cinemas.get(i).getCinemaCode().equals(cinemaCode)){
				Cinema newData = cinemas.get(i);
				newData.addShowTime(showTime);
				cinemas.set(i, newData);
			}
		}
	}

	/**
	 * Update show time of a cinema in a cineplex
	 * @param cinemaCode cinemaCode to add showtime
	 * @param showTime show time to add
	 * @param showTimeIndex showtime index to update
	 */
	public void updateShowTime(String cinemaCode, ShowTime newShowTime, int showTimeIndex){
		for(int i=0; i<cinemas.size(); i++){
			if(cinemas.get(i).getCinemaCode().equals(cinemaCode)){
				Cinema newData = cinemas.get(i);
				newData.updateShowTime(newShowTime, showTimeIndex);
				cinemas.set(i, newData);
			}
		}
	}

	/**
	 * Remove show time of a cinema in a cineplex
	 * @param cinemaCode cinemaCode to remove showtime
	 * @param showTimeIndex showtime index to remove
	 */
	public void removeShowTime(String cinemaCode, int showTimeIndex){
		for(int i=0; i<cinemas.size(); i++){
			if(cinemas.get(i).getCinemaCode().equals(cinemaCode)){
				Cinema newData = cinemas.get(i);
				newData.removeShowTime(showTimeIndex);
				cinemas.set(i, newData);
			}
		}
	}

	/**
	 * Book a seat
	 * @param cinemaCode cinema code used to indicate what cinema the booking is for
	 * @param showTimeIndex showtime index to remove
	 * @param colIndex col index of seat
	 * @param rowIndex row index of seat
	 * @param username who book the seat
	 */
	public boolean bookSeat(String cinemaCode, int showTimeIndex, int colIndex, int rowIndex, String username){
		for(int i=0; i<cinemas.size(); i++){
			if(cinemas.get(i).getCinemaCode().equals(cinemaCode)){
				Cinema newData = cinemas.get(i);
				boolean flag = newData.bookSeat(showTimeIndex, colIndex, rowIndex, username);
				cinemas.set(i, newData);
				return flag;
			}
		}
		return false;
	}
}