package org.model;

public class WardModel extends CityModel {
	private int wardId;
	private String wardName;
	private String wardPinCode;

	public WardModel() {

	}

	public WardModel(String wardName, int wardId, String wardPinCode) {
		this.wardId = wardId;
		this.wardName = wardName;
		this.wardPinCode = wardPinCode;
	}

	public int getWardId() {
		return wardId;
	}

	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getWardPinCode() {
		return wardPinCode;
	}

	public void setWardPinCode(String wardPinCode) {
		this.wardPinCode = wardPinCode;
	}

}
