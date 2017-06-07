/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.controllers;

import com.olmectron.fire.R;
import com.olmectron.material.components.ColoredStackPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Edgar
 */
public class EmblemPortrait{
    private StringProperty baseName;
private StringProperty baseNameProperty(){
   if(baseName==null){
       baseName=new SimpleStringProperty(this,"baseName");
       baseName.set(null);
   }
   return baseName;
}
private void setBaseName(String val){
   baseNameProperty().set(val);
}
public String getBaseName(){
   return baseNameProperty().get();
}
private StringProperty backName;
private StringProperty backNameProperty(){
   if(backName==null){
       backName=new SimpleStringProperty(this,"backName");
       backName.set(null);
   }
   return backName;
}
private void setBackName(String val){
   backNameProperty().set(val);
}
public String getBackName(){
   return backNameProperty().get();
}
private StringProperty hairName;
private StringProperty hairNameProperty(){
   if(hairName==null){
       hairName=new SimpleStringProperty(this,"hairName");
       hairName.set(null);
   }
   return hairName;
}
private void setHairName(String val){
   hairNameProperty().set(val);
}
public String getHairName(){
   return hairNameProperty().get();
}

    private EmblemUnit unit;
    public EmblemPortrait(EmblemUnit portrait){
        this.unit=portrait;
        String rootDirectory="units";
        if(EmblemHex.getFates()){
            rootDirectory="fates";
            }
        
        if(!unit.getPortraitVariable()){
            setBaseName(R.imagesPath+"/"+rootDirectory+"/"+unit.getProperName().toLowerCase()+".png");
        }
        else if(unit.getAvatar() && unit.getHairColorEditable() && unit.getAppearanceEditable()){
            setBaseName(R.imagesPath+"/"+rootDirectory+"/"+unit.getProperName().toLowerCase()+
                        "/"+"build_"+unit.getUnitBlock().getUnitBody()+"_"+unit.getUnitBlock().getUnitFace()+".png");
            setBackName(R.imagesPath+"/"+rootDirectory+"/"+unit.getProperName().toLowerCase()+
                        "/"+"back_"+unit.getUnitBlock().getUnitBody()+"_"+unit.getUnitBlock().getUnitHair()+".png");
            setHairName(R.imagesPath+"/"+rootDirectory+"/"+unit.getProperName().toLowerCase()+
                        "/"+"hair_"+unit.getUnitBlock().getUnitBody()+"_"+unit.getUnitBlock().getUnitHair()+".png");
        }
        else if(unit.isDLCUnit()){
            System.out.println(unit.getName()+", DLC: "+unit.getUnitBlock().getUnitFace());
            setBaseName(R.imagesPath+"/"+rootDirectory+"/spotpass/dlc"+unit.getUnitBlock().getUnitFace()+".png"); 
            
        }
        else if(unit.isSpotpassUnit()){
             System.out.println(unit.getName()+", Spotpass: "+unit.getUnitBlock().getUnitSpotpassCode());
           
           setBaseName(R.imagesPath+"/"+rootDirectory+"/spotpass/"+unit.getUnitBlock().getUnitSpotpassCode()+".png"); 
        }
        else if(unit.isLogbookAvatar()){
            String pathName="avatar_"+unit.retrieveGenderLetter().toLowerCase();
            //System.out.println("Body: "+unit.getUnitBlock().getUnitBody()+", Hair: "+unit.getUnitBlock().getUnitFace()+", Face: "+unit.getUnitBlock().getUnitFace());
            setBaseName(R.imagesPath+"/"+rootDirectory+"/"+pathName+
                        "/"+"build_"+unit.getUnitBlock().getUnitBody()+"_"+unit.getUnitBlock().getUnitFace()+".png");
            setBackName(R.imagesPath+"/"+rootDirectory+"/"+pathName+
                        "/"+"back_"+unit.getUnitBlock().getUnitBody()+"_"+unit.getUnitBlock().getUnitHair()+".png");
            setHairName(R.imagesPath+"/"+rootDirectory+"/"+pathName+
                        "/"+"hair_"+unit.getUnitBlock().getUnitBody()+"_"+unit.getUnitBlock().getUnitHair()+".png");
        }
        else if(unit.getChild() && unit.getHairColorEditable()){
            setBaseName(R.imagesPath+"/"+rootDirectory+"/children/"+unit.getProperName().toLowerCase()+".png");
            setBackName(R.imagesPath+"/"+rootDirectory+"/children/"+unit.getProperName().toLowerCase()+"_shadow.png");
            setHairName(R.imagesPath+"/"+rootDirectory+"/children/"+unit.getProperName().toLowerCase()+"_hair.png");
        }
        else{
            setBaseName(R.imagesPath+"/"+rootDirectory+"/unknown.png");
        }
        if(getBaseName()==null){
            setBaseName(R.imagesPath+"/"+rootDirectory+"/unknown.png");
        }
        
    }
    private Node load(double width, double height){
            try{
                    
                    
                //ImageView view=new ImageView();
                
                ImageView base=null;
                if(getBaseName()!=null){
                    base=new ImageView(getBaseName());
                }
                ImageView back=null;
                if(getBackName()!=null){
                    back=new ImageView(getBackName());
                }
                ImageView hair=null;
                if(getHairName()!=null)
                {
                    hair=new ImageView(getHairName());
                }
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
                */
                //view.setImage();
               
                //image.getPixelWriter().setPixels(0, 0, 128, 128, base.getPixelReader()
                //        , 0, 0);
                //image.getPixelWriter().setPixels(0,0,128,128, hair.getPixelReader(),0,0);
                
               //view.setImage(image);
                //System.out.println(imageName);
                //return MaterialDesign.customPath+"/character_images/"+nombre+"/"+imageName+".png";
                StackPane image=new StackPane();
                     
                
                if(base!=null){    
                base.setFitWidth(width);
                    
                    base.setFitHeight(height);
                    image.getChildren().add(base);
                }
                
                final ColoredStackPane backPane=new ColoredStackPane(back);
                unit.getUnitBlock().unitHairColorProperty().addListener(new ChangeListener<Color>(){
                    @Override
                    public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color nuevo) {
                        backPane.setColorStyle(nuevo);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                    if(back!=null){
                    back.setFitHeight(height);
                    
                back.setFitWidth(width);
               
                        //backPane=
                     
                    
                    backPane.setColorStyle(unit.getUnitBlock().getUnitHairColor());
                    image.getChildren().add(backPane);
                
                
                    }
                    if(hair!=null){
                    hair.setFitHeight(height);
                    
                    hair.setFitWidth(width);
                    image.getChildren().add(hair);
                    }
                   
                    //System.out.println(unit.getUnitBlock().getUnitHairColor());
                    
                    image.setPrefWidth(width);
                    image.setPrefHeight(height);
                    SnapshotParameters sp=new SnapshotParameters();
                    sp.setFill(Color.TRANSPARENT);
                    image.setMaxWidth(width);
                    image.setMaxHeight(height);
                     //WritableImage im = image.snapshot(sp, null);
                     //view.setFitWidth(128);
                     //view.setFitHeight(128);
                     //view.setImage(im);
                    return image;
                }
                catch(IllegalArgumentException ex){
                    return null;
                }
    }
    private Node actualPortrait;
    public Node getPortrait(double width, double height){
        if(actualPortrait==null){
            actualPortrait=load(width,height);
        }
        return actualPortrait;
    }
    public Node getPortrait(){
        if(actualPortrait==null){
            actualPortrait=load(128,128);
        }
        return actualPortrait;
    }
}
