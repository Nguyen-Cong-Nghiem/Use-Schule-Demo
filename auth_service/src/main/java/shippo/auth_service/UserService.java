package shippo.auth_service;

import com.avaje.ebean.EbeanServer;

import shippo.entity.User;
import shippo.global.PostgressDbConf;

import java.sql.*;
import java.util.List;

public class UserService extends Thread {
    public static void main(String[] args) {
        runable();
    }

    private static void deleteToken() {
        EbeanServer server = PostgressDbConf.getAuthDb();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("Timestamp:" + timestamp);

        List<User> list = server.find(User.class).where().le("expires_at", timestamp).findList();
        for (User user : list) {
            System.out.printf(user.getUsername() + "\n");
        }
        server.deleteAll(list);
    }

    public static void runable() {
        final long timeInteval = 60*60*1000;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Runnnn");
                    deleteToken();
                    try {
                        Thread.sleep(timeInteval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

}
