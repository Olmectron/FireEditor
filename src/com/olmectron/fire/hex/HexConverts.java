/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.hex;

import com.olmectron.fire.controllers.EmblemFile;

/**
 *
 * @author Edgar
 */
public class HexConverts {
    public static void writeBytes(byte[] copy, byte[] to, int offset){
        int copyCounter=0;
        for(int i=offset;i<copy.length+offset;i++){
            to[i]=copy[copyCounter];
            copyCounter++;
        }
    }
    public static String getHexFromLetter(String letter){
        
        return getHexPair(letter.getBytes()[0]);
    }
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
         return data;
    }
    public static String getHexPair(int code){
        if(code<0){
            code=code & 0xff;
        }
        if(code<0x10){
            return "0"+Integer.toHexString(code).toUpperCase();
        }
        else{
            return Integer.toHexString(code).toUpperCase();
        }
    }
    public static String getHexString(byte[] array){
        String hex="";
        for(int i=0;i<array.length;i++){
            hex=hex+getHexPair(array[i]);
        }
        return hex;
    }
    public static String trimBinaryZeroValueBytes(String binary){
            String b=binary;
            while(b.startsWith("00")){
              b=b.substring(2);
            }
            while(b.endsWith("00")){
                b=b.substring(0,b.length()-2);
            }
            return b;
    }
    public static String getWordFromHex(String hex){
        if(hex.length()%2!=0){
            System.out.println(hex.length()%2+" "+hex);
            return null;
        }
        else{
            String version=EmblemFile.getActualFile().getRegion();
            if(version.equals(EmblemFile.WEST)){
                String result="";
                for(int i=0;i<hex.length();i+=2){
                    String hexPair=hex.substring(i,i+2);
                    if(!hexPair.equals("00")){
                        char newChar=(char)getIntFromHex(hexPair);
                        result=result+String.valueOf(newChar);
                    }
                }
                return result;
            }
            else if(version.equals(EmblemFile.JAP)){
                        //String hex=hexx.toString();
                        //System.out.println(hex+" JAP WORD");
			String result="";
                        try{
			for(int i=0;i<hex.length();i+=4){
				String newHex=hex.substring(i+2,i+4)+hex.substring(i,i+2);
                               
				result+=Character.toString((char)(Integer.decode("0x"+newHex).intValue()));
			}
                        }catch(StringIndexOutOfBoundsException ex){
                            System.err.println(ex.getMessage());
                        }
			return result;
            }
            return null;
        }
    }
    public static int getIntFromHex(String hex){
        if(!hex.startsWith("0x")){
            return Integer.decode("0x"+hex);
        }
        else{
            return Integer.decode(hex);
        }
    }
}
