package com.cg.asset.services;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.cg.asset.daos.AssetDaoImpl;
import com.cg.asset.daos.IAssetDao;
import com.cg.asset.dtos.Asset;
import com.cg.asset.dtos.AssetReport;
import com.cg.asset.dtos.Request;
import com.cg.asset.dtos.RequestAndAssetQty;
import com.cg.asset.dtos.User;
import com.cg.asset.exception.AssetException;

public class AssetServiceImpl implements IAssetService {

	IAssetDao dao;
	public AssetServiceImpl() {
		dao=new AssetDaoImpl();
	}
	
	@Override
	public List<Asset> getAssetDetailsListAdmin() throws AssetException {
		return dao.getAssetDetailsListAdmin();
	}

	@Override
	public int authenticate(String userName, String password) throws AssetException {
	
		System.out.println(userName+" "+password);
		User user = dao.getUserDetails(userName);
		System.out.println("after getrequest");
		System.out.println(user.getUserName()+user.getUserPassword()+user.getUserId());
		String privilege="Admin";
		
		System.out.println(user);
		
		if (password.equals(user.getUserPassword())) {
			 if (privilege.equals(user.getUserType())){
				 return 1;
			 }
			return 2;	
		}
	
	return 0;
		
	}

	@Override
	public boolean isDateValid(Date reqSQLDate) {
		java.sql.Date todaysDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		System.out.println("Todays date: "+todaysDate);
		System.out.println("User entered date: "+reqSQLDate);
		
		if(reqSQLDate.compareTo(todaysDate)>0){
			return true;
		}else{
			return false;
		}	
	}
	
	@Override
	public boolean removeAsset(int assetId) throws AssetException {
		return dao.removeAsset(assetId);
	}

	@Override
	public List<Request> getRequestsPendingList() throws AssetException {
		List<Request> requestList=dao.getRequestsPendingList();
		return requestList;
	}

	@Override
	public boolean updateAssetAdd(int assetId, int plusQuantity) throws AssetException {
		return dao.updateAssetAdd(assetId, plusQuantity);
	
	}

	@Override
	public boolean addAsset(Asset asset) throws AssetException {
		return dao.addAsset(asset);
	}

	@Override
	public List<Asset> getAssetDetailsListAvailable() throws AssetException {
		return dao.getAssetDetailsListAvailable();
	}

	@Override
	public Asset getAssetDetails(int assetId) throws AssetException {
		return dao.getAssetDetails(assetId);
	}

	@Override
	public int getEmployeeNoFromUserName(String userName) throws AssetException {
		return dao.getEmployeeNoFromUserName(userName);
	}

	@Override
	public int getAssetId(String assetName) throws AssetException {
		return dao.getAssetId(assetName);
	}

	@Override
	public int addRequest(Request req) throws AssetException {
		return dao.addRequest(req);
	}

	
	@Override
	public int getEmpNo(String empName) throws AssetException {
		return dao.getEmpNo(empName);
	}

	@Override
	public boolean rejectRequest(int requestId) throws AssetException {
		return dao.rejectRequest(requestId);
	}

	@Override
	public boolean approveRequest(int requestId) throws AssetException {
		return dao.approveRequest(requestId);
	}

	@Override
	public List<String> getAvailableEmployees(int mgrId) throws AssetException {
		// TODO Auto-generated method stub
		return dao.getAvailableEmployees(mgrId);
	}

	@Override
	public List<String> getAvailableAssets() throws AssetException {
		// TODO Auto-generated method stub
		return dao.getAvailableAssets();
	}

	

	@Override
	public void updateAssetStatus() throws AssetException {
		dao.updateAssetStatus();
	}

	
	@Override
	public List<Request> showAllRequests(int mgrId) throws AssetException {
		
		return dao.showAllRequests(mgrId);
	}

	@Override
	public List<AssetReport> getAssetReport() throws AssetException {
		
		return dao.getAssetReport();
	}

	@Override
	public List<RequestAndAssetQty> getRequestsPendingListWithQty() throws AssetException {
		// TODO Auto-generated method stub
		return dao.getRequestsPendingListWithQty();
	}


	

}
