/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.controllers;

import com.olmectron.material.utils.XMLAttributeList;
import com.olmectron.material.utils.XMLReader;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.xml.sax.Attributes;

/**
 *
 * @author Edgar
 */
public class EmblemXML {
    private static ObservableList<EmblemUnit> units=null;
    private static ObservableList<EmblemUnit> unitsFates=null;
    
    public static ObservableList<EmblemUnit> readFatesFile(){
        if(unitsFates==null){
            
        
        SimpleIntegerProperty counter=new SimpleIntegerProperty(EmblemXML.class,"counter");
        counter.set(0);
        XMLReader<EmblemUnit> reader=new XMLReader<EmblemUnit>("/com/olmectron/fire/res/values/strings/units_fates.xml","UNIT") {
            @Override
            public EmblemUnit parseObject(String value, XMLAttributeList attr, ArrayList<String> tags, ArrayList<String> values, ArrayList<XMLAttributeList> attrs) {
                EmblemUnit unit=new EmblemUnit(attr.getValue("id"),attr.getValue("name"),attr.getValue("gender"));
                for(int i=0;i<tags.size();i++){
                    if(tags.get(i).equalsIgnoreCase("locale")){
                        unit.
                   addName(EmblemLocale.parseLocale(attrs.get(i).getValue("lang").toLowerCase()),values.get(i+1));
                        //System.out.println(EmblemLocale.parseLocale(attrs.get(i).getValue("lang"))+" "+values.get(i+1));
                    }
                }
                
                unit.setHairColorEditable(tags.contains("hair-color-editable"));
                unit.setNameEditable(tags.contains("name-editable"));
                unit.setAppearanceEditable(tags.contains("appearance-editable"));
                unit.setChild(tags.contains("child"));
                unit.setPortraitVariable(tags.contains("portrait-variable"));
                unit.setAvatar(tags.contains("avatar"));
                unit.setDLCSpotpass(tags.contains("dlc-spotpass"));
                //System.out.println(unit.getAvatar()+" avatar");
                /*counter.set(counter.get()+1);
                
                System.out.println("---NEW UNIT "+counter.get());
                
                System.out.println("NAME :"+attr.getValue("name"));
                System.out.println("ID :"+attr.getValue("id"));
                
                
                }
                System.out.println("");
                */
               /* for(int i=0;i<tags.size();i++){
                    System.out.println("+TAG: "+tags.get(i)
                    );}*/
                return unit;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
        unitsFates=reader.readElementList();
        }
        return unitsFates;
    }
    public static ObservableList<EmblemUnit> readUnitsFile(){
        if(units==null){
            
        
        SimpleIntegerProperty counter=new SimpleIntegerProperty(EmblemXML.class,"counter");
        counter.set(0);
        XMLReader<EmblemUnit> reader=new XMLReader<EmblemUnit>("/com/olmectron/fire/res/values/strings/units.xml","UNIT") {
            @Override
            public EmblemUnit parseObject(String value, XMLAttributeList attr, ArrayList<String> tags, ArrayList<String> values, ArrayList<XMLAttributeList> attrs) {
                EmblemUnit unit=new EmblemUnit(attr.getValue("id"),attr.getValue("name"),attr.getValue("gender"));
                for(int i=0;i<tags.size();i++){
                    if(tags.get(i).equalsIgnoreCase("locale")){
                        unit.
                   addName(EmblemLocale.parseLocale(attrs.get(i).getValue("lang").toLowerCase()),values.get(i+1));
                        //System.out.println(EmblemLocale.parseLocale(attrs.get(i).getValue("lang"))+" "+values.get(i+1));
                    }
                }
                
                unit.setHairColorEditable(tags.contains("hair-color-editable"));
                unit.setNameEditable(tags.contains("name-editable"));
                unit.setAppearanceEditable(tags.contains("appearance-editable"));
                unit.setChild(tags.contains("child"));
                unit.setPortraitVariable(tags.contains("portrait-variable"));
                unit.setAvatar(tags.contains("avatar"));
                unit.setImportant(tags.contains("important-unit"));
                unit.setDLCSpotpass(tags.contains("dlc-spotpass"));
                //System.out.println(unit.getAvatar()+" avatar");
                /*counter.set(counter.get()+1);
                
                System.out.println("---NEW UNIT "+counter.get());
                
                System.out.println("NAME :"+attr.getValue("name"));
                System.out.println("ID :"+attr.getValue("id"));
                
                
                }
                System.out.println("");
                */
               /* for(int i=0;i<tags.size();i++){
                    System.out.println("+TAG: "+tags.get(i)
                    );}*/
                return unit;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
        units=reader.readElementList();
        }
        return units;
    }
}
