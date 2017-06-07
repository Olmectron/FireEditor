/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.panes;

import com.olmectron.fire.R;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialTransparentPane;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.layouts.MaterialFullPane;
import com.olmectron.material.layouts.MaterialPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Edgar
 */
public class EmptyPane extends MaterialTransparentPane{
    public EmptyPane(){
        super();
        StackPane pane=new StackPane();
        pane.setPrefHeight(1000);
        setRootComponent(pane);
        MaterialDisplayText text=new MaterialDisplayText(R.string.open_options.get());
        text.setFontSize(30);
        text.setColorCode(MaterialColor.material.BLACK_54);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().add(text);
          
    }
    @Override
    public void onShown() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
