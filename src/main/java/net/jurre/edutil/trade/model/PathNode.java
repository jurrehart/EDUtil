/*
 * Copyright (C) 2015 jhart
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package net.jurre.edutil.trade.model;

/**
 *
 * @author jhart
 */
public class PathNode {
    private float pathCost;
    private float estimatePathCost;
    private PathNode previousInPath;
    private Object currentRef;
    
    private float x;
    private float y;
    private float z;

    public float getPathCost() {
        return pathCost;
    }

    public void setPathCost(float pathCost) {
        this.pathCost = pathCost;
    }

    public float getEstimatePathCost() {
        return estimatePathCost;
    }

    public void setEstimatePathCost(float estimatePathCost) {
        this.estimatePathCost = estimatePathCost;
    }

    public PathNode getPreviousInPath() {
        return previousInPath;
    }

    public void setPreviousInPath(PathNode previousInPath) {
        this.previousInPath = previousInPath;
    }

    public Object getCurrentRef() {
        return currentRef;
    }

    public void setCurrentRef(Object currentRef) {
        this.currentRef = currentRef;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
    
    public float distanceTo(PathNode node){
        //d = SRTQ ( (x2-x1)^2 + (y2-y1)^2 +(z2-z1)^2 )
        float retValue = 0f;
        
        float dx = this.x - node.getX();
        float dy = this.y - node.getY();
        float dz = this.z - node.getZ();
        
        retValue = (float)Math.sqrt(dx * dx + dy * dy + dz * dz);
        
        return retValue;
    }
}
