package com.cg.asset.controller;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cg.asset.dtos.Asset;
import com.cg.asset.dtos.AssetReport;
import com.cg.asset.dtos.Request;
import com.cg.asset.dtos.RequestAndAssetQty;
import com.cg.asset.exception.AssetException;
import com.cg.asset.services.AssetServiceImpl;
import com.cg.asset.services.IAssetService;

@WebServlet("*.do")
public class AssetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher;
	private String nextJsp;
	String message = null;
	private IAssetService service;
	private int mgrId ;
 
	private static final Logger mylogger=Logger.getLogger(AssetController.class);
	public void init() throws ServletException {
		service = new AssetServiceImpl();
	}
	
    public AssetController() {
        super();
		
    }

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processrequest(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
		
	}
	
	private void processrequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	String path = request.getServletPath();
		//System.out.println(path+ "   path");
		
		mylogger.info("Application started");
	
		if (path.equals("/login.do"))
		{
			//mylogger.info("Application started");
			nextJsp="login.jsp";
			
		}
		
		
			if (path.equals("/authenticate.do"))
		{
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			
			//System.out.println(userName);
			//System.out.println(password);
			try {
				int isAuthenticated = service.authenticate(userName, password);
				System.out.println(isAuthenticated);
				
				if (isAuthenticated == 1) {
					HttpSession session= request.getSession(true);
					mylogger.info("admin is authenticated");
					//System.out.println("admin authenticated successfully");
					//Update asset status to 'not available' if it is not available
					service.updateAssetStatus();
					mylogger.info("Asset status is set to unavailable");
					//request.setAttribute("userName", userName);
					session.setAttribute("userName" , userName);
					nextJsp = "adminHomePage.jsp";
				} else if (isAuthenticated == 2) {
					mylogger.info("manager is authenticated");
					mgrId= service.getEmployeeNoFromUserName(userName);
					HttpSession session= request.getSession(true);
					session.setAttribute("mgrId" , mgrId);
					session.setAttribute("userName", userName);
					System.out.println(mgrId);
					request.setAttribute("userName", userName);
					nextJsp = "managerHomePage.jsp";
						
				} else {

					nextJsp = "login.jsp";
					message = "Incorrect username or password.";
					request.setAttribute("errorMsg", message); // used to pass
					mylogger.info("entered wrong credentials ");
				}
			} catch (Exception e) {
				// e.printStackTrace();
				message = "Invalid username or password.";
				nextJsp = "login.jsp";
				request.setAttribute("errorMsg", message);	
				System.out.println(e.getMessage());
				mylogger.error("Username does not exist");
			}

		}
		
			if (path.equals("/showAssets.do"))
	{		
				try {
					List<Asset> myList = service.getAssetDetailsListAdmin();
				System.out.println(myList);
				
				nextJsp= "/showAssets.jsp";
				request.setAttribute("asset", myList);
				mylogger.info("Displayed details of all the assets");
			} catch (AssetException e) {
				nextJsp="error1.jsp";
				//request.setAttribute("errorMsg", e);
				mylogger.error("Could not select asset details");
			}	
		}
			if (path.equals("/showPendingRequests.do")) {
				try {
					List<RequestAndAssetQty> pendingRequestList = service.getRequestsPendingListWithQty();
					System.out.println("here");
					System.out.println(pendingRequestList);
					nextJsp = "/pendingRequestList.jsp";
					request.setAttribute("requests", pendingRequestList);
				} catch (AssetException e) {
					nextJsp = "error1.jsp";
					request.setAttribute("errorMsg", e);
					mylogger.error("Could not show pending requests details");
				}
			}
		
			if (path.equals("/adminModify.do"))
			{
			try {
				String data = request.getQueryString();
				System.out.println(data + "  before");
				String data1 = data.substring(3, data.length());
				int assetId = Integer.parseInt(data1);
				System.out.println("assetId  " + assetId);
				Asset asset = service.getAssetDetails(assetId);
				System.out.println(asset);
				nextJsp="/modifyAssetForm.jsp";
				request.setAttribute("asset", asset);
			} catch (NumberFormatException e) {
				nextJsp="error1.jsp";
				request.setAttribute("errorMsg", e);
				mylogger.error("NumberFormatException occurred");
			} catch (AssetException e) {
				nextJsp="error1.jsp";
				//request.setAttribute("errorMsg", e);
				mylogger.error("Could not select asset details");
			}
		}
			
			
			if (path.equals("/adminUpdateConfirm.do"))
			{
			try {
				String data = request.getQueryString();
				System.out.println(data + "  before");
				String data1 = data.substring(3, data.length());
				int assetId = Integer.parseInt(data1);

				String addedqty = request.getParameter("addedquantity");
				System.out.println(addedqty);
				int quantityToAdd = Integer.parseInt(addedqty);
				boolean boo = service.updateAssetAdd(assetId, quantityToAdd);
				if (boo == true) {
					System.out.println("Asset quantity updated successfully");
					log("Asset quantity updated successfully");
					nextJsp="/showAssets.do";
				}
			} catch (NumberFormatException e) {
				nextJsp="error1.jsp";
				request.setAttribute("errorMsg", e);
				mylogger.error("NumberFormatException exception occurred");
			} catch (AssetException e) {
				nextJsp="error1.jsp";
				request.setAttribute("errorMsg", e);
				mylogger.error("Exception occurred during updation of asset quantity");
			}
		}
		
			if (path.equals("/delete.do"))
		{
			try {
				String data = request.getQueryString();
				System.out.println(data + "  before");
				String data1 = data.substring(3, data.length());
				int assetId = Integer.parseInt(data1);
				boolean boo = service.removeAsset(assetId);
				if (boo == true) {
					System.out.println("Asset deleted successfully");
					mylogger.error("Deletion failed");
					nextJsp="/showAssets.do";
				} else {
					//System.out.println("deletion failed");
					mylogger.error("deletion failed");
				}
			} catch (NumberFormatException e) {
				nextJsp="error1.jsp";
				request.setAttribute("errorMsg", e);
				mylogger.error("Could not delete asset because of NumberFormatException");
			} catch (AssetException e) {
				mylogger.error("Could not delete asset");
				nextJsp="error1.jsp";
				request.setAttribute("errorMsg", e);
				
			}
		}
		
			if (path.equals("/showPendingRequests.do"))
	{
			try {
				List<Request> pendingRequestList =  service.getRequestsPendingList();
				System.out.println("here");
				System.out.println(pendingRequestList);
				nextJsp="/pendingRequestList.jsp";
				request.setAttribute("requests", pendingRequestList);
			} catch (AssetException e) {
				nextJsp="error1.jsp";
				request.setAttribute("errorMsg", e);
				mylogger.error("Could not display pending requests");
			}
		}
		
			if (path.equals("/addNewAsset.do"))
		{
			try {
				String assetName = request.getParameter("astname");
				String assetDesc = request.getParameter("astdesc");
				String qty = request.getParameter("aqty");
				int quantity = Integer.parseInt(qty);
				// System.out.println(quantity);
				Asset asset = new Asset(0, assetName, assetDesc, quantity, "status");
				boolean boo = service.addAsset(asset);
				if (boo == true) {
					//System.out.println("New asset added successfully");
					mylogger.info("Added new asset successfully");
					nextJsp="/showAssets.do";
				} else {
					//System.out.println("Asset addition failed");
					mylogger.error("Asset addition failed");
				}
			} catch (NumberFormatException e) {
				nextJsp="error1.jsp";
				request.setAttribute("errorMsg", e);
				mylogger.error("Asset addition failed because of NumberFormatException");
			} catch (AssetException e) {
				nextJsp="error1.jsp";
				request.setAttribute("errorMsg", e);
				mylogger.error("Asset addition failed");
			}
		}
		
			if (path.equals("/rejectRequest.do"))
		{
			try {
				String data = request.getQueryString();
				System.out.println(data + "  before");
				String data1 = data.substring(3, data.length());
				int requestId = Integer.parseInt(data1);
				System.out.println("after substring "+requestId);
				boolean boo = service.rejectRequest(requestId);
				if (boo == true){
					//System.out.println("Request reject successfully");
					mylogger.info("Request reject successfully");
				}
				else{
					//System.out.println("Failed to deny request ");
					mylogger.error("Failed to deny request");
				}
				nextJsp="/showPendingRequests.do";
			} catch (NumberFormatException e) {
				nextJsp="error1.jsp";
				request.setAttribute("errorMsg", e);
				mylogger.error("Failed to deny request because of NumberFormatException");
			} catch (AssetException e) {
				nextJsp="error1.jsp";
				request.setAttribute("errorMsg", e);
				mylogger.error("Failed to deny request");
			}
		}
		
			if (path.equals("/approveRequest.do"))
	{
			try {
				String data = request.getQueryString();
				System.out.println(data + "  before");
				String data1 = data.substring(3, data.length());
				int requestId = Integer.parseInt(data1);
				System.out.println("after substring "+requestId);
				boolean boo = service.approveRequest(requestId);
				if (boo == true){
					//System.out.println("Request approved successfully");
					mylogger.info("Failed to deny request");		
				}
				else{
					//System.out.println("Failed to approve request ");
					mylogger.error("Failed to approve request");
				}
				nextJsp="/showPendingRequests.do";
			} catch (NumberFormatException e) {
				nextJsp="error1.jsp";
				request.setAttribute("errorMsg", e);
				mylogger.error("Failed to deny request due to NumberFormatException");
			} catch (AssetException e) {
				nextJsp="error1.jsp";
				request.setAttribute("errorMsg", e);
				mylogger.error("Failed to deny request");
			}	
		}
			
			
		
			if (path.equals("/createRequestData.do"))
			   {
				System.out.println("in create");
				List<String> eNames=new ArrayList<>();
				System.out.println("after array");
				try {
				System.out.println("in try");
				eNames = service.getAvailableEmployees(mgrId);
				System.out.println("got id");
				List<String> availAssets= service.getAvailableAssets();
				System.out.println("got assets");
				HttpSession session= request.getSession(false);
				session.setAttribute("availableEmps", eNames);
				session.setAttribute("availAssets", availAssets);
				/*request.setAttribute("availableEmps", eNames);
				request.setAttribute("availAssets", availAssets);*/	
				//System.out.println("added names and assets");
				
				nextJsp = "requestForm.jsp";
				
				} catch (AssetException e) {
					nextJsp="error1.jsp";
					request.setAttribute("errorMsg", e);
					mylogger.error("Failed to populate request form");
				}
			}
		
			
	/*		if (path.equals("/getManagerHomePage.do"))
		{
			nextJsp = "managerHomePage.jsp";
			
		}*/
			
			
			if (path.equals("/addRequest.do"))
		{
			
			try {
				int mgrId= Integer.parseInt(request.getParameter("mgrId"));
				String empName= request.getParameter("empName");
				int empId= service.getEmpNo(empName);
				String assetName= request.getParameter("assetName");
				int assetId= service.getAssetId(assetName);
				String reqStrDate= request.getParameter("reqDate").toString();
				java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(reqStrDate); //
				java.sql.Date reqSQLDate = new java.sql.Date(date.getTime());
				
				System.out.println("SQL DAte: "+reqSQLDate);
				
				boolean validDate= service.isDateValid(reqSQLDate);
				if(validDate){
					int reqDays=Integer.parseInt(request.getParameter("reqDays"));
					Request req= new Request();
					req.setMgrId(mgrId);
					req.setEmpNo(empId);
					req.setAssetId(assetId);
					req.setRequestDate(reqSQLDate);
					req.setRequestForDays(reqDays);
					req.setStatus("Pending");
					int requestId= service.addRequest(req);
					nextJsp="successRequest.jsp";
					HttpSession session= request.getSession(false);
					session.setAttribute("request", req);
					session.setAttribute("requestId", requestId);
					//request.setAttribute("request", req);
					//System.out.println("Raised "+addedReqs+" request successfully");
				}else{
					System.out.println("invalid date");
					message = "Date should be greater than todays date";
					nextJsp = "requestForm.jsp";
					request.setAttribute("errorMsg", message);	
				}
		
					/*int reqDays=Integer.parseInt(request.getParameter("reqDays"));
					Request req= new Request();
					req.setMgrId(mgrId);
					req.setEmpNo(empId);
					req.setAssetId(assetId);
					req.setRequestDate(reqSQLDate);
					req.setRequestForDays(reqDays);
					req.setStatus("Pending");
					int addedReqs= service.addRequest(req);
					nextJsp="successRequest.jsp";
					request.setAttribute("request", req);
					System.out.println("Raised "+addedReqs+" request successfully");*/
				
			} catch (ParseException e) {
				System.out.println(e);
				mylogger.error("Failed to raise asste request due to ParseException");
			} catch (AssetException e) {
				System.out.println(e);
				//message = "Date should be greater than todays date";
				nextJsp = "error1.jsp";
				request.setAttribute("errorMsg", e);	
				mylogger.error("Failed to raise asste request");
			}	

		}
		
			if (path.equals("/showAllRequests.do"))
		{
			try {
				System.out.println("showall");
				System.out.println(mgrId);
				List<Request>myList=service.showAllRequests(mgrId);
				request.setAttribute("data", myList);
				System.out.println(myList);

			} catch (AssetException e) {
				nextJsp="error1.jsp";
				request.setAttribute("errorMsg", e);
				mylogger.error("Could not display requests raised by the logged in manager");
			}
			nextJsp="showAllRequests.jsp";
		}
			
			if (path.equals("/showReport.do")) {
				try {
					List<AssetReport> myList=service.getAssetReport();
					List<Asset> list=service.getAssetDetailsListAvailable();
					RequestDispatcher req = request
							.getRequestDispatcher("/report.jsp");
					request.setAttribute("report", myList);
					request.setAttribute("avail",list );
					req.forward(request, response);
				} catch (AssetException e) {
					nextJsp="error1.jsp";
					request.setAttribute("errorMsg", e);
					mylogger.error("Could not display requests raised by the logged in manager");
				}						
			}
			
			if (path.equals("/generateReport.do")) {
				try {
					List<AssetReport> myList=service.getAssetReport();
					List<Asset> list=service.getAssetDetailsListAvailable();
					RequestDispatcher req = request
							.getRequestDispatcher("/assetreport.jsp");
					request.setAttribute("report", myList);
					request.setAttribute("avail",list );
					req.forward(request, response);
				} catch (AssetException e) {
					nextJsp="error1.jsp";
					request.setAttribute("errorMsg", e);
					mylogger.error("Could not generate report");
				}
				
				
			}
		
			if (path.equals("/logout.do"))
		{
			System.out.println("in logout.do");
			HttpSession session= request.getSession(false);
			session.invalidate();
			nextJsp="login.jsp";
			mylogger.info("user logged out");
		}
		
		

		RequestDispatcher req = request.getRequestDispatcher(nextJsp);
		req.forward(request, response);			
	}

}
