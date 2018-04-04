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

import javax.xml.ws.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class pplCtrl implements Initializable {
    ObservableList<AccountObject> data = FXCollections.observableArrayList();
    @FXML
    Button backToHome;
    @FXML
    ListView frndsList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            File fl = new File("currentUser.bin");
            FileInputStream fis = new FileInputStream(fl);
            ObjectInputStream ois= new ObjectInputStream(fis);

            AccountObject user= (AccountObject)ois.readObject();

            System.out.println(user.getFriends().get(0).getFriends().get(0));

            for(int i=0; i<user.getFriends().size();i++)
            {

                for(int j=0;j<user.getFriends().get(i).getFriends().size();j++)
                {
                    if(data.contains(user.getFriends().get(i).getFriends().get(j)))
                    {
                        continue;
                    }
                    else
                    {
                        data.add(user.getFriends().get(i).getFriends().get(j));
                    }
                }
            }
            frndsList.getItems().addAll(data);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


       // frndsList.getItems().addAll(data);
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
