/**
 * Copyright (c) 2015, Yuriy Stul. All rights reserved
 */
package com.stulsoft.test.yslrkeyword.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.stulsoft.yslrkeyword.model.Keyword;

/**
 * @author Yuriy Stul
 *
 */
public class KeywordTest {

	/**
	 * Test method for {@link com.stulsoft.yslrkeyword.model.Keyword#KeyWord(java.lang.String)}.
	 */
	@Test
	public void testKeyWord() {
		Keyword key = new Keyword(0, "keyword",null);
		assertNotNull(key);
	}
}
