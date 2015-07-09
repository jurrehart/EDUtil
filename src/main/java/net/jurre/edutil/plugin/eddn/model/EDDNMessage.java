/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil.plugin.eddn.model;

/**
 *
 * @author jurre
 */
public class EDDNMessage {
    private int buyPrice;
    private String timestamp;
    private int stationStock;
    private String systemName;
    private String stationName;
    private int demand;
    private String demandLevel;
    private String itemName;
    private int sellPrice;

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStationStock() {
        return stationStock;
    }

    public void setStationStock(int stationStock) {
        this.stationStock = stationStock;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public String getDemandLevel() {
        return demandLevel;
    }

    public void setDemandLevel(String demandLevel) {
        this.demandLevel = demandLevel;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getSellprice() {
        return sellPrice;
    }

    public void setSellprice(int sellprice) {
        this.sellPrice = sellprice;
    }
    
    
}
