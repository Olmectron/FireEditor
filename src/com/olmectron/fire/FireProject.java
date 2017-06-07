/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire;

import com.olmectron.fire.compression.DataView;
import com.olmectron.fire.controllers.EmblemFile;
import com.olmectron.fire.controllers.EmblemHex;
import com.olmectron.fire.controllers.EmblemLocale;
import com.olmectron.fire.controllers.EmblemUnit;
import com.olmectron.fire.controllers.EmblemXML;
import com.olmectron.fire.hex.Hex;
import com.olmectron.fire.panes.ProgressPane;
import com.olmectron.fire.panes.EmptyPane;
import com.olmectron.fire.panes.SettingsPane;
import com.olmectron.fire.panes.UnitsPane;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.FontWeight;
import com.olmectron.material.components.MaterialConfirmDialog;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialDropdownMenu;
import com.olmectron.material.components.MaterialDropdownMenuItem;
import com.olmectron.material.components.MaterialIconButton;
import com.olmectron.material.components.MaterialStandardListItem;
import com.olmectron.material.components.MaterialTabs;
import com.olmectron.material.components.MaterialTextField;
import com.olmectron.material.components.MaterialToast;
import com.olmectron.material.components.MaterialTooltip;
import com.olmectron.material.components.RaisedButton;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.helpers.IconButtonContainer;
import com.olmectron.material.layouts.MaterialEditableLayout;
import com.olmectron.material.utils.BackgroundTask;
import com.olmectron.material.utils.LanguageRegion;
import java.io.File;
import java.util.Locale;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.Mnemonic;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Edgar
 */
