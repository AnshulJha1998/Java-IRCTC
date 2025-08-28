package org.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.entities.Ticket;
import org.entities.User;
import org.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;
import java.io.File;

public class UserBookingService{
    private User mainUser;

    private List<User> userList;

    private ObjectMapper objMapper = new ObjectMapper(); // used for mapping keys (here it will be used for user.json)

    private static final String USERS_PATH = "../localDb/users.json";

    public UserBookingService(User user) throws IOException {
      this.mainUser = user;
      File users = new File(USERS_PATH);  // mapping file since we are using a localDB only
      userList = objMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public  Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter((user)->{
            return user.getUserId().equals(mainUser.getUserId()) && UserServiceUtil.checkPassword(mainUser.getPassword(),user.getPassword());
        }).findFirst();

        return foundUser.isPresent();
    }

    public Boolean signUpUser(User user){
        try{
            userList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;

        }catch(IOException e){
             return Boolean.FALSE;
        }
    }

    public void saveUserListToFile() throws IOException{
        File userFile = new File(USERS_PATH);
        objMapper.writeValue(userFile,userList);
    }

    // json TO Object : Deserialize
    // Object TO json : Serialize

    public  void fetchBooking(){
        mainUser.printTickets();
    }

    public Boolean cancelBooking(String ticketId){
        List<Ticket> tickets = mainUser.getTicketsBooked();

        return Boolean.TRUE;
    }
}