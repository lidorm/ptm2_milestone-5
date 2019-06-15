package view;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.control.TextArea;

import java.util.Arrays;
import java.util.Scanner;

import interpeter.Lexer;
import interpeter.Parser;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;

import javafx.stage.Stage;

public class MainWindowController {

     @FXML
     TextArea scriptPane;


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
    	
    	String[] args = {
    			"instrumentation/airspeed-indicator/indicated-speed-kt",
    			"instrumentation/altimeter/indicated-altitude-ft",
    			"instrumentation/altimeter/pressure-alt-ft",
    			"instrumentation/attitude-indicator/indicated-pitch-deg",
    			"instrumentation/attitude-indicator/indicated-roll-deg",
    			"instrumentation/attitude-indicator/internal-pitch-deg",
    			"instrumentation/attitude-indicator/internal-roll-deg",
    			"instrumentation/encoder/indicated-altitude-ft",
    			"instrumentation/encoder/pressure-alt-ft",
    			"instrumentation/gps/indicated-altitude-ft",
    			"instrumentation/gps/indicated-ground-speed-kt",
    			"instrumentation/gps/indicated-vertical-speed",
    			"instrumentation/heading-indicator/indicated-heading-deg",
    			"instrumentation/magnetic-compass/indicated-heading-deg",
    			"instrumentation/slip-skid-ball/indicated-slip-skid",
    			"instrumentation/turn-indicator/indicated-turn-rate",
    			"instrumentation/vertical-speed-indicator/indicated-speed-fpm",
    			"controls/flight/aileron",
    			"controls/flight/elevator",
    			"controls/flight/rudder",
    			"controls/flight/flaps",
    			"controls/engines/current-engine/throttle",
    			"engines/engine/rpm"	
    			};
    	
		
		Lexer lexer = new Lexer();
		Parser parser = new Parser(args);
		
		return (int) parser.parse(lexer.getLineAndAdd(lines));
    	}
    }



