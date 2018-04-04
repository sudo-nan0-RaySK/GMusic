package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class frndListCtrl implements Initializable {
    ObservableList<MusicItem> data = FXCollections.observableArrayList(
            new MusicItem("Stronger",3.5f,"Kanye West","Rock"));

    @FXML
    ListView frndsList;
    @FXML
    Button backToHome;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            File fl = new File("currentUser.bin");
            FileInputStream fis = new FileInputStream(fl);
            ObjectInputStream ois= new ObjectInputStream(fis);

            AccountObject user= (AccountObject)ois.readObject();

            for(int i=0; i<user.getFriends().size();i++)
            {
                frndsList.getItems().add(user.getFriends().get(i).name);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    public void loadHome(ActionEvent actionEvent)throws IOException
    {
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("mainView.fxml"));
        window.setTitle("Hello World");
        window.setScene(new Scene(root));
        window.show();
    }
}
