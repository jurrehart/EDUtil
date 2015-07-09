/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil.plugin.edsc.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author jhart
 */
public class RequestData {
    /**
 * var data = {
 *        data: {
 *          ver:2,
 *          test: true, 
 *          outputmode:1, 
 *          filter:{
 *              knownstatus:0,
 *              systemname: "sol",
 *              cr:5,
 *              date:"2014-09-18 12:34:56",
 *              coordcube: [[-10,10],[-10,10],[-10,10]],
 *              coordsphere: {radius: 123.45, origin: [10,20,30]}
 *          }
 *        }
 *      };
 */
    @SerializedName("ver")private int APIversion;
    @SerializedName("test")private boolean isTest;
    private int outputmode;
    private RequestFilter filter;

    public RequestData(boolean test){
        this.APIversion = 2;
        this.isTest = test;
        this.filter = null;
    }
    
    public int getAPIversion() {
        return APIversion;
    }

    public void setAPIversion(int APIversion) {
        this.APIversion = APIversion;
    }

    public boolean isIsTest() {
        return isTest;
    }

    public void setIsTest(boolean isTest) {
        this.isTest = isTest;
    }

    public int getOutputMode() {
        return outputmode;
    }

    public void setOutputMode(int outputMode) {
        this.outputmode = outputMode;
    }

    public RequestFilter getFilter() {
        return filter;
    }

    public void setFilter(RequestFilter filter) {
        this.filter = filter;
    }
    
    
    
}
