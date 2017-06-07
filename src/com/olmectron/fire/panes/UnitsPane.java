/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.panes;

import com.olmectron.fire.FireProject;
import com.olmectron.fire.R;
import com.olmectron.fire.controllers.EmblemHex;
import com.olmectron.fire.controllers.EmblemUnit;
import com.olmectron.fire.hex.HexConverts;
import com.olmectron.material.components.GUITimer;
import com.olmectron.material.components.MaterialCard;
import com.olmectron.material.components.MaterialDesignTable;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialDropdownMenu;
import com.olmectron.material.components.MaterialDropdownMenuItem;
import com.olmectron.material.components.MaterialFlowList;
import com.olmectron.material.components.MaterialIconButton;
import com.olmectron.material.components.MaterialStandardDrawer;
import com.olmectron.material.components.MaterialStandardListItem;
import com.olmectron.material.components.MaterialTab;
import com.olmectron.material.components.MaterialTabs;
import com.olmectron.material.components.MaterialTransparentPane;
import com.olmectron.material.components.RaisedButton;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.layouts.MaterialEditableLayout;
import com.olmectron.material.utils.BackgroundTask;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Edgar
 */
public class UnitsPane extends BorderPane{
    public void addUnit(EmblemUnit unit){
        
        R.getUnitList().add(unit);
        //unitTable.addItem(unit);
        unitList.addItem(unit);
        
    }
    public void removeUnitFromPane(EmblemUnit unit){
        unitList.removeItem(unit);
    }
    public void changeUnit(EmblemUnit unit){
        unitList.addItem(unit);
    }
    public MaterialFlowList getUnitList(){
        return unitList;
    }
   
    public MaterialStandardListItem<EmblemUnit> getLastDraggedUnit(){
        MaterialStandardListItem<EmblemUnit> lastUnit=unitList.getLastDraggedItem();
        unitList.nullLastDraggedItem();
        return lastUnit;
    }
    public void clear(){
        R.getUnitList().clear();
        unitList.clear();
    }
    private static String globalKeyword="";
    private final BackgroundTask queryService=new BackgroundTask<ObservableList<EmblemUnit>>() {
            @Override
            public ObservableList<EmblemUnit> onAction() {
                ObservableList<EmblemUnit> nuevos=search(globalKeyword);
                return nuevos;
    // //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSucceed(ObservableList<EmblemUnit> units) {
                if(units!=null){
                    unitList.clear();
                    unitList.addObservableItems(units);
                    //if(unitList.size()>0){
                    //    getTable().getSelectionModel().select(0);
                    //    getTable().scrollTo(0);
                    //}
                }
    // //To change body of generated methods, choose Tools | Templates.
            }
        }; 
    
