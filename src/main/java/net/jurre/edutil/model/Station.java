/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author jhart
 */
public class Station {
    private int id;
    private String name;
    private int system_id;
    @SerializedName("max_landing_pad_size")private String maxlandingSize;
    @SerializedName("distance_to_star")private int starDistance;
    private String faction;
    private String government;
    private String allegiance;
    private String state;
    private String type;
    private int has_blackmarket;
    private int has_commodities;
    private int has_refuel;
    private int has_repair;
    private int has_rearm;
    private int has_outfitting;
    private int has_shipyard;
    private List<String> import_commodities;
    private List<String> export_commodities;
    private List<String> prohibited_commodities;
    private List<String> economies;
    private int updated_at;

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

    public int getSystem_id() {
        return system_id;
    }

    public void setSystem_id(int system_id) {
        this.system_id = system_id;
    }

    public String getMaxlandingSize() {
        return maxlandingSize;
    }

    public void setMaxlandingSize(String maxlandingSize) {
        this.maxlandingSize = maxlandingSize;
    }

    public int getStarDistance() {
        return starDistance;
    }

    public void setStarDistance(int starDistance) {
        this.starDistance = starDistance;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHas_blackmarket() {
        return has_blackmarket;
    }

    public void setHas_blackmarket(int has_blackmarket) {
        this.has_blackmarket = has_blackmarket;
    }

    public int getHas_commodities() {
        return has_commodities;
    }

    public void setHas_commodities(int has_commodities) {
        this.has_commodities = has_commodities;
    }

    public int getHas_refuel() {
        return has_refuel;
    }

    public void setHas_refuel(int has_refuel) {
        this.has_refuel = has_refuel;
    }

    public int getHas_repair() {
        return has_repair;
    }

    public void setHas_repair(int has_repair) {
        this.has_repair = has_repair;
    }

    public int getHas_rearm() {
        return has_rearm;
    }

    public void setHas_rearm(int has_rearm) {
        this.has_rearm = has_rearm;
    }

    public int getHas_outfitting() {
        return has_outfitting;
    }

    public void setHas_outfitting(int has_outfitting) {
        this.has_outfitting = has_outfitting;
    }

    public int getHas_shipyard() {
        return has_shipyard;
    }

    public void setHas_shipyard(int has_shipyard) {
        this.has_shipyard = has_shipyard;
    }

    public List<String> getImport_commodities() {
        return import_commodities;
    }

    public void setImport_commodities(List<String> import_commodities) {
        this.import_commodities = import_commodities;
    }

    public List<String> getExport_commodities() {
        return export_commodities;
    }

    public void setExport_commodities(List<String> export_commodities) {
        this.export_commodities = export_commodities;
    }

    public List<String> getProhibited_commodities() {
        return prohibited_commodities;
    }

    public void setProhibited_commodities(List<String> prohibited_commodities) {
        this.prohibited_commodities = prohibited_commodities;
    }

    public List<String> getEconomies() {
        return economies;
    }

    public void setEconomies(List<String> economies) {
        this.economies = economies;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }
    
    
}
