/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.panes;

import com.olmectron.fire.R;
import com.olmectron.fire.controllers.EmblemUnit;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.FloatingNode;
import com.olmectron.material.components.FontWeight;
import com.olmectron.material.components.MaterialConfirmDialog;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialDropdownMenu;
import com.olmectron.material.components.MaterialDropdownMenuItem;
import com.olmectron.material.components.MaterialTextField;
import com.olmectron.material.components.RaisedButton;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.helpers.FloatingStage;
import com.olmectron.material.utils.ColorBox;
import com.olmectron.material.utils.ImageUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Accordion;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import org.utilities.StringTrimmer;

/**
 *
 * @author Edgar
 */
public class UnitInfoPane extends VBox {
    
    public UnitInfoPane(EmblemUnit unit){
        setPadding(new Insets(16));
        MaterialTextField hpField=new MaterialTextField(unit.getUnitBlock().getUnitHP()+"","HP");
        hpField.lockLetters();
        hpField.setMaxWidth(100);
        hpField.textField().textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                if(Integer.parseInt(newValue)>255){
                    hpField.setText("255");
                }
                }
                catch(NumberFormatException ex){
                    
                }
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        MaterialTextField strField=new MaterialTextField(unit.getUnitBlock().getUnitSTR()+"","STR");
        strField.setMaxWidth(100);
        