    public void searchUnit(String keyword){
        this.globalKeyword=keyword;
        queryService.setDebounce(300);
        queryService.play();
        
       /* ObservableList<EmblemUnit> nuevos=search(globalKeyword);
    if(nuevos!=null){
        unitList.clear();
                    unitList.addObservableItems(nuevos);
    }*/
    }
    private ObservableList<EmblemUnit> search(String name){
        ObservableList<EmblemUnit> list=FXCollections.observableArrayList();
            for(int i=0;i<R.getUnitList().size();i++){
                EmblemUnit u=R.getUnitList().get(i);
                //System.err.println(u.getName()+" checking");
               if(u.getName().toLowerCase().contains(name.toLowerCase())){
                   //System.out.println(u.getName()+" result");
                   if(UnitsPane.getUnitsTabs().getSelectedTabIndex()==0){
                       if(u.isAlive()){
                           list.add(u);
                       }
                   }
                   else if(UnitsPane.getUnitsTabs().getSelectedTabIndex()==1)
                       
                   {
                       if(!u.isAlive()){
                           list.add(u);
                       }
                   }
                   
               } 

            }
            return list;
        
    }
    //private VBox pane;
    private MaterialEditableLayout main;
    private MaterialEditableLayout getMainLayout(){
        return  main;
    }
    private MaterialStandardDrawer  drawer;
    private VBox drawerBox;
    private BorderPane drawerPane;
    public void emptyDrawerPane(){
        drawerPane.setCenter(null);
    }
    public UnitsPane(MaterialEditableLayout main, String type, ObservableList<EmblemUnit> units){
        super();
        this.main=main;
        
        //StackPane pane=new StackPane();
        //pane.setPrefHeight(1080);
        //this.getContentCard().setCardPadding(new Insets(0));
        //setRootComponent(pane);
        //pane.setAlignment(Pos.CENTER);
         RaisedButton newUnit=new RaisedButton("New unit");
        newUnit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
           addUnit(EmblemHex.getNewEmblemUnit(EmblemUnit.getUnitModelWithId("03")));
          
            }
        });
        setTop(newUnit);
        setCenter(initList());
        drawer=new MaterialStandardDrawer();
        drawerBox=new VBox(drawer);
        drawerPane=new BorderPane();
        drawer.getChildren().add(drawerPane);
        //drawerBox.setPadding(new Insets(16));
        //drawer.setDrawerWidth(0);
        drawer.setPrefHeight(1080);
       
               
                setRight(drawerBox);
        getMainLayout().backButtonProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    unitList.hideCheckBoxes();
                }
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
        switch(type){
            case ALIVE:
                for(EmblemUnit unit: units){
                    if(unit.isAlive()){
                        this.addUnit(unit);
                    }
                }
                break;
            case DEAD:
                for(EmblemUnit unit: units){
                    if(!unit.isAlive()){
                        this.addUnit(unit);
                    }
                }
                break;
        }
          
       
    }
    private void setRandomRippleColor(MaterialStandardListItem<EmblemUnit> unitContainer){
        Random random=new Random();
                   int r=random.nextInt(256);
                   int g=random.nextInt(256);
                   int b=random.nextInt(256);
                   
                    String color=HexConverts.getHexPair(r)+HexConverts.getHexPair(g)+HexConverts.getHexPair(b);
                   unitContainer.setRippleColor(color);
    }
    private MaterialFlowList<EmblemUnit> unitList;
    //private MaterialDesignTable<EmblemUnit> unitTable;
    private Node initList(){
        /*unitTable=new MaterialDesignTable<EmblemUnit>() {
            @Override
            public Property[] getObservedProperties(EmblemUnit t) {
                return null;
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Node getLayout() {
                StackPane stack=new StackPane();
                
                MaterialDisplayText text=new MaterialDisplayText("");
                text.setId("name_text");
                stack.setId("image_box");
                VBox imageBox=new VBox(stack,text);
                return imageBox;
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void fillGraphic(Node graphic, EmblemUnit value) {
                ((StackPane)graphic.lookup("#image_box")).getChildren().clear();
                ((StackPane)graphic.lookup("#image_box")).getChildren().add(value.getPortrait());
                  ((MaterialDisplayText)graphic.lookup("#name_text")).setText(value.getName());
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onRowAdded(ListChangeListener.Change c, Object addedObject) {
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onRowRemoved(ListChangeListener.Change c, Object removedObject) {
                 //To change body of generated methods, choose Tools | Templates.
            }
        };
        */
        
        unitList=new MaterialFlowList<EmblemUnit>(this) {
            

            @Override
            public void onLongPressSelection(int selected) {
                if(selected>0){
                        getMainLayout().setBackButton(true);
                    
                    getMainLayout().setTitle(""+selected);
                }
                else{
                    if(FireProject.ACTUAL_MODULE_TITLE!=null)
                    getMainLayout().setTitle(FireProject.ACTUAL_MODULE_TITLE);
                    getMainLayout().setBackButton(false);
                }
                //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public boolean asCard(){
                return true;
            }
            

            @Override
            public void onRippleEnd(EmblemUnit item, MaterialStandardListItem<EmblemUnit> itemBox) {
                
//To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onItemContainerClick(MaterialStandardListItem<EmblemUnit> itemBox, MouseEvent event) {
                //if(itemBox.getItem().getHairColorEditable()){
                    
                    
                   //itemBox.getItem().getUnitBlock().setStringHairColor(itemBox.getRippleColor());
                   //setRandomRippleColor(itemBox);
                //}
                //triggerAnimation(itemBox);
                
                
                //super.onItemContainerClick(itemBox, event); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public void onItemClick(EmblemUnit unit, MouseEvent event) {
                if(unit!=null){
                    //if(getRight()==null){
                      //  setRight(drawerBox);
                    //}
                  //  drawerPane.setCenter(itemBox.getItem().getUnitScrollPane());
                  if(drawer.getDrawerWidth()==0){
                             Timeline timeline=new Timeline();
                     
            KeyValue widthValue=new KeyValue(drawer.drawerWidthProperty(),280);
            
            KeyFrame kf=new KeyFrame(Duration.millis(250),widthValue);
            timeline.getKeyFrames().add(kf);
                    timeline.play();
                    timeline.setOnFinished(new EventHandler<ActionEvent>(){
                                 @Override
                                 public void handle(ActionEvent event) {
                                     
                                     drawer.setDrawerWidth(280);
                                     ScrollPane scroll=unit.getUnitScrollPane();
                                     scroll.setOpacity(0.0);
                                     drawerPane.setCenter(scroll);
                                     new GUITimer(200) {
                                         @Override
                                         public void action() {
                                              FadeTransition transition=new FadeTransition();
                                     transition.setDuration(Duration.millis(500));
                                     transition.setNode(scroll);
                                     transition.setToValue(1.0);
                                     transition.play();
                                         }
                                     };
                                    
                                     
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                 }
                             });
                  
                  }
                  else{
                           ScrollPane scroll=unit.getUnitScrollPane();
                                     scroll.setOpacity(0.0);
                                     drawerPane.setCenter(scroll);
                                     
                                     FadeTransition transition=new FadeTransition();
                                     transition.setDuration(Duration.millis(500));
                                     transition.setNode(scroll);
                                     transition.setToValue(1.0);
                                     transition.play();
                  }
 //                   drawer.getChildren().clear(); 
                //getMainLayout().setRootView(itemBox.getItem().getUnitLayout());
                    
                }
                /*if(event.getButton().equals(MouseButton.SECONDARY)){
                    MaterialDropdownMenu menu=new MaterialDropdownMenu(event.getScreenX(),event.getScreenY());
                    menu.addItem(new MaterialDropdownMenuItem("Save portrait"){
                        @Override
                        public void onItemClick(){
                            R.saveImageToFile(unit.getName(),((ImageView)unit.getPortrait()).getImage());
                        }
                    
                    });
                    menu.unhide();
                }*/
                // //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public void onCardAttached(MaterialStandardListItem<EmblemUnit> unitContainer){
                if(unitContainer.getItem().getHairColorEditable()){
                    //setRandomRippleColor(unitContainer);
                    
                }
            }
            @Override
            public void cardConverter(MaterialCard card, EmblemUnit unit, MaterialStandardListItem<EmblemUnit> itemContainer){
                
                card.setCardWidth(128);
                
                MaterialDisplayText nameText=new MaterialDisplayText(unit.getName());
                nameText.setFontSize(18);
                nameText.setColorCode(MaterialColor.material.BLACK_87);
                if(unit.getNameEditable()){
                    
                    nameText.textProperty().bind(unit.getUnitBlock().unitNameProperty());
                }
                Node imagen=unit.getPortrait();
                
                VBox portraitBox=new VBox();
                if(imagen!=null){
                    portraitBox.getChildren().addAll(imagen);
                imagen.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        System.err.println("Trigger");
                        //triggerAnimation(itemContainer);
                        // //To change body of generated methods, choose Tools | Templates.
                    }
                });
                
                    //card.addComponent(imagen);
                }
                portraitBox.getChildren().add(nameText);
                BorderPane border=new BorderPane();
                border.setId("border_info_pane");
                
                
                
                portraitBox.setAlignment(Pos.CENTER);
                //BorderPane.setAlignment(portraitBox, Pos.BOTTOM_CENTER);
                border.setRight(portraitBox);
                card.align(Pos.CENTER);
                card.addComponent(border);
               
            }
            @Override
            public Node itemConverter(EmblemUnit unit, MaterialStandardListItem<EmblemUnit> itemContainer) {
                
                
                return null;
                // //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public void onItemSwap(int index1, int index2, EmblemUnit unit1, EmblemUnit unit2){
                int indexB=R.getUnitList().indexOf(unit2);
                int indexA=R.getUnitList().indexOf(unit1);
                EmblemUnit item2=R.getUnitList().set(indexB,null);
                EmblemUnit item1=R.getUnitList().set(indexA,null);
                
            R.getUnitList().set(indexA,item2);
            R.getUnitList().set(indexB,item1);
            unitList.nullLastDraggedItem();
            }

        };
        
        //return unitTable.getTable();
        return unitList.getPerfectSizeFlowListPane();
    }
  

    public void selectAllUnits() {
        unitList.selectAllCheckBoxes();
        // //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    
    
    
    
      
    private void onItemExpanded(MaterialStandardListItem<EmblemUnit> item){
        //MaterialIconButton button=((MaterialIconButton)item.lookup("#back-button"));
        //button.setVisible(true);
        
    }
    private void onItemDiminished(MaterialStandardListItem<EmblemUnit> item){
        //MaterialIconButton button=((MaterialIconButton)item.lookup("#back-button"));
        //button.setVisible(false);
    }
    
    
    
    
    
    private double originalCardHeight=0, originalCardWidth=0;
    private double originalHeight=0, originalWidth=0;
    public void triggerAnimation(MaterialStandardListItem<EmblemUnit> pressedItem){
        
                
                MaterialCard theCard=((MaterialCard)pressedItem.lookup("#the_card"));
                if(originalCardHeight==0){
                    originalCardHeight=theCard.getCardHeight(); 
                }
                if(originalCardWidth==0){
                    originalCardWidth=theCard.getCardWidth();
                    
                }
                if(originalHeight==0){
                    originalHeight=pressedItem.getHeight();
                }
                if(originalWidth==0){
                    originalWidth=pressedItem.getWidth();
                }
                unitList.setReplaceAnimation(false);
                if(isExpanded){
                    condenseItem(pressedItem);
                    
                }
                else{
                    lastScrollPosition=unitList.getPerfectScroll().getVvalue();
                    expandItem(pressedItem);
                }
    }
    private MaterialStandardListItem<EmblemUnit> selectedItem;
     public MaterialStandardListItem<EmblemUnit> getSelectedItem(){
        return selectedItem;
    }
     public void setSelectedItem(MaterialStandardListItem<EmblemUnit> item){
         this.selectedItem=item;
     }
     private double lastScrollPosition=0;
    private void setRealPositions(MaterialStandardListItem<EmblemUnit> pressedItem){
        pressedItem.setTranslateX(0);
        pressedItem.setTranslateY(0);
        for(int i=0;i<unitList.size();i++){
                    if(!pressedItem.equals(unitList.getItemBox(i))){
                        unitList.getItemBox(i).setManaged(false);
                    }}
    }
    private void restoreItems(MaterialStandardListItem<EmblemUnit> pressedItem){
        onAnimationEnded(pressedItem);
        for(int i=0;i<unitList.size();i++){
                        
                        unitList.getItemBox(i).setManaged(true);
                        unitList.getItemBox(i).setVisible(true);
                    }
        
          TimerTask debounceTask=new TimerTask() {

                        @Override
                        public void run() {
         Platform.runLater(new Runnable() {
            public void run() {
               
               showAllItems(pressedItem);
               
            unitList.scrollToPosition((int)(lastScrollPosition*100));
               //System.out.println("I'm playing ");
            }
        });
                        }};
                            
                            
                            
                        
                new Timer().schedule(
                debounceTask, 300);
        
        
        
        
        
        
        
    
    }
    private void moveUpPressedItem(MaterialStandardListItem<EmblemUnit> pressedItem){
        pressedItem.setFlowLayoutOriginalXPosition(pressedItem.getLayoutX());
        pressedItem.setFlowLayoutOriginalYPosition(pressedItem.getLayoutY());
        
        Timeline timeline=new Timeline();
            
            KeyValue kv8=new KeyValue(pressedItem.translateXProperty(),-pressedItem.getLayoutX()+((Pane)pressedItem.getParent()).getWidth()/2-pressedItem.getWidth()/2);
            KeyValue kv9=new KeyValue(pressedItem.translateYProperty(),-pressedItem.getLayoutY());
                
            //System.out.println(-pressedItem.getTranslateY()+" y translate");
            KeyFrame kf=new KeyFrame(Duration.millis(250),kv8,kv9);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                setRealPositions(pressedItem);
                changeSizeAnimation(pressedItem);
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
            timeline.play();
    }
    private double expandedWidth=550;
    private double expandedHeight=380;
    private boolean isExpanded=false;
    private void expandItem(MaterialStandardListItem<EmblemUnit> pressedItem){
        isExpanded=true;
        setSelectedItem(pressedItem);
        fadeNonPressedItems(pressedItem);
                
                moveUpPressedItem(pressedItem);
                onItemExpanded(pressedItem);
              
    }
    private EventHandler actualReleaseHandler=null;
    private void condenseItem(MaterialStandardListItem<EmblemUnit> pressedItem){
        
        isExpanded=false;
        setSelectedItem(null);
        //this.moveToOriginalPlace(pressedItem);
        //restoreItemsPositions(pressedItem);
        reduceSizeAnimation(pressedItem);
        onItemDiminished(pressedItem);
    }
    private void reduceSizeAnimation(MaterialStandardListItem<EmblemUnit> pressedItem){
        MaterialCard theCard=((MaterialCard)pressedItem.lookup("#the_card"));
        
        Timeline timeline=new Timeline();
            KeyValue kv=new KeyValue(pressedItem.prefHeightProperty(),originalHeight);
            KeyValue kv2=new KeyValue(pressedItem.prefWidthProperty(),originalWidth);
             KeyValue kv3=new KeyValue(pressedItem.maxHeightProperty(),originalHeight);
            KeyValue kv4=new KeyValue(pressedItem.maxWidthProperty(),originalWidth);
            KeyValue kv5=new KeyValue(theCard.cardHeightProperty(),originalCardHeight);
            KeyValue kv6=new KeyValue(theCard.cardWidthProperty(),originalCardWidth);
                 KeyValue fadeValue=new KeyValue(theCard.opacityProperty(),0);
            KeyValue fadeValue2=new KeyValue(pressedItem.opacityProperty(),0);
            
            
            KeyFrame kf=new KeyFrame(Duration.millis(300),fadeValue,fadeValue2,kv,kv2,kv3,kv4,kv5,kv6);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                pressedItem.setVisible(false);
                pressedItem.setManaged(false);
                restoreItems(pressedItem);
                //restoreItemsPositions(pressedItem);
                //moveToOriginalPlace(pressedItem);
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
            timeline.play();
    }
    private void changeSizeAnimation(MaterialStandardListItem<EmblemUnit> pressedItem){
        MaterialCard theCard=((MaterialCard)pressedItem.lookup("#the_card"));
        
        theCard.setCardWidth(originalCardWidth);
        theCard.setCardHeight(originalCardHeight);
        pressedItem.prefHeightProperty().set(originalHeight);
        pressedItem.maxHeightProperty().set(originalHeight);
        
        pressedItem.prefWidthProperty().set(originalWidth);
        pressedItem.maxWidthProperty().set(originalWidth);
        
        Timeline timeline=new Timeline();
            KeyValue kv=new KeyValue(pressedItem.prefHeightProperty(),expandedHeight);
            KeyValue kv2=new KeyValue(pressedItem.prefWidthProperty(),expandedWidth);
             KeyValue kv3=new KeyValue(pressedItem.maxHeightProperty(),expandedHeight);
            KeyValue kv4=new KeyValue(pressedItem.maxWidthProperty(),expandedWidth);
            KeyValue kv5=new KeyValue(theCard.cardHeightProperty(),expandedHeight);
            KeyValue kv6=new KeyValue(theCard.cardWidthProperty(),expandedWidth);
                
            
            KeyFrame kf=new KeyFrame(Duration.millis(300),kv,kv2,kv3,kv4,kv5,kv6);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(isExpanded){
                                 pressedItem.removeRipple();
                                 onAnimationEnded(pressedItem);
                                 //System.out.println("amsdkasmdk");
                            }// //To change body of generated methods, choose Tools | Templates.
            }
        });
            timeline.play();
    }
    private void moveToOriginalPlace(MaterialStandardListItem<EmblemUnit> pressedItem){
         MaterialCard theCard=((MaterialCard)pressedItem.lookup("#the_card"));
       
         Timeline timeline=new Timeline();
         /*   
         KeyValue kv=new KeyValue(pressedItem.prefHeightProperty(),originalHeight);
            KeyValue kv2=new KeyValue(pressedItem.prefWidthProperty(),originalWidth);
             KeyValue kv3=new KeyValue(pressedItem.maxHeightProperty(),originalHeight);
            KeyValue kv4=new KeyValue(pressedItem.maxWidthProperty(),originalWidth);
            KeyValue kv5=new KeyValue(theCard.cardHeightProperty(),originalCardHeight);
            KeyValue kv6=new KeyValue(theCard.cardWidthProperty(),originalCardWidth);
                */
            KeyValue kv8=new KeyValue(pressedItem.translateXProperty(),pressedItem.getFlowLayoutOriginalXPosition()-pressedItem.getLayoutX());
            KeyValue kv9=new KeyValue(pressedItem.translateYProperty(),pressedItem.getFlowLayoutOriginalYPosition()-pressedItem.getLayoutY());
                
            //System.out.println(-pressedItem.getTranslateY()+" y translate");
            KeyFrame kf=new KeyFrame(Duration.millis(300),kv8,kv9);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //setRealPositions(pressedItem);
                //changeSizeAnimation(pressedItem);
                // //To change body of generated methods, choose Tools | Templates.
            }
        });
            timeline.play();
    }
    private void fadeNonPressedItems(MaterialStandardListItem<EmblemUnit> pressedItem){
        for(int i=0;i<unitList.size();i++){
                    if(!pressedItem.equals(unitList.getItemBox(i))){
                         Timeline timeline=new Timeline();
                          MaterialStandardListItem<EmblemUnit> newItem=unitList.getItemBox(i);
                MaterialCard newCard=((MaterialCard)newItem.lookup("#the_card"));
               
            KeyValue fadeValue=new KeyValue(newCard.opacityProperty(),0);
            KeyValue fadeValue2=new KeyValue(newItem.opacityProperty(),0);
            
            KeyFrame kf=new KeyFrame(Duration.millis(250),fadeValue,fadeValue2);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent event) {
                            newItem.setVisible(false);
                            //newItem.setManaged(false);
                        }
                    });
            timeline.play();
                    }
                }
    }
    private void showAllItems(MaterialStandardListItem<EmblemUnit> pressedItem){
        for(int i=0;i<unitList.size();i++){
                    
                         Timeline timeline=new Timeline();
                          MaterialStandardListItem<EmblemUnit> newItem=unitList.getItemBox(i);
                MaterialCard newCard=((MaterialCard)newItem.lookup("#the_card"));
               
            KeyValue fadeValue=new KeyValue(newCard.opacityProperty(),1);
            KeyValue fadeValue2=new KeyValue(newItem.opacityProperty(),1);
            
            KeyFrame kf=new KeyFrame(Duration.millis(250),fadeValue,fadeValue2);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent event) {
                            if(!isExpanded){
                                 pressedItem.restoreRipple();
                                 
                            }
                            //newItem.setVisible(false);
                            //newItem.setManaged(false);
                        }
                    });
            timeline.play();
                    }
                
    }
    public static UnitsPane getActualPane(){
        if(UnitsPane.getUnitsTabs()!=null){
           if(UnitsPane.getUnitsTabs().getSelectedTabIndex()==0){
               return getAlivePane();
           }
           else if(UnitsPane.getUnitsTabs().getSelectedTabIndex()==1){
               return getDeadPane();
           }
       }
        return null;
    }
    public static MaterialTabs getUnitsTabs(){
        return unitsTabs;
    }
    private boolean deleted=false;
    public void onAnimationEnded(MaterialStandardListItem<EmblemUnit> pressedItem){
        MaterialCard card=((MaterialCard)pressedItem.lookup("#the_card"));
        //actualReleaseHandler=pressedItem.getOnMouseReleased();
                pressedItem.setOnMouseReleased(actualReleaseHandler);
        if(isExpanded){
            BorderPane border=(BorderPane)pressedItem.lookup("#border_info_pane");
        
            RaisedButton closeButton=new RaisedButton("Genial");
            closeButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    triggerAnimation(pressedItem);
                    // //To change body of generated methods, choose Tools | Templates.
                }
            });
            border.setCenter(new UnitInfoPane(pressedItem.getItem()));
            
            //infoPane.getChildren().add(closeButton);
            
            //ROMEditPane info=new ROMEditPane(pressedItem.getItem()){
                
            //};
            //info.setId("file_info");
            //infoPane.setId("unit_info");
            //card.addComponent(infoPane);
        }
        else{
            unitList.setReplaceAnimation(true);
            BorderPane border=(BorderPane)pressedItem.lookup("#border_info_pane");
            border.setCenter(null);
            //StackPane info=(StackPane)card.lookup("#unit_info");
            //if(info!=null){
            //    card.removeComponent(info);
                
            //}
        }
    }
    public static UnitsPane getAlivePane(){
        return alivePane;
    }
    public static final String ALIVE="alive";
    public static final String DEAD="dead";
    
    public static UnitsPane getDeadPane(){
        return deadPane;
    }
    private static MaterialTabs unitsTabs;
    private static UnitsPane alivePane, deadPane;
    private static MaterialEditableLayout staticLayout;
    public void onUnitRemoved(EmblemUnit unit){
        
    }
    public static MaterialTabs fillData(ObservableList<EmblemUnit> units, MaterialEditableLayout mLayout) {
       staticLayout=mLayout;
       // alivePane=new  UnitsPane(mLayout);
      // deadPane=new  UnitsPane(mLayout);
       for(int i=0;i<units.size();i++){
           if(units.get(i).isAlive()){
               alivePane.addUnit(units.get(i));
           }
           else{
               deadPane.addUnit(units.get(i));
           }
       }
       unitsTabs=new MaterialTabs(true);
    
        unitsTabs.setAlignment(Pos.CENTER_LEFT);
       unitsTabs.addTab(R.string.alive.get());
       unitsTabs.addTab(R.string.fallen.get());
       unitsTabs.setPadding(new Insets(0,0,0,66));
       
        unitsTabs.getTabAt(0).setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                      staticLayout.setRootView(getAlivePane());
                        // //To change body of generated methods, choose Tools | Templates.
                    }
                });
                unitsTabs.getTabAt(1).setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        
                      staticLayout.setRootView(getDeadPane());
                        // //To change body of generated methods, choose Tools | Templates.
                    }
                });
return unitsTabs;
       // //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
