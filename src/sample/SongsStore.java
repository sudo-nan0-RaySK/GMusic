package sample;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class SongsStore implements Serializable{
    SplayTree splayTree;
    AccountObject activeUser;
    HashMap<Integer,MusicItem> hashMap;

    public SongsStore(SplayTree splayTree, AccountObject activeUser,HashMap<Integer,MusicItem> hashMap) {
        this.splayTree = splayTree;
        this.activeUser = activeUser;
        this.hashMap=hashMap;
    }

    public SplayTree getSplayTree() {
        return splayTree;
    }

    public AccountObject getActiveUser() {
        return activeUser;
    }

    public HashMap<Integer, MusicItem> getHashMap() {
        return hashMap;
    }
}
