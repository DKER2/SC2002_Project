package src.Control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import src.Entity.Admin;
import src.utils.SerializeDB;

public class Admin_Controller {
    public final static String FILENAME = "././data/admin.txt";
    public final static String SEPARATOR = "|";

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
}
