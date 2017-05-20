/**
 * Copyright (c) 2015, Yuriy Stul. All rights reserved
 */
package com.stulsoft.yslrkeyword.model;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Yuriy Stul
 *
 */
public class Keyword {
	private int level;
	private String name;
	private Collection<String> aliases = new ArrayList<String>();
	private Collection<Keyword> subkeys = new ArrayList<Keyword>();
	private Keyword parentKeyword;

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException("name is null or empty.");
		}
		this.name = name;
	}

	/**
	 * @return the aliases
	 */
	public Collection<String> getAliases() {
		return aliases;
	}

	/**
	 * @param aliases
	 *            the aliases to set
	 */
	public void setAliases(Collection<String> aliases) {
		this.aliases = aliases;
	}

	/**
	 * @return the subkeys
	 */
	public Collection<Keyword> getSubkeys() {
		return subkeys;
	}

	/**
	 * @param subkeys
	 *            the sub keys to set
	 */
	public void setSubkeys(Collection<Keyword> subkeys) {
		this.subkeys = subkeys;
	}

	/**
	 * @return the parent keyword
	 */
	public Keyword getParentKeyword() {
		return parentKeyword;
	}

	/**
	 * @param parentKeyword
	 *            the parent keyword to set
	 */
	public void setParentKeyword(Keyword parentKeyword) {
		this.parentKeyword = parentKeyword;
	}

	/**
	 * @param level
	 * @param name
	 * @param parentKeyword
	 */
	public Keyword(final int level, final String name, final Keyword parentKeyword) {
		setLevel(level);
		setName(name);
		setParentKeyword(parentKeyword);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KeyWord [level=" + level + ", name=" + name + ", aliases=" + aliases 
				+ ", subkeys=" + (String)(subkeys == null ? "null" : String.valueOf(subkeys.size()))
				+ ", parentKeyword=" + (String)(parentKeyword == null? "null": parentKeyword.getName())
				+ "]";
	}
}
