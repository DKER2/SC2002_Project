package src.Control;

import java.io.IOException;
import java.util.ArrayList;

import src.Entity.Admin;

public class Test {
    public static void main(String args[]){
        //Admin_Controller.addAdmin("Phuong", "123");

        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(5);
        a.add(6);
        a.add(7);
        a.add(9);
        ArrayList<Integer> b = (ArrayList) a.clone();
        b.set(1, 100);
        for(int i=0; i<4; i++){
            System.out.println(b.get(i));
        }

    }
}
