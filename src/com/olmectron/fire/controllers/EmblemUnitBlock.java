/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.controllers;

import com.olmectron.fire.hex.Hex;
import com.olmectron.fire.hex.HexConverts;
import com.olmectron.material.MaterialDesign;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

/**
 *
 * @author Edgar
 */
public class EmblemUnitBlock {
   
    public EmblemUnitBlock(EmblemUnit unit, String block, int posicion){
        setOriginalBlock(block);
        setUnit(unit);
        setPosicion(posicion);
        if(EmblemHex.getFates()){
            fatesBlock();
        }
        else{
            parseBlock();
        }
        
    }
    private EmblemUnit unit;
    private void setUnit(EmblemUnit unit){
        this.unit=unit;
    }
    private EmblemUnit getUnit(){
        return unit;
    }
    private StringProperty unitName;
public StringProperty unitNameProperty(){
   if(unitName==null){
       unitName=new SimpleStringProperty(this,"unitName");
   }
   return unitName;
}
public void setUnitName(String val){
   unitNameProperty().set(val);
}
public String getUnitName(){
   return unitNameProperty().get();
}

private StringProperty unitFace;
private StringProperty unitFaceProperty(){
   if(unitFace==null){
       unitFace=new SimpleStringProperty(this,"unitFace");
   }
   return unitFace;
}
private void setUnitFace(String val){
   unitFaceProperty().set(val);
}
public String getUnitFace(){
   return unitFaceProperty().get();
}
private StringProperty unitBody;
private StringProperty unitBodyProperty(){
   if(unitBody==null){
       unitBody=new SimpleStringProperty(this,"unitBody");
   }
   return unitBody;
}
private void setUnitBody(String val){
   unitBodyProperty().set(val);
}
public String getUnitBody(){
   return unitBodyProperty().get();
}
private StringProperty unitHair;
private StringProperty unitHairProperty(){
   if(unitHair==null){
       unitHair=new SimpleStringProperty(this,"unitHair");
   }
   return unitHair;
}
private void setUnitHair(String val){
   unitHairProperty().set(val);
}
public String getUnitHair(){
   return unitHairProperty().get();
}

private ObjectProperty<Color> unitHairColor;
public ObjectProperty<Color> unitHairColorProperty(){
   if(unitHairColor==null){
       unitHairColor=new SimpleObjectProperty<Color>(this,"unitHairColor");
       
       unitHairColor.addListener(new ChangeListener<Color>(){
           @Override
           public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
               EmblemHex.updateUnitHairColor(unit, newValue);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
       });
       
       
       
   }
   return unitHairColor;
}
private IntegerProperty posicion;
private IntegerProperty posicionProperty(){
   if(posicion==null){
       posicion=new SimpleIntegerProperty(this,"posicion");
   }
   return posicion;
}
private void setPosicion(int val){
    //System.out.println("Posicion: "+val);
    
   posicionProperty().set(val);
   setHexPosition(val/2);
}
public int getPosicion(){
   return posicionProperty().get();
}
private IntegerProperty hexPosition;
private IntegerProperty hexPositionProperty(){
   if(hexPosition==null){
       hexPosition=new SimpleIntegerProperty(this,"hexPosition");
       
       
   }
   return hexPosition;
}
private void setHexPosition(int val){
   hexPositionProperty().set(val);
}
public int getHexPosition(){
   return hexPositionProperty().get();
}

public void setStringHairColor(String color){
    if(color.contains("#")){
    setUnitHairColor(Color.web(color));    
    
    }
    else{
        setUnitHairColor(Color.web("#"+color));
    }
    
}
public void setUnitHairColor(Color val){
   unitHairColorProperty().set(val);
}
public Color getUnitHairColor(){
   return unitHairColorProperty().get();
}

private StringProperty unitId;
private StringProperty unitIdProperty(){
   if(unitId==null){
       unitId=new SimpleStringProperty(this,"unitId");
   }
   return unitId;
}
private void setUnitId(String val){
   unitIdProperty().set(val);
}
private String getUnitId(){
   return unitIdProperty().get();
}

private StringProperty day;
private StringProperty dayProperty(){
   if(day==null){
       day=new SimpleStringProperty(this,"day");
   }
   return day;
}
public void setDay(String val){
   dayProperty().set(val);
}
public String getDay(){
   return dayProperty().get();
}

    
private StringProperty month;
private StringProperty monthProperty(){
   if(month==null){
       month=new SimpleStringProperty(this,"month");
   }
   return month;
}
public void setMonth(String val){
   monthProperty().set(val);
}
public String getMonth(){
   return monthProperty().get();
}
public byte[] getByteArrayBlock(){
    byte[] editedBytes=HexConverts.hexStringToByteArray(getBlock());
    return editedBytes;
}

private IntegerProperty unitHP;
public IntegerProperty unitHPProperty(){
   if(unitHP==null){
       unitHP=new SimpleIntegerProperty(this,"unitHP");
   }
   return unitHP;
}
public void setUnitHP(int val){
   unitHPProperty().set(val);
}
public int getUnitHP(){
   return unitHPProperty().get();
}

private IntegerProperty unitSTR;
public IntegerProperty unitSTRProperty(){
   if(unitSTR==null){
       unitSTR=new SimpleIntegerProperty(this,"unitSTR");
   }
   return unitSTR;
}
public void setUnitSTR(int val){
   unitSTRProperty().set(val);
}
public int getUnitSTR(){
   return unitSTRProperty().get();
}

private IntegerProperty unitMAG;
public IntegerProperty unitMAGProperty(){
   if(unitMAG==null){
       unitMAG=new SimpleIntegerProperty(this,"unitMAG");
   }
   return unitMAG;
}
public void setUnitMAG(int val){
   unitMAGProperty().set(val);
}
public int getUnitMAG(){
   return unitMAGProperty().get();
}

private IntegerProperty unitSKL;
public IntegerProperty unitSKLProperty(){
   if(unitSKL==null){
       unitSKL=new SimpleIntegerProperty(this,"unitSKL");
   }
   return unitSKL;
}
public void setUnitSKL(int val){
   unitSKLProperty().set(val);
}
public int getUnitSKL(){
   return unitSKLProperty().get();
}

private IntegerProperty unitSPD;
public IntegerProperty unitSPDProperty(){
   if(unitSPD==null){
       unitSPD=new SimpleIntegerProperty(this,"unitSPD");
   }
   return unitSPD;
}
public void setUnitSPD(int val){
   unitSPDProperty().set(val);
}
public int getUnitSPD(){
   return unitSPDProperty().get();
}

private IntegerProperty unitLCK;
public IntegerProperty unitLCKProperty(){
   if(unitLCK==null){
       unitLCK=new SimpleIntegerProperty(this,"unitLCK");
   }
   return unitLCK;
}
public void setUnitLCK(int val){
   unitLCKProperty().set(val);
}
public int getUnitLCK(){
   return unitLCKProperty().get();
}

private IntegerProperty unitDEF;
public IntegerProperty unitDEFProperty(){
   if(unitDEF==null){
       unitDEF=new SimpleIntegerProperty(this,"unitDEF");
   }
   return unitDEF;
}
public void setUnitDEF(int val){
   unitDEFProperty().set(val);
}
public int getUnitDEF(){
   return unitDEFProperty().get();
}

private IntegerProperty unitRES;
public IntegerProperty unitRESProperty(){
   if(unitRES==null){
       unitRES=new SimpleIntegerProperty(this,"unitRES");
   }
   return unitRES;
}
public void setUnitRES(int val){
   unitRESProperty().set(val);
}
public int getUnitRES(){
   return unitRESProperty().get();
}


private IntegerProperty unitAsset;
public IntegerProperty unitAssetProperty(){
   if(unitAsset==null){
       unitAsset=new SimpleIntegerProperty(this,"unitAsset");
   }
   return unitAsset;
}
public void setUnitAsset(int val){
   unitAssetProperty().set(val);
}
public int getUnitAsset(){
   return unitAssetProperty().get();
}
private IntegerProperty unitFlaw;
public IntegerProperty unitFlawProperty(){
   if(unitFlaw==null){
       unitFlaw=new SimpleIntegerProperty(this,"unitFlaw");
   }
   return unitFlaw;
}

private IntegerProperty unitAddedMovement;
public IntegerProperty unitAddedMovementProperty(){
   if(unitAddedMovement==null){
       unitAddedMovement=new SimpleIntegerProperty(this,"unitAddedMovement");
   }
   return unitAddedMovement;
}
public void setUnitAddedMovement(int val){
   unitAddedMovementProperty().set(val);
}
public int getUnitAddedMovement(){
   return unitAddedMovementProperty().get();
}

public void setUnitFlaw(int val){
   unitFlawProperty().set(val);
}
public int getUnitFlaw(){
   return unitFlawProperty().get();
}
    private void fatesBlock(){
        
    }
//HP=01,STR=02,MAG=03,SKL=04,SPD=05,LCK=06,DEF=07,RES=08
    private void parseBlock(){
        String statsBlock=getBlock().substring(20,36);
        setUnitHP(Integer.decode("0x"+statsBlock.substring(0,2)));
        setUnitSTR(Integer.decode("0x"+statsBlock.substring(2,4)));
        setUnitMAG(Integer.decode("0x"+statsBlock.substring(4,6)));
        setUnitSKL(Integer.decode("0x"+statsBlock.substring(6,8)));
        setUnitSPD(Integer.decode("0x"+statsBlock.substring(8,10)));
        setUnitLCK(Integer.decode("0x"+statsBlock.substring(10,12)));
        setUnitDEF(Integer.decode("0x"+statsBlock.substring(12,14)));
        setUnitRES(Integer.decode("0x"+statsBlock.substring(14,16)));
        setUnitAddedMovement(Integer.decode("0x"+getBlock().substring(42,44)));
        unitAddedMovementProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String updateBlock=getBlock().substring(0,42)+HexConverts.getHexPair(newValue.intValue())+
                        getBlock().substring(44);
                updateEditedBlock(updateBlock);
            }
        });
        
        unitHPProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String updateBlock=getBlock().substring(0,20)+HexConverts.getHexPair(newValue.intValue())+
                        getBlock().substring(22);
                updateEditedBlock(updateBlock);
            }
        });
        
        unitSTRProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String updateBlock=
                        getBlock().substring(0,22)
                        +HexConverts.getHexPair(newValue.intValue())+
                        getBlock().substring(24);
                updateEditedBlock(updateBlock);
            }
        });
        unitMAGProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String updateBlock=
                        getBlock().substring(0,24)
                        +HexConverts.getHexPair(newValue.intValue())+
                        getBlock().substring(26);
                updateEditedBlock(updateBlock);
            }
        });
        unitSKLProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String updateBlock=
                        getBlock().substring(0,26)
                        +HexConverts.getHexPair(newValue.intValue())+
                        getBlock().substring(28);
                updateEditedBlock(updateBlock);
            }
        });
        unitSPDProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String updateBlock=
                        getBlock().substring(0,28)
                        +HexConverts.getHexPair(newValue.intValue())+
                        getBlock().substring(30);
                updateEditedBlock(updateBlock);
            }
        });
        unitLCKProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String updateBlock=
                        getBlock().substring(0,30)
                        +HexConverts.getHexPair(newValue.intValue())+
                        getBlock().substring(32);
                updateEditedBlock(updateBlock);
            }
        });
        unitDEFProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String updateBlock=
                        getBlock().substring(0,32)
                        +HexConverts.getHexPair(newValue.intValue())+
                        getBlock().substring(34);
                updateEditedBlock(updateBlock);
            }
        });
        unitRESProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String updateBlock=
                        getBlock().substring(0,34)
                        +HexConverts.getHexPair(newValue.intValue())+
                        getBlock().substring(36);
                updateEditedBlock(updateBlock);
            }
        });
        
        if(unit.getNameEditable()){
        setUnitId(getBlock().substring(4,6));
        
        int nameStart=getBlock().
                indexOf(Hex.FFFF_IDENTIFIER)+
                Hex.FFFF_IDENTIFIER.length()+30;
                String nameHex=
                HexConverts.
                        trimBinaryZeroValueBytes(getBlock().
                                substring(nameStart,nameStart+Hex.getNameLength()));
                setUnitName(HexConverts.getWordFromHex(nameHex));
                unitNameProperty().addListener(new ChangeListener<String>(){
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        String bloque=getBlock();
                       
                        String nombre="";
                        int wordCount=0;
                        for(int i=0;i<26;i++){
                            if(i%2==0){
                                if(wordCount<newValue.length())
                                nombre=nombre+HexConverts.getHexFromLetter(newValue.substring(wordCount));
                                else
                                nombre=nombre+"00";
                                
                                wordCount++;
                            }
                            else{
                                nombre=nombre+"00";
                            }
                            
                        }
                        bloque=bloque.substring(0,nameStart)+nombre+bloque.substring(nameStart+52);
                        updateEditedBlock(bloque);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
        }                
         if(unit.getAvatar() || unit.getDLCSpotpass()){
             
        
         final int   startGender=getBlock().indexOf(Hex.FFFF_IDENTIFIER)
                +Hex.FFFF_IDENTIFIER.length()+38+Hex.getNameLength();
         
         
         
        //if(getHexCode()==2){
        //    startGender=charBlock.indexOf(HexConstants.DLC_IDENTIFIER)
        //        +EmblemController.dlcIdentifier.length()+82+4;
        
        //}
                String asset=getBlock().substring(startGender-4,startGender-2);
                String flaw=getBlock().substring(startGender-2,startGender);
                setUnitAsset(Integer.decode("0x"+asset));
                setUnitFlaw(Integer.decode("0x"+flaw));
                unitAssetProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String updateBlock=getBlock().substring(0,startGender-4)+HexConverts.getHexPair(newValue.intValue())+
                        getBlock().substring(startGender-2);
                updateEditedBlock(updateBlock);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        } );
                unitFlawProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String updateBlock=getBlock().substring(0,startGender-2)+HexConverts.getHexPair(newValue.intValue())+
                        getBlock().substring(startGender);
                updateEditedBlock(updateBlock);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        } );
                String gender=getBlock().substring(startGender,startGender+2);
                String build=getBlock().substring(startGender+2,startGender+4);
                String face=getBlock().substring(startGender+4,startGender+6);
                String hair=getBlock().substring(startGender+6,startGender+8);
                String hairColorString=getBlock().substring(startGender+8,startGender+14);
                setUnitHairColor(Color.web("#"+hairColorString));
                setUnitBody((build));
                setUnitFace((face));
                setUnitHair((hair));
                setUnitGender(gender);
                //testParseAvatar=(charBlock.substring(startGender, startGender+14));
                setDay(getBlock().substring(startGender+18, startGender+20));
                setMonth(getBlock().substring(startGender+20, startGender+22));
                setUnitSpotpassCode(getBlock().substring(startGender+22, startGender+24));
                //testParseAvatar=""+diaCumple+" "+mesCumple;
         
         
         }
         if(unit.getChild() && unit.getHairColorEditable()){
        		//var companionBlock=emblemCharacter.hexBlock;
					
					int	start=getBlock().indexOf(Hex.F_BLOCK_STARTER)+96;
						
						
						
					
					//var start=personaje.hexBlock.indexOf("FFFFFFFFFF")+"FFFFFFFFFF".length+18;
					
                                        String hairColorString=getBlock().substring(start,start+6);
					setUnitHairColor(Color.web("#"+hairColorString));
					//console.log("child hair "+hairColorString);
										//return MaterialDesign.customPath+"/character_images/"+nombre+"/"+imageName+".png";
							/*String backImageName=MaterialDesign.customPath+"/character_images/portraits/children/"+
										getName().replace("'","").replace(" ","").toLowerCase()+"_shadow.png";
							String portraitName=MaterialDesign.customPath+"/character_images/portraits/children/"+
										getName().replace("'","").replace(" ","").toLowerCase()+".png";
							*/	
                                                      
         }
         if(unit.getAvatar() || !unit.getNameEditable()){
            String blockLeft=getBlock().substring(52);
            int startAliveHex=blockLeft.indexOf("FFFF")-(8);
            try{
            if(blockLeft.substring(startAliveHex,startAliveHex+2).equals("08")){
                setAlive(false);
                //System.out.println(unit.getName()+" muerto "+blockLeft.substring(startAliveHex,startAliveHex+16));
                
            }
            else if(blockLeft.substring(startAliveHex+2,startAliveHex+4).equals("04")
                    || blockLeft.substring(startAliveHex,startAliveHex+8).equals("00000000")
                    ){
                setAlive(true);
                //System.out.println(unit.getName()+" vivo "+blockLeft.substring(startAliveHex,startAliveHex+16));
                
            }
            aliveProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean alive) {
                    String bloque=getBlock();
                    if(alive){
                        bloque=bloque.substring(0,startAliveHex+52)+"00000000"+bloque.substring(startAliveHex+52+8);
                    }
                    else{
                        
                        bloque=bloque.substring(0,startAliveHex+52)+"08000000"+bloque.substring(startAliveHex+52+8);
                    }
                    updateEditedBlock(bloque);
            //        byte[] editedBytes=HexConverts.hexStringToByteArray(bloque);
           //HexConverts.writeBytes(editedBytes, EmblemFile.getActualFile().getEditableBytes(),
           //        EmblemHex.getUnitsBlockPosition()+getHexPosition());
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
            }
            catch(StringIndexOutOfBoundsException ex){
                System.err.println(ex.getMessage()+", Unit: "+unit.getName());
            }
         }
                /*int genderInt=Utils.getIntFromHex(gender);
                if(genderInt==0){
                    person.setGender(EmblemClass.MALE);
                }
                else if(genderInt==1){
                    person.setGender(EmblemClass.FEMALE);
                }
                else{
                    System.out.println("Character without gender");
                }*/
    }
    private StringProperty unitSpotpassCode;
