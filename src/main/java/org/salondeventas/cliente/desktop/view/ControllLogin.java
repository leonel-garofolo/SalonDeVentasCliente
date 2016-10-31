/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.view.login.FadeInLeftTransition;
import org.salondeventas.cliente.desktop.view.login.FadeInLeftTransition1;
import org.salondeventas.cliente.desktop.view.login.FadeInRightTransition;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Herudi
 */
public class ControllLogin extends Application implements Initializable {
	private Stage primaryStage;
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Text lblWelcome;
	@FXML
	private Text lblUserLogin;
	@FXML
	private Text lblUsername;
	@FXML
	private Text lblPassword;
	@FXML
	private Button btnLogin;
	@FXML
	private Text lblRudyCom;
	@FXML
	private Label lblClose;
	@FXML
	private static Scene scene;

	Stage stage;

	/**
	 * Initializes the controller class.
	 * 
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		anchorPane.getStylesheets().add(getClass().getResource("css/login.css").toExternalForm());
		Platform.runLater(() -> {
			new FadeInRightTransition(lblUserLogin).play();
			new FadeInLeftTransition(lblWelcome).play();
			new FadeInLeftTransition1(lblPassword).play();
			new FadeInLeftTransition1(lblUsername).play();
			new FadeInLeftTransition1(txtUsername).play();
			new FadeInLeftTransition1(txtPassword).play();
			new FadeInRightTransition(btnLogin).play();
			lblClose.setOnMouseClicked((MouseEvent event) -> {
				Platform.exit();
				System.exit(0);
			});
		});
		// TODO
	}

	@FXML
	private void aksiLogin(ActionEvent event) {
		if (txtUsername.getText().equals("herudi") && txtPassword.getText().equals("herudi")) {
			// config2 c = new config2();
			// c.newStage(stage, lblClose, "/herudi/view/formMenu.fxml", "Test
			// App", true, StageStyle.UNDECORATED, false);
		} else {
			// config2.dialog(Alert.AlertType.ERROR, "Error Login, Please Chek
			// Username And Password");
		}
	}

	public static void iniciar() {
		String[] args = {};
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		fxmlLoader.setResources(ResourceBundle.getBundle("i18n.ValidationMessages"));
		fxmlLoader.setRoot(root);

		fxmlLoader.setRoot(root);
		this.scene = new Scene(root);
		anchorPane.getStylesheets().add(getClass().getResource("css/login.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.resizableProperty().set(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});

		Platform.runLater(() -> {
			new FadeInRightTransition(lblUserLogin).play();
			new FadeInLeftTransition(lblWelcome).play();
			new FadeInLeftTransition1(lblPassword).play();
			new FadeInLeftTransition1(lblUsername).play();
			new FadeInLeftTransition1(txtUsername).play();
			new FadeInLeftTransition1(txtPassword).play();
			new FadeInRightTransition(btnLogin).play();
			lblClose.setOnMouseClicked((MouseEvent event) -> {
				Platform.exit();
				System.exit(0);
			});
		});

	}

	@FXML
	private void login(ActionEvent event) {
		if (txtUsername.getText().equals("admin") && txtPassword.getText().equals("123")) {
			anchorPane.setVisible(false);
			Stage stage = new Stage();
			Principal pri= new Principal();
			Scene scene = new Scene(pri);
			anchorPane.getStylesheets().add(getClass().getResource("css/login.css").toExternalForm());
			stage.setScene(scene);
			stage.resizableProperty().set(false);
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			stage.setX(bounds.getMinX());
			stage.setY(bounds.getMinY());
			stage.setWidth(bounds.getWidth());
			stage.setHeight(bounds.getHeight());		
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			stage.setOnHiding(new EventHandler<WindowEvent>() {

				public void handle(WindowEvent event) {
					System.exit(0);
				}
			});
		} else {
			Alert alert = new Alert(AlertType.ERROR);		
			alert.setHeaderText("Error Login, Please Chek Username And Password.");				
			alert.showAndWait();			
		}
	}

}
