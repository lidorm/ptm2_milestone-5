package view;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import javafx.scene.control.TextArea;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import interpeter.Interpeter;
import interpeter.Lexer;
import interpeter.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;

import javafx.stage.Stage;
import server.SimValuePath;

public class MainWindowController {

     @FXML
     TextArea scriptPane;
     MapDisplayer md;
    public void autoPilotTextShow() {
        FileChooser fc = new FileChooser();
        fc.setTitle("autopilot script text");
        fc.setInitialDirectory(new File("./resources"));
        File c = fc.showOpenDialog(null);
        if(c!=null) {
            this.ShowText(c);
        }
    }

    public void ShowText(File f) {
        scriptPane.setWrapText(true);
        try {
            Scanner s = new Scanner(f);
            while(s.hasNextLine()) {
                scriptPane.appendText(s.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void openConnectPopupWindow() throws Exception{
        ConnectPopupWindow cpw = new ConnectPopupWindow();
        cpw.start(new Stage());
    }
    
    public void takeTextOfScriptAndRun(){
    	
    		if(scriptPane.getText() != null) {
        		runScript(scriptPane.getText().split("\n"));
    			// System.out.println(Arrays.asList(scriptPane.getText().split("\n")));
        	}  	
     }
    
    private int runScript(String[] lines) {
    	Interpeter interpeter = new Interpeter();
    	
    	@SuppressWarnings("static-access")
		Thread interpretScript = new Thread(() -> {
    		interpeter.interpret(lines);
		});
		interpretScript.start();
		return 2;
    }	
    
    public void loadMap() {
    		md = new MapDisplayer();
    		FileChooser fc = new FileChooser();
            fc.setTitle("map");
            fc.setInitialDirectory(new File("./resources"));
            File c = fc.showOpenDialog(null);
            if(c!=null) {
            	this.md.generateMap(c);      
            }
    }
}



