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
    private static final String COMMODITIES_COLLECTION = "Commodities";
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
    
    private void checkDB(){
        DBCollection stationColl = this.db.getCollection(STATIONS_COLLECTION);
        stationColl.createIndex("name");
        stationColl.createIndex("system_id");
        stationColl.createIndex( new BasicDBObject( "name", "text" ));
        
        DBCollection systemsColl = this.db.getCollection(SYSTEMS_COLLECTION);
        systemsColl.createIndex("name");
        systemsColl.createIndex( new BasicDBObject( "name", "text" ));
        
    }           
    
    private boolean updateStationMarketData(String stationName, String systemName, Object marketData){
        logger.debug("updated market data for " + stationName + " in " + systemName);
        DBCollection stations = this.db.getCollection(STATIONS_COLLECTION);
        BasicDBObject filter = new BasicDBObject();
        filter.put("system_id", getSystemID(systemName));
        filter.append("name", stationName);
        
        DBObject station = stations.findOne(filter);
        
        if ( station != null ){
            station.put("commodities", marketData);           
            stations.save(station);
            logger.info("updated market info for Station : " + stationName + " in System: " + systemName);
            return true;
        }
        else{
            logger.error("Unknown station " + stationName + " in " + systemName);
            // look up possible alternatives , for example fulltext search of stations in system high scorer
        }
        return false;
    }
        
    private int getSystemID(String systemName){
        DBCollection systems = this.db.getCollection(SYSTEMS_COLLECTION);
        int systemID = -1;
        
        BasicDBObject system = (BasicDBObject)systems.findOne(new BasicDBObject("name", systemName));
        if (system != null ) systemID = system.getInt("id");
        
        return systemID;
    }
    
    public void saveEDDNData(String data){
        BasicDBObject dataObj = (BasicDBObject)JSON.parse(data);
        
        String schema = dataObj.getString("$schemaRef");
        dataObj.removeField("$schemaRef");
        dataObj.append("schemaRef",schema);
        
        if ( dataObj.getString("schemaRef").equalsIgnoreCase("http://schemas.elite-markets.net/eddn/commodity/2")){
            BasicDBObject message = (BasicDBObject)dataObj.get("message");
            boolean stationupdated = updateStationMarketData( message.getString("stationName"), message.getString("systemName"), message.get("commodities"));
            if ( stationupdated ) dataObj.append("stationUpdated", true);
        }
        
        DBCollection collection = this.db.getCollection(MongoDB.EDDN_COLLECTION);
        collection.insert(dataObj);
    }
    
    private void saveData(String data, String collectionName){
        BasicDBObject dataObj = (BasicDBObject)JSON.parse(data);
        DBCollection collection = this.db.getCollection(collectionName);
        
        
        if ( ! dataObj.containsField("_id")){
            if ( dataObj.containsField("id"))
                dataObj.append("_id", dataObj.get("id"));
        }
        /*
        BasicDBObject search = new BasicDBObject();
        search.put("name", dataObj.getString("name"));
        DBObject lookup = collection.findOne(search);
        
        if ( lookup != null){
            dataObj.append("_id", lookup.get("_id"));
            logger.debug("Found id adding to object");
        }
        */
        collection.save(dataObj);
    }
    
    public void saveStation(String data){
       this.saveData(data, MongoDB.STATIONS_COLLECTION);
    }
    
    public void saveSystem(String data){
        this.saveData(data, MongoDB.SYSTEMS_COLLECTION);
    }
    
    public void saveCommodity(String data){
        this.saveData(data, MongoDB.COMMODITIES_COLLECTION);
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

