/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.hex;

import com.olmectron.fire.controllers.EmblemFile;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Edgar
 */
public class Hex {
    
    public static String CHARACTERS_HEADER=new String("54494E55"); //TINU
    public static String CHARACTERS_IDENTIFIER="FFFF04";
    public static String CHARACTERS_FOOTER=new String("FF49464552"); //ÿIFER
    public static String PMOC="504D4F43";
    public static final String DLC_IDENTIFIER="FFFF00000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
    public static final String FFFF_IDENTIFIER="FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
    public static final String F_BLOCK_STARTER="32FFFF";	
    public static int getNameLength(){
         switch(EmblemFile.getActualFile().getRegion()){
            case EmblemFile.WEST:
                return 48;
                
                
            case EmblemFile.JAP:
            
                return 24;
        }
         return 0;
    }
    public static int findArray(byte[] arreglo, byte[] grande){
        return Collections.indexOfSubList(Arrays.asList(grande), Arrays.asList(arreglo));
    }
    public static int getBytesOffset(int val){
        
        switch(EmblemFile.getActualFile().getRegion()){
            case EmblemFile.WEST:
                return 0+val;
                
                
            case EmblemFile.JAP:
            
                return 0+val;
        }
        
        return 0;
    }
}
