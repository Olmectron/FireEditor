/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.controllers;

import java.util.HashMap;

/**
 *
 * @author Edgar
 */
public class EmblemNameMap {
    private HashMap hashMap;
    public EmblemNameMap(){
        hashMap=new HashMap();
    }
    public void addName(EmblemLocale locale, String name){
        hashMap.put(locale.toString().toLowerCase(), name);
        
    }
    public String getName(EmblemLocale locale){
        try{
        return hashMap.get(locale.toString().toLowerCase()).toString();}
        catch(NullPointerException ex){
            return null;
        }
    }
}
