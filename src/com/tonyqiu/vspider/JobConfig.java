package com.tonyqiu.vspider;

public class JobConfig {
	
	public static Integer jobId = 1;
	public static String imageSrcBaseUrl = "http://gz.house.163.com/";
	public static String imageTargetBaseUrl = "http://localhost/upload/image/";
	public static String localImageFolder = "F:/tmp/vSpiderImage/";
	public static String listPageUrl="http://gz.house.163.com/exclusive/";
	public static String detailAnchorSelector= "ul.textList li a";
	public static String nextPageSelector="div.listPages a:contains(下一页)";
	public static int oneBatchColumnsToSaveDb = 100;
	
	
}
