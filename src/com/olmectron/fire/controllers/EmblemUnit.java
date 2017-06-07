/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.controllers;

import com.olmectron.fire.R;
import com.olmectron.fire.hex.HexConverts;
import com.olmectron.fire.panes.UnitInfoPane;
import com.olmectron.fire.panes.UnitPane;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.ColoredStackPane;
import static com.sun.media.jfxmedia.control.VideoFormat.ARGB;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
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
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Edgar
 */
public class EmblemUnit {
    private BooleanProperty avatar;
    public byte[] getBytesBlock(){
        return getUnitBlock().getByteArrayBlock();
    }
    
    
private BooleanProperty important;
public BooleanProperty importantProperty(){
   if(important==null){
       important=new SimpleBooleanProperty(this,"important");
   }
   return important;
}
public void setImportant(boolean val){
   importantProperty().set(val);
}
public boolean getImportant(){
   return importantProperty().get();
}

private BooleanProperty avatarProperty(){
   if(avatar==null){
       avatar=new SimpleBooleanProperty(this,"avatar");
   }
   return avatar;
}
public int retrieveFace(){
    return Integer.decode("0x"+getUnitBlock().getUnitFace());
    
}
private ScrollPane unitScroll;
public ScrollPane getUnitScrollPane(){
   if(unitScroll==null){
       
                    unitScroll=new ScrollPane(getUnitPane());
                    unitScroll.setFitToWidth(true);
                    unitScroll.setPrefHeight(1080);
                    unitScroll.getStyleClass().add("material-scroll");
   }
   return unitScroll;
}
public UnitInfoPane unitInfoPane;
public UnitInfoPane getUnitPane(){
    if(unitInfoPane==null){
        unitInfoPane=new UnitInfoPane(this);
    }
    return unitInfoPane;
}
public UnitPane unitPane;
public UnitPane getUnitLayout(){
    if(unitPane==null){
        unitPane=new UnitPane(this);
    }
    return unitPane;
}
public String retrieveGenderLetter(){
    int gender=Integer.decode("0x"+getUnitBlock().getUnitGender());
    if(gender==0){
        return "m";
    }
    else if(gender==1){
        return "f";
    }
    else return "u";
}
public boolean hasBirthday(){
    return ((Integer.decode("0x"+getUnitBlock().getDay()) + Integer.decode("0x"+getUnitBlock().getMonth()))!=0);
        
    
}
public boolean isDLCUnit(){
    return getDLCSpotpass() && retrieveFace()>5 && !hasBirthday();
}
public boolean isSpotpassUnit(){
    
    return getDLCSpotpass() && retrieveFace()<5 && !hasBirthday();

}
public boolean isLogbookAvatar(){
    return getDLCSpotpass() && retrieveFace()<5 && hasBirthday();
    
}

public void setAvatar(boolean val){
   avatarProperty().set(val);
}
public boolean getAvatar(){
   return avatarProperty().get();
}
private BooleanProperty DLCSpotpass;
private BooleanProperty DLCSpotpassProperty(){
   if(DLCSpotpass==null){
       DLCSpotpass=new SimpleBooleanProperty(this,"DLCSpotpass");
   }
   return DLCSpotpass;
}
public boolean isAlive(){
    return getUnitBlock().getAlive();
}
public void setDLCSpotpass(boolean val){
   DLCSpotpassProperty().set(val);
}
public boolean getDLCSpotpass(){
   return DLCSpotpassProperty().get();
}

