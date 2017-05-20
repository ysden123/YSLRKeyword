/**
 * Copyright (c) 2015, Yuriy Stul. All rights reserved
 */
package com.stulsoft.yslrkeyword.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;

import com.stulsoft.yslrkeyword.model.Keyword;

/**
 * @author Yuriy Stul
 *
 */
public class DataProvider {

	public Keyword load(final String fileName) {

		// load
		Keyword root = new Keyword(-1, "root", null);
		String theFileName;
		try {
			theFileName = getAbsoluteFileName(fileName);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println(theFileName);
		File file = new File(theFileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			parse(root, reader);
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return root;
	}

	private void parse(final Keyword rootKeyword, final BufferedReader reader) {
		try {
			String line;
			Keyword parentKeyword = rootKeyword;
			Keyword lastKeyword = null;
			Keyword keyword;
			while (!StringUtils.isEmpty((line = reader.readLine()))) {
				if (line.contains("{")) {
					String alias = line.replace("\t", "").replace("{", "").replace("}", "");
					lastKeyword.getAliases().add(alias);
					continue;
				}

				int currentLevel = getCurrentLevel(line);

				line = line.replace("\t", "");
				if (lastKeyword != null && currentLevel != lastKeyword.getLevel()) {
					if (currentLevel > lastKeyword.getLevel()) {
						parentKeyword = lastKeyword;
						keyword = new Keyword(currentLevel, line, parentKeyword);
					} else {
						parentKeyword = getParent(currentLevel, parentKeyword);
						keyword = new Keyword(currentLevel, line, parentKeyword);
					}
				} else {
					keyword = new Keyword(currentLevel, line, parentKeyword);
				}

				lastKeyword = keyword;
				parentKeyword.getSubkeys().add(keyword);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int getCurrentLevel(final String line) {
		int currentLevel = line.split("\t").length - 1;
		return currentLevel;
	}

	/**
	 * Returns the absolute file name.
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public String getAbsoluteFileName(final String fileName) throws FileNotFoundException {
		if (fileName == null || fileName.isEmpty()) {
			throw new IllegalArgumentException("fileName is null or empty.");
		}

		URL url = getClass().getClassLoader().getResource(fileName);
		if (url == null) {
			throw new FileNotFoundException("File " + fileName + " not found.");
		}
		String absoluteFileName;
		try {
			absoluteFileName = Paths.get(url.toURI()).toFile().getAbsolutePath();
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
			throw new FileNotFoundException(e.getMessage());
		}
		return absoluteFileName;
	}

	private Keyword getParent(final int level, final Keyword keyword) {
		Keyword parent = keyword.getParentKeyword();
		while (parent != null && parent.getLevel() >= level) {
			parent = parent.getParentKeyword();
		}
		return parent;
	}
}
