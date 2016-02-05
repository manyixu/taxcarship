package com.derun.common.util;

public class SBDZUtil {
	private static int count =1;
	private static int sb = 1;
	
	public synchronized static int  getCount() {
		if(count<30){
			count++;
		}else{
			count=1;
		}
		return count;
	}
	
    public synchronized static int getSbCount(){
    	if(sb<40){
    		sb++;
    	}else{
    		sb=1;
    	}
    	return sb;
    }
}
