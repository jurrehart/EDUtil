/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil.plugin.edsc.model;

import java.util.ArrayList;

/**
 *
 * @author jhart
 */

/**
 * {
 * "d": {
 *  "ver":2.0,
 *  "date":"2015-04-24 14:35:04",
 *  "status":{
 *              "input":[{"status":{"statusnum":0,"msg":"Success"}}]},
 *  "systems":[]
 *  }
 * }
 * 
 * @author jhart
 */
public class EDSCResponseData {

    private int ver;
    private String date;
    
    private ArrayList<EDSCSystemData> systems;

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<EDSCSystemData> getSystems() {
        return systems;
    }

    public void setSystems(ArrayList<EDSCSystemData> systems) {
        this.systems = systems;
    }
    
    
}
