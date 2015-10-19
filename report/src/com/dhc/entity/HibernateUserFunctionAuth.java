package com.dhc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * 
 * @author hanliang 20160617
 * -table REPORTFORM_USER_FUNC entity
 * -using hibernate mapping
 *
 */
@Entity(name = "REPORTFORM_USER_FUNC")
//@Entity
//@Table(name = "REPORTFORM_USER_FUNC")
public class HibernateUserFunctionAuth implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5772370798883809725L;

	@EmbeddedId //_PK
	private HibernateUserPrimaryKey primaryKey;

	@Column(name="AUTHORITY")
	private String authority;

	public HibernateUserPrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(HibernateUserPrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}
