package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.List;

public interface IThumbnailService {
	public void  crateThumnailDynamically( List<String> filePaths, String realContextPath, int width ,int height);

	/*
	public String  crateThumnailForAdmin(int[] ids,String realContextPath);
	public  String  resizeImages(String path, String destinationSize, int width ,int height,File f2,String realContextPath) ;
	public String  crateThumnaiForProfiles( String filePaths, String realContextPath ) ;

*/}
