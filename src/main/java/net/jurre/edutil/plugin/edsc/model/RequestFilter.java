/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil.plugin.edsc.model;

/**
 *
 * @author jhart
 */
public class RequestFilter {
/*    nownstatus:0,
 *              systemname: "sol",
 *              cr:5,
 *              date:"2014-09-18 12:34:56",
 *              coordcube: [[-10,10],[-10,10],[-10,10]],
 *              coordsphere: {radius: 123.45, origin: [10,20,30]}
 */
    private int knownstatus; //return based on know status 0=All 1=Only System with Coords known 2=Only systems without Coords
    private String systemname; //filter to name sonly containing this string
    private int cr;//minimum amount of data confirmations in DB for data being returned
    private String date; // only data with updated timestamp equal or newer than this
    private int[][] coordcube;
    private CoordSphere coordsphere;

    public int getKnownStatus() {
        return knownstatus;
    }

    public void setKnownStatus(int knownStatus) {
        this.knownstatus = knownStatus;
    }

    public String getSystemname() {
        return systemname;
    }

    public void setSystemname(String systemname) {
        this.systemname = systemname;
    }

    public int getCr() {
        return cr;
    }

    public void setCr(int cr) {
        this.cr = cr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int[][] getCoordcube() {
        return coordcube;
    }

    public void setCoordcube(int[][] coordcube) {
        this.coordcube = coordcube;
    }

    public CoordSphere getCoordsphere() {
        return coordsphere;
    }

    public void setCoordsphere(CoordSphere coordsphere) {
        this.coordsphere = coordsphere;
    }
    
    
}
