package src.Entity;
import java.io.Serializable;
import java.util.ArrayList;

import src.Entity.CONSTANTS.ClassOfCinema;

public class Cinema implements Serializable{
	private ClassOfCinema classOfCinema;
    /**
     * Cinema code used for Transaction ID.
     */
    private String cinemaCode;
	/**
	 * 2D Array containing Seat Layout
	 */
	private Seat[][] seats;
	/**
	 * Number of rows for Seat Layout
	 */
	private int row;
	/**
	 * Number of column for Seat Layout
	 */
	private int column;
	/**
	 * Show Time List
	 */
	ArrayList<ShowTime> showTimeList;
	/**
	 * Constructor for Cinema Object
	 * 
	 * @param cinemaClass Class of Cinema (i.e. GOLD)
	 * @param cinemaCode Used for Transaction ID
	 * @param row Number of rows in Seat Layout
	 * @param column Number of columns in Seat Layout
	 */
	public Cinema(ClassOfCinema cinemaClass, String cinemaCode, int row, int column, Seat[] seats){
		this.classOfCinema = cinemaClass;
        this.cinemaCode = cinemaCode;
		this.row = row;
		this.column = column;
		this.seats = new Seat[row][column];
		for (int i=0; i<row; i++){
			for (int j=0; j<column; j++){
				this.seats[i][j]=seats[i*column+j];
                this.seats[i][j].setID('A'+i, j+1);
			}
		}
		this.showTimeList = new ArrayList<ShowTime>();
	}

	/**
	 * Gets Class of this Cinema
	 * @return Class Of Cinema (Enum)
	 */
	public ClassOfCinema getClassOfCinema(){
		return classOfCinema;
	}

	/**
	 * Gets specific seat in this Cinema
	 * @param row Row of Seat
	 * @param col Column of Seat
	 * @return Returns Seat
	 */
	public Seat getSeat(int row, int col){
		return seats[row][col];
	}

    /**
     * Gets this Cinema's code
     * @return Cinema Code
     */
    public String getCinemaCode() {
        return cinemaCode;
    }

	/**
	 * Returns 2D Array of Seat Layout
	 * @return Seat Layout
	 */
	public Seat[][] getSeatLayout(){
		return seats;
	}

	/**
	 * Gets row number
	 * @return Row Number
	 */
	public int getRow(){
		return row;
	}

	/**
	 * Gets column number
	 * @return Column Number
	 */
	public int getCol(){
		return column;
	}

	public ArrayList<ShowTime> getShowTimeList(){
		return showTimeList;
	}

	public void addShowTime(ShowTime showTime){
		showTimeList.add(showTime);
	}

	public void updateShowTime(ShowTime newShowTime, int showTimeIndex){
		showTimeList.set(showTimeIndex, newShowTime);
	}

	public void removeShowTime(int showTimeIndex){
		showTimeList.remove(showTimeIndex);
	}
}
