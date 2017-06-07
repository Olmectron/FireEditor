/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.controllers;

import com.olmectron.fire.hex.Hex;
import com.olmectron.fire.hex.HexConverts;
import com.olmectron.material.utils.BackgroundTask;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

/**
 *
 * @author Edgar
 */
public abstract class EmblemHex {
    private static int afterTINUPos=0;
    private static int unitCounter=0;
    private static void setAfterTINUHexPosition(int position){
        afterTINUPos=position/2;
        System.out.println("Byte TINU after POS: "+afterTINUPos);
    }
    private static void setActiveUnitsCounterFromHeader(int counter){
        unitCounter=counter;
        System.out.println("Header units counter: "+counter);
    }
    public static int getActiveUnitsCounter(){
        return unitCounter;
    }
    public static int getUnitsBlockPosition(){
        return afterTINUPos;
    }
    public static void updateUnitHairColor(EmblemUnit unit, Color color){
        if(unit.getUnitBlock()!= null && unit.getUnitBlock().getEditedBlock()!=null){
        String block=unit.getUnitBlock().getEditedBlock();
        
        String colorString=color.toString().substring(2);
        colorString=colorString.substring(0, colorString.length()-2).toUpperCase();
                 
        if(!unit.getDLCSpotpass()){
       int startColor=block.
               indexOf(Hex.F_BLOCK_STARTER)
                +Hex.F_BLOCK_STARTER.length()+((0x2D)*2);
       block=block.substring(0,startColor)+colorString+block.substring(startColor+6);
                unit.getUnitBlock().updateEditedBlock(
                block
                );
        }
        else{
            
           int startColor=block.indexOf(Hex.DLC_IDENTIFIER)+Hex.DLC_IDENTIFIER.length()+((0x09)*2);
           block=block.substring(0,startColor)+colorString+block.substring(startColor+6);
           unit.getUnitBlock().updateEditedBlock(block);
           int startColorAfterName=block.indexOf(Hex.DLC_IDENTIFIER)+Hex.DLC_IDENTIFIER.length()+((0x2F)*2);
           block=block.substring(0,startColorAfterName)+colorString+block.substring(startColorAfterName+6);
           unit.getUnitBlock().updateEditedBlock(block);
           
       
        }
        
       if(unit.getAvatar()){
           int startColorAfterName=block.
               indexOf(Hex.F_BLOCK_STARTER)
                +Hex.F_BLOCK_STARTER.length()+((0x53)*2);
           block=block.substring(0,startColorAfterName)+
                        colorString+block.substring(startColorAfterName+6);
           unit.getUnitBlock().updateEditedBlock(
                block
                );
           
       
       }
       
       //byte[] editedBytes=HexConverts.hexStringToByteArray(block);
       //    HexConverts.writeBytes(editedBytes, EmblemFile.getActualFile().getEditableBytes(),
       //            EmblemHex.getUnitsBlockPosition()+unit.getUnitBlock().getHexPosition());
       
        }
                    
    }
    
