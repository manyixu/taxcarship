package com.derun.controller.whiteList.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.derun.beans.TaxCarCount;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX_TEMP;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * @author MILI
 * @time 2014-12-4 08:46:55
 * @描述：白名单工具类
 * */
public class WhiteList_Tool {
	/**
	 * @author MILI
	 * @time 2014-12-4 08:47:54
	 * @描述：解析xls
	 * */
	public List<String[]> analyze_xls(String path) {
		File file = new File(path);
		List<String[]> excelValueList = new ArrayList<String[]>();
		// 文件是否存在 并且 可读取 并且是 xls 文件
		if (file.exists() && file.canRead()
				&& (file.getName().lastIndexOf(".xls") >= 1)) {
			Workbook workbook = null;
			try {
				workbook = Workbook.getWorkbook(file);
				// 得到 所有分页的名字
				// String[] names = workbook.getSheetNames();
				// 取 第一分页 0
				Sheet sheet = workbook.getSheet(0);
				Cell cell = null;
				int row = sheet.getRows();
				int col = sheet.getColumns();
				for (int r = 0; r < row; r++) {
					String[] rowValue = new String[col];
					for (int c = 0; c < col; c++) {
						cell = sheet.getCell(c, r) ;
				        if(cell.getType() == CellType.DATE){   
//				            System.out.print(((DateCell)cell).getDate()); 
				            rowValue[c] = DateUtil.getStringDate(((DateCell)cell).getDate(),null);
				        } else{  
				        	rowValue[c] = cell.getContents() != null ? cell.getContents() : "";
						}
					}
					excelValueList.add(rowValue);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (BiffException e) {
				e.printStackTrace();
			} finally {
				if (workbook != null) {
					workbook.close();
				}
			}
		}
		return excelValueList ;
	}
	/**
	 * @author MILI
	 * @throws UnsupportedEncodingException 
	 * @time 2014-12-3 14:53:11
	 * @描述：图片文件上传
	 * */
	public Map<String,String> setPhoto(HttpServletRequest request, HttpServletResponse resp,String flag) throws UnsupportedEncodingException{
		Map<String, String> map = new HashMap<String, String>();
		request.setCharacterEncoding("utf-8"); // 设置编码
		resp.setContentType("text/html;charset=utf-8");	// 设置编码
		String file_date = DateUtil.getStringDate(new Date(),null).replace("-", "");
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 获取文件需要上传到的路径
//		String path = request.getRealPath("/upload/wspz");
		String path = request.getSession().getServletContext().getRealPath("/") ;
		if("xls".equals(flag)){	// 文件上传路径
			path += "upload/bmd/" + file_date;
		}else if("jpg".equals(flag)){	// 图片上传路径
			path += "upload/wspz/" + file_date;
		}else{}
		File file = new File(path) ;
		// 文件夹如果不存在  创建一个
		if(!file.exists() && !file.isDirectory()){
			file.mkdir(); 
		}
		map.put("File_path", file_date);
		// 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
		// 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem
		 * 格式的 然后再将其真正写到 对应目录的硬盘上
		 */
		factory.setRepository(file);
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024 * 1024);
		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
//		String enCoding = request.getCharacterEncoding();
//		if(enCoding != null){
//			upload.setHeaderEncoding(enCoding);
//		}
		try {
			// 可以上传多个文件
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				// 获取表单的属性名字
				String name = item.getFieldName();
				if (item.isFormField()) {
					// 获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
					// 关键一行代码 处理中文乱码
					String value = item.getString("utf-8");
//					System.out.println(name + "---" + value + "----------");
					map.put(name, value);
				}
				// 对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
				else if("WSPZ".equals(name) || "fileToUpload".equals(name)){
					// 获取路径名
					String value = item.getName();
					// 判断是否有上传文件文本
					if(value != null && !"".equals(value)){
						// 索引到最后一个反斜杠
						int start = value.lastIndexOf("\\");
						// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
						String filename = value.substring(start + 1);
						int begin = filename.lastIndexOf(".");
						String newfilename = filename.substring(begin + 1);
						TaxDealCode_Type a = TaxCarCount.getTaxqueryNo("","","","");
						newfilename = a.getTaxDealCode_Type() + "." + newfilename ;
						map.put("NAME",newfilename);
						map.put(name, "upload/wspz/" + file_date + "/" + newfilename);
						// 真正写到磁盘上
						// 它抛出的异常 用exception 捕捉
						// item.write( new File(path,filename) );//第三方提供的
						// 手动写的
						OutputStream out = new FileOutputStream(new File(path,newfilename));
						InputStream in = item.getInputStream();
						int length = 0;
						byte[] buf = new byte[1024];
//						System.out.println("获取上传文件的总共的容量：" + item.getSize());
						// in.read(buf) 每次读到的数据存放在 buf 数组中
						while ((length = in.read(buf)) != -1) {
							// 在 buf 数组中 取出数据 写到 （输出流）磁盘上
							out.write(buf, 0, length);
						}
						in.close();
						out.close();
					}
				}else{
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return map ;
	}
	/**
	 * @author MILI
	 * @time 2014-12-5 11:16:46
	 * @描述：String 2014/12/5 转 2014-12-5
	 * */
	public static String Date_format(String strdate){
		String str_date = strdate ;
		if(strdate != null || !"".equals(strdate)){
			str_date = strdate.replace("/", "-") ;
		}
		return str_date ;
	}
	/**
	 * @author MILI
	 * @Date : 2015年5月29日10:34:38
	 * @描述：汉化
	 * */
	public static SYJK_CCS_QGCLWTMDXX_TEMP Hanization(SYJK_CCS_QGCLWTMDXX_TEMP temp){
		SYJK_CCS_QGCLWTMDXX_TEMP wtmdxx_temp = temp;
		// 汉化异常原因代码
		if(wtmdxx_temp != null){
			if("W".equals(wtmdxx_temp.getYCYYDM())){
				wtmdxx_temp.setYCYYDM("白名单");
			}else if("P".equals(wtmdxx_temp.getYCYYDM())){
				wtmdxx_temp.setYCYYDM("算欠税和滞纳金");
			}else if("S".equals(wtmdxx_temp.getYCYYDM())){
				wtmdxx_temp.setYCYYDM("只算欠税");
			}else if("M".equals(wtmdxx_temp.getYCYYDM())){
				wtmdxx_temp.setYCYYDM("负值（多缴税款）");
			}else{
				
			}
		}
		return wtmdxx_temp ;
	}
}
