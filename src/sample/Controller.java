package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Controller {
    public Button btnLogin;
    @FXML
    PasswordField pswrd;
    @FXML
    TextField userTxt;
    @FXML
    public void btnClicked(ActionEvent actionEvent)throws Exception
    {
        File f= new File("Accounts.bin");
        FileInputStream fis= new FileInputStream(f);
        ObjectInputStream ois= new ObjectInputStream(fis);

        String username=userTxt.getText();
        String password=pswrd.getText();

        System.out.println(password);

        HashMap<Integer,AccountObject> list= (HashMap<Integer,AccountObject>)ois.readObject();

        if(list.keySet().contains(Integer.parseInt(String.valueOf(hashCode(username+password)))))
        {
            AccountObject resutAcc= list.get(hashCode(username+password));
            File fl= new File("currentUser.bin");
            FileOutputStream fos=new FileOutputStream(fl);
            ObjectOutputStream oos= new ObjectOutputStream(fos);

            oos.writeObject(resutAcc);

            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
            window.setTitle("Hello World");
            window.setScene(new Scene(root));
            window.show();

        }
        else
        {
            System.err.println("UserName/Pass Invalid");
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
