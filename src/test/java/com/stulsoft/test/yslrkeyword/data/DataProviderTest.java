/**
 * Copyright (c) 2015, Yuriy Stul. All rights reserved
 */
package com.stulsoft.test.yslrkeyword.data;

import org.junit.Test;

import com.stulsoft.yslrkeyword.data.DataProvider;
import com.stulsoft.yslrkeyword.model.Keyword;

/**
 * @author Yuriy Stul
 *
 */
public class DataProviderTest {

	@Test
	public void test() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("==>main");
		DataProvider provider = new DataProvider();
		Keyword root = provider.load("LR-keywords.txt");
		// Keyword root = provider.load("lr-test01.txt");
		System.out.println("Number of keyword on 1st level: " + root.getSubkeys().size());


		outputTree(root);
		System.out.println("<==main");
	}

	private static void outputTree(final Keyword keyword) {
		if (keyword == null) {
			return;
		}
		if (keyword.getLevel() >= 0) {
			for (int i = 0; i < keyword.getLevel(); ++i) {
				System.out.print("\t");
			}
			System.out.println(keyword.getName());
			for (String alias : keyword.getAliases()) {
				for (int i = 0; i <= keyword.getLevel(); ++i) {
					System.out.print("\t");
				}
				System.out.println("{" + alias + "}");
			}
		}
		for (Keyword subkeyword : keyword.getSubkeys()) {
			outputTree(subkeyword);
		}
	}
}
