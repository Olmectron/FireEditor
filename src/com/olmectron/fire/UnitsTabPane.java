/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire;

import com.olmectron.fire.controllers.EmblemHex;
import com.olmectron.fire.controllers.EmblemUnit;
import com.olmectron.fire.panes.UnitsPane;
import com.olmectron.material.components.MaterialTabPane;
import com.olmectron.material.components.RaisedButton;
import com.olmectron.material.layouts.MaterialEditableLayout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Édgar
 */
public class UnitsTabPane extends MaterialTabPane {
    private MaterialEditableLayout editable;
    
    public UnitsTabPane(MaterialEditableLayout editable){
        super();
        this.setTitle(R.string.units.get());
        
        this.setIdentifier("Units");
        this.editable=editable;
       
        
        
    }
    private UnitsPane alivePane,deadPane;
    public void clearPanes(){
        alivePane.clear();
        deadPane.clear();
    }
    public void setUnits(ObservableList<EmblemUnit> units){
        
         alivePane=new UnitsPane(editable,UnitsPane.ALIVE,units);
         deadPane=new UnitsPane(editable,UnitsPane.DEAD,units);
         for(int i=0;i<units.size();i++){
            final EmblemUnit unit=units.get(i);
            unit.getUnitBlock().aliveProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(newValue){
                        deadPane.removeUnitFromPane(unit);
                        deadPane.emptyDrawerPane();
                        alivePane.changeUnit(unit);
                    }
                    else{
                        
                        alivePane.removeUnitFromPane(unit);
                        alivePane.emptyDrawerPane();
                        deadPane.changeUnit(unit);
                    }
                        
                }
            });
        }
        this.getTabs().clear();
        this.addTab(R.string.alive.get(),alivePane);
        this.addTab(R.string.fallen.get(),deadPane);
        
    }
    @Override
    public void onPaneShown() {
   
    }
    
}
