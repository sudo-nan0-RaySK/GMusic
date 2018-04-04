package sample;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class AccountObject implements Serializable {
    String name;
    String pass;
    LinkedList <MusicItem> songs;
    LinkedList <AccountObject> friends;
    int age;
    int key;

    public AccountObject(String name,String pass, LinkedList<MusicItem> songs,int age,LinkedList<AccountObject> friends) {
        this.name = name;
        this.songs = songs;
        this.pass=pass;
        this.key = (int)Math.random();
        this.age=age;
        this.friends=friends;
    }

    public String getName() {
        return name;
    }

    public LinkedList<MusicItem> getSongs() {
        return songs;
    }

    public String getPass() {
        return pass;
    }

    public int getKey() {
        return key;
    }

    public LinkedList<AccountObject> getFriends() {
        return friends;
    }

    @Override
    public String toString()
    {
        return this.getName();
    }
}
