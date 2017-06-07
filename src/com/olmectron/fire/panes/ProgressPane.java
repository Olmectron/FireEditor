/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.panes;

import com.olmectron.fire.R;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialProgressBar;
import com.olmectron.material.components.MaterialTransparentPane;
import com.olmectron.material.constants.MaterialColor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Edgar
 */
public class ProgressPane extends MaterialTransparentPane{
    public ProgressPane(){
        super();
        StackPane pane=new StackPane();
        pane.setPrefHeight(1080);
        setRootComponent(pane);
        MaterialDisplayText text=new MaterialDisplayText(R.string.loading.get().toUpperCase());
        text.setFontSize(20);
        text.setPadding(new Insets(0,0,12,0));
        text.setColorCode(MaterialColor.material.BLACK_87);
        
        pane.setAlignment(Pos.CENTER);
        
        MaterialProgressBar bar=new MaterialProgressBar();
        bar.setColor(MaterialColor.BLUE);
        VBox box=new VBox(text,bar);
        box.setPrefHeight(1080);
        box.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(box);
          
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
