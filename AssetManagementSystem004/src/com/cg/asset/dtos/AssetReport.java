package com.cg.asset.dtos;

import java.sql.Date;

public class AssetReport {
	private int assetId;
	private String empName;
	private int allocationId;
	private Date allocationDate;
	private Date releaseDate;
	private String assetName;
	



	public AssetReport(int assetId, String empName, int allocationId,
			Date allocationDate, Date releaseDate, String assetName) {
		super();
		this.assetId = assetId;
		this.empName = empName;
		this.allocationId = allocationId;
		this.allocationDate = allocationDate;
		this.releaseDate = releaseDate;
		this.assetName = assetName;
	}

	public AssetReport(int assetId) {
		super();
		this.assetId = assetId;
	}

	@Override
	public String toString() {
		return "AssetReport [allocationId=" + allocationId + ", assetId="
				+ assetId + ", allocationDate=" + allocationDate
				+ ", releaseDate=" + releaseDate + ", empName=" + empName
				+ ", assetName=" + assetName + "]";
	}

	public int getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(int allocationId) {
		this.allocationId = allocationId;
	}

	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}


	public Date getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(Date allocationDate) {
		this.allocationDate = allocationDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	

}
