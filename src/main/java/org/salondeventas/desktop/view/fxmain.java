package org.salondeventas.desktop.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class fxmain extends Application {
	MenuBar menuBar = new MenuBar();
	Menu menu = new Menu("First menu");
	MenuItem firstmenuitem = new MenuItem("FirstMenu Item");
	Menu secondmenuitem = new Menu("SecondMenu Item");
	MenuItem thirdmenuitem = new MenuItem("ThirdMenu Item");
	MenuItem fourthmenuitem = new MenuItem("ThirdMenu Item");
	MenuItem firstsubmenu = new MenuItem("FirstSubMenu");
	MenuItem secondsubmenu = new MenuItem("SeconSubMenu");
	CustomMenuItem slide = new CustomMenuItem(new Slider());

	@Override
	public void start(Stage primaryStage) {

		menuBar.getMenus().add(menu);
		menu.getItems().addAll(firstmenuitem, secondmenuitem, thirdmenuitem, new SeparatorMenuItem(), slide);
		secondmenuitem.getItems().addAll(firstsubmenu, secondsubmenu);

		Group root = new Group();
		root.getChildren().add(menuBar);
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		Scene scene = new Scene(root, 300, 250, Color.BLUEVIOLET);

		primaryStage.setTitle("Menu In Fx");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}