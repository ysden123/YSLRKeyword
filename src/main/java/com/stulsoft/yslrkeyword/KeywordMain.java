/**
 * Copyright (c) 2015, Yuriy Stul. All rights reserved
 */
package com.stulsoft.yslrkeyword;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Yuriy Stul
 *
 */
public class KeywordMain extends Application  {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			VBox root = FXMLLoader.load(getClass().getResource("/fxml/keywordview.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("YSLRKeyword");
			primaryStage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
