package sample;

import java.io.*;
import java.util.LinkedList;

public class AccountGenerator {

    public static void main(String args[])throws IOException
    {

        AccountObject ac1= new AccountObject("ray","kan",new LinkedList<>(),19,new LinkedList<AccountObject>());
        ac1.getSongs().add(new MusicItem("Stronger",3.5f,"Kanye West","Rock"));
        ac1.getSongs().add(new MusicItem("WalkAlone",3.0f,"Green Day","Pop"));

        AccountObject ac2= new AccountObject("tom","riddle",new LinkedList<>(),24,new LinkedList<AccountObject>());
        ac2.getSongs().add(new MusicItem("WalkAlone",3.0f,"Green Day","Pop"));
        AccountObject ac3= new AccountObject("jay","derek",new LinkedList<>(),22,new LinkedList<AccountObject>());
        ac3.getSongs().add(new MusicItem("WeDont",4.0f,"Charlie Puth","Pop"));

        ac1.getFriends().add(ac2);
        ac2.getFriends().add(ac3);


        LinkedList<AccountObject> toStore= new LinkedList<>();
        toStore.add(ac1);
        toStore.add(ac2);
        toStore.add(ac3);

        File f= new File("Accounts.bin");

        FileOutputStream fos= new FileOutputStream(f);
        ObjectOutputStream oos= new ObjectOutputStream(fos);

        oos.writeObject(toStore);
    }
}
