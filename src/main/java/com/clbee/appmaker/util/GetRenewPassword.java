package com.clbee.appmaker.util;

public class GetRenewPassword {
	public static String getRenewPassword(){
		  String password="";
		  for(int i= 0; i<8; i++){
			   if(i%2 == 0){
				   int rnum = (int)(Math.random() * 10);
				   password += rnum;
			   }else{
				   char lowerStr = (char)(Math.random() * 26 + 97);
				   password += lowerStr;
			   }
		  }
		  
		  return password;
	}
}	  