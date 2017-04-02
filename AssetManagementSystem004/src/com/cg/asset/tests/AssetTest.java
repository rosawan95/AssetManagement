package com.cg.asset.tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.asset.daos.AssetDaoImpl;
import com.cg.asset.daos.IAssetDao;
import com.cg.asset.services.AssetServiceImpl;
import com.cg.asset.services.IAssetService;



public class AssetTest {

	static IAssetDao dao;
	static IAssetService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new AssetDaoImpl();
		service = new AssetServiceImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
	}

	@Test
	public void testDao() {
		assertNotNull(dao.getAssetDetails(1006));
	}
	
	
	@Test
	public void testService() {
		assertNotNull(service.getAssetDetails(1006));
	}
	
	
}