    private static String getCharactersBlock(){
         String full=EmblemFile.getActualFile().getFullDecompressedHex();
         
        int start=full.indexOf(Hex.CHARACTERS_HEADER)+
                Hex.CHARACTERS_HEADER.length();
        setAfterTINUHexPosition(start);

                int end=full.indexOf(Hex.CHARACTERS_FOOTER);
                
                String bloque=full.substring(start,end);
                setActiveUnitsCounterFromHeader(Integer.decode("0x"+bloque.substring(4, 6)));
System.out.println("Characters hex: "+bloque);
        return bloque;
    }
    private static ArrayList<Integer> getCharacterPositions(String hexBlock){
        ArrayList<Integer> positionList=new ArrayList<Integer>();
        for (int index = hexBlock.indexOf(Hex.CHARACTERS_IDENTIFIER);
        index >= 0;
        index = hexBlock.indexOf(Hex.CHARACTERS_IDENTIFIER, index + 1))
    {   
        String hocBlock=hexBlock.substring(index-48);
        if(hocBlock.startsWith("07") && hocBlock.substring(4,6).equals(("00"))){
            positionList.add(index-48);
            System.out.println("Índice: "+ index);
        }
        
        
    }
        return positionList;
        
    }
    private static ArrayList<Integer> getFatesPositions(String hexBlock){
        ArrayList<Integer> positionList=new ArrayList<Integer>();
        for (int index = hexBlock.indexOf("FFFF");
        index >= 0;
        index = hexBlock.indexOf("FFFF", index + 1))
    {   
        try{
            String hocBlock=hexBlock.substring(index-168);
            if(hocBlock.startsWith("07") && hocBlock.substring(168,172).equals(("FFFF"))){
                positionList.add(index-168);
                //System.out.println("Índice: "+ index);
            }
        }
        catch(StringIndexOutOfBoundsException ex){
            
        }
        
    }
        return positionList;
        
    }
    private static ArrayList<String> getFatesHexBlocks(String hexBlock, ArrayList<Integer> posiciones){
        //ArrayList<Integer> posiciones=getCharacterPositions(hexBlock);
        ArrayList<String> hexBlocks=new ArrayList<String>();
        for (int i=0; i<posiciones.size();i++) {
            Integer posicion=posiciones.get(i);
            if(i+1<posiciones.size()){
                Integer posicionFinal=posiciones.get(i+1);
            
                hexBlocks.add(hexBlock.substring(posicion, posicionFinal));
        
            }
            else{
                hexBlocks.add(hexBlock.substring(posicion));
        
            }
            
            
        }
        return hexBlocks;
    }
    /*private static ArrayList<Integer> getFatesPositions(String hexBlock){
        ArrayList<Integer> positionList=new ArrayList<Integer>();
        for (int index = hexBlock.indexOf("FFFFFFFFFFFFFFFF");
        index >= 0;
        index = hexBlock.indexOf("FFFFFFFFFFFFFFFF", index + 1))
    {   
        String hocBlock=hexBlock.substring(index-126);
        if(hocBlock.startsWith("07") && hocBlock.substring(168,172).equals(("FFFF"))){
            positionList.add(index-126);
            //System.out.println("Índice: "+ index);
        }
        
        
    }
        return positionList;
        
    }*/
    private static ArrayList<String> getCharacterHexBlocks(String hexBlock, ArrayList<Integer> posiciones){
        //ArrayList<Integer> posiciones=getCharacterPositions(hexBlock);
        ArrayList<String> hexBlocks=new ArrayList<String>();
        for (int i=0; i<posiciones.size();i++) {
            Integer posicion=posiciones.get(i);
            if(i+1<posiciones.size()){
                Integer posicionFinal=posiciones.get(i+1);
            
                hexBlocks.add(hexBlock.substring(posicion, posicionFinal));
        
            }
            else{
                hexBlocks.add(hexBlock.substring(posicion));
        
            }
            
            
        }
        return hexBlocks;
    }
    private static int getCharacterCount(String hexBlock){
        int count=Integer.decode("0x"+hexBlock.substring(4, 6));
        //setCharacterCount(count);
        return count;
    }
    public static EmblemUnit getNewEmblemUnit(EmblemUnit unit){
        
        String hexBlock="07"+HexConverts.getHexPair(unit.getId())+
                "00"+"000000000000000000000000000000000000000000"+Hex.CHARACTERS_IDENTIFIER+
                "400000000040000000004000000000400000000040000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFF0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFF00000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000000000000"
                ;
        return unit.copy(hexBlock, 0);
    }
    private static EmblemUnit getEmblemUnit(String charBlock, int position){
        if(charBlock.startsWith("07") && charBlock.substring(4, 6).equals("00") && charBlock.substring(48, 54).equals(Hex.CHARACTERS_IDENTIFIER)){
            int charCode=Integer.decode("0x"+charBlock.substring(2,4));
            for (EmblemUnit unit : EmblemUnit.getUnits()) {
                if(charCode==unit.getId()){

                    return unit.copy(charBlock, position);
                }
            }
        }
        return null;
    }
    private static EmblemUnit getFatesUnit(String charBlock, int position){
        try{
            int charCode=Integer.decode("0x"+charBlock.substring(26,28));
            for (EmblemUnit unit : EmblemUnit.getUnits()) {
                if(charCode==unit.getId()){

                    return unit.copy(charBlock,position);
                }
            }
        }
        catch(StringIndexOutOfBoundsException ex){
            
        }
        return null;
    }
    
