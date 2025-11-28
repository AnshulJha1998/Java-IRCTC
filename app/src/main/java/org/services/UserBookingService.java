package org.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.entities.Ticket;
import org.entities.User;
import org.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;
import java.io.File;

import org.entities.Train;

public class UserBookingService {

    private User mainUser;

    private List<User> userList;

    private ObjectMapper objMapper = new ObjectMapper(); // used for mapping keys (here it will be used for user.json)

    private static final String USERS_PATH = "../localDb/users.json";

    // Constructor 1 with users
    public UserBookingService(User user) throws IOException {
        this.mainUser = user;
        loadUsers();
    }

    // Constructor 2 without users
    public UserBookingService() throws IOException {
        loadUsers();
    }

    // To Load users even before login
    public List<User> loadUsers() {
        File users = new File(USERS_PATH);  // mapping file since we are using a localDB only
        return objMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter((user) -> {
            return user.getUserId().equals(mainUser.getUserId()) && UserServiceUtil.checkPassword(mainUser.getPassword(), user.getPassword());
        }).findFirst();

        return foundUser.isPresent();
    }

    public Boolean signUpUser(User user) {
        try {
            userList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;

        } catch (IOException e) {
            return Boolean.FALSE;
        }
    }

    public void saveUserListToFile() throws IOException {
        File userFile = new File(USERS_PATH);
        objMapper.writeValue(userFile, userList);
    }

    // json TO Object : Deserialize
    // Object TO json : Serialize
    public void fetchBooking() {
        mainUser.printTickets();
    }

    public List<List<Integer>> fetchSeats(Train train) {
        return train.getSeats();
    }

    public Boolean cancelBooking(String ticketId) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter the ticket id to cancel");
        ticketId = s.next();

        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("A valid ticket id is required!");
            return Boolean.FALSE;
        }
        List<Ticket> tickets = mainUser.getTicketsBooked();

        String finalTicketId1 = ticketId;  //Because strings are immutable
        boolean removed = tickets.removeIf(ticket -> ticket.getTicketId().equals(finalTicketId1));

        String finalTicketId = ticketId;
        tickets.removeIf(Ticket -> Ticket.getTicketId().equals(finalTicketId));
        if (removed) {
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return Boolean.TRUE;
        } else {
            System.out.println("No ticket found with ID " + ticketId);
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (IOException e) {
            System.out.println("Unable to fetch trains. Something went wrong!");
            return new ArrayList<>();
        }
    }
}
