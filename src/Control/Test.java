package src.Control;

import java.io.IOException;
import java.util.ArrayList;

import src.Entity.Admin;

public class Test {
    public static void main(String args[]){
        //Admin_Controller.addAdmin("Phuong", "123");

        ArrayList<Admin> adminList = new ArrayList<Admin>();

        adminList = Admin_Controller.getAllAdmins();

        System.out.println(adminList.get(0).getPassword());

    }
}
