/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.controllers;

import com.olmectron.fire.R;
import com.olmectron.material.files.FieldsFile;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class EmblemLocale{
    
    public static final EmblemLocale ENG=new EmblemLocale("en");
    public static final EmblemLocale JPN=new EmblemLocale("ja");
    public static final EmblemLocale ESP=new EmblemLocale("es");
    
    private static final EmblemLocale[] LOCALES=new EmblemLocale[]{ENG,JPN,ESP};
   public static final EmblemLocale DEFAULT=getIdentifiedLanguage();
    
    public static final EmblemLocale PREFERRED=new EmblemLocale(R.getSettingsFile().getValue("game_lang", getIdentifiedLanguage().toString()));
    
    private static final EmblemLocale getIdentifiedLanguage(){
        EmblemLocale local=new EmblemLocale(Locale.getDefault().getLanguage());
        for(int i=0;i<LOCALES.length;i++){
            EmblemLocale l=LOCALES[i];
            if(local.equals(l)){
                return l;
            }
        }
        return ENG;
    }
    public static final EmblemLocale parseLocale(String id){
        /*for(int i=0;i<locales.length;i++){
            if(locales[i].toString().equalsIgnoreCase(id)){
                return locales[i];
            }
        }
        return null;*/
        return new EmblemLocale(id);
    }
    @Override
    public boolean equals(Object o){
        EmblemLocale l=(EmblemLocale)o;
        if(l.toString().equalsIgnoreCase(this.id)){
            return true;
        }
        return false;
    }
    private String id;
    
    private EmblemLocale(String id){
        this.id=id.trim();
    }
    @Override
    public String toString(){
        return this.id;
    }
}