        strField.lockLetters();
        MaterialTextField magField=new MaterialTextField(unit.getUnitBlock().getUnitMAG()+"","MAG");
        MaterialTextField sklField=new MaterialTextField(unit.getUnitBlock().getUnitSKL()+"","SKL");
        MaterialTextField spdField=new MaterialTextField(unit.getUnitBlock().getUnitSPD()+"","SPD");
        MaterialTextField lckField=new MaterialTextField(unit.getUnitBlock().getUnitLCK()+"","LCK");
        MaterialTextField defField=new MaterialTextField(unit.getUnitBlock().getUnitDEF()+"","DEF");
        MaterialTextField resField=new MaterialTextField(unit.getUnitBlock().getUnitRES()+"","RES");
        strField.textField().textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                if(Integer.parseInt(newValue)>255){
                   strField.setText("255");
                }
                }
                catch(NumberFormatException ex){
                    
                }
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
        magField.textField().textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                if(Integer.parseInt(newValue)>255){
                   magField.setText("255");
                }
                }
                catch(NumberFormatException ex){
                    
                }
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
        sklField.textField().textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                if(Integer.parseInt(newValue)>255){
                    sklField.setText("255");
                }
                }
                catch(NumberFormatException ex){
                    
                }
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
        spdField.textField().textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                if(Integer.parseInt(newValue)>255){
                    spdField.setText("255");
                }
                }
                catch(NumberFormatException ex){
                    
                }
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
        lckField.textField().textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                if(Integer.parseInt(newValue)>255){
                    lckField.setText("255");
                }
                }
                catch(NumberFormatException ex){
                    
                }
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
        defField.textField().textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                if(Integer.parseInt(newValue)>255){
                    defField.setText("255");
                }
                }
                catch(NumberFormatException ex){
                    
                }
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
        resField.textField().textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                if(Integer.parseInt(newValue)>255){
                    resField.setText("255");
                }
                }
                catch(NumberFormatException ex){
                    
                }
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
        magField.lockLetters();
        sklField.lockLetters();
        spdField.lockLetters();
        lckField.lockLetters();
        defField.lockLetters();
        resField.lockLetters();
        
        magField.setMaxWidth(100);
        sklField.setMaxWidth(100);
        spdField.setMaxWidth(100);
        lckField.setMaxWidth(100);
        defField.setMaxWidth(100);
        
        resField.setMaxWidth(100);
        
        strField.setPadding(new Insets(0,4,0,4));
        magField.setPadding(new Insets(0,4,0,4));
        sklField.setPadding(new Insets(0,4,0,4));
        spdField.setPadding(new Insets(0,4,0,4));
        lckField.setPadding(new Insets(0,4,0,4));
        
        defField.setPadding(new Insets(0,4,0,4));
        resField.setPadding(new Insets(0,4,0,4));
        
        
        FlowPane statBox=new FlowPane(hpField,strField,magField,sklField,spdField,lckField,defField,resField);
        
        hpField.textField().textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(newValue.trim().equals(""))
                         unit.getUnitBlock().setUnitHP(0);
                    else
                    unit.getUnitBlock().setUnitHP(Integer.parseInt(newValue));
                   
                    // //To change body of generated methods, choose Tools | Templates.
                }
            });
        strField.textField().textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(newValue.trim().equals(""))
                         unit.getUnitBlock().setUnitSTR(0);
                    else
                    unit.getUnitBlock().setUnitSTR(Integer.parseInt(newValue));
                   
                    // //To change body of generated methods, choose Tools | Templates.
                }
            });
        magField.textField().textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(newValue.trim().equals(""))
                         unit.getUnitBlock().setUnitMAG(0);
                    else
                    unit.getUnitBlock().setUnitMAG(Integer.parseInt(newValue));
                   
                    // //To change body of generated methods, choose Tools | Templates.
                }
            });
        sklField.textField().textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(newValue.trim().equals(""))
                         unit.getUnitBlock().setUnitSKL(0);
                    else
                    unit.getUnitBlock().setUnitSKL(Integer.parseInt(newValue));
                   
                    // //To change body of generated methods, choose Tools | Templates.
                }
            });
        spdField.textField().textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(newValue.trim().equals(""))
                         unit.getUnitBlock().setUnitSPD(0);
                    else
                    unit.getUnitBlock().setUnitSPD(Integer.parseInt(newValue));
                   
                    // //To change body of generated methods, choose Tools | Templates.
                }
            });
        lckField.textField().textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(newValue.trim().equals(""))
                         unit.getUnitBlock().setUnitLCK(0);
                    else
                    unit.getUnitBlock().setUnitLCK(Integer.parseInt(newValue));
                   
                    // //To change body of generated methods, choose Tools | Templates.
                }
            });
        defField.textField().textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(newValue.trim().equals(""))
                         unit.getUnitBlock().setUnitDEF(0);
                    else
                    unit.getUnitBlock().setUnitDEF(Integer.parseInt(newValue));
                   
                    // //To change body of generated methods, choose Tools | Templates.
                }
            });
        resField.textField().textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(newValue.trim().equals(""))
                         unit.getUnitBlock().setUnitRES(0);
                    else
                    unit.getUnitBlock().setUnitRES(Integer.parseInt(newValue));
                   
                    // //To change body of generated methods, choose Tools | Templates.
                }
            });
        MaterialTextField addedMovementField=new MaterialTextField(unit.getUnitBlock().getUnitAddedMovement()+"","Added movement");
        addedMovementField.textField().textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                if(Integer.parseInt(newValue)>255){
                    addedMovementField.setText("255");
                }
                }
                catch(NumberFormatException ex){
                    
                }
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
        addedMovementField.textField().textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(newValue.trim().equals(""))
                         unit.getUnitBlock().setUnitAddedMovement(0);
                    else
                    unit.getUnitBlock().setUnitAddedMovement(Integer.parseInt(newValue));
                   
                    // //To change body of generated methods, choose Tools | Templates.
                }
            });
        MaterialDisplayText nameText=new MaterialDisplayText(unit.getName());
            if(unit.getNameEditable())
                    nameText.textProperty().bind(unit.getUnitBlock().unitNameProperty());
        getChildren().add(nameText);
        nameText.setFontSize(16);
        nameText.setFontWeight(FontWeight.MEDIUM);
        nameText.setColorCode(MaterialColor.material.INDIGO);
        Node port=unit.getCopyPortrait();
        port.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.SECONDARY)){
                    MaterialDropdownMenu menu=new MaterialDropdownMenu((Region)port);
                    menu.addItem(new MaterialDropdownMenuItem("Exportar imagen"){

                        @Override
                        public void onItemClick() {
                            SnapshotParameters params = new SnapshotParameters();
params.setFill(Color.TRANSPARENT);
                            WritableImage snapshot = port.snapshot(params, null);
                            ImageUtils.saveToFile("example.png", snapshot);
                        }
                        
                    });
                    menu.unhide();
                }
            }
        });
        getChildren().add(port);
        
        setAlignment(Pos.CENTER);
        Accordion accordion=new Accordion();
        
        
        
         VBox otherStatsBox=new VBox(addedMovementField);
        ScrollPane otherStatsScroll=new ScrollPane(otherStatsBox);
        VBox appearanceBox=new VBox();
            appearanceBox.setPadding(new Insets(12));
           
        TitledPane appearancePane=new TitledPane(R.string.appearance.get(),appearanceBox);
            
        if(unit.getNameEditable()){
            MaterialTextField assetField=new MaterialTextField(unit.getUnitBlock().getUnitAsset()+"","Asset");
            
        MaterialTextField flawField=new MaterialTextField(unit.getUnitBlock().getUnitFlaw()+"","Flaw");
        assetField.lockLetters();
        flawField.lockLetters();
        otherStatsBox.getChildren().add(new HBox(assetField,flawField));
        assetField.textField().textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                if(Integer.parseInt(newValue)>255){
                    assetField.setText("255");
                }
                }
                catch(NumberFormatException ex){
                    
                }
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
        flawField.textField().textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                if(Integer.parseInt(newValue)>255){
                    flawField.setText("255");
                }
                }
                catch(NumberFormatException ex){
                    
                }
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
        assetField.textField().textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(newValue.trim().equals(""))
                         unit.getUnitBlock().setUnitAsset(0);
                    else
                    unit.getUnitBlock().setUnitAsset(Integer.parseInt(newValue));
                   
                    // //To change body of generated methods, choose Tools | Templates.
                }
            });
        flawField.textField().textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(newValue.trim().equals(""))
                         unit.getUnitBlock().setUnitFlaw(0);
                    else
                    unit.getUnitBlock().setUnitFlaw(Integer.parseInt(newValue));
                   
                    // //To change body of generated methods, choose Tools | Templates.
                }
            });
            MaterialTextField nombreField=new MaterialTextField(R.string.name.get());
            nombreField.allowSpace();
            nombreField.allowDot();
            nombreField.setLimite(12);
            nombreField.textField().textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    unit.getUnitBlock().setUnitName(StringTrimmer.trim(newValue));
                   
                    // //To change body of generated methods, choose Tools | Templates.
                }
            });
            
            appearanceBox.getChildren().add(nombreField);
            nombreField.setText(unit.getName());
            
        }
        
        if(unit.getHairColorEditable()){
            ColorPicker hairColorPicker = new ColorPicker(unit.getUnitBlock().getUnitHairColor());
             unit.getUnitBlock().unitHairColorProperty().bind(hairColorPicker.valueProperty());
             appearanceBox.getChildren().add(hairColorPicker);
            //getChildren().add(hairColorPicker);
        }
        if(unit.getNameEditable() || unit.getHairColorEditable())
        accordion.getPanes().add(appearancePane);
            
        ScrollPane statScroll=new ScrollPane(statBox);
        statScroll.setPrefHeight(250);
        statBox.setPadding(new Insets(12));
        statScroll.setFitToWidth(true);
        TitledPane statsPane=new TitledPane(R.string.main_stats.get(),statScroll);
        accordion.getPanes().add((statsPane));
        
        getChildren().add(accordion);
        
        RaisedButton raisedButton;
        if(unit.isAlive()){
        raisedButton=new RaisedButton(R.string.kill_unit.get());
        
            
        }
        else{
            raisedButton=new RaisedButton(R.string.revive_unit.get());
        
        }
        
        
                   raisedButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                toggleUnitAlive(unit);
                
            }
        });
                   
        final VBox spanBox=new VBox(raisedButton);
        unit.getUnitBlock().aliveProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                   spanBox.getChildren().clear();
                   RaisedButton auxButton=new RaisedButton(R.string.kill_unit.get());
                   auxButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                toggleUnitAlive(unit);
            }
        });
                   spanBox.getChildren().add(auxButton);
                }
                else{
                    
                   spanBox.getChildren().clear();
                   RaisedButton auxButton=new RaisedButton(R.string.revive_unit.get());
                   auxButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                toggleUnitAlive(unit);
                
            }
        });
                   spanBox.getChildren().add(auxButton);
                }
            }
        });
            
        
        
        spanBox.setPadding(new Insets(16));
        getChildren().add(spanBox);
        //getChildren().add(addedMovementField);
        //getChildren().add(new HBox(assetField,flawField));
       
        otherStatsScroll.setPrefHeight(120);
        otherStatsScroll.setFitToWidth(true);
        
        otherStatsBox.setPadding(new Insets(12));
        TitledPane otherStatsPane=new TitledPane(R.string.other_stats.get(),otherStatsScroll);
        accordion.getPanes().add(otherStatsPane);
        
        
        
    }
    private void toggleUnitAlive(EmblemUnit unit){
        System.out.println(unit.getName()+" "+unit.getImportant());
        if(!unit.getImportant() || (unit.getImportant() && !unit.isAlive())){
        unit.getUnitBlock().setAlive(!unit.isAlive());
            
        }
        else{
            
            MaterialConfirmDialog confirmDialog=new MaterialConfirmDialog(R.string.are_you_sure.get(),
            R.string.killing_message.get(),R.string.do_it.get(),R.string.wait.get()){
                        private AudioClip audio;

                @Override
                public void onPositiveButton() {
                    unit.getUnitBlock().setAlive(!unit.isAlive());
                    dismiss();
                }
                @Override
                public void onDialogShown() {
                    audio=new AudioClip(MaterialDesign.class.getResource("/com/olmectron/fire/res/audio/withdrawing.mp3").toExternalForm());
                    audio.play();
                     //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void onDialogHidden() {
                    if(audio!=null){
                        audio.stop();
                    }
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
            //FloatingStage stage=new FloatingStage();
            
            
            ImageView image=new ImageView(new Image("/com/olmectron/fire/res/images/gui/fates_dialog.png"));
            StackPane stack=new StackPane(image);
            stack.setMaxWidth(700);
            stack.setMaxHeight(300);
            
            
            VBox box=new VBox();
            VBox box2=new VBox();
            ColorBox nameBox=new ColorBox();
            nameBox.setMinWidth(200);
            nameBox.setMaxWidth(200);
            nameBox.setMinHeight(45);
            nameBox.setMaxHeight(45);
            ColorBox messageBox=new ColorBox();
            messageBox.setMinWidth(665);
            messageBox.setMaxWidth(665);
            messageBox.setMinHeight(89);
            messageBox.setMaxHeight(89);
            
            //messageBox.setColor("#FF0000");
            MaterialDisplayText messageText=new MaterialDisplayText("Chrom...everyone... F-forgive me... Chrom...everyone... F-forgive me... Chrom...everyone... F-forgive me...");
            messageText.setColorCode(new MaterialColor("#421004"));
         
            messageText.setFontSize(28);
            messageBox.getChildren().add(messageText);
            messageText.setFontWeight(FontWeight.MEDIUM);
            messageText.setWrapText(true);
            messageBox.setPadding(new Insets(10,34,10,34));
            MaterialDisplayText nameText=new MaterialDisplayText(unit.getName());
            nameText.setFontWeight(FontWeight.MEDIUM);
            box.setPadding(new Insets(60,0,0,8));
            box2.setPadding(new Insets(95,0,0,20));
            nameBox.getChildren().add(nameText);
            nameBox.setAlignment(Pos.CENTER);
            nameText.setColorCode(MaterialColor.material.WHITE);
            box2.getChildren().add(messageBox);
            box.getChildren().addAll(nameBox);
            stack.getChildren().addAll(box,box2);
            image.setPreserveRatio(true);
            image.setFitWidth(700);
            
            FloatingNode stage=new FloatingNode(stack){
                 private AudioClip audio;    
                @Override
                public void onDialogShown() {
               audio=new AudioClip(MaterialDesign.class.getResource("/com/olmectron/fire/res/audio/withdrawing.mp3").toExternalForm());
                    audio.play();
                }

                @Override
                public void onDialogHidden() {
                if(audio!=null){
                        audio.stop();
                    }
                }
                
            };
            //stage.getPopupContainer().getContent().add(image);
            
            stage.unhide();
//confirmDialog.unhide();
        }
            
                
    }
    
    
    
    
}
