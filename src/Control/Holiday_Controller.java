package src.Control;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import src.Boundary.AdminMenu.AdminMainMenu;
import src.Entity.Holiday;
import src.utils.SerializeDB;

public class Holiday_Controller {
    /**
     * data file location
     */
    public final static String FILENAME = "././data/holidays.txt";

    public Holiday_Controller(){

    }

    /**
	 * get all Holidays 
     * @return Array list of holiday
	 */
    public static ArrayList<Holiday> getAllHolidays(){
        ArrayList<Holiday> cineplexList = new ArrayList<Holiday>();
        if(SerializeDB.readSerializedObject(FILENAME) == null){
            return cineplexList;
        }
        cineplexList = (ArrayList<Holiday>) SerializeDB.readSerializedObject(FILENAME);
        return cineplexList;
    }

    /**
	 * add Holidays 
     * @return Array list of holiday
	 */
    public static boolean addHoliday(String name, Date time){
        Holiday Holiday = new Holiday(time, name);

        ArrayList<Holiday> HolidayList = new ArrayList<Holiday>();

        HolidayList = getAllHolidays();

        boolean exist = false;

        if(HolidayList != null){
            for(int i=0; i<HolidayList.size(); i++){
                if(HolidayList.get(i).getName().equals(HolidayList)){
                    exist = true;
                }
            }
        } else{
            HolidayList = new ArrayList<Holiday>();
        }

        if(exist==false){
            HolidayList.add(Holiday);
            AdminMainMenu.displayString("Added Holiday Sucessfully\n");
            SerializeDB.writeSerializedObject(FILENAME, HolidayList);
        }

        return !exist;
    }

    /**
	 * Save Holidays 
     * @return Array list of holiday
	 */
    public static void saveHolidays(ArrayList<Holiday> holidays){
        SerializeDB.writeSerializedObject(FILENAME, holidays);
    }

    /**
	 * Configure Holday
	 */
    public static void configureHolidays(){
        ArrayList<Holiday> holidayList = Holiday_Controller.getAllHolidays();
        AdminMainMenu.displayString("Holiday List:");
        for(int i=0; i<holidayList.size(); i++){
            AdminMainMenu.displayString("\n" + (i+1) + "." + holidayList.get(i).getName() + "     " + holidayList.get(i).getDatetime());
        }

        AdminMainMenu.displayString("\n----------Holiday Configure---------\n");
        AdminMainMenu.displayString("1. Update date \n"+
        "2. Add date \n"+
        "3. Go back\n");
        int choice = AdminMainMenu.getChoice(3);
        
        switch(choice){
            case 1:
                if(holidayList.size()==0){
                    AdminMainMenu.displayString("There are no holiday to update\n");
                    break;
                }
                AdminMainMenu.displayString("----------Holiday Configure---------\n");
                AdminMainMenu.displayString("Holiday List:\n");
                for(int i=0; i<holidayList.size(); i++){
                    AdminMainMenu.displayString((i+1) + "." + holidayList.get(i).getName() + "     " + holidayList.get(i).getDatetime() + "\n");
                }
                choice = AdminMainMenu.getChoice(holidayList.size()+1);

                AdminMainMenu.displayString("----------Holiday Configure---------\n");
                AdminMainMenu.displayString("1. Update name of holiday \n"+
                "2. Update date \n"+
                "3. Go back\n");

                int choice1 = AdminMainMenu.getChoice(3);

                if(choice1==1){
                    AdminMainMenu.displayString("Type in new Holiday name\n");
                    String name = AdminMainMenu.getString();
                    Holiday changeHoliday = holidayList.get(choice-1);
                    changeHoliday.setName(name);
                    holidayList.set(choice-1, changeHoliday);
                    SerializeDB.writeSerializedObject(FILENAME, holidayList);
                    configureHolidays();
                }
                else if (choice1==2){
                    AdminMainMenu.displayString("Type in new Holiday date\n");
                    SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");  
                    Date time = new Date();
                    try{
                        time = formatter1.parse(AdminMainMenu.getString());
                        Holiday changeHoliday = holidayList.get(choice-1);
                        changeHoliday.setDatetime(time);
                        holidayList.set(choice-1, changeHoliday);
                        SerializeDB.writeSerializedObject(FILENAME, holidayList);
                        configureHolidays();
                    }catch(Exception ex){
                        AdminMainMenu.displayString("Follow Datetime format dd/MM/YYYY\n");
                    }
                }
                else{
                    configureHolidays();
                }

                break;

            case 2:
                AdminMainMenu.displayString("----------Holiday Configure---------\n");
                AdminMainMenu.displayString("Type in new Holiday name\n");
                String name = AdminMainMenu.getString();
                AdminMainMenu.displayString("Type in Date\n");
                SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");  
                Date time = new Date();
                try{
                    time = formatter1.parse(AdminMainMenu.getString());
                    Holiday_Controller.addHoliday(name, time);
                    holidayList = Holiday_Controller.getAllHolidays();
                    AdminMainMenu.displayString("Holiday List:\n");
                    for(int i=0; i<holidayList.size(); i++){
                        AdminMainMenu.displayString((i+1) + "." + holidayList.get(i).getName() + "     " + holidayList.get(i).getDatetime() + "\n");
                    }
                    configureHolidays();
                }catch(Exception ex){
                    AdminMainMenu.displayString("Follow Datetime format dd/MM/YYYY \n");
                }
                break;
            case 3:
                Admin_Controller.configSetting();
                break;
        }
    }
    
     /**
	 * Check if a date is holiday or not
     * @param date date to check
	 */
    public static boolean isHoliday(Date date){
        boolean isHoliday = false;
        ArrayList<Holiday> holidayList = Holiday_Controller.getAllHolidays();
        for(int i=0; i<holidayList.size(); i++){
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(date);
            cal2.setTime(holidayList.get(i).getDatetime());
            boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                            cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
            if(sameDay){
                isHoliday = sameDay;
                break;
            }
        }
        return isHoliday;
    }
}
