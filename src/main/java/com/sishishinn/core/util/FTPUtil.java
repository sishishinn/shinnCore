package com.sishishinn.core.util;

import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtil {

	private static String username = "";
	private static String password = "";
	private static String ip = "10.4.252.217";
	private static int port = 21;
	
	
	/**
	 * 连接到服务器
	 */
	public static FTPClient openConnect(){

		FTPClient ftpClient = null;
		
		int reply;
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("GBK");
			ftpClient.setDefaultPort(port);
			ftpClient.connect(ip);
			ftpClient.login(username, password);
			
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ftpClient;
	}
	
	/**
	 * 关闭连接
	 */
	public static void closeConnect(FTPClient ftpClient){
		try {
			if (ftpClient!=null) {
				ftpClient.logout();
				ftpClient.disconnect();
				ftpClient = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断目录是否存在
	 */
	public static boolean isDirExist(String dir,FTPClient ftpClient) {
		try {
			return ftpClient.changeWorkingDirectory(dir);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 创建目录
	 */
	public static boolean createDir(String dir,FTPClient ftpClient) {
        try {   
        	String str=dir;  //赋值给临时变量
        	while(str.length()>1 ){
        		String[] strs=str.split("/");
        		//逐层创建目录
    			if((strs.length-1)>1){
        			if(!isDirExist(dir.substring(0,dir.length()-str.length()),ftpClient)){
        				String dir1 = dir.substring(0,dir.length()-str.length()   );
        				ftpClient.makeDirectory(dir1);
        			}
        			str=str.substring(str.indexOf("/",1),str.length());
    			}else{
    				//创建最后两层目录
    				String dir2 = dir.substring(0,(dir.length()-str.length()));
    				ftpClient.makeDirectory(dir2);
    				ftpClient.makeDirectory(dir);
    				str="";
    			}
    		}
        	ftpClient.makeDirectory(dir.substring(0,(dir.length()-str.length())));
        	return true;
        } catch (Exception e) {
        	e.printStackTrace();   
        	return false;
        }   
	}
	
	/**
	 * 上传
	 */
	public static boolean putFile(InputStream inputStream,String uploadDir,String fileName,FTPClient ftpClient){
		boolean result = false;
		try {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			
			//设置为被动模式
			ftpClient.enterLocalPassiveMode();
			ftpClient.setRemoteVerificationEnabled(false);
			
			//主要是这句代码进行设置缓冲大小,可以提高文件上传速度
			ftpClient.setBufferSize(100000);
			
			boolean isDirExist = ftpClient.changeWorkingDirectory(uploadDir);
			
			if (!isDirExist) {
				createDir(uploadDir, ftpClient);
				ftpClient.changeWorkingDirectory(uploadDir);
			}
			result = ftpClient.storeFile(fileName, inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}








