    private EmblemNameMap namesMap;
    private void setNamesMap(EmblemNameMap nMap){
        this.namesMap=nMap;
    }
    public void addName(EmblemLocale locale,String name){
        getNamesMap().addName(locale, name);
       
    }
    private EmblemPortrait portrait;
    public Node getCopyPortrait(double width, double height){
        return new EmblemPortrait(this).getPortrait(width,height);
    }
    public Node getCopyPortrait(){
        return new EmblemPortrait(this).getPortrait();
    }
    public Node getPortrait(){
        if(portrait==null){
            portrait=new EmblemPortrait(this);
        }
        return portrait.getPortrait();
        /*
        if(!getPortraitVariable()){
            try{
                ImageView view=new ImageView(
                R.imagesPath+"/units/"+getProperName().toLowerCase()+".png");
            view.setFitHeight(128);
            view.setFitWidth(128);
        return view;
            }
            catch(IllegalArgumentException ex){
                return null;
            }
            
        }
        else if(getChild() && getHairColorEditable()){
            try{
                    //String imageName="build_"+block.getUnitBody()+"_"+block.getUnitFace();
                ImageView view=new ImageView(
                R.imagesPath+"/units/children/"+getProperName().toLowerCase()+".png");
                
                //System.out.println(imageName);
                //return MaterialDesign.customPath+"/character_images/"+nombre+"/"+imageName+".png";
                    view.setFitHeight(128);
                    view.setFitWidth(128);
                    return view;
                }
                catch(IllegalArgumentException ex){
                    return null;
                }
        }
        else if(getAvatar() && getAppearanceEditable()){
                
                try{
                    String baseName="build_"+block.getUnitBody()+"_"+block.getUnitFace();
                    String backName="back_"+block.getUnitBody()+"_"+block.getUnitHair();
                    String hairName="hair_"+block.getUnitBody()+"_"+block.getUnitHair();
                    
                ImageView view=new ImageView();
                ImageView base=new ImageView(R.imagesPath+"/units/"+getProperName().toLowerCase()+
                        "/"+baseName+".png");
                ImageView back=new ImageView(R.imagesPath+"/units/"+getProperName().toLowerCase()+
                        "/"+backName+".png");
                
                ImageView hair=new ImageView(R.imagesPath+"/units/"+getProperName().toLowerCase()+
                        "/"+hairName+".png");
                /*WritableImage image=new WritableImage(128,128){};//=new WritableImage();
                for(int i=0;i<128;i++){
                   for(int j=0;j<128;j++){
                       
                       //image.getPixelWriter().setArgb(i, j, base.getPixelReader().getArgb(i, j));
                       //if(hair.getPixelReader().getArgb(i, j)){
                       int backARGB=back.getPixelReader().getArgb(i, j);
                       //int hairARGB=hair.getPixelReader().getArgb(i,j);
                         if(backARGB!=0){
                             image.getPixelWriter().setColor(i, j,block.getUnitHairColor());
                          //System.out.println(Integer.toBinaryString(i));
                          
                         }  
                          
                              //image.getPixelWriter().setArgb(i,j,hairARGBimage.getPixelReader().getArgb(i, j));
                          
                       //}
                   }
               }*/
//WritablePixelFormat format;
                /*Canvas canvas=new Canvas();
                canvas.setHeight(128);
                canvas.setWidth(128);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.drawImage(base, 0, 0,128,128);
                gc.drawImage(image, 0, 0,128,128);
                gc.drawImage(hair, 0, 0,128,128);
                
                //view.setImage();
               
                //image.getPixelWriter().setPixels(0, 0, 128, 128, base.getPixelReader()
                //        , 0, 0);
                //image.getPixelWriter().setPixels(0,0,128,128, hair.getPixelReader(),0,0);
                
               //view.setImage(image);
                //System.out.println(imageName);
                //return MaterialDesign.customPath+"/character_images/"+nombre+"/"+imageName+".png";
                    back.setFitHeight(128);
                    hair.setFitHeight(128);
                    base.setFitHeight(128);
                    
                    hair.setFitWidth(128);
                    base.setFitWidth(128);
                    back.setFitWidth(128);
                    
                    ColoredStackPane backPane=new ColoredStackPane(back);
                     
                    
                    backPane.setColorStyle(block.getUnitHairColor());
                    System.out.println(block.getUnitHairColor());
                    StackPane image=new StackPane(base,backPane,hair);
                    
                    image.setPrefWidth(128);
                    image.setPrefHeight(128);
                    SnapshotParameters sp=new SnapshotParameters();
                    sp.setFill(Color.TRANSPARENT);
                    
                     WritableImage im = image.snapshot(sp, null);
                     view.setFitWidth(128);
                     view.setFitHeight(128);
                     view.setImage(im);
                    return image;
                }
                catch(IllegalArgumentException ex){
                    return null;
                }
            }
            
            else{
                    try{
                ImageView view=new ImageView(
                R.imagesPath+"/units/unknown.png");
            view.setFitHeight(128);
            view.setFitWidth(128);
        return view;
            }
            catch(IllegalArgumentException ex){
                return null;
            }
                    }*/
            
        
    }
    public String getName(EmblemLocale locale){
        if(getNameEditable()){
           if(block!=null){
               return block.getUnitName();
           }
           else{
               return R.errors.ERROR_PARSING_BLOCK;
           }
       }
       else{
            String name=getNamesMap().getName(locale);
            if(name==null){
                name=getNamesMap().getName(EmblemLocale.ENG);
                if(name==null){
                    name=getProperName();
                }
            }
            return name;
        }
    }
    public String getName(){
       
            return getName(EmblemLocale.PREFERRED);
       
    }
    private EmblemNameMap getNamesMap(){
        if(namesMap==null){
            namesMap=new EmblemNameMap();
        }
        return namesMap;
    }
    public static ObservableList<EmblemUnit> getUnits(){
        if(EmblemHex.getFates()){
            return EmblemXML.readFatesFile();
        }
        else{
            return EmblemXML.readUnitsFile();
        }
        
    }
    public static EmblemUnit getUnitModelWithId(String hex){
        for(EmblemUnit unit: getUnits()){
            if(unit.getId()==HexConverts.getIntFromHex(hex)){
                return unit;
            }
        }
        return null;
    }
    private EmblemUnit(){
        
    }
    public EmblemUnit(String id, String name, String gender){
        
        if(id.contains("0x") || id.contains("0X")){
            setId(Integer.decode(id));
        
        }
        else{
            setId(Integer.decode("0x"+id));
        }
        setProperName(name);
        setGender(Gender.parseGender(gender));
    }
    private BooleanProperty nameEditable;
    private BooleanProperty nameEditableProperty(){
       if(nameEditable==null){
           nameEditable=new SimpleBooleanProperty(this,"nameEditable");
       }
       return nameEditable;
    }
    public void setNameEditable(boolean val){
       nameEditableProperty().set(val);
    }
    public boolean getNameEditable(){
       return nameEditableProperty().get();
    }
    private BooleanProperty hairColorEditable;
    private BooleanProperty hairColorEditableProperty(){
       if(hairColorEditable==null){
           hairColorEditable=new SimpleBooleanProperty(this,"hairColorEditable");
       }
       return hairColorEditable;
    }
    public void setHairColorEditable(boolean val){
       hairColorEditableProperty().set(val);
    }
    public boolean getHairColorEditable(){
       return hairColorEditableProperty().get();
    }
    private BooleanProperty appearanceEditable;
    private BooleanProperty appearanceEditableProperty(){
       if(appearanceEditable==null){
           appearanceEditable=new SimpleBooleanProperty(this,"appearanceEditable");
       }
       return appearanceEditable;
    }
    public void setAppearanceEditable(boolean val){
       appearanceEditableProperty().set(val);
    }
    public boolean getAppearanceEditable(){
       return appearanceEditableProperty().get();
    }