private StringProperty unitSpotpassCodeProperty(){
   if(unitSpotpassCode==null){
       unitSpotpassCode=new SimpleStringProperty(this,"unitSpotpassCode");
   }
   return unitSpotpassCode;
}
private void setUnitSpotpassCode(String val){
   unitSpotpassCodeProperty().set(val);
}
public String getUnitSpotpassCode(){
   return unitSpotpassCodeProperty().get();
}

    private StringProperty unitGender;
private StringProperty unitGenderProperty(){
   if(unitGender==null){
       unitGender=new SimpleStringProperty(this,"unitGender");
   }
   return unitGender;
}
public void setUnitGender(String val){
   unitGenderProperty().set(val);
}
public String getUnitGender(){
   return unitGenderProperty().get();
}

    private StringProperty originalBlock;
private StringProperty originalBlockProperty(){
   if(originalBlock==null){
       originalBlock=new SimpleStringProperty(this,"originalBlock");
   }
   return originalBlock;
}
private void setOriginalBlock(String val){
    setEditedBlock(val);
   originalBlockProperty().set(val);
}
public String getOriginalBlock(){
   return originalBlockProperty().get();
}

private StringProperty editedBlock;
private StringProperty editedBlockProperty(){
   if(editedBlock==null){
       editedBlock=new SimpleStringProperty(this,"editedBlock");
   }
   return editedBlock;
}
private void setEditedBlock(String val){
   editedBlockProperty().set(val);
}
public void updateEditedBlock(String newBlock){
    setEditedBlock(newBlock);
}
public String getEditedBlock(){
   return editedBlockProperty().get();
}

private String getBlock(){
    return getEditedBlock();
}
private BooleanProperty alive;
public BooleanProperty aliveProperty(){
   if(alive==null){
       alive=new SimpleBooleanProperty(this,"alive");
       alive.set(true);
   }
   return alive;
}
public void setAlive(boolean val){
   aliveProperty().set(val);
}
public boolean getAlive(){
   return aliveProperty().get();
}


}
