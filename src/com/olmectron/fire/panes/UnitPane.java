/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.panes;

import com.olmectron.fire.controllers.EmblemUnit;
import com.olmectron.material.components.MaterialTransparentPane;

/**
 *
 * @author Édgar
 */
public class UnitPane extends MaterialTransparentPane{
    public UnitPane(EmblemUnit unit){
        super();
        setRootComponent(unit.getUnitPane());
    }
    @Override   
    public void onShown() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
