/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil.model;

/**
 *
 * @author jhart
 */
public enum WareCategory {
    
    CHEMICALS ("Chemicals"),
    CONSUMER_ITEMS ("Consumer Items"),
    DRUGS ("Drugs"),
    FOODS ("Foods"),
    INDUSTRIAL_MATERIALS ("Industrial Materials"),
    MACHINERY ("Machinery"),
    MEDICINES ("Medicines"),
    METALS ("Metals"),
    MINERALS ("Minerals"),
    TECHNOLOGY ("Technology"),
    TEXTILES ("Textiles"),
    WASTE ("Waste"),
    SLAVERY ("Slavery"),
    WEAPONS ("Weapons"),
    RARES ("Rares");
    
    public final String name;
    
    WareCategory(String n){
        this.name = n;
    }

}
