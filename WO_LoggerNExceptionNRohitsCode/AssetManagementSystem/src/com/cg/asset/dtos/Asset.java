package com.cg.asset.dtos;

public class Asset {
	
	private int assetId;
	private String assetName;
	private String assetDesc;
	private int quantity;
	private String status;
	
	
	@Override
	public String toString() {
		return "Asset [assetId=" + assetId + ", assetName=" + assetName
				+ ", assetDesc=" + assetDesc + ", quantity=" + quantity
				+ ", status=" + status + "]";
	}

	
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetDesc() {
		return assetDesc;
	}
	public void setAssetDesc(String assetDesc) {
		this.assetDesc = assetDesc;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Asset() {
		super();
	}
	
	public Asset(int assetId, String assetName, String assetDesc, int quantity,
			String status) {
		super();
		this.assetId = assetId;
		this.assetName = assetName;
		this.assetDesc = assetDesc;
		this.quantity = quantity;
		this.status = status;
	}
	
	
	
}
