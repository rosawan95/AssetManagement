package com.cg.asset.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cg.asset.dtos.Asset;
import com.cg.asset.dtos.AssetAllocation;
import com.cg.asset.dtos.AssetReport;
import com.cg.asset.dtos.Request;
import com.cg.asset.dtos.User;
import com.cg.asset.exception.AssetException;
import com.cg.asset.util.JndiUtil;



public class AssetDaoImpl implements IAssetDao {

	Connection con = null;
	PreparedStatement pst = null;
	JndiUtil util = null;
	public AssetDaoImpl() {
		try {
			util= new JndiUtil();
			con = util.getConnection();
		} catch (AssetException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Operations on Asset
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//returns single asset based on its id
	@Override
	public Asset getAssetDetails(int assetId) throws AssetException {        
		
		String query = "select ASSETID,ASSETNAME,ASSETDES,QUANTITY,STATUS from asset where ASSETID=?";
		Asset asset = null;
		try {
		
			pst = con.prepareStatement(query);
			pst.setInt(1,assetId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				int assetid = rs.getInt(1);
				String assetName = rs.getString(2);
				String assetDesc = rs.getString(3);
				int quantity = rs.getInt(4);
				String status = rs.getString(5);
				asset = new Asset(assetid, assetName, assetDesc, quantity,
						status);
				return asset;

			}
		} catch (SQLException e) {
			throw new AssetException("Could not select asset details for a given id.\n Error in select query execution",e);
		}
		return asset;
	}

	// /////////////////////////////////////////////////////////////////////////
	@Override
	public List<Asset> getAssetDetailsListAdmin() throws AssetException {           //returns asset List for admin Show All

		String query = "select ASSETID,ASSETNAME,ASSETDES,QUANTITY,STATUS from asset";
		List<Asset> mylist = new ArrayList<Asset>();

		try {
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				int assetid = rs.getInt("ASSETID");
				String assetName = rs.getString("ASSETNAME");
				String assetDesc = rs.getString("ASSETDES");
				int quantity = rs.getInt("QUANTITY");
				String status = rs.getString("STATUS");
				Asset asset = new Asset(assetid,assetName,assetDesc,quantity,status);
				mylist.add(asset);

			}
		} catch (SQLException e) {
			throw new AssetException("Could not select asset details.\n Error in select query execution",e);
		}

		return mylist;
	}
	
	

	// /////////////////////////////////////////////////////////////////////////
		@Override
		public List<Asset> getAssetDetailsListAvailable() throws AssetException {     //returns asset List for manager & admin-for report generation 

			String query = "select ASSETID,ASSETNAME,ASSETDES,QUANTITY,STATUS from asset where quantity>0";
			List<Asset> mylist = new ArrayList<Asset>();

			try {
				pst = con.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {

					int assetid = rs.getInt("ASSETID");
					String assetName = rs.getString("ASSETNAME");
					String assetDesc = rs.getString("ASSETDES");
					int quantity = rs.getInt("QUANTITY");
					String status = rs.getString("STATUS");
					Asset asset = new Asset(assetid,assetName,assetDesc,quantity,status);
					mylist.add(asset);

				}
			} catch (SQLException e) {
				throw new AssetException("Error during execution of select query", e);
			}

			return mylist;
		}
	
	///////////////////////////////////////////////////////////////////////////

	@Override
	public boolean addAsset(Asset asset) throws AssetException {           //ASSET ADDED BY ADMIN
		String query = "insert into asset values(asset_id_seq.NEXTVAL,?,?,?,?)";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, asset.getAssetName());
			pst.setString(2, asset.getAssetDesc());
			pst.setInt(3, asset.getQuantity());
			pst.setString(4, "Available");
			int recordsAffected = pst.executeUpdate();
			if (recordsAffected != 0) {
				return true;
			}
		} catch (SQLException e) {
			
			throw new AssetException("Error during execution of insert query", e);
		}
		return false;
	}

	/////////////////////////Deletion of an asset////////////////////////////////////////////////////
	@Override
	public boolean removeAsset(int assetId) throws AssetException {
		String query = "delete from asset where assetid = ?";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, assetId);
			int recordsAffected = pst.executeUpdate();
			if (recordsAffected != 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new AssetException("Error during execution of insert query", e);
		}

		return false;
	}

	// /////////////////////////////////////////////////////////////////////////////
	
	@Override
	public boolean updateAssetAdd(int assetId, int plusQuantity) throws AssetException {  //to update quantity after admin modifies assets
		String query = "update asset set quantity = quantity + ? where assetid = ?";

		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, plusQuantity);
			pst.setInt(2, assetId);
			int recordsAffected = pst.executeUpdate();
			if (recordsAffected != 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new AssetException("Error during execution of update  asset query", e);
		}

		return false;
	}
	
	

	// /////////////////////////////////////////////////////////////////////////////

	private boolean updateQuantity(int assetId, int quantity) throws AssetException {    // to update quantity after asset request is approved by admin

		String query = "update asset set quantity = quantity - ? where assetid = ?";

		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, quantity);
			pst.setInt(2, assetId);
			int recordsAffected = pst.executeUpdate();
			if (recordsAffected != 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new AssetException("Error during execution of UPDATE  asset query", e);
		}

		return false;
	}
	
	// /////////////////////////////////////////////////////////////////////////////

	//returns corresponding assetId to insert in request table
	@Override
	public int getAssetId(String assetName) throws AssetException {   
		
		String getAidQry="SELECT assetid FROM ASSET WHERE assetname=?";
		ResultSet rs=null;
		int assetId=0;
		try {
			con=util.getConnection();
			pst= con.prepareStatement(getAidQry);
			
			pst.setString(1, assetName);
			
			rs=pst.executeQuery();
			
			if(rs.next()){
				assetId=rs.getInt("assetid");
				return assetId;
			}
		} catch (SQLException e) {
			throw new AssetException("Error during execution of SELECT query", e);
		}
		return assetId;
	}

	
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Operations on request
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public int addRequest(Request req) throws AssetException {   //to insert request
		
	
		String reqInsQry="INSERT INTO request(requestid,mgrId,empno, assetid, requestdate, requestfordays, status) VALUES(?,?,?,?,?,?,?)";
		int requestId;
		try {
	
			pst= con.prepareStatement(reqInsQry);
			requestId= getRequestId();
			pst.setInt(1, requestId);
			pst.setInt(2, req.getMgrId());  //mgr_id
			pst.setInt(3, req.getEmpNo());
			pst.setInt(4,req.getAssetId());
			pst.setDate(5, req.getRequestDate());
			pst.setInt(6, req.getRequestForDays());
			pst.setString(7, req.getStatus());
			
			pst.executeUpdate();
			
			
			
		} catch (SQLException e) {
			throw new AssetException("Error during execution of INSERT query", e);
		}	
		return requestId;
	}
	
	
	
	
	// /////////////////////////////////////////////////////////////////////////////

	
	@Override
	public List<Request> getRequestsPendingList() throws AssetException {             //TO SHOW ADMIN ALL PENDING REQUESTS

		String query = "select * from request where status = ?";
		List<Request> requestList = new ArrayList<Request>();
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, "Pending");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				int requestId=rs.getInt("requestid") ;
				int empNo=rs.getInt("empno");
				int mgrId = rs.getInt("mgrId");
				int assetId=rs.getInt("assetid");
				java.sql.Date date=rs.getDate("requestdate");						
				int requestForDays=rs.getInt("requestfordays");
				String status=rs.getString("status");
				Request request = new Request(requestId,mgrId,empNo,assetId,date,requestForDays,status);
				requestList.add(request);

			}
		} catch (SQLException e) {
			throw new AssetException("Error during execution of select query", e);
		}
		return requestList;
	}

	// /////////////////////////////////////////////////////////////////////////////

	
	@Override
	public boolean rejectRequest(int requestId) throws AssetException {
		
		String query = "update  request set status = ? where requestid = ?";
		
			try {
				pst = con.prepareStatement(query);
				pst.setString(1, "Denied");
				pst.setInt(2,requestId);
				int result = pst.executeUpdate();
				if (result != 0){
					return true;
				}
			} catch (SQLException e) {
				throw new AssetException("Error during execution of UPDATE query", e);
			}			
		return false;
	}
	
	// /////////////////////////////////////////////////////////////////////////////

	

		public boolean updateRequestStatusToApproved(int requestId) throws AssetException {
			
			String query = "update  request set status = ? where requestid = ?";
			
				try {
					pst = con.prepareStatement(query);
					pst.setString(1, "Approved");
					pst.setInt(2,requestId);
					int result = pst.executeUpdate();
					if (result != 0){
						return true;
					}
				} catch (SQLException e) {
					throw new AssetException("Error during execution of UPDATE query", e);
				}			
			return false;
		}
	
	
	// /////////////////////////////////////////////////////////////////////////////
		
	private Request getRequestDetails(int requestId) throws AssetException{
		
		String query = "select * from request where requestid = ?";
		Request request = null;

		
			try {
				pst = con.prepareStatement(query);
				pst.setInt(1, requestId);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {

					int empNo=rs.getInt("empno");
					int mgrId = rs.getInt("mgrId");
					int assetId=rs.getInt("assetid");
					java.sql.Date date=rs.getDate("requestdate");						
					int requestForDays=rs.getInt("requestfordays");
					String status=rs.getString("status");
					request = new Request(requestId,mgrId,empNo,assetId,date,requestForDays,status);
					return request;
				}
			} catch (SQLException e) {
				throw new AssetException("Error during execution of SELECT query", e);
			}
	
			return request;

	}
	
	
	
	// /////////////////////////////////////////////////////////////////////////////
	
	@Override
	public boolean approveRequest(int requestId) throws AssetException {
		
		Request request = getRequestDetails(requestId);
		int assetId = request.getAssetId();
		int quantity = 1;
		int status = 0;
		
		if (request != null){
		
		boolean boo = updateQuantity(assetId,quantity);  //update Quantity
		
		System.out.println("quantity updated successfully");
		
		if(boo = true){
			
			
			try {
				
				 System.out.println(request.getAssetId()+"  assetid");
				// pst.setInt(1,10);
				// pst.setInt(2,request.getAssetId());
				System.out.println(request.getEmpNo()+"  employeeNo");
				// pst.setInt(3,request.getEmpNo());
				// pst.setDate(4,request.getRequestDate());
				System.out.println(request.getRequestDate()+"  requestDate");
				
				int requestForDays = request.getRequestForDays();
				java.sql.Date requestDate = request.getRequestDate();
				java.sql.Date releaseDate = addDate(requestDate, requestForDays);
				System.out.println(releaseDate+"  releaseDate");
				
				// pst.setDate(5,releaseDate);
				
				AssetAllocation allo = new AssetAllocation(10,request.getAssetId(),request.getEmpNo(),request.getRequestDate(),releaseDate);
				System.out.println("allo object   "+allo);
				String query = "insert into Asset_Allocation values (asset_allocation_id_seq.nextval,?,?,?,?)";
				
				pst = con.prepareStatement(query);
				
				 pst.setInt(1,allo.getAssetId());
				 pst.setInt(2,allo.getEmpNo());
				 pst.setDate(3,allo.getAllocationDate());
				 pst.setDate(4,allo.getReleaseDate());
			   	 status = pst.executeUpdate();
				
				 if (status != 0){
					System.out.println("record inserted into Asset_Allocation successfully");
					System.out.println("Asset allocated successfully");
					
					System.out.println("request id  "+request.getRequestId());
					boolean boo1 = updateRequestStatusToApproved(request.getRequestId());
					if(boo1 == true){
						System.out.println("status updation successful after approve request");
						return true;
					}
					else{
						System.out.println("status updation failed after approve request");
					}
				}
			} catch (SQLException e) {
				throw new AssetException("Error during execution of SELECT query\nstatus updation failed after approve request", e);
			}
		
		}
		else{
			System.out.println("quantity updation failed");
		}
		}  
		   return false;
	}  
		
	// /////////////////////////////////////////////////////////////////////////////
	
	public java.sql.Date addDate(java.sql.Date requestDate,int requestForDays) throws AssetException{
		
		String query = "select ? + ? from dual ";
		 Date date = null;  
		
			try {
				pst = con.prepareStatement(query);
				pst.setDate(1,requestDate);
				pst.setInt(2,requestForDays);
				ResultSet rs = pst.executeQuery();	
				rs = pst.executeQuery();
				if (rs.next()) {
				  date = rs.getDate(1);
				}
			} catch (SQLException e) {
				throw new AssetException("Error while updation of date", e);
			}
			
	return date;
	}	

	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Login operations
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	@Override
	public User getUserDetails(String userName) throws AssetException {
		
		System.out.println("In do");
		String query = "select userid, userpassword, usertype  from user_master where username = ?";
		User user= null;
		try {
			System.out.println("in try of dao");
			pst = con.prepareStatement(query);
			System.out.println("made query");
			pst.setString(1,userName);
			System.out.println("setString");
			ResultSet rs = pst.executeQuery();	
			rs = pst.executeQuery();
			System.out.println("executed the query");
			if (rs.next()) {
				System.out.println("got data from table!");
				String userpassword = rs.getString("userpassword");
				String userid = rs.getString("userid");
				String usertype= rs.getString("usertype");
				user = new User(userid, userName, userpassword,usertype);
				System.out.println("here in daoImpl");
				System.out.println(user);
				return user;
			} 

		} catch (SQLException e) {
			throw new AssetException("Error while execution of SELECT query", e);
		}	
		return user;
		
	}



	// /////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Employee operations
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	public int getEmpNo(String empName) throws AssetException {
		Connection con=null;
		PreparedStatement pst=null;
		String getEnoQry="SELECT empno FROM EMPLOYEE WHERE ename=? ";
		ResultSet rs=null;
		int empNo=0;
		try {
			con=util.getConnection();
			pst= con.prepareStatement(getEnoQry);
			
			pst.setString(1, empName);
			
			rs=pst.executeQuery();
			
			if(rs.next()){
				empNo=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new AssetException("Error while execution of SELECT query", e);
		}
		return empNo;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Anonymous operations 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private int getRequestId() throws AssetException {
		Connection con=null;
		PreparedStatement pst=null;
		String reqIdQry="SELECT request_id_seq.nextval FROM DUAL";
		ResultSet rs=null;
		int reqId=0;
		try {
			con=util.getConnection();
			pst= con.prepareStatement(reqIdQry);
			
			rs=pst.executeQuery();
			
			if(rs.next()){
				reqId=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new AssetException("Could not create sequence",e);
		}
		return reqId;
	}
	
	// /////////////////////////////////////////////////////////////////////////////

	
	@Override
	public int getEmployeeNoFromUserName(String userName) throws AssetException {      //map manager username and his empno

		String query = "select empno from user_map_employee where username = ?";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1,userName);
			ResultSet rs = pst.executeQuery();	
			rs = pst.executeQuery();
			if (rs.next()) {
			 int empno=rs.getInt(1);
			 return empno;
			}
		} catch (SQLException e) {
			throw new AssetException("Error while execution of SELECT query", e);
		}
	return 0;
		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<String> getAvailableAssets() throws AssetException {
		Connection con=null;
		PreparedStatement pst=null;
		String getEmpsQry="SELECT assetname FROM Asset WHERE quantity>?";
		ResultSet rs=null;
		List<String> assetList= null;
		try {
			con=util.getConnection();
			pst= con.prepareStatement(getEmpsQry);
			String availableStatus="Available";
			pst.setInt(1,0);
			rs= pst.executeQuery();
			assetList=new ArrayList<String>();
			while(rs.next()){
				String assetName= rs.getString(1);
				
				assetList.add(assetName);
			}
			
			return assetList;
			
		} catch (SQLException e) {
			throw new AssetException("Error while creation of preparedstatement!", e);
		}
	}

	@Override
	public List<String> getAvailableEmployees(int mgrId) throws AssetException {
		Connection con=null;
		PreparedStatement pst=null;
		String getEmpsQry="SELECT ename FROM employee WHERE mgr=?";
		ResultSet rs=null;
		List<String> eNameList= null;
		try {
			con=util.getConnection();
			pst= con.prepareStatement(getEmpsQry);
			pst.setInt(1, mgrId);
			rs= pst.executeQuery();
			eNameList=new ArrayList<String>();
			while(rs.next()){
				String eName= rs.getString(1);
				
				eNameList.add(eName);
			}
			
			return eNameList;
			
		} catch (SQLException e) {
			throw new AssetException("Error while creation of preparedstatement!\n", e);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void updateAssetStatus() throws AssetException {
			
		String query="update asset set status = ? where quantity < 1";		
			try {
				pst= con.prepareStatement(query);
				pst.setString(1,"Not Available");
				int result  = pst.executeUpdate();
				System.out.println("Asset status refreshed successfully");
				
			} catch (SQLException e) {
				throw new AssetException("Error while execution of UPDATE query!\n", e);
			}

	}

	@Override
	public List<Request> showAllRequests(int mgrId) throws AssetException {
		
		List<Request> myList =new ArrayList<Request>();
		
		String query="select requestid,empno,assetid,requestdate,requestfordays,status from request where mgrId=?";
		try {
			pst=con.prepareStatement(query);
			pst.setInt(1,mgrId);
			ResultSet res=pst.executeQuery();
			
			while(res.next()){
				Request request=new Request();
				request.setRequestId(res.getInt("requestid"));
			    
				request.setEmpNo(res.getInt("empno"));
				request.setAssetId(res.getInt("assetid"));
				request.setRequestDate(res.getDate("requestdate"));
				request.setRequestForDays(res.getInt("requestfordays"));
				request.setStatus(res.getString("status"));
				request.setMgrId(mgrId);
				myList.add(request);
				System.out.println(myList);
			}
			
		}catch(SQLException e) {
			throw new AssetException("Error while execution of SELECT query!\n", e);
		}
		return myList;
	}

	@Override
	public List<AssetReport> getAssetReport() throws AssetException {
		String query = "select ast.assetId,emp.ename,ast.allocationid,ast.allocation_date,ast.release_date,a.assetname from Asset_Allocation ast,Employee emp,Asset a where ast.empNo=emp.empNo and ast.assetid=a.assetId";
		List<AssetReport> myList = new ArrayList<AssetReport>();
		try {
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int assetid =(rs.getInt(1));
				String ename=(rs.getString(2));
				int allocationid=(rs.getInt(3));
				java.sql.Date date = rs.getDate(4);
				java.sql.Date dates = rs.getDate(5);
				String aname=(rs.getString(6));
				
				/*int allocationid = rs.getInt("AST.ALLOCATIONID");
				int empNo = rs.getInt("EMP.EMPNO");
				java.sql.Date date = rs.getDate("ALLOCATION_DATE");
				java.sql.Date dates = rs.getDate("RELEASE_DATE");
				String empName = rs.getString("ENAME")*/;
				AssetReport astReport = new AssetReport(assetid, ename, allocationid, date, dates, aname);
				myList.add(astReport);

			}

		} catch (SQLException e) {
			throw new AssetException("Error while execution of SELECT query!\n", e);
		}

		return myList;
	}



}
