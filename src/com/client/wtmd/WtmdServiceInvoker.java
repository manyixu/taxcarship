package com.client.wtmd;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.rpc.ServiceException;

import sun.util.logging.resources.logging;

public class WtmdServiceInvoker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			WtmdService ws = new WtmdServiceLocator();
			WtmdServicePortType client = ws.getWtmdServiceHttpPort();
			String filePath = "C:\\Users\\Administrator\\Desktop\\sql.txt"; 
			FileInputStream fis = new FileInputStream(filePath);
			byte[] buffer = new byte[fis.available()];
			String res = client.wtmd(buffer);
			System.out.println(res);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
