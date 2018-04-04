package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

public class suggestedSongsCtrl implements Initializable {

    ObservableList<MusicItem> data = FXCollections.observableArrayList();

    @FXML
    ListView songList;

    @FXML
    Button toHome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try
        {
            File fl = new File("currentUser.bin");
            FileInputStream fis= new FileInputStream(fl);
            ObjectInputStream ois= new ObjectInputStream(fis);

            AccountObject currentPlayer= (AccountObject)ois.readObject();
            System.out.println(currentPlayer.getName());
            System.out.println(currentPlayer.getSongs());
            LinkedList<AccountObject> frnds=currentPlayer.getFriends();

            /**Breadth First Traversal to get suggested songs**/

            HashSet<AccountObject> visitedFrnds= new HashSet<>();
            Queue<AccountObject> queue= new LinkedList<>();
            for(AccountObject frnd :frnds)
            {
                if(!visitedFrnds.contains(frnd))
                {
                    queue.add(frnd);
                }

                while (!queue.isEmpty())
                {
                    visitedFrnds.add(queue.peek());

                    LinkedList<MusicItem> frndSongs= queue.peek().getSongs();
                    for(MusicItem song:frndSongs)
                    {
                        if((!has(currentPlayer.getSongs(),song)&&(!has(data,song))))
                        {
                            //currentPlayer.getSongs().add(song);

                            data.add(song);
                        }
                    }

                    for(AccountObject fof:queue.peek().getFriends())
                    {
                        if(!visitedFrnds.contains(fof))
                        {
                            queue.add(fof);
                        }

                    }
                    queue.poll();
                }


            }
            songList.getItems().addAll(data);
        }
        catch(Exception e)
        {
                e.printStackTrace();
        }

    }

    @FXML
    public void destroyScene()
    {
        System.exit(0);
    }

    @FXML
    public void goToHome(javafx.event.ActionEvent actionEvent)throws IOException
    {
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        window.setTitle("Hello World");
        window.setScene(new Scene(root));
        window.show();
    }
    //Search utility
    public boolean has(LinkedList<MusicItem> l,MusicItem m)
    {
        for(MusicItem sng:l)
        {
            if(sng.getName().equals(m.getName()))
            {
                return true;
            }
        }
        return false;
    }

    public boolean has(ObservableList<MusicItem> l,MusicItem m)
    {
        for(MusicItem sng:l)
        {
            if(sng.getName().equals(m.getName()))
            {
                return true;
            }
        }
        return false;
    }


}
