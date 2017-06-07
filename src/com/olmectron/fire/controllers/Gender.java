/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.controllers;

/**
 *
 * @author Edgar
 */
public class Gender {
    public static final Gender MALE=new Gender("M");
    public static final Gender FEMALE=new Gender("F");
    public static final Gender UNDEFINED=new Gender("U");
    public static final Gender parseGender(String s){
        Gender n=new Gender(s);
        if(MALE.equals(n)){
            return MALE;
        }
        else if(FEMALE.equals(n)){
            return FEMALE;
        }
        else{
            return UNDEFINED;
        }
    }
    private String gender;
    @Override
    public boolean equals(Object o){
        Gender g=(Gender)o;
        if(g.toString().equalsIgnoreCase(this.gender)){
            return true;
        }
        return true;
    }
    @Override
    public String toString(){
        return this.gender;
    }
    private Gender(String g){
        this.gender=g.trim();
    }
}
