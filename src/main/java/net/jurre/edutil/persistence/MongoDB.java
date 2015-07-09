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
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import net.jurre.edutil.model.PluginConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jhart
 */
public class MongoDB {
    static final Logger logger = LogManager.getLogger(MongoDB.class.getName());
    
    private MongoClient mongo;
    private DB db;
    
    private static final String CONFIG_COLLECTION = "Configs";
    private static final String SYSTEMS_COLLECTION = "Systems";
    private static final String STATIONS_COLLECTION = "Stations";
    private static final String COMODITIES_COLLECTION = "Comodities";
    private static final String EDDN_COLLECTION = "EDDN";
    
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
    
    private void saveData(String data, String collectionName){
        BasicDBObject dataObj = (BasicDBObject)JSON.parse(data);
        DBCollection collection = this.db.getCollection(collectionName);
        
        
        
        BasicDBObject search = new BasicDBObject();
        search.put("name", dataObj.getString("name"));
        DBObject lookup = collection.findOne(search);
        
        if ( lookup != null){
            dataObj.append("_id", lookup.get("_id"));
            logger.debug("Found id adding to object");
        }
        
        collection.save(dataObj);
    }
    
    public void saveStation(String data){
       this.saveData(data, MongoDB.STATIONS_COLLECTION);
    }
    
    public void saveSystem(String data){
        this.saveData(data, MongoDB.SYSTEMS_COLLECTION);
    }
    
    public void saveCommodity(String data){
        this.saveData(data, MongoDB.COMODITIES_COLLECTION);
    }
    
    public void savePluginConfig(PluginConfiguration config){
        BasicDBObject dataObj = new BasicDBObject();
        
        dataObj.put("docType", "PluginConfiguration");
        dataObj.put("pluginName", config.getPluginName());
        dataObj.put("parameters", config.getAllParameters());
        
        DBCollection collection = this.db.getCollection(MongoDB.CONFIG_COLLECTION);
        collection.save(dataObj);
    }
    
    public PluginConfiguration loadPluginConfiguration(String pluginName){
        DBCollection collection = this.db.getCollection(MongoDB.CONFIG_COLLECTION);
        
        BasicDBObject query = new BasicDBObject();
        query.put("pluginName", pluginName);
        query.put("docType", "PluginConfiguration");
        
        DBObject doc = collection.findOne(query);
        
        PluginConfiguration config = null;
        if ( doc != null ) {
            config = new PluginConfiguration((String)doc.get("pluginName"));
            config.appendParameters((HashMap)doc.get("parameters"));
        }
       
        return config;
    }
    
    public void clearCollection(String collectionName){
        DBCollection coll = this.db.getCollection(collectionName);
        coll.drop();
    }
}