public class FireProject extends Application {
    private IconButtonContainer openFileContainer;
    private MaterialTooltip openFileTooltip;
    //private UnitsPane unitsPane;
    @Override
    public void start(Stage primaryStage) {
       /*  byte[] example = new byte[4];
            example[0] = 12;

            example[1] = 10;
            example[2] = 0;
            example[3] = 1;
            long pk4_pos = DataView.getUint16(example, 0);
            System.out.println("pk4_pos "+pk4_pos);
        
        byte[] d=DataView.getBytes(16385);
        for(int i=0;i<d.length;i++){
            System.out.println("byte["+i+"]: "+d[i]);
        }*/
        /*int c=0;
        for (Locale l : Locale.getAvailableLocales()) {
            c++;
            System.out.println(c+" Locale: "+l.getDisplayLanguage()+", "+l.getDisplayName()+", "+l.getLanguage());
        }*/
        MaterialDesign.setContentStage(primaryStage);
        MaterialDesign.setExitOnClose(true);
        MaterialDesign.setTooltipsOn(true);
        
        R.initLanguage();
        //MaterialDesign.setLanguage(R.languagePath,LanguageRegion.IDENTIFY);
        MaterialDesign.setAnimationType(MaterialDesign.ANIMATION_FADE);
        //System.out.println(Locale.getDefault().getLanguage());
        StackPane desktop=new StackPane();
        Scene mainScene=MaterialDesign.getMaterialScene(desktop, 820, 620);
        addKeyFunctions(mainScene);
        setDragAndDropFunction(mainScene);
        desktop.getChildren().add(getMaterialLayout());
        
        //unitsPane=new UnitsPane(getMaterialLayout());
        primaryStage.setTitle(R.string.title.get());
        
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    private void addKeyFunctions(Scene scene){
        
    }
    private UnitsTabPane unitsTabPane;
    public UnitsTabPane getUnitsTabPane(){
        if(unitsTabPane==null){
            unitsTabPane=new UnitsTabPane(getMaterialLayout());
        }
        return unitsTabPane;
    }
    private MaterialEditableLayout mLayout;
    private MaterialEditableLayout getMaterialLayout(){
        if(mLayout==null){
            
            mLayout=new MaterialEditableLayout(false) {
                @Override
                public void onMenuButtonPressed(Button button) {
                    MaterialDropdownMenu menu=new MaterialDropdownMenu(button);
                    menu.addItem(new MaterialDropdownMenuItem(R.string.units.get()){
                        @Override
                        public void onItemClick(){
                            clickDrawerItem("Units");
                           //setRootView(unitsPane);
                        }
                    
                    });
                     /*menu.addItem(new MaterialDropdownMenuItem(R.string.inventory.get()){
                        @Override
                        public void onItemClick(){
                            
                        }
                    
                    });
                     menu.addItem(new MaterialDropdownMenuItem(R.string.experimental.get()){
                        @Override
                        public void onItemClick(){
                            
                        }
                    
                    });*/
                     menu.addItem(new MaterialDropdownMenuItem(R.string.settings.get()){
                        @Override
                        public void onItemClick(){
                            
                            clickDrawerItem("Settings");
                        }
                    
                    });
                    menu.unhide();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                @Override
                public void onBackButtonPressed(Button button){
                     //if(ACTUAL_MODULE_TITLE!=null)
                     //   setTitle(ACTUAL_MODULE_TITLE);
                     
                     setBackButton(false);
                }
            };
            mLayout.setWindowTitle("Fire Project beta nightly v0.2.3-b");
            MaterialDesign.primary.setTitle("Fire Editor");
            mLayout.addDrawerPane(getUnitsTabPane());
            mLayout.addDrawerItem("Settings",null,new SettingsPane(),true);
            mLayout.setBigToolbar(true);
            mLayout.setMiniDrawerSize(0);
            MaterialIconButton langButton=new MaterialIconButton();
            langButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    mLayout.clickDrawerItem("Settings");
                }
            });
            langButton.setMini(true);
            langButton.setIcon("/com/olmectron/fire/res/images/icons/ic_language.png");
            MaterialDisplayText langText=new MaterialDisplayText(R.getLanguageRegion().getCode().toUpperCase());
            langText.setFontSize(15);
            langText.setFontWeight(FontWeight.MEDIUM);
            langText.setColorCode(MaterialColor.material.WHITE);
            HBox langBox=new HBox(langButton,langText);
            langBox.setPadding(new Insets(0,12,0,0));
            langBox.setAlignment(Pos.CENTER);
            new MaterialTooltip(R.string.gui_language.get(),langBox);
            mLayout.getToolbar().getStatusBar().addElementToRight(langBox);
            mLayout.setMiniDrawer(true);
            
            mLayout.backButtonProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(newValue){
                        mLayout.switchToolbarActions(1);
                    }
                    else{
                        
                        mLayout.switchToolbarActions(2);
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            //mLayout.setBackButton(false);
            setTitulo(R.string.title.get());
            
            openFileContainer=mLayout.addToolbarActionButton(MaterialIconButton.FOLDER_OPEN_ICON, openFileHandler);
            
            openFileTooltip=new MaterialTooltip(R.string.open_file.get(),openFileContainer);
            
            MaterialIconButton searchButton=new MaterialIconButton(MaterialIconButton.SEARCH_ICON);
            searchButton.setOnClick(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    MaterialDropdownMenu menu=new MaterialDropdownMenu(searchButton);
                    MaterialTextField searchField=new MaterialTextField("Search an unit");
                    searchField.allowSpace();
                    searchField.allowDot();
                    searchField.setPadding(new Insets(8,16,8,16));
                    if(!mLayout.getTitle().equals(FireProject.ACTUAL_MODULE_TITLE)){
                        searchField.setText(mLayout.getTitle());
                    }
                    menu.addNodeAsItem(searchField);
                    searchField.setOnAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent event) {
                            menu.hideMenu();
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                    searchField.setOnKeyReleased(new EventHandler<KeyEvent>(){
                        @Override
                        public void handle(KeyEvent event) {
                            if(event.getCode().equals(KeyCode.ESCAPE)){
                                menu.hideMenu();
                            }
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                    searchField.textField().textProperty().addListener(new ChangeListener<String>(){
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            if(newValue.trim().equals("")){
                           
                              mLayout.setTitle(FireProject.ACTUAL_MODULE_TITLE);   
                            }
                            else{
                                 mLayout.setTitle(newValue);
                            }
                            UnitsPane unitPane=UnitsPane.getActualPane();
       if(unitPane!=null){
           unitPane.searchUnit(searchField.getTrimmedText());
       }
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                    menu.unhide();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            mLayout.addToolbarActionButton(searchButton,2);
            
            
            MaterialIconButton selectButton=new MaterialIconButton(MaterialIconButton.SELECT_ALL_ICON);
            
            
            new MaterialTooltip(R.string.select_all.get(),selectButton);
            selectButton.setOnClick(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    showSelectionDropdown();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            mLayout.addToolbarActionButton(selectButton,1);
            
            
            /*MaterialIconButton saveImageButton=new MaterialIconButton(MaterialIconButton.SAVE_ICON);
            
            
            new MaterialTooltip(R.string.select_all.get(),selectButton);
            selectButton.setOnClick(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    showSelectionDropdown();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            mLayout.addToolbarActionButton(selectButton,1);*/
            
            //new MaterialTooltip(R.string.save_file.get(),mLayout.addToolbarActionButton(MaterialIconButton.SAVE_ICON, saveFileHandler,2));
            mLayout.addToolbarActionButton(MaterialIconButton.SAVE_ICON, optionsHandler,2);
            mLayout.addToolbarActionButton(MaterialIconButton.MORE_VERT_ICON, settingsHandler,2);
            
            mLayout.setRootView(new EmptyPane());
            mLayout.fullSize();
            
            mLayout.getToolbar().getStatusBar().hideMinimizeButtons();
            mLayout.getToolbar().getStatusBar().hideHour();
        }
        
        return mLayout;
    }
    private void showSelectionDropdown(){
       /* MaterialDropdownMenu menu=new MaterialDropdownMenu(node);
        menu.addItem(new MaterialDropdownMenuItem(R.string.select_all.get()){
            @Override
            public void onItemClick(){*/
                //unitsPane.selectAllUnits();
       /*     }
        
        });
        menu.unhide();*/
       
       UnitsPane unitPane=UnitsPane.getActualPane();
       if(unitPane!=null){
           unitPane.selectAllUnits();
       }
    }
    public static String ACTUAL_MODULE_TITLE;
    private void setTitulo(String title){
        ACTUAL_MODULE_TITLE=title;
        getMaterialLayout().setTitle(title);
    }
    private void setDragAndDropFunction(Scene scene){
        scene.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.LINK);
                } else {
                    event.consume();
                }
            }
        });
        
        // Dropping over surface
        scene.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    if(db.getFiles().size()>1){
                        new MaterialToast(R.string.drop_only_one.get(),MaterialToast.LENGTH_SHORT).unhide();
                    }
                    else{
                        EmblemFile open=EmblemFile.openFile(db.getFiles().get(0));
                        loadFile(open);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private void setOpenButtonIcon(String materialIcon){
        
        openFileContainer.getIconButton().setMaterialIcon(materialIcon);
        if(materialIcon.equals(MaterialIconButton.CLOSE_ICON)){
            openFileTooltip.setText(R.string.close_file.get());
        openFileContainer.getIconButton().setOnAction(closeFileHandler);
        
        }
        else if(materialIcon.equals(MaterialIconButton.FOLDER_OPEN_ICON)){
            openFileTooltip.setText(R.string.open_file.get());
        openFileContainer.getIconButton().setOnAction(openFileHandler);
        
        }
    }
    private EventHandler<ActionEvent> settingsHandler=new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent aevent) {
                    MaterialDropdownMenu menu=new MaterialDropdownMenu((Region)aevent.getSource());
                    menu.addItem(new MaterialDropdownMenuItem(R.string.close_file.get(),MaterialIconButton.CLOSE_ICON,true){
                        @Override
                        public void onItemClick(){
                            closeFileHandler.handle(aevent);
                            
                        }
                    });
                    
                    menu.unhide();
                }
            };
    private EventHandler<ActionEvent> optionsHandler=new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent aevent) {
                    MaterialDropdownMenu menu=new MaterialDropdownMenu((Region)aevent.getSource());
                    menu.addItem(new MaterialDropdownMenuItem(R.string.overwrite_file.get()){//,MaterialIconButton.SAVE_ICON,true){
                        @Override
                        public void onItemClick(){
                                   overwriteFileHandler.handle(aevent);
                              
                        }
                    });
                    menu.addItem(new MaterialDropdownMenuItem(R.string.save_compressed_file.get()){//,MaterialIconButton.SAVE_ICON,true){
                        @Override
                        public void onItemClick(){
                            menu.setOnHidden(new EventHandler<WindowEvent>(){
                                @Override
                                public void handle(WindowEvent event) {
                                    saveCompressedFileHandler.handle(aevent);
                                    
                                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                }
                            });
                            menu.hide();
                        }
                    });
                    menu.addItem(new MaterialDropdownMenuItem(R.string.save_decompressed_file.get()){//,MaterialIconButton.SAVE_ICON,true){
                        @Override
                        public void onItemClick(){
                            menu.setOnHidden(new EventHandler<WindowEvent>(){
                                @Override
                                public void handle(WindowEvent event) {
                                    saveDecompressedFileHandler.handle(aevent);
                                    
                                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                }
                            });
                            menu.hide();
                        }
                    });
                    menu.unhide();
                }
            };
     private EventHandler<ActionEvent> overwriteFileHandler=new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    if(EmblemFile.getActualFile()!=null){
                        EmblemFile.getActualFile().overwriteActualFile();
                    }
                    
                }
            };
    private EventHandler<ActionEvent> saveCompressedFileHandler=new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    if(EmblemFile.getActualFile()!=null){
                        EmblemFile.getActualFile().saveCompressedEditedFile();
                    }
                    
                }
            };
    private EventHandler<ActionEvent> saveDecompressedFileHandler=new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    if(EmblemFile.getActualFile()!=null){
                        EmblemFile.getActualFile().saveDecompressedEditedFile();
                    }
                    
                }
            };
    private EventHandler<ActionEvent> openFileHandler=new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                     EmblemFile open=EmblemFile.openFile();
                     loadFile(open);
                    
                }
            };
    private EventHandler<ActionEvent> closeFileHandler=new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                /*getMaterialLayout().setRootView(new EmptyPane(){
                    @Override
                    public void onShown(){
                        //unitsPane.clear();
                    }
                });*/
                
                getMaterialLayout().showModule("Fire Editor", new EmptyPane(){
                    @Override
                    public void onShown(){
                        getUnitsTabPane().clearPanes();
                        //UnitsPane.getAlivePane().clear();
                        //UnitsPane.getDeadPane().clear();
                        //unitsPane.clear();
                    }
                });
                EmblemFile.releaseFile();
                
                System.out.println("File has been released");
                //setOpenButtonIcon(MaterialIconButton.FOLDER_OPEN_ICON);
                getMaterialLayout().switchToolbarActions(0);
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    private BackgroundTask<Void> loadFileTask=new BackgroundTask<Void>(){
        @Override
        public Void onAction() {
            EmblemFile.getActualFile().initData();
            return null;
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void onSucceed(Void valor) {
            
                getMaterialLayout().switchToolbarActions(2);
                //setOpenButtonIcon(MaterialIconButton.CLOSE_ICON);
                //new MaterialToast(R.string.load_file_success.get(),MaterialToast.LENGTH_SHORT).unhide();
                
                loadFileData();
                
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    private void synchedLoadFile(){
         EmblemFile.getActualFile().initData();
                getMaterialLayout().switchToolbarActions(2);
                //setOpenButtonIcon(MaterialIconButton.CLOSE_ICON);
                //new MaterialToast(R.string.load_file_success.get(),MaterialToast.LENGTH_SHORT).unhide();
                
                loadFileData();
                
    }
    private void loadFile(EmblemFile file){
        
                     if(file!=null){
                         getMaterialLayout().setRootView(new ProgressPane());
                        //synchedLoadFile(); 
                        loadFileTask.play();
                     }
    }
    private void loadFileData(){
        new EmblemHex(){
            @Override
            public void onUnitLoaded(EmblemUnit unit) {
                String name=unit.getProperName();
                while(name.length()<20){
                    name=name+" ";
                }
                System.out.println(name+": "+unit.getHexBlock());
                //unitsPane.addUnit(unit);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onLoadSucceed(ObservableList<EmblemUnit> units) {
              
                System.out.println("Loaded units: "+units.size());
                //MaterialTabs tabs=UnitsPane.fillData(units,getMaterialLayout());
               //getMaterialLayout().addDrawerPane();
               getUnitsTabPane().setUnits(units);
//                getMaterialLayout().showTab("Units");
                getMaterialLayout().clickDrawerItem("Units");
               // getMaterialLayout().showModule("Units", null,tabs);
                /// tabs.getTabAt(0).select();
                /*EventHandler filter = new EventHandler<InputEvent>() {
                    @Override
                    public void handle(InputEvent event) {
                        System.out.println("Filtering out event " + event.getEventType()); 
                  event.consume();
                    }
                };*/
                 
                //tabs.getTabAt(0).addEventFilter(MouseEvent.ANY, filter);
 /*              
tabs.getTabAt(0).setOnMouseEntered(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        MaterialStandardListItem<EmblemUnit> lastDraggedUnit=UnitsPane.getActualPane().getLastDraggedUnit();
                        if(!UnitsPane.getActualPane().equals(UnitsPane.getAlivePane()) && 
                               lastDraggedUnit!=null){
                            UnitsPane.getActualPane().getUnitList().removeItem(lastDraggedUnit);
                            lastDraggedUnit.getItem().getUnitBlock().setAlive(true);
                            UnitsPane.getAlivePane().changeUnit(lastDraggedUnit.getItem());
                        }
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
tabs.getTabAt(1).setOnMouseEntered(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        MaterialStandardListItem<EmblemUnit> lastDraggedUnit=UnitsPane.getActualPane().getLastDraggedUnit();
                        if(!UnitsPane.getActualPane().equals(UnitsPane.getDeadPane()) && 
                               lastDraggedUnit!=null){
                            UnitsPane.getActualPane().getUnitList().removeItem(lastDraggedUnit);
                         
                            lastDraggedUnit.getItem().getUnitBlock().setAlive(false);
                            UnitsPane.getDeadPane().changeUnit(lastDraggedUnit.getItem());
                        }
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });*/
                
                // for(int i=0;i<units.size();i++){
               //     System.out.println(units.get(i).getName());
               //     System.out.println(units.get(i).getUnitBlock().getEditedBlock());
               // }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }.getUnits();
        
        /*for (EmblemUnit unit : EmblemUnit.getUnits()) {
            System.out.println("ID: "+unit.getHexId());
            System.out.println("NAME: "+unit.getName());
            System.out.println("JP NAME: "+unit.getName(EmblemLocale.JPN));
            
            System.out.println("GENDER: "+unit.getGender());
            System.out.println("CHILD: "+unit.getChild());
            System.out.println("HAIR-EDITABLE: "+unit.getHairColorEditable());
            
                    }*/
        //System.out.println(EmblemFile.getLastOpenedFile().getFullDecompressedHex());
    }
}
