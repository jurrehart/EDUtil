/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.NavigableMap;

/**
 *
 * @author jhart
 */
public class SystemData {
    private int id;
    private String name;
    private double x;
    private double y;
    private double z;
    private String faction;
    private long population;
    private String government;
    private String allegiance;
    private String state;
    private String security;
    @SerializedName("primary_economy")private String primaryEconomy;
    @SerializedName("needs_permit")private int needsPermit;
    @SerializedName("updated_at")private int lastupdateEDDN;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getGovernment() {
        return government;
    }

    public void setGovernment(String government) {
        this.government = government;
    }

    public String getAllegiance() {
        return allegiance;
    }

    public void setAllegiance(String allegiance) {
        this.allegiance = allegiance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getPrimaryEconomy() {
        return primaryEconomy;
    }

    public void setPrimaryEconomy(String primaryEconomy) {
        this.primaryEconomy = primaryEconomy;
    }

    public int getNeedsPermit() {
        return needsPermit;
    }

    public void setNeedsPermit(int needsPermit) {
        this.needsPermit = needsPermit;
    }

    public int getLastupdateEDDN() {
        return lastupdateEDDN;
    }

    public void setLastupdateEDDN(int lastupdateEDDN) {
        this.lastupdateEDDN = lastupdateEDDN;
    }

    
}
