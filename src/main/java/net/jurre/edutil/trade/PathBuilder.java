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
package net.jurre.edutil.trade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.jurre.edutil.trade.model.PathNode;
import net.jurre.edutil.trade.util.PathNodeComparator;

/**
 *
 * @author jhart
 * 
 * Build path from start to goal following A* algorithm
 * self written as training
 */
public class PathBuilder {
    private List<PathNode> toVisitList = new ArrayList();
    private List visited = new ArrayList();;
    
    private PathNode start;
    private PathNode goal;
    
    private float maxJumpDistance;
    
    public void findPath(){
        start.setPathCost(0f); //Start Pathcost is 0
        start.setEstimatePathCost(start.getPathCost() + start.distanceTo(goal)); // Estimate is current path cost (0) + Eucledian distance to goal.
        this.toVisitList.add(start);
        
        while ( toVisitList.size() > 0){
            //set current 
            Collections.sort(toVisitList, new PathNodeComparator()); //Sort toVisitList on Estimated path cost
            PathNode current = toVisitList.get(0); //get first node
            //check if current is goal if so break;
            
            //move current from toVisitList to visitedList;
        }
        
    }

}
