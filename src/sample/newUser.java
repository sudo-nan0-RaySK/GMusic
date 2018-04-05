package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class newUser implements Initializable {
    @FXML
    TextField fndKey;
    @FXML
    TextField userFld;
    @FXML
    TextField passFld;
    @FXML
    TextField ageFld;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void onSave(ActionEvent actionEvent)throws Exception
    {
        File f= new File("Accounts.bin");
        FileInputStream fis= new FileInputStream(f);
        ObjectInputStream objectInputStream= new ObjectInputStream(fis);
        HashMap<Integer,AccountObject> hashMap= (HashMap<Integer,AccountObject>)objectInputStream.readObject();
        String k= fndKey.getText();
        AccountObject frnd=hashMap.get(Integer.parseInt(k));
        fis.close();
        objectInputStream.close();
        FileOutputStream fos= new FileOutputStream(f);
        ObjectOutputStream oos= new ObjectOutputStream(fos);
        AccountObject newGuy= new AccountObject(userFld.getText(),passFld.getText(),new LinkedList<MusicItem>(),Integer.parseInt(ageFld.getText()),
                new LinkedList<AccountObject>());

        newGuy.getFriends().add(frnd);
        frnd.getFriends().add(newGuy);
        hashMap.put(Integer.parseInt(k),frnd);
        hashMap.put(hashCode(newGuy.getName()+newGuy.getPass()),newGuy);

        oos.writeObject(hashMap);

        oos.close();
        fos.close();

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.setTitle("Hello World");
        window.setScene(new Scene(root));
        window.show();

    }
    public static int hashCode(String str)
    {
        int h = 0;

        for (int i = 0; i < str.length(); i++)
            h = (h * 31) + str.charAt(i);

        return h;
    }
}
