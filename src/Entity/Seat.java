package src.Entity;
import java.io.Serializable;
import src.Entity.CONSTANTS;

public class Seat implements Serializable{

    /**
     * Seat ID
     */
    private String seatID;

    /**
     * Information on Seat Status (Enum)
     */
    private CONSTANTS.seatStatus seatStatus;

    /**
     * Constructor for Seat Object
     * @param status Seat Status (Enum)
     * @param seatID String value of Seat ID
     */
    public Seat(CONSTANTS.seatStatus status, String seatID) {
        this.seatStatus = status;
        this.seatID = seatID;
    }

    /**
     * Used for printing seat layout
     */
    public String toString() {
        if(seatStatus == CONSTANTS.seatStatus.NOTEXIST){
            return "   ";
        }
        else if (seatStatus == CONSTANTS.seatStatus.TAKEN){
            return "[X]";
        }
        else {
            return "[ ]";
        }
    }

    /**
     * Gets Seat Status
     * @return Seat Status (Enum)
     */
    public CONSTANTS.seatStatus getStatus() {
        return seatStatus;
    }

    /**
     * Sets Seat Status
     * @param status New Seat Status (Enum)
     */
    public void setStatus(CONSTANTS.seatStatus status) {
        seatStatus = status;
    }

    /**
     * Sets seat ID
     * @param row Row which this seat resides in
     * @param col Column which this seat resides in
     */
    public void setID(int row, int col) {
        char base = 'A';
        char letterRow = (char)((int)base + row);
        seatID = letterRow + "" + col;
    }

    /**
     * Gets Seat ID
     * @return String value of SeatID
     */
    public String getSeatID() {
        return seatID;
    }
}