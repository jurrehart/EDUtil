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
public enum Wares {
    GOLD ("Gold", 1000.00),
    SILVER ("Silver", 1000.00);
    
    private final String name;
    private final double averagePrice;
    
    Wares(String name, double price){
        this.name = name;
        this.averagePrice = price;
    }
}
