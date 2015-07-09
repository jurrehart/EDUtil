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
public class CoordSphere {
    private double radius;
    private double[] origin;

    public CoordSphere(double r, double[] o){
        this.radius = r;
        this.origin = o;
    }
    
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double[] getOrigin() {
        return origin;
    }

    public void setOrigin(double[] origin) {
        this.origin = origin;
    }
    
    
}
