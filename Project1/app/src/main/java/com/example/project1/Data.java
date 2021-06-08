package com.example.project1;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Data implements Serializable {

    // A hashmap data structure for holding usernames and passwords pair
    HashMap <String, String> hmCredentials;

    public Data(){

        hmCredentials = new HashMap<>();

        // Adding some items into the hashmap table

        hmCredentials.put("AJ", "CoolDude1");
        hmCredentials.put("test", "1234");
    }


    // This method adds a new username and password to the hashmap
    public void AddCredential(String username, String password){
        hmCredentials.put(username, password);
    }

    // This method checks if username exists in the hashmap
    public Boolean CheckUsername(String username){
        Boolean  retval = true;
        // checks to see if the username is a key in the hashmap
        // and if it doesn't it sets retval to false
        if(!hmCredentials.containsKey(username))
            retval = false;

        return retval;
    }

    // This method checks a username and password combination is correct!
    public Boolean CheckCredentials(String username, String Password){
        Boolean  retval = true;
        // Write your code here
        if(hmCredentials.containsKey(username)) {
            if (!hmCredentials.get(username).equals(Password))
                retval = false;
        }
        if(!hmCredentials.containsKey(username))
            retval = false;

        return retval;
    }

}
