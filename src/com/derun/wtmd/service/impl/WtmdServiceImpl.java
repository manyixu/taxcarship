package com.derun.wtmd.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;

import com.derun.wtmd.service.WtmdService;

/**
 * 
 * @date 2014-5-14
 * 
 *       问题名单服务
 * @version
 */
public class WtmdServiceImpl implements WtmdService {

	@Override
	public String wtmd(byte[] wtmdPara) {
		// TODO Auto-generated method stub
		FileOutputStream fos = null;  
        try{  
            fos = new FileOutputStream("D:\\abcdef");  
              
            //将字节数组bytes中的数据，写入文件输出流fos中  
            fos.write(wtmdPara);  
            fos.flush();  
        }catch (Exception e){  
            e.printStackTrace();  
            return "fail";  
        }finally{  
            try {  
                fos.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }     
        }  
        return "success"; 
	}
	
	
}
