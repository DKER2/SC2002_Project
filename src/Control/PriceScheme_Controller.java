package src.Control;

import src.Boundary.AdminMenu.AdminMainMenu;
import src.Entity.CONSTANTS;
import src.Entity.Cinema;
import src.Entity.Movie;
import src.utils.SerializeDB;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class PriceScheme_Controller {
    private static final String FILENAME = "././data/price.txt";

    public static ArrayList<HashMap<String, Float>> getAllPriceScheme(){
        ArrayList<HashMap<String, Float>> priceSchemaList = new ArrayList<HashMap<String, Float>>();
        if(SerializeDB.readSerializedObject(FILENAME) == null){
            return priceSchemaList;
        }
        priceSchemaList = (ArrayList<HashMap<String, Float>>) SerializeDB.readSerializedObject(FILENAME);
        return priceSchemaList;
    }

    public static void configurePrice(){
        ArrayList<HashMap<String, Float>> priceSchemaList = new ArrayList<HashMap<String, Float>>();
        priceSchemaList = getAllPriceScheme();
        HashMap<String,Float> priceMap = priceSchemaList.get(0);
        Scanner sc = new Scanner(System.in);

        ArrayList<String> keyList = new ArrayList<String>();
        int iter = 0;
        SortedSet<String> keys = new TreeSet<>(priceMap.keySet());
        for (String key : keys) { 
            Float value = priceMap.get(key);
            keyList.add(key);
            System.out.println((iter+1) + ". " + key + ":" + " " + value);
            iter++;
        }

        System.out.println("Choose a price to update");
        int priceIndex = sc.nextInt()-1;
        String key = keyList.get(priceIndex);

        System.out.println("Type in new price to update");
        float newPrice = sc.nextFloat();

        priceMap.put(key, newPrice);

        priceSchemaList.set(0, priceMap);

        SerializeDB.writeSerializedObject(FILENAME, priceSchemaList);

        System.out.println("Update Price Successfully");

        AdminMainMenu.load();
    }   

    public static Float caculatePrice(CONSTANTS.ClassOfCinema classOfCinema, CONSTANTS.TypeOfMovie typeOfMovie, int age, Date showTime){
        ArrayList<HashMap<String, Float>> priceSchemaList = new ArrayList<HashMap<String, Float>>();
        priceSchemaList = getAllPriceScheme();
        HashMap<String,Float> priceMap = priceSchemaList.get(0);

        Float price = (float) 0;
        String key = "";
        if(age < 21){
            key = String.format("%s CINEMA && %s MOVIE && AGE < 21", classOfCinema.name(), typeOfMovie.name());
            price  = priceMap.get(key);
        }
        else{
            key = String.format("%s CINEMA && %s MOVIE && AGE > 21", classOfCinema.name(), typeOfMovie.name());
            price  = priceMap.get(key);
        }

        if(Holiday_Controller.isHoliday(showTime)){
            price += priceMap.get("HOLIDAY INCREASE PRICE");
        }

        return price;
    }

    public static void main(String args[]){
        HashMap<String, Float> priceMap = new HashMap<String, Float>();
        priceMap.put("NORMAL CINEMA && DIGITAL MOVIE && AGE < 21", (float) 10);
        priceMap.put("NORMAL CINEMA && DIGITAL MOVIE && AGE > 21", (float) 12.5);
        priceMap.put("NORMAL CINEMA && BLOCKBUSTER MOVIE && AGE < 21", (float) 12.5);
        priceMap.put("NORMAL CINEMA && BLOCKBUSTER MOVIE && AGE > 21", (float) 15);
        priceMap.put("NORMAL CINEMA && _3D MOVIE && AGE < 21", (float) 15);
        priceMap.put("NORMAL CINEMA && _3D MOVIE && AGE > 21", (float) 17.5);
        
        priceMap.put("MAX CINEMA && DIGITAL MOVIE && AGE < 21", (float) 12.5);
        priceMap.put("MAX CINEMA && DIGITAL MOVIE && AGE > 21", (float) 15);
        priceMap.put("MAX CINEMA && BLOCKBUSTER MOVIE && AGE < 21", (float) 15);
        priceMap.put("MAX CINEMA && BLOCKBUSTER MOVIE && AGE > 21", (float) 17.5);
        priceMap.put("MAX CINEMA && _3D MOVIE && AGE < 21", (float) 17.5);
        priceMap.put("MAX CINEMA && _3D MOVIE && AGE > 21", (float) 20);

        priceMap.put("GOLD CINEMA && DIGITAL MOVIE && AGE < 21", (float) 15);
        priceMap.put("GOLD CINEMA && DIGITAL MOVIE && AGE > 21", (float) 17.5);
        priceMap.put("GOLD CINEMA && BLOCKBUSTER MOVIE && AGE < 21", (float) 17.5);
        priceMap.put("GOLD CINEMA && BLOCKBUSTER MOVIE && AGE > 21", (float) 20);
        priceMap.put("GOLD CINEMA && _3D MOVIE && AGE < 21", (float) 20);
        priceMap.put("GOLD CINEMA && _3D MOVIE && AGE > 21", (float) 22.5);

        priceMap.put("HOLIDAY INCREASE PRICE", (float) 5);

        ArrayList<HashMap<String, Float>> priceList = new ArrayList<HashMap<String, Float>>();
        priceList.add(priceMap);

        SerializeDB.writeSerializedObject(FILENAME, priceList);
    }
}
