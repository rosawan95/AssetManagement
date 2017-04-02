package com.cg.asset.dtos;

import java.sql.Date;


public class Request {

	private int requestId;
	private int mgrId; 
	private int empNo;
	private int assetId;
	private Date requestDate;
	private int requestForDays;
	private String status;
	
	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", mgrId=" + mgrId
				+ ", empNo=" + empNo + ", assetId=" + assetId
				+ ", requestDate=" + requestDate + ", requestForDays="
				+ requestForDays + ", status=" + status + "]";
	}

	public Request(int requestId, int mgrId, int empNo, int assetId,
			Date requestDate, int requestForDays, String status) {
		super();
		this.requestId = requestId;
		this.mgrId = mgrId;
		this.empNo = empNo;
		this.assetId = assetId;
		this.requestDate = requestDate;
		this.requestForDays = requestForDays;
		this.status = status;
	}

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getMgrId() {
		return mgrId;
	}

	public void setMgrId(int mgrId) {
		this.mgrId = mgrId;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public int getRequestForDays() {
		return requestForDays;
	}

	public void setRequestForDays(int requestForDays) {
		this.requestForDays = requestForDays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
