package src.Control;

import java.util.ArrayList;

import src.Entity.CONSTANTS;
import src.Entity.Movie;
import src.Entity.Review;
import src.utils.SerializeDB;

public class Movie_Controller {
    public final static String FILENAME = "././data/movie.txt";
    final static int CHANGE_TITLE = 1;
    final static int CHANGE_SYSNOPSIS = 2;
    final static int CHANGE_DIRECTOR = 3;
    final static int CHANGE_ACTOR = 4;
    final static int CHANGE_SHOWINGSTATUS = 5;

    public static ArrayList<Movie> getAllMovies(){
        ArrayList<Movie> MovieList = new ArrayList<Movie>();
        if(SerializeDB.readSerializedObject(FILENAME) == null){
            return MovieList;
        }
        MovieList = (ArrayList<Movie>) SerializeDB.readSerializedObject(FILENAME);
        return MovieList;
    }

    public static boolean addMovie(String title, CONSTANTS.ShowingStatus showingStatus, String sysnopsis, String director, ArrayList<String> actorList){
        Movie Movie = new Movie(title, showingStatus, sysnopsis, director, actorList);

        ArrayList<Movie> MovieList = new ArrayList<Movie>();

        MovieList = getAllMovies();

        boolean exist = false;

        if(MovieList != null){
            for(int i=0; i<MovieList.size(); i++){
                if(MovieList.get(i).getTitle() == title){
                    exist = true;
                }
            }
        } else{
            MovieList = new ArrayList<Movie>();
        }

        if(exist==false){
            MovieList.add(Movie);
            SerializeDB.writeSerializedObject(FILENAME, MovieList);
        }

        return !exist;
    }

    public static void deleteMovie(String title) {
        ArrayList<Movie> Data = new ArrayList<Movie>();

        Data = getAllMovies();
        
        ArrayList<Movie> UpdateData = new ArrayList<Movie>();
        Movie a;

        for (int i = 0; i < Data.size(); i++) {
            a = Data.get(i);
            if (!(a.getTitle() == title)) {
                UpdateData.add(a);
            }
        }

        SerializeDB.writeSerializedObject(FILENAME, UpdateData);
    }

    public static void updateMovie(int choice, String title, Object newData) {
        ArrayList<Movie> Data = getAllMovies();
        ArrayList<Movie> UpdateData = new ArrayList<Movie>();
        Movie m;

        for (int i = 0; i < Data.size(); i++) {
            m = Data.get(i);
            if (m.getTitle() == title) {
                switch (choice) {
                    case CHANGE_TITLE:
                        m.setTitle((String) newData);
                        break;
                    case CHANGE_SYSNOPSIS:
                        m.setSynopsis((String) newData);
                        break;
                    case CHANGE_DIRECTOR:
                        m.setDirector((String) newData);
                        break;
                    case CHANGE_ACTOR:
                        m.setActorList((ArrayList<String>) newData);
                        break;
                    case CHANGE_SHOWINGSTATUS:
                        m.setShowingStatus((CONSTANTS.ShowingStatus) newData);
                        break;
                    default:
                        break;
                }
            }
            UpdateData.add(m);
        }

        SerializeDB.writeSerializedObject(FILENAME, UpdateData);
    }
}
