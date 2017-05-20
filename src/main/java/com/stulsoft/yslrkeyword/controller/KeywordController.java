/**
 * Copyright (c) 2015, Yuriy Stul. All rights reserved
 */
package com.stulsoft.yslrkeyword.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.stulsoft.yslrkeyword.data.DataProvider;
import com.stulsoft.yslrkeyword.model.Keyword;

/**
 * @author Yuriy Stul
 *
 */
public class KeywordController {

	private final Image tagIcon = new Image(KeywordController.class.getResourceAsStream("/images/tag.png"));
	private final Image tagAliasIcon = new Image(KeywordController.class.getResourceAsStream("/images/tag-alias.png"));

	@FXML
	// ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML
	// URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	// fx:id="tree"
	private TreeView<String> tree; // Value injected by FXMLLoader

	@FXML
	// This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert tree != null : "fx:id=\"tree\" was not injected: check your FXML file 'keywordview.fxml'.";

		DataProvider provider = new DataProvider();
		// String fileName = "lr-test01.txt";
		String fileName = "LR-keywords.txt";

		Keyword root = provider.load(fileName);

		TreeItem<String> rootItem = new TreeItem<>(fileName);
		rootItem.expandedProperty().set(true);

		for (Keyword keyword : root.getSubkeys()) {
			fillTree(keyword, rootItem);
		}

		tree.setRoot(rootItem);
	}

	private void fillTree(final Keyword keyword, final TreeItem<String> parentTreeItem) {

		if (keyword == null) {
			return;
		}

		TreeItem<String> treeItem = new TreeItem<>(keyword.getName(), new ImageView(tagIcon));
		parentTreeItem.getChildren().add(treeItem);

		for (String alias : keyword.getAliases()) {
			TreeItem<String> aliasItem = new TreeItem<String>(alias, new ImageView(tagAliasIcon));
			treeItem.getChildren().add(aliasItem);
		}

		for (Keyword subKeyword : keyword.getSubkeys()) {
			fillTree(subKeyword, treeItem);
		}
	}
}