    public abstract void onUnitLoaded(EmblemUnit unit);
    public abstract void onLoadSucceed(ObservableList<EmblemUnit> units);
    private BackgroundTask<ObservableList<EmblemUnit>> unitsTask=new BackgroundTask<ObservableList<EmblemUnit>>() {
        @Override
        public ObservableList<EmblemUnit> onAction() {
            
            return getUnitsAction();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void onSucceed(ObservableList<EmblemUnit> valor) {
            onLoadSucceed(valor);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    public void getUnits(){
        onLoadSucceed(getUnitsAction());
        //unitsTask.play();
    }
    private ObservableList<EmblemUnit> parseFatesUnits(){
        String charsBlock=getCharactersBlock();
        int totalCount=getCharacterCount(charsBlock);
        
        ObservableList<EmblemUnit> unitsArray=FXCollections.observableArrayList();
        ArrayList<Integer> position=getFatesPositions(charsBlock);
        System.out.println("Fates save");
        ArrayList<String> characterBlocks=getFatesHexBlocks(charsBlock,position);
        int counter=0;
        for (String characterBlock : characterBlocks) {
            //System.out.println(characterBlock.substring(0,10)+" block start "+position.get(c)+" pos start");
            EmblemUnit unit=getFatesUnit(characterBlock,position.get(counter));
            counter++;
            if(unit!=null){
                
                
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        onUnitLoaded(unit);
                        //To change body of generated methods, choose Tools | Templates.
                    }
                });
                /*
                
                if(isYourUnit(personaje,characterBlock) || isDLCCharacter(personaje,characterBlock)){
                    int start=characterBlock.indexOf(fIdentifier)+fIdentifier.length()+30;
                         String nameHex=Utils.trimBinaryPair(characterBlock.substring(start,start+48));
                         personaje.setDisplayName(Utils.getStringFromHex(nameHex));
                         
                }
                
                
                
                personaje.setHexPosition(position.get(c));
                personaje.setHexBlock(characterBlock);
                
                */
                
                unitsArray.add(unit);
            }
        }
        return unitsArray;
    }
    public ObservableList<EmblemUnit> getUnitsAction(){
        String charsBlock=getCharactersBlock();
        int totalCount=getCharacterCount(charsBlock);
        //System.out.println("Total characters: "+totalCount);
        ObservableList<EmblemUnit> unitsArray=FXCollections.observableArrayList();
        ArrayList<Integer> position=getCharacterPositions(charsBlock);
        for(Integer val: position){
            System.err.print(val.intValue()+",");
        }
        
        ArrayList<String> characterBlocks=getCharacterHexBlocks(charsBlock,position);
        
//int c=0;
        //System.out.println(characterBlocks.size()+" character blocks");
        int counter=0;
        for (String characterBlock : characterBlocks) {
            //System.out.println(characterBlock.substring(0,10)+" block start "+position.get(c)+" pos start");
            EmblemUnit unit=getEmblemUnit(characterBlock,position.get(counter));
            counter++;
            if(unit!=null){
                
                
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        onUnitLoaded(unit);
                        //To change body of generated methods, choose Tools | Templates.
                    }
                });
                /*
                
                if(isYourUnit(personaje,characterBlock) || isDLCCharacter(personaje,characterBlock)){
                    int start=characterBlock.indexOf(fIdentifier)+fIdentifier.length()+30;
                         String nameHex=Utils.trimBinaryPair(characterBlock.substring(start,start+48));
                         personaje.setDisplayName(Utils.getStringFromHex(nameHex));
                         
                }
                
                
                
                personaje.setHexPosition(position.get(c));
                personaje.setHexBlock(characterBlock);
                
                */
                
                unitsArray.add(unit);
            }
            //c++;
            
            
        }
       if(unitsArray.isEmpty()){
           setFates(true);
           return parseFatesUnits();
       }
       else{
           setFates(false);
       }
       
        return unitsArray;
    }
    
private static BooleanProperty fates;
public static BooleanProperty fatesProperty(){
   if(fates==null){
       fates=new SimpleBooleanProperty(EmblemHex.class,"fates");
       fates.set(false);
   }
   return fates;
}
public static void setFates(boolean val){
   fatesProperty().set(val);
}
public static boolean getFates(){
   return fatesProperty().get();
}

}
