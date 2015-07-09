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

//http://www.mkyong.com/mongodb/java-mongodb-hello-world-example/
package net.jurre.edutil.persistence;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jhart
 */
public class MongoDB {
    static final Logger logger = LogManager.getLogger(MongoDB.class.getName());
    
    MongoClient mongo;
    DB db;
    
    public MongoDB(){
        try{
            Properties prop = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("mongodb.properties");
            prop.load(input);
            
            this.mongo = new MongoClient(prop.getProperty("host"));
            this.db = mongo.getDB(prop.getProperty("database"));
        }
        catch(IOException e){
            logger.fatal(e.getMessage());
        }    
        catch(MongoException me){
            logger.fatal(me.getMessage());
        }
        
    }
    
    public void saveEDDNData(String data){
        BasicDBObject dataObj = (BasicDBObject)JSON.parse(data);
        
        String schema = dataObj.getString("$schemaRef");
        dataObj.removeField("$schemaRef");
        dataObj.append("schemaRef",schema);
        
        DBCollection collection = this.db.getCollection("EDDN");
        collection.insert(dataObj);
    }
    
    public void saveStation(String data){
        BasicDBObject dataObj = (BasicDBObject)JSON.parse(data);
        DBCollection collection = this.db.getCollection("Stations");
        collection.save(dataObj);
    }
    
    public void saveSystem(String data){
        BasicDBObject dataObj = (BasicDBObject)JSON.parse(data);
        DBCollection collection = this.db.getCollection("Systems");
        collection.save(dataObj);
    }
    
    public void saveCommodity(String data){
        BasicDBObject dataObj = (BasicDBObject)JSON.parse(data);
        DBCollection collection = this.db.getCollection("Comodities");
        collection.save(dataObj);
    }
}

