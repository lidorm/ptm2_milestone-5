package view;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import commands.Command;
import commands.ConnectCommand;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ConnectPopupWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button button = new Button();
        button.setText("connect");
        button.setTranslateX(-120);
        button.setTranslateY(70);

        Button cancel = new Button();
        cancel.setText("cancel");
        cancel.setTranslateX(120);
        cancel.setTranslateY(70);

        Label ip = new Label("IP:");
        ip.setTranslateX(-120);
        ip.setTranslateY(-70);

        TextField ipTF = new TextField();
        ipTF.setPromptText("Type the ip here.");
        ipTF.setTranslateX(-10);
        ipTF.setTranslateY(-70);
        ipTF.setMaxSize(150,10);


        TextField portTF = new TextField();
        portTF.setPromptText("Type the port here.");
        portTF.setTranslateX(-10);
        portTF.setTranslateY(-30);
        portTF.setMaxSize(150,10);

        Label port = new Label("PORT:");
        port.setTranslateX(-120);
        port.setTranslateY(-30);

        Label error = new Label();
        error.setTranslateX(-10);
        error.setTranslateY(20);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event){
                String ip=null;
                String port=null;


                if ((ipTF.getText() != null && !ipTF.getText().isEmpty())){
                    ip = ipTF.getText();
                }

                if ((portTF.getText() != null && !portTF.getText().isEmpty())) {
                    port = portTF.getText();
                }
                if(ip != null && port != null) {
                   
                    primaryStage.close();

                }else{
                    error.setText("Invalid ip or port, please try again.");
                }
            }




        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);
        root.getChildren().add(cancel);
        root.getChildren().add(ip);
        root.getChildren().add(port);
        root.getChildren().add(ipTF);
        root.getChildren().add(portTF);
        root.getChildren().add(error);
        Scene scene = new Scene(root, 350, 250);

        primaryStage.setTitle("Connect to server");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
