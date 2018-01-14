package buythecheapest.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class View implements Initializable {

    @FXML
    private Button end;

    @FXML
    private Button start;

    @FXML
    public TextField url;

    @FXML
    private TextField email;

    @FXML
    private Circle circle;

    @FXML
    private PasswordField password;
  
	private boolean working=false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.start.setOnAction(event -> {
            this.working=true;
            this.circle.setFill(Color.RED);
            final Runnable taskThread = new MyRun(this.url.getText(), this.email.getText(), this.password.getText(), ()->this.working);
            final Thread nextThread = new Thread(taskThread);
            nextThread.start();
            
            this.url.setEditable(false);
            this.email.setEditable(false);
            this.password.setEditable(false);
        });
 
        this.end.setOnAction(event -> {
        	this.working=false;
            this.circle.setFill(Color.rgb(245, 240, 250));
            
            this.url.setEditable(true);
            this.email.setEditable(true);
            this.password.setEditable(true);
 
        });
		
	}//end initialize
}//end View
