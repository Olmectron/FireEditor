/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.panes;

import com.olmectron.fire.R;
import com.olmectron.fire.controllers.EmblemLocale;
import com.olmectron.material.components.MaterialConfirmDialog;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialLabel;
import com.olmectron.material.components.MaterialSelector;
import com.olmectron.material.components.MaterialTransparentPane;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.utils.LanguageRegion;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Edgar
 */
public class SettingsPane extends MaterialTransparentPane{
    public SettingsPane(){
        super();
        VBox pane=new VBox();
        pane.setPrefHeight(1080);
        setRootComponent(pane);
        MaterialLabel gameLabel=new MaterialLabel(R.string.game_language.get());
        
        MaterialSelector<String> gameSelector=new MaterialSelector<String>(){
            @Override
            public String getDataString(String string){
                
                return string;
            }
            @Override
            public void onSelectionChange(String string){
                
                R.getSettingsFile().setValue("game_lang", string);
                showConfirmDialog();
            }
        };
        gameSelector.addItem("en");
        gameSelector.addItem("ja");
        gameSelector.addItem("es");
        gameSelector.addItem("fr");
        
        gameSelector.getSelectionModel().select(EmblemLocale.PREFERRED.toString());
        
        MaterialLabel guiLabel=new MaterialLabel(R.string.gui_language.get());
        MaterialSelector<String> guiSelector=new MaterialSelector<String>(){
            

            
            @Override
            public String getDataString(String string){
                
                return string;
            }
            @Override
            public void onSelectionChange(String string){
                R.setLanguageRegion(LanguageRegion.getLanguageRegionWithCode(string));
                showConfirmDialog();
                //R.getSettingsFile().setValue("gui_lang", string);
            }
        };
        
        gameSelector.setLabel(gameLabel);
        guiSelector.setLabel(guiLabel);
        VBox gameBox=new VBox(gameLabel,gameSelector);
        VBox guiBox=new VBox(guiLabel,guiSelector);
        gameBox.setPadding(new Insets(8));
        guiBox.setPadding(new Insets(8));
        gameSelector.setPrefWidth(250);
        guiSelector.setPrefWidth(250);
        
        guiSelector.addItem("en");
        guiSelector.addItem("es");
               guiSelector.getSelectionModel().select(R.getLanguageRegion().getCode());
 
        //MaterialDisplayText text=new MaterialDisplayText(R.string.open_options.get());
        //text.setFontSize(30);
        //text.setColorCode(MaterialColor.material.BLACK_54);
        //pane.setAlignment(Pos.CENTER);
        
        pane.getChildren().addAll(gameBox,guiBox);
          
    }
    @Override
    public void onShown() {
        // //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        // //To change body of generated methods, choose Tools | Templates.
    }
    private void showConfirmDialog(){
        MaterialConfirmDialog confirmDialog=new MaterialConfirmDialog(R.string.should_restart.get(),
        R.string.restart_message.get(),R.string.ok.get()
        ){
            

            @Override
            public void onPositiveButton() {
                dismiss();
            }
            @Override
            public void onDialogShown() {
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onDialogHidden() {
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onDialogKeyReleased(KeyEvent event) {
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onDialogKeyPressed(KeyEvent event) {
                 //To change body of generated methods, choose Tools | Templates.
            }
        };
        confirmDialog.unhide();
    }
    
}
