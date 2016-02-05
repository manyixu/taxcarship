package com.derun.common.util;

import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.DiskUsage;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetConnection;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Who;


/***
 * 该类可以监控window以及Linux等系统的一些情况
 * 如物理内存的使用情况
 * cpu的使用情况
 * 网络连接数
 * IP地址
 * 该类依赖sigar开源java工具
 * sigar是一个很强大的服务器监控工具，本类只是用了部分，它可以监控的情况包括但不限于一下
 * 1， CPU信息，包括基本信息（vendor、model、mhz、cacheSize）和统计信息（user、sys、idle、nice、wait）
 * 2， 文件系统信息，包括Filesystem、Size、Used、Avail、Use%、Type
 * 3， 事件信息，类似Service Control Manager
 * 4， 内存信息，物理内存和交换内存的总数、使用数、剩余数；RAM的大小
 * 5， 网络信息，包括网络接口信息和网络路由信息
 * 6， 进程信息，包括每个进程的内存、CPU占用数、状态、参数、句柄
 * 7， IO信息，包括IO的状态，读写大小等
 * 8， 服务状态信息
 * 9， 系统信息，包括操作系统版本，系统资源限制情况，系统运行时间以及负载，JAVA的版本信息等 
 * @author shuzhen
 * @mail talentshu@163.com
 * @date 2012-10-09
 */
public class SystemUtil {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void getSystemInfo(){
		
		Sigar sigar = new Sigar();
		double freeMem=0;
		double usedMem=0;
		double totalMem=0;
		
		//获取系统内存使用信息
		try {
			Mem mem=sigar.getMem();
			freeMem = mem.getFree()*1.0/1024/1024;
			usedMem = mem.getUsed()*1.0/1024/1024;
			totalMem = mem.getTotal()*1.0/1024/1024;
			freeMem = new BigDecimal(freeMem).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			usedMem = new BigDecimal(usedMem).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			totalMem = new BigDecimal(totalMem).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
			System.out.println("系统内存使用情况----------------------------------");
			System.out.println("总物理内存："+totalMem+"M");
			System.out.println("已使用物理内存："+usedMem+"M");
			System.out.println("剩余物理内存:"+freeMem+"M");
			System.out.println();
			System.out.println();
		} catch (SigarException e) {
			e.printStackTrace();
		}
		
