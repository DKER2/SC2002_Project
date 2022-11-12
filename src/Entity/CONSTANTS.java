package src.Entity;

public class CONSTANTS {
    /**
     * enum for Showing Status of Movie
     */
    public static enum ShowingStatus{ COMINGSOON, PREVIEW, NOWSHOWING, ENDOFSHOWING }

    /**
     * Enum for Class of Cinema for differential pricing.
     */
    public static enum ClassOfCinema { GOLD, MAX, NORMAL }

    /**
     * Enum for Class of seat status
     */
    public static enum seatStatus { NOTEXIST, TAKEN, NOTTAKEN }

    /**
     * Enum for Class of censorship
     */
    public enum Censorship {G, PG, PG13, NC16, M18, R21}

    /**
     * Enum for Class of type of movie
     */
    public enum TypeOfMovie {DIGITAL, BLOCKBUSTER, _3D}
}
