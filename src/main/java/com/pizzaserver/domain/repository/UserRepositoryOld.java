package com.pizzaserver.domain.repository;

import com.pizzaserver.domain.entity.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserRepository {
    public UserRepository(){

    }

    private ArrayList<User> readUserDatabase(){
        String csvFile = "userDatabase.csv";
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ";";
        ArrayList<User> UserDatabase = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {
                String[] user = line.split(cvsSplitBy);
                UserDatabase.add(new User(user));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return UserDatabase;
    }

    private ArrayList<User> writeUserDatabase(){
        String csvFile = "userDatabase.csv";
        BufferedWriter bw = null;
        String line;
        String cvsSplitBy = ";";
        ArrayList<User> UserDatabase = new ArrayList<>();
        try {
            bw = new BufferedWriter(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {
                String[] user = line.split(cvsSplitBy);
                UserDatabase.add(new User(user));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return UserDatabase;
    }

    public Boolean registerUser(User user){
        ArrayList<User> userDatabase = readUserDatabase();
        for(User user1 : userDatabase){
            if(user.getLogin().equals(user1.getLogin())){
                return false;
            }
        }
        userDatabase.add(user);
        return true;
    }


}
