package src.Control;


import java.util.ArrayList;
import java.util.Scanner;

import src.Boundary.MainMenu;
import src.Boundary.AdminMenu.AdminMainMenu;
import src.Entity.Admin;
import src.utils.SerializeDB;

public class Admin_Controller {
    /**
     * data file location
     */
    public final static String FILENAME = "././data/admin.txt";
    final static int CHANGE_USERNAME = 1;
    final static int CHANGE_PASSWORD = 2;

    public Admin_Controller(){

    }

    /**
	 * Gets all admins
	 * @return ArrayList of admins
	 */
    public static ArrayList<Admin> getAllAdmins(){
        ArrayList<Admin> adminList = new ArrayList<Admin>();
        if(SerializeDB.readSerializedObject(FILENAME) == null){
            return adminList;
        }
        adminList = (ArrayList<Admin>) SerializeDB.readSerializedObject(FILENAME);
        return adminList;
    }

    /**
	 * Add admin to database
     * @param Username username of admin
     * @param Password password of admin
	 * @return flag indicate whether the add is successful or not
	 */
    public static boolean addAdmin(String Username, String Password){
        Admin Admin = new Admin(Username, Password);

        ArrayList<Admin> adminList = new ArrayList<Admin>();

        adminList = getAllAdmins();

        boolean exist = false;

        if(adminList != null){
            for(int i=0; i<adminList.size(); i++){
                if(adminList.get(i).getUsername().equals(Username)){
                    exist = true;
                }
            }
        } else{
            adminList = new ArrayList<Admin>();
        }

        if(exist==false){
            adminList.add(Admin);
            SerializeDB.writeSerializedObject(FILENAME, adminList);
        }

        return !exist;
    }

    /**
	 * Delete admin to database
     * @param Username username of admin
	 */
    public static void deleteAdmin(String Username) {
        ArrayList<Admin> Data = new ArrayList<Admin>();

        Data = getAllAdmins();
        
        ArrayList<Admin> UpdateData = new ArrayList<Admin>();
        Admin a;

        for (int i = 0; i < Data.size(); i++) {
            a = Data.get(i);
            if (!(a.getUsername() == Username)) {
                UpdateData.add(a);
            }
        }

        SerializeDB.writeSerializedObject(FILENAME, UpdateData);
    }
    
    /**
	 * Update admin
     * @param choice option of update
     * @param Username username of admin
     * @param newData new data for admin
	 */
    public static void updateAdmin(int choice, String Username, String newData) {
        ArrayList<Admin> Data = getAllAdmins();
        ArrayList<Admin> UpdateData = new ArrayList<Admin>();
        Admin m;

        for (int i = 0; i < Data.size(); i++) {
            m = Data.get(i);
            if (m.getUsername() == Username) {
                switch (choice) {
                    case CHANGE_USERNAME:
                        m.setUsername(newData);
                        break;
                    case CHANGE_PASSWORD:
                        m.setPassword(newData);
                        break;
                    default:
                        break;
                }
            }
            UpdateData.add(m);
        }

        SerializeDB.writeSerializedObject(FILENAME, UpdateData);
    }

    /**
	 * Sign In admin
     * @param Username username of admin
     * @param Password password of admin
	 */
    public static void signIn(String Username, String Password){
        ArrayList<Admin> Data = new ArrayList<Admin>();

        Data = getAllAdmins();

        boolean exist = false;

        for(int i=0; i<Data.size(); i++){
            if(Data.get(i).getUsername().equals(Username) && Data.get(i).getPassword().equals(Password)){
                exist = true;
            }
        }
        
        if(exist){
			AdminMainMenu.displayString("Login Successfuly \n");
			AdminMainMenu.load();
		}
		else{
			AdminMainMenu.displayString("Login Fail\n");
			MainMenu.load();
		}
    }

    /**
	 * Sign Up admin
     * @param Username new username of admin
     * @param Password new password of admin
	 */
    public static void signUp(String Username, String Password){
        boolean status = addAdmin(Username, Password);

		if(status){
			AdminMainMenu.displayString("Sign Up Successfuly \n");
			AdminMainMenu.load();
		}
		else{
			AdminMainMenu.displayString("Admin Existed \n");
            MainMenu.load();
		}
    }

    /**
	 * Config Setting method that take input from admin side
	 */
    public static void configSetting(){
        AdminMainMenu.displayString("-----------Configure System---------\n");
        AdminMainMenu.displayString("Choose from one of the following options: \n" +
                "1. Update Ticket Price \n" + 
                "2. Holidays \n" +
                "3. Configure Accessibility of Top 5 Listing \n" +
                "4. Back \n");
        
        int choice = AdminMainMenu.getChoice(4);

        switch(choice){
            case 1:
                PriceScheme_Controller.configurePrice();
                break;
            case 2:
                Holiday_Controller.configureHolidays();
                break;
            case 3:
                Movie_Controller.configureTop5();
                break;
            case 4:
                AdminMainMenu.load();
                break;
        }
    }    
}
