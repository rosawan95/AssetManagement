package com.cg.asset.services;

import java.sql.Date;
import java.util.List;

import com.cg.asset.dtos.Asset;
import com.cg.asset.dtos.AssetReport;
import com.cg.asset.dtos.Request;
import com.cg.asset.exception.AssetException;


public interface IAssetService {

	public List<Asset> getAssetDetailsListAdmin()throws AssetException;
	public List<Asset> getAssetDetailsListAvailable()throws AssetException;
	public int authenticate(String userName,String password)throws AssetException;
	public boolean removeAsset(int assetId)throws AssetException;
	public List<Request> getRequestsPendingList()throws AssetException;
	public boolean updateAssetAdd(int assetId,int plusQuantity)throws AssetException;
	public boolean addAsset(Asset asset)throws AssetException;
	public Asset getAssetDetails(int assetId)throws AssetException;

	int addRequest(Request req)throws AssetException;
	public int getEmployeeNoFromUserName(String userName) throws AssetException;
	int getAssetId(String assetName) throws AssetException;
	int getEmpNo(String empName) throws AssetException;
	List<String> getAvailableEmployees(int mgrId)throws AssetException;
	List<String> getAvailableAssets() throws AssetException;
	public boolean isDateValid(Date reqSQLDate)throws AssetException;

	public List<AssetReport> getAssetReport()throws AssetException;
	
	public boolean rejectRequest(int requestId)throws AssetException;
	public boolean approveRequest(int requestId)throws AssetException;
	
	public List<Request> showAllRequests(int mgrId) throws AssetException;
	
	void updateAssetStatus() throws AssetException;
	
}
