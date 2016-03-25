package com.entity;

import java.util.List;

/**
 * 
 * @author hanliang
 * 前台交互类
 */
public class Message {

	private int id;
	private String command;
	private String description;
	private List<CommandContent> contents;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<CommandContent> getContents() {
		return contents;
	}
	public void setContents(List<CommandContent> contents) {
		this.contents = contents;
	}
}
