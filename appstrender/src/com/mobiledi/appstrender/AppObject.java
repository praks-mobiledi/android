package com.mobiledi.appstrender;

import java.io.Serializable;
import java.sql.Timestamp;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import android.graphics.drawable.Drawable;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppObject implements Serializable {

	@JsonIgnore
	int id = 0;

	String appName;
	int appUid;
	String carrier;
	String category;
	String deviceId;
	int phoneNum;
	Timestamp timeStamp;
	long sent = 0;
	long recieved = 0;
	@JsonIgnore
	Drawable icon;

	public long getRecieved() {
		return recieved;
	}

	public void setRecieved(long recieved) {
		this.recieved = recieved;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@JsonIgnore
	public Drawable getIcon() {
		return icon;
	}

	@JsonIgnore
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	public int getAppUid() {
		return appUid;
	}

	public void setAppUid(int appUid) {
		this.appUid = appUid;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public long getSent() {
		return sent;
	}

	public void setSent(long sent) {
		this.sent = sent;
	}

}
