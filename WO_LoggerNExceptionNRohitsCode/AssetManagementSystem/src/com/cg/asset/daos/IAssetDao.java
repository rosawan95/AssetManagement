package com.cg.asset.daos;

import java.util.List;

import com.cg.asset.dtos.Asset;
import com.cg.asset.dtos.AssetReport;
import com.cg.asset.dtos.Request;
import com.cg.asset.dtos.User;
import com.cg.asset.exception.AssetException;



public interface IAssetDao {
 
	public List<Asset> getAssetDetailsListAdmin() throws AssetException;
	public List<Asset> getAssetDetailsListAvailable()throws AssetException;
	public Asset getAssetDetails(int assetId) throws AssetException;
	public boolean addAsset(Asset asset)throws AssetException;
	public boolean removeAsset(int assetId)throws AssetException;
	public boolean updateAssetAdd(int assetId,int plusQuantity)throws AssetException;
	
	
	
	public List<Request> getRequestsPendingList()throws AssetException;
	
	public User getUserDetails(String userName) throws AssetException;
	 
	public int getEmployeeNoFromUserName(String userName)throws AssetException;
	int addRequest(Request req)throws AssetException;
	int getAssetId(String assetName) throws AssetException;
	int getEmpNo(String empName) throws AssetException;
	List<String> getAvailableEmployees(int mgrId)throws AssetException;
	List<String> getAvailableAssets() throws AssetException;
	
	public boolean rejectRequest(int requestId)throws AssetException;
	public boolean approveRequest(int requestId)throws AssetException;
	void updateAssetStatus()throws AssetException;

	
	public List<AssetReport> getAssetReport()throws AssetException;
	
	public List<Request> showAllRequests(int mgrId) throws AssetException;

}