		//获取cpu 使用率
		double combined=0;
		try {
			CpuPerc cpu = sigar.getCpuPerc();
			combined = (new Double(CpuPerc.format(cpu.getCombined()).replace("%", "")));
			System.out.println("CPU 使用情况率："+combined+"%");
		} catch (SigarException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println();
		
		//获取磁盘使用情况
		boolean isWindows=true;
		List<Map<String, List<Double>>> disinfos = new ArrayList<Map<String, List<Double>>>();
		double disWritBytes=0;
		double disReadBytes=0;
		String disName = null;
		try {
			org.hyperic.sigar.SysInfo sysInfo = new org.hyperic.sigar.SysInfo();
			sysInfo.gather(sigar);
			String systemVendor = sysInfo.getVendorName();
			FileSystem [] fileSystems = sigar.getFileSystemList();
			int index = 0;
			for (FileSystem fileSystem : fileSystems) {
				disName=fileSystem.getDirName();
				if(index>3)break;
				index++;
				DiskUsage diskUsage = sigar.getDiskUsage(disName);
				disWritBytes+=diskUsage.getWriteBytes();
				disReadBytes+=diskUsage.getReadBytes();
				if(!systemVendor.toLowerCase().startsWith("windows")){
					isWindows=false;
					/*if(!disName.equalsIgnoreCase("/")) {//限制如果是Linux系统只获取根目录情况
						continue;
					}*/
				}/*else{//限制只获取C盘和D盘
					if(!disName.equalsIgnoreCase("c:\\")&&!disName.equalsIgnoreCase("d:\\")){
						continue;
					}
				}*/
				double total=0; //分区总容量
				double used=0;  //分区已使用容量
				double avail=0; //分区剩下容量
				double usePercent=0; //分区容量使用百分比
				FileSystemUsage fileSystemUsage = null;
				try {
					try{
						fileSystemUsage=sigar.getFileSystemUsage(disName);
					}catch(SigarException e){
						continue;
					}
					if(fileSystemUsage==null) continue;
					total = fileSystemUsage.getTotal()*1.0/1024/1024; //获取分区总容量
					used = fileSystemUsage.getUsed()*1.0/1024/1024;   //获取分区已使用容量
					avail = fileSystemUsage.getAvail()*1.0/1024/1024;  //获取分区剩余容量
					usePercent = fileSystemUsage.getUsePercent()*100.0;  //获取分区容量使用百分比
					total =new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					used =new BigDecimal(used).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					avail =new BigDecimal(avail).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					usePercent =new BigDecimal(usePercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				Map<String, List<Double>> disInfo = new HashMap<String, List<Double>>();
				List<Double> values = new LinkedList<Double>();
				values.add(total);	
				values.add(used);
				values.add(avail);  
				values.add(usePercent);
				if(isWindows){
					disName = disName.replace(":\\", "盘");
				}
				disInfo.put(disName, values);
				disinfos.add(disInfo);
				/*if(!isWindows){//限制如果是Linux系统只获取根目录
					break;
				}*/
			}
			disWritBytes =new BigDecimal(disWritBytes*1.0/1024/1024/1024).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			disReadBytes =new BigDecimal(disReadBytes*1.0/1024/1024/1024).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(disName+"盘被保护不可访问!");
		}
		
		System.out.println("磁盘容量使用情况-----------------------------");
		for (Map<String, List<Double>> disInfo : disinfos) {
			for(String key: disInfo.keySet()){
				List<Double> values = disInfo.get(key);
				System.out.println(key+" 共："+values.get(0)+"G  使用："+values.get(1)+"G  剩余："+values.get(2)+"G");
			}
		}
		System.out.println();
		System.out.println();
		
		//获取IP地址
		String localAddress=null;
		Enumeration en=null;
		try {
			en = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		List<String> ipList = new ArrayList(); 
		while (en.hasMoreElements()) {
			NetworkInterface intf = (NetworkInterface)en.nextElement();
			Enumeration enAddr = intf.getInetAddresses();
			while (enAddr.hasMoreElements()) {
				InetAddress addr = (InetAddress) enAddr.nextElement();
				if(addr instanceof Inet4Address){
					ipList.add(addr.getHostAddress());
				}
			}
		}
		for(String ip:ipList){
			if(ip.startsWith("192.")){
				localAddress = ip;
				break;
			}
		}
		if(null==localAddress){
			localAddress="未知";
		}
		
		System.out.println("该机器IP为："+localAddress);
		System.out.println();
		System.out.println();
		
		//获取网络连接数
		int flags = NetFlags.CONN_CLIENT;
	    flags |= NetFlags.CONN_TCP;
	    flags |= NetFlags.CONN_UDP;
	    flags |= NetFlags.CONN_RAW;
	    flags |= NetFlags.CONN_UNIX;
	    flags |= NetFlags.CONN_SERVER;
	    flags |= NetFlags.CONN_PROTOCOLS;
	    int connectionSum=0;
	    try{
		    NetConnection[] connections = sigar.getNetConnectionList(flags);
		    connectionSum=connections.length;
	    }catch(Exception e){
	    	connectionSum=0;
	    }
		
	    System.out.println("当前共 "+connectionSum+" 个网络连接。");
	    
	    try {
	    	Who[] whos = sigar.getWhoList();
	    	System.out.println();
	    	System.out.println("");
	    	for(int w=0;w<whos.length;w++){
	    		System.out.println(whos[w].getDevice()+"---"+whos[w].getHost()+"---"+String.valueOf(whos[w].getTime())+"---"+whos[w].getUser());
	    	}
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("获取用户列表失败");
		}
	}
	
	public static void main(String[] args) {
		getSystemInfo();
	}
}
