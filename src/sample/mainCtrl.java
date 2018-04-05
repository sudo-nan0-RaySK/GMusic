package sample;

import com.jfoenix.controls.JFXButton;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class mainCtrl implements Initializable{
    ObservableList<MusicItem> data = FXCollections.observableArrayList();
    MusicItem currentlyLoaded;
    MediaPlayer mediaPlayer;
    boolean playing=false;
    ArrayList<Integer> arr = new ArrayList<>();
    @FXML
    JFXButton playBtn;
    @FXML
    TextField searchBar;
    @FXML
    Label nameLabel;
    @FXML
    Label genreLabel;
    @FXML
    Label artistLabel;
    @FXML
    Label durationLabel;
    @FXML
    Button loadBtn;
    @FXML
    Button frndsBtn;
    @FXML
    ListView listView;
    @FXML
    Button searchBtn;
    @FXML
    Button pplBtn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            File f = new File("currentUser.bin");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            AccountObject accountObject= (AccountObject)ois.readObject();
            ObservableList<MusicItem> observableList;
            LinkedList<MusicItem> musicItemLinkedList= accountObject.getSongs();

            SplayTree splayTree= new SplayTree();
            HashMap<Integer,MusicItem> hashMap= new HashMap<>();

            for(int i=0; i<musicItemLinkedList.size(); i++)
            {
                hashMap.put(hashCode(musicItemLinkedList.get(i).getName()),musicItemLinkedList.get(i));
            }

            for(int i:hashMap.keySet())
            {
                splayTree.insert(i);
            }

            SongsStore songsStore= new SongsStore(splayTree,accountObject,hashMap);

            File fl = new File("songsdump.bin");
            FileOutputStream fileOutputStream= new FileOutputStream(fl);
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(songsStore);

            FileInputStream fileInputStream= new FileInputStream(fl);
            ObjectInputStream objectInputStream= new ObjectInputStream(fileInputStream);

            SongsStore store= (SongsStore)objectInputStream.readObject();

            SplayTree spt= store.getSplayTree();

            HashMap<Integer,MusicItem> hmp= store.getHashMap();

            preOrderTraversal(spt.root);

            for(int i=0; i<arr.size(); i++)
            {
                System.out.println(arr.get(i));
            }

            for(int i=0; i<arr.size(); i++)
            {
                data.add(hmp.get(arr.get(i)));
                //listView.getItems().add(hmp.get(arr.get(i)));
            }

            listView.setItems(FXCollections.observableArrayList(data));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    public void doSearch()
    {
        //listView.getItems().clear();
        //data=FXCollections.observableArrayList( new MusicItem("<--Songs-->",0f,"NIL","NIL"));
        //data.add(new MusicItem("WeDont",4.0f,"Charlie","pop"));
        listView.setItems(data);
       try
        {

            File fl = new File("songsdump.bin");
            FileInputStream fileInputStream= new FileInputStream(fl);
            ObjectInputStream objectInputStream= new ObjectInputStream(fileInputStream);

            SongsStore store= (SongsStore)objectInputStream.readObject();

            String query= searchBar.getText();

            SplayTree spt= store.getSplayTree();


            HashMap<Integer,MusicItem> hmp= store.getHashMap();

            if(hmp.keySet().contains(hashCode(query))) {
            SplayNode node= spt.search(hashCode(query));
            System.out.println(spt.contains(hashCode(query)));
            searchBar.setText("");

                System.out.println(hmp.get(node.element));
                arr.clear();
                preOrderTraversal(spt.root);
                listView.getItems().clear();

                for (int i = 0; i < arr.size(); i++) {
                    System.out.println(arr.get(i));
                }

                for (int i = 0; i < arr.size(); i++) {
                    data.add(hmp.get(arr.get(i)));
                }
            }
            else
            {
                searchBar.setText("");
                System.err.println("Invalid Song query");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "No Songs found !", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            }
        }
        catch(Exception e)
        {

        }
        listView.setItems(data);
    }
    @FXML
    public void openFrndsList(ActionEvent actionEvent)throws IOException
    {
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("frndsList.fxml"));
        window.setTitle("Hello World");
        window.setScene(new Scene(root));
        window.show();
    }
    @FXML
    public void openPplList(ActionEvent actionEvent)throws IOException
    {
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("knownPpl.fxml"));
        window.setTitle("Hello World");
        window.setScene(new Scene(root));
        window.show();
    }
    @FXML
    public void loadSong()
    {
        currentlyLoaded= (MusicItem) listView.getSelectionModel().getSelectedItem();
        nameLabel.setText(currentlyLoaded.getName());
        genreLabel.setText(currentlyLoaded.getGenre());
        durationLabel.setText(String.valueOf(currentlyLoaded.getDuration()));
        artistLabel.setText(currentlyLoaded.getArtist());
        System.out.println(currentlyLoaded);

    }

    @FXML
    public void playSong()
    {

        if(playing==false){

            try{

                if(currentlyLoaded==null) {
                    System.err.println("Select a song noob!");
                    return;
                }
                String bip = currentlyLoaded.getName()+".mp3";
                Media hit = new Media(new File(bip).toURI().toString());
                mediaPlayer = new MediaPlayer(hit);
                playing=true;
                playBtn.setText("||");
                mediaPlayer.play();


            }  catch(Exception e){
                System.out.println(e);
            }

           // mediaPlayer.stop();

        }
        else
        {
            playing=false;
            mediaPlayer.stop();
            playBtn.setText("▶");
        }

    }

    @FXML
    public void loadSuggested(ActionEvent actionEvent)throws IOException
    {
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("sugSongs.fxml"));
        window.setTitle("Hello World");
        window.setScene(new Scene(root));
        window.show();
    }

    public void preOrderTraversal(SplayNode sp)
    {
        if(sp!=null)
        {
            arr.add(sp.element);
            preOrderTraversal(sp.left);
            preOrderTraversal(sp.right);
        }
    }

    public static int hashCode(String str)
    {
        int h = 0;

        for (int i = 0; i < str.length(); i++)
            h = (h * 31) + str.charAt(i);

        return h;
    }

}

