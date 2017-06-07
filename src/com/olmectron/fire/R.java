/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire;

import com.olmectron.fire.controllers.EmblemUnit;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.files.FieldsFile;
import com.olmectron.material.utils.LanguageReader;
import com.olmectron.material.utils.LanguageRegion;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;

/**
 *
 * @author Edgar
 */
public class R {
    private static ObservableList<EmblemUnit> unitList;
    public static ObservableList<EmblemUnit> getUnitList(){
        if(unitList==null){
            unitList=FXCollections.observableArrayList();
        }
        return unitList;
    }
    
    
    public static String languagePath="/com/olmectron/fire/res/values/strings";
    public static String imagesPath="/com/olmectron/fire/res/images";
    private static FieldsFile settingsFile;
    public static void initLanguage(){
        if(getLanguageRegion()==null){
            setLanguageRegion(LanguageRegion.IDENTIFY);
        }
        else{
            setLanguageRegion(getLanguageRegion());
        }
    }
    public static void setLanguageRegion(LanguageRegion reg){
        getSettingsFile().setValue("gui_lang",reg.getCode());
        MaterialDesign.setLanguage(R.languagePath,reg);
    }
    public static LanguageRegion getLanguageRegion(){
        if(getSettingsFile().getValue("gui_lang")!=null){
            return LanguageRegion.getLanguageRegionWithCode(getSettingsFile().getValue("gui_lang"));
        }
        return null;
    }
    public static FieldsFile getSettingsFile(){
        try {
            if(settingsFile==null){
                settingsFile=new FieldsFile("settings");
            }
            return settingsFile;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static void saveImageToFile(String name,Image image) {
        FileChooser chooser=new FileChooser();
        chooser.setSelectedExtensionFilter(new ExtensionFilter("PNG File",".png"));
        chooser.setInitialFileName(name);
        File outputFile=chooser.showSaveDialog(MaterialDesign.primary);
        
    //File outputFile = new File("C:/JavaFX/");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
          ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
  }
    public static String unitsImagesPath=imagesPath+"/units";
    public static class string{
        
        public static StringProperty experimental=LanguageReader.getProperty("experimental");
        public static StringProperty units=LanguageReader.getProperty("units");
        public static StringProperty inventory=LanguageReader.getProperty("inventory");
        public static StringProperty open_file=LanguageReader.getProperty("open_file");
        public static StringProperty save_file=LanguageReader.getProperty("save_file");
        public static StringProperty title=LanguageReader.getProperty("title");
        public static StringProperty open_options=LanguageReader.getProperty("open_options");
        public static StringProperty invalid_file=LanguageReader.getProperty("invalid_file");
        public static StringProperty drop_only_one=LanguageReader.getProperty("drop_only_one");
        public static StringProperty load_file_success=LanguageReader.getProperty("load_file_success");
        public static StringProperty close_file=LanguageReader.getProperty("close_file");
        public static StringProperty select_all=LanguageReader.getProperty("select_all");
        public static StringProperty export_decompressed_file=LanguageReader.getProperty("export_decompressed_file");
        public static StringProperty decompressed_file_exported=LanguageReader.getProperty("decompressed_file_exported");
        public static StringProperty loading=LanguageReader.getProperty("loading");
        public static StringProperty game_language=LanguageReader.getProperty("game_language");
        public static StringProperty gui_language=LanguageReader.getProperty("gui_language");
        public static StringProperty save_decompressed_file=LanguageReader.getProperty("save_decompressed_file");
        
        public static StringProperty save_compressed_file=LanguageReader.getProperty("save_compressed_file");
         public static StringProperty overwrite_file=LanguageReader.getProperty("overwrite_file");
         public static StringProperty overwrite_success=LanguageReader.getProperty("overwrite_success");
         public static StringProperty alive=LanguageReader.getProperty("alive");
         public static StringProperty fallen=LanguageReader.getProperty("fallen");
        public static StringProperty main_stats=LanguageReader.getProperty("main_stats");
        public static StringProperty other_stats=LanguageReader.getProperty("other_stats");
        public static StringProperty name=LanguageReader.getProperty("name");
        public static StringProperty settings=LanguageReader.getProperty("settings");
        public static StringProperty appearance=LanguageReader.getProperty("appearance");
        public static StringProperty kill_unit=LanguageReader.getProperty("kill_unit");
        public static StringProperty revive_unit=LanguageReader.getProperty("revive_unit");
        
        public static StringProperty are_you_sure=LanguageReader.getProperty("are_you_sure");
        public static StringProperty killing_message=LanguageReader.getProperty("killing_message");
        public static StringProperty do_it=LanguageReader.getProperty("do_it");
        public static StringProperty wait=LanguageReader.getProperty("wait");
        public static StringProperty should_restart=LanguageReader.getProperty("should_restart");
        public static StringProperty restart_message=LanguageReader.getProperty("restart_message");
        public static StringProperty ok=LanguageReader.getProperty("ok");
        
    }

    public static class errors {

        public static String ERROR_PARSING_BLOCK="ERROR_PARSING_BLOCK";
    }
}
