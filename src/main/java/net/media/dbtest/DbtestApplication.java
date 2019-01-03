package net.media.dbtest;

import net.media.dbtest.DAL.MongoDB;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbtestApplication {

    public static void main(String[] args) {
        System.out.println("DbtestApplication Running");
        MongoDB mongo = new MongoDB("localhost",27017,"Dbtest","temp");


    }

}

