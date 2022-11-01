package src.Control;

import java.util.ArrayList;

import src.Entity.Cinema;
import src.Entity.Cineplex;
import src.utils.SerializeDB;

public class Cineplex_Controller {
    public final static String FILENAME = "././data/cineplex.txt";

    public Cineplex_Controller(){

    }

    public static ArrayList<Cineplex> getAllAdmins(){
        ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
        if(SerializeDB.readSerializedObject(FILENAME) == null){
            return cineplexList;
        }
        cineplexList = (ArrayList<Cineplex>) SerializeDB.readSerializedObject(FILENAME);
        return cineplexList;
    }

    public static boolean addAdmin(String cineplexName,ArrayList<Cinema> cinemasList){
        Cineplex cineplex = new Cineplex(cineplexName, cinemasList);

        ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();

        cineplexList = getAllAdmins();

        boolean exist = false;

        if(cineplexList != null){
            for(int i=0; i<cineplexList.size(); i++){
                if(cineplexList.get(i).getCineplexName().equals(cineplexName)){
                    exist = true;
                }
            }
        } else{
            cineplexList = new ArrayList<Cineplex>();
        }

        if(exist==false){
            cineplexList.add(cineplex);
            SerializeDB.writeSerializedObject(FILENAME, cineplexList);
        }

        return !exist;
    }

}
