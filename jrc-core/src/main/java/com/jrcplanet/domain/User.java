package com.jrcplanet.domain;

import java.util.Date;

/**
 * 用户
 * @author rxb
 *
 * 2015年11月30日
 */
public class User extends BaseEntity {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 手机号
	 */
	private String mobilePhone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 盐
	 */
	private String salt;
	/**
	 * 是否锁定
	 */
	private Boolean locked=false;
	/**
	 * 注册时间
	 */
	private Date registerDate;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", realName='" + realName + '\'' +
				", gender=" + gender +
				", mobilePhone='" + mobilePhone + '\'' +
				", email='" + email + '\'' +
				", salt='" + salt + '\'' +
				", locked=" + locked +
				", registerDate=" + registerDate +
				'}';
	}
}
