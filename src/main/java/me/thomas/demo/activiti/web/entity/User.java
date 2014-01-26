package me.thomas.demo.activiti.web.entity;

import me.thomas.demo.activiti.web.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@Table(name = "demo_user")
public class User extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 6222832557692442272L;

	private String password;
	private boolean activity;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the activity
	 */
	public boolean isActivity() {
		return activity;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(boolean activity) {
		this.activity = activity;
	}

}
