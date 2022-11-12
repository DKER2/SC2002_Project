package src.Entity;
import java.io.Serializable;

public class Seat implements Serializable{
    private static final long serialVersionUID = 6L;

    /**
     * Seat ID
     */
    private String seatID;
    
    /**
     * owner
     */
    private String owner;

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
     * Book Seat 
     * @param owner the booking person
     * @return flag indicate booking is successful
     */
    public boolean bookSeat(String owner){
        if(seatStatus.equals(CONSTANTS.seatStatus.TAKEN)){
            return false;
        }
        this.seatStatus = CONSTANTS.seatStatus.TAKEN;
        this.owner = owner;
        return true;
    }

    /**
     * Free Seat 
     */
    public void freeSeat(){
        seatStatus = CONSTANTS.seatStatus.NOTTAKEN;
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

    /**
     * Gets Owner
     * @return String value of owner
     */
    public String getOwner() {
        if(seatStatus.equals(CONSTANTS.seatStatus.TAKEN)){
            return owner;
        }
        else{
            System.out.println("The seat have not been occupied");
            return " ";
        }
    }
}