/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil.plugin.eddb;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.mongodb.util.JSON;
import java.io.IOException;
import java.io.InputStreamReader;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jurre.edutil.persistence.PersistenceService;

import net.jurre.edutil.model.Commodity;
import net.jurre.edutil.model.Station;
import net.jurre.edutil.model.SystemData;
import net.jurre.edutil.persistence.MongoDB;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author jurre
 */
public class EDDBImport {
//http://eddb.io/archive/v3/stations_lite.json
//http://eddb.io/archive/v3/systems.json
//http://eddb.io/archive/v3/commodities.json
    static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(EDDBImport.class.getName());
    
    //private final PersistenceService persistence;
    private MongoDB mongo;
    
    public EDDBImport(){
        //this.persistence = PersistenceService.getInstance();
        this.mongo = new MongoDB();
        
        // ad check since last import if < 24 hours skipp all and close
        this.importCommodities();
        this.importSystems();
        this.importStations();
        
        
    }
    
    private void importCommodities(){
        logger.debug("Importing EDDB Commodities");
        Gson gson = new Gson();
        Type commodityList = new TypeToken<Collection<Commodity>>() {}.getType();
        try{
            URL url = new URL("http://eddb.io/archive/v3/commodities.json");
            JsonReader jsonReader = new JsonReader(new InputStreamReader(url.openStream()));
            List<Commodity> commodities = gson.fromJson(jsonReader, commodityList);
            
            for( int i=0; i < commodities.size(); i++){
                //persistence.saveCommodity(commodities.get(i));
                mongo.saveCommodity(gson.toJson(commodities.get(i), Commodity.class));
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(EDDBImport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EDDBImport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    private void importSystems(){
        logger.debug("Importing EDDB systems");
        Gson gson = new Gson();
        Type systemList = new TypeToken<Collection<SystemData>>() {}.getType();
        try{
            URL url = new URL("http://eddb.io/archive/v3/systems.json");
            JsonReader jsonReader = new JsonReader(new InputStreamReader(url.openStream()));
            List<SystemData> systems = gson.fromJson(jsonReader, systemList);
            
            for( int i=0; i < systems.size(); i++){
                //persistence.saveSystem(systems.get(i));
                mongo.saveSystem(gson.toJson(systems.get(i), SystemData.class));
            }
        } catch (MalformedURLException ex) {
            logger.error("Can't load systems data " + ex.getMessage());
        } catch (IOException ex) {
            logger.error("Error reading system data " + ex.getMessage());
        }
        logger.debug("System Import ended");
    }
    
    private void importStations(){
        logger.debug("Importing EDDB stations");
        Gson gson = new Gson();
        Type stationList = new TypeToken<Collection<Station>>() {}.getType();
        try{
            URL url = new URL("http://eddb.io/archive/v3/stations_lite.json");
            JsonReader jsonReader = new JsonReader(new InputStreamReader(url.openStream()));
            List<Station> stations = gson.fromJson(jsonReader, stationList);
            
            for( int i=0; i < stations.size(); i++){
                //persistence.saveStation(stations.get(i));
                mongo.saveStation(gson.toJson(stations.get(i), Station.class));
            }
        } catch (MalformedURLException ex) {
            logger.error("Can't load station data " + ex.getMessage());
        } catch (IOException ex) {
            logger.error("Error reading station data " + ex.getMessage());
        }
        logger.debug("Stations Import ended");
    }
}
