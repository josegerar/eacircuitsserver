/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStaticBD;

import java.io.Serializable;
import java.util.ArrayList;
import models.Users;

/**
 *
 * @author SOLIMAR
 */
public class UsersConnected implements Serializable {

    public static ArrayList<Users> USERSCONNECTED = new ArrayList<>();

    public static void printUsers() {
        UsersConnected.USERSCONNECTED.forEach((user) -> {
            System.out.println(user.toString());
        });
    }

    public static boolean isConnected(String email) {
        return UsersConnected.USERSCONNECTED.stream().anyMatch((users) -> (users.getEmail_user().equals(email)));
    }

    public static void updateListUsersConnected() {
        UsersConnected.USERSCONNECTED.forEach((user) -> {
            long tiempo = System.currentTimeMillis();
            if (tiempo > user.getExpires_in()) {
                UsersConnected.USERSCONNECTED.remove(user);
            }
        });
    }

}
