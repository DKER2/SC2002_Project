package src.Control;

import java.util.ArrayList;

import src.Entity.Admin;
import src.utils.SerializeDB;

public class Admin_Controller {
    public final static String FILENAME = "././data/admin.txt";
    public final static String SEPARATOR = "|";
    final static int CHANGE_USERNAME = 1;
    final static int CHANGE_PASSWORD = 2;

    public Admin_Controller(){

    }

    public static ArrayList<Admin> getAllAdmins(){
        ArrayList<Admin> adminList = new ArrayList<Admin>(SerializeDB.readSerializedObject(FILENAME));
        return adminList;
    }

    public static void addAdmins(String Username, String Password){
        Admin Admin = new Admin(Username, Password);

        ArrayList<Admin> Data = new ArrayList<Admin>();

        Data = getAllAdmins();

        Data.add(Admin);

        SerializeDB.writeSerializedObject(FILENAME, Data);
    }

    public void deleteAdmin(String Username) {
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

    public void updateAdmin(int choice, String Username, String newData) {
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
}
