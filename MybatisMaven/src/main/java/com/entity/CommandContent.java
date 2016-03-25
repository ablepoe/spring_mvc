package com.entity;

/**
 * 
 * @author hanliang
 * command_content实体对应类
 */
public class CommandContent {

	private int id;
	private String content;
	private String command_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommand_id() {
		return command_id;
	}
	public void setCommand_id(String command_id) {
		this.command_id = command_id;
	}
}