    private BooleanProperty child;
    private BooleanProperty childProperty(){
       if(child==null){
           child=new SimpleBooleanProperty(this,"child");
       }
       return child;
    }
    public void setChild(boolean val){
       childProperty().set(val);
    }
    public boolean getChild(){
       return childProperty().get();
    }


    private IntegerProperty id;
    private IntegerProperty idProperty(){
       if(id==null){
           id=new SimpleIntegerProperty(this,"id");
       }
       return id;
    }
    private void setId(int val){
        setHexId(HexConverts.getHexPair(val));
       idProperty().set(val);
    }
    public int getId(){
        
       return idProperty().get();
    }
    private StringProperty properName;
private StringProperty properNameProperty(){
   if(properName==null){
       properName=new SimpleStringProperty(this,"properName");
   }
   return properName;
}
private void setProperName(String val){
   properNameProperty().set(val);
}
public String getProperName(){
   return properNameProperty().get();
}

    private ObjectProperty<Gender> gender;
    private ObjectProperty<Gender> genderProperty(){
       if(gender==null){
           gender=new SimpleObjectProperty<Gender>(this,"gender");
       }
       return gender;
    }
    private void setGender(Gender val){
       genderProperty().set(val);
    }
    public Gender getGender(){
       return genderProperty().get();
    }
    private BooleanProperty portraitVariable;
    private BooleanProperty portraitVariableProperty(){
       if(portraitVariable==null){
           portraitVariable=new SimpleBooleanProperty(this,"portraitVariable");
       }
       return portraitVariable;
    }
    public void setPortraitVariable(boolean val){
       portraitVariableProperty().set(val);
    }
    public boolean getPortraitVariable(){
       return portraitVariableProperty().get();
    }

    private StringProperty hexId;
    private StringProperty hexIdProperty(){
       if(hexId==null){
           hexId=new SimpleStringProperty(this,"hexId");
       }
       return hexId;
    }
    private void setHexId(String val){
       hexIdProperty().set(val);
    }
    public String getHexId(){
       return hexIdProperty().get();
    }
    private EmblemUnitBlock block;
   private StringProperty hexBlock;
    private StringProperty hexBlockProperty(){
       if(hexBlock==null){
           hexBlock=new SimpleStringProperty(this,"hexBlock");
       }
       return hexBlock;
    }
    public EmblemUnitBlock getUnitBlock(){
        return block;
    }
    private void setHexBlock(String val, int position){
        block=new EmblemUnitBlock(this,val,position);
        
       hexBlockProperty().set(val);
       
       
       
    }
    public String getHexBlock(){
       return hexBlockProperty().get();
    }
    private void build(String hexBlock,int position){
        setHexBlock(hexBlock,position);
    }
   public EmblemUnit copy(String charBlock,int position) {
        EmblemUnit unit=new EmblemUnit();
        unit.setProperName(getProperName());
        unit.setId(getId());
        unit.setGender(getGender());
        unit.setAppearanceEditable(getAppearanceEditable());
        unit.setChild(getChild());
        unit.setHairColorEditable(getHairColorEditable());
        unit.setNameEditable(getNameEditable());
        unit.setNamesMap(getNamesMap());
        unit.setPortraitVariable(getPortraitVariable());
        unit.setAvatar(getAvatar());
        unit.setImportant(getImportant());
        unit.setDLCSpotpass(getDLCSpotpass());
        unit.build(charBlock,position);
        return unit;
    }


}
