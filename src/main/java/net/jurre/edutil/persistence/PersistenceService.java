/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import net.jurre.edutil.model.Category;
import net.jurre.edutil.model.Commodity;
import net.jurre.edutil.model.MarketEntry;
import net.jurre.edutil.model.Station;
import net.jurre.edutil.model.SystemData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.jurre.edutil.plugin.eddn.model.*;
/**
 *
 * @author jhart
 */
public class PersistenceService {
    static final Logger logger = LogManager.getLogger(PersistenceService.class.getName());
    
    private Connection db;
    
    private PersistenceService() {
        initBackend();
    }
    
    public static PersistenceService getInstance() {
        return PersistenceServciceHolder.INSTANCE;
    }
    
    private static class PersistenceServciceHolder {

        private static final PersistenceService INSTANCE = new PersistenceService();
    }
    
    private void initBackend(){
        boolean DBpresent = false;
        
        
        try {
            Properties prop = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("persistency.properties");
            prop.load(input);
            
            Class.forName(prop.getProperty("JDBCDriver"));
            db = DriverManager.getConnection(prop.getProperty("JDBCBase") +"://"+prop.getProperty("host")+"/"+prop.getProperty("database"),prop.getProperty("username"), prop.getProperty("password"));
            
        } catch (ClassNotFoundException e) {
            logger.fatal("Failed to load HSQLDB JDBC driver.");
        }
        catch ( SQLException e){
            logger.fatal("Error While trying to connect to database: " + e.getMessage());
        } catch (IOException ex) {
            logger.fatal("IOException encoutnered : " + ex.getLocalizedMessage());
        }
        
        try{
            
            DatabaseMetaData metadata = db.getMetaData();
            ResultSet resultSet;
            resultSet = metadata.getTables(null, null, "configs", null);
            if(!resultSet.next()){
                //Table does not exist
                logger.debug("Configs table missing thus creating table");
                String SQL = "CREATE TABLE CONFIGS (parameter VARCHAR(20), value VARCHAR(50))";
                Statement stmt = db.createStatement();
                stmt.execute(SQL);
                stmt.execute("INSERT INTO CONFIGS (parameter,value) VALUES ('dbversion','1.0')");
            }
                

            resultSet = metadata.getColumns(null, null, "system", null);
            if(!resultSet.next()){
                //Missing systems table
                logger.debug("system table missing thus creating table");
                String SQL = "CREATE TABLE system (" +
                        "id INTEGER,"+
                        "name VARCHAR(50),"+
                        "x DECIMAL(10,5),"+
                        "y DECIMAL(10,5),"+
                        "z DECIMAL(10,5),"+
                        "faction VARCHAR(100),"+
                        "population BIGINT,"+
                        "government VARCHAR(50),"+
                        "allegiance VARCHAR(50),"+
                        "state VARCHAR(20),"+
                        "security VARCHAR(20),"+
                        "primary_economy VARCHAR(20),"+
                        "eddb_update INTEGER,"+
                        "needspermit INTEGER)";
                Statement stmt = db.createStatement();
                stmt.execute(SQL);
            }
             
            resultSet = metadata.getColumns(null, null, "station", null);
            if(!resultSet.next()){
                //Missing systems table
                logger.debug("stations table missing thus creating table");
                String SQL = "CREATE TABLE station (" +
                        "id INTEGER,"+
                        "name VARCHAR(50),"+
                        "ls DECIMAL(10,2),"+
                        "eddn_update VARCHAR(20),"+
                        "local_update VARCHAR(20),"+
                        "refuel BOOLEAN DEFAULT FALSE,"+
                        "rearm BOOLEAN DEFAULT FALSE,"+
                        "outfit BOOLEAN DEFAULT FALSE,"+
                        "shipyard BOOLEAN DEFAULT FALSE,"+
                        "repair BOOLEAN DEFAULT FALSE)";
                Statement stmt = db.createStatement();
                stmt.execute(SQL);
            }
            
            resultSet = metadata.getColumns(null, null, "eddndata", null);
            if(!resultSet.next()){
                //Missing systems table
                logger.debug("EDDN table missing thus creating table");
                String SQL = "CREATE TABLE eddndata (" +
                        "buyPrice INT,"+
                        "timestamp VARCHAR(30),"+
                        "stationStock INT,"+
                        "systemName VARCHAR(50),"+
                        "stationName VARCHAR(50),"+
                        "demand INT,"+
                        "demandLevel VARCHAR(10),"+
                        "itemName VARCHAR(30),"+
                        "sellPrice INT,"+
                        "eddn_hash VARCHAR(50),"+
                        "imported BOOLEAN,"+
                        "eddn_source TEXT)";
                Statement stmt = db.createStatement();
                stmt.execute(SQL);
            }
            
            resultSet = metadata.getColumns(null, null, "commodity", null);
            if(!resultSet.next()){
                //Missing systems table
                logger.debug("Commodity table missing thus creating table");
                String SQL = "CREATE TABLE commodity (" +
                        "id INT,"+
                        "name VARCHAR(30),"+
                        "category_id INT,"+
                        "average_price int,"+
                        "max_price INT,"+
                        "min_price INT)";
                Statement stmt = db.createStatement();
                stmt.execute(SQL);
            }
            
            resultSet = metadata.getColumns(null, null, "category", null);
            if(!resultSet.next()){
                //Missing systems table
                logger.debug("Categories table missing thus creating table");
                String SQL = "CREATE TABLE category (" +
                        "id INT,"+
                        "name VARCHAR(30))";
                Statement stmt = db.createStatement();
                stmt.execute(SQL);
            }
            
        //Check Correct state DB
        PreparedStatement dbversion = db.prepareStatement("SELECT value FROM CONFIGS WHERE parameter=?");
        dbversion.setString(1, "dbversion");
        ResultSet rs = dbversion.executeQuery();
        if (rs.next()) logger.info("EDUtil DB Version :" + rs.getString("value"));
        }
        catch (SQLException e){
            logger.error("Checkdb error occured");
            logger.error(e.getMessage());
        }
    }
    
    public void saveMarketEntry(MarketEntry data){
        
    }
    
    
    public boolean saveEDDNData(EDDNData data){
        try{
            PreparedStatement stmt = db.prepareStatement("INSERT INTO eddndata (buyPrice,timestamp,stationStock,systemName,stationName,demand,demandLevel,itemName,sellPrice,eddn_hash,imported,eddn_source) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            EDDNMessage msg = data.getMessage();
            
            stmt.setInt(1, msg.getBuyPrice());
            stmt.setString(2, msg.getTimestamp());
            stmt.setInt(3, msg.getStationStock());
            stmt.setString(4, msg.getSystemName());
            stmt.setString(5, msg.getStationName());
            stmt.setInt(6, msg.getDemand());
            stmt.setString(7, msg.getDemandLevel());
            stmt.setString(8, msg.getItemName());
            stmt.setInt(9, msg.getSellprice());
            stmt.setString(10, data.getHash());
            stmt.setBoolean(11, false);
            stmt.setString(12, data.getJson());
            
            stmt.execute();
        }
        catch (SQLException e){
            logger.error("Error saving eddn data for station " + data.getMessage().getStationName() + " : " + e.getLocalizedMessage() );
            return false;
        }
        return true;
    }
    public boolean commodityExists(Commodity com){
        boolean exists = false;
        try{
        PreparedStatement dbversion = db.prepareStatement("SELECT name FROM commodity WHERE id=?");
        dbversion.setInt(1, com.getId());
        ResultSet rs = dbversion.executeQuery();
        if (rs.next()) {
            //logger.debug("Lookup success for commodity :" + rs.getString("name"));
            exists = true;
        }
        }
        catch (SQLException e){
            logger.error("Commodity lookup error occured");
            logger.error(e.getMessage());
        }
        return exists;
    }
    public boolean saveCommodity(Commodity com){
        try{
            PreparedStatement stmt;
        
            if ( commodityExists(com) ){
                stmt = db.prepareStatement("UPDATE commodity SET name=?, category_id=?, average_price=?, max_price=?, min_price=? WHERE id=?");
            }
            else{
                stmt = db.prepareStatement("INSERT INTO commodity (name, category_id, average_price, max_price, min_price, id) VALUES (?, ?, ?, ?, ?, ?)");
            }
                    
            stmt.setString(1, com.getName());
            stmt.setInt(2, com.getCategoryID());
            stmt.setInt(3, com.getAveragePrice());
            stmt.setInt(4, com.getMaxPrize());
            stmt.setInt(5, com.getMinPrice());
            stmt.setInt(6, com.getId());
            stmt.execute();
            
            saveCategory(com.getCategory());
        }
        catch (SQLException e){
            logger.error("Error saving Commodity data for " + com.getName() + " : " + e.getLocalizedMessage() );
            return false;
        }
        return true;    
    }
    
    public boolean categoryExists(Category cat){
        boolean exists = false;
        try{
        PreparedStatement dbversion = db.prepareStatement("SELECT name FROM category WHERE id=?");
        dbversion.setInt(1, cat.getId());
        ResultSet rs = dbversion.executeQuery();
        if (rs.next()) {
            //logger.debug("Lookup success for category :" + rs.getString("name"));
            exists = true;
        }
        }
        catch (SQLException e){
            logger.error("Category lookup error occured");
            logger.error(e.getMessage());
        }
        return exists;
    }
    public boolean saveCategory(Category cat){
        try{
            PreparedStatement stmt;
        
            if ( categoryExists(cat) ){
                stmt = db.prepareStatement("UPDATE category SET name=? WHERE id=?");
            }
            else{
                stmt = db.prepareStatement("INSERT INTO category (name, id) VALUES (?, ?)");
            }
                    
            stmt.setString(1, cat.getName());
            stmt.setInt(2, cat.getId());
            
            stmt.execute();
        }
        catch (SQLException e){
            logger.error("Error saving Category data for " + cat.getName() + " : " + e.getLocalizedMessage() );
            return false;
        }
        return true;    
    }
    
    public boolean systemExists(SystemData system){
        boolean exists = false;
        try{
        PreparedStatement dbversion = db.prepareStatement("SELECT name FROM system WHERE id=?");
        dbversion.setInt(1, system.getId());
        ResultSet rs = dbversion.executeQuery();
        if (rs.next()) {
            logger.debug("Lookup success for system :" + rs.getString("name"));
            exists = true;
        }
        }
        catch (SQLException e){
            logger.error("system lookup error occured :" + e.getMessage());
        }
        return exists;
    }
    
    public boolean saveSystem(SystemData system){
        try{
            PreparedStatement stmt;
        
            if ( systemExists(system) ){
                stmt = db.prepareStatement("UPDATE system SET name=?, x=?, y=?, z=?, faction=?, population=?, government=?, allegiance=?, state=?, security=?, primary_economy=?, eddb_update=?, needspermit=? INTEGER WHERE id=?");
            }
            else{
                stmt = db.prepareStatement("INSERT INTO system (name, x, y, z, faction, population, government, allegiance, state, security, primary_economy, eddb_update, needspermit, id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            }
                    
            stmt.setString(1, system.getName());
            stmt.setDouble(2, system.getX());
            stmt.setDouble(3, system.getY());
            stmt.setDouble(4, system.getZ());
            stmt.setString(5, system.getFaction());
            stmt.setLong(6, system.getPopulation());
            stmt.setString(7, system.getGovernment());
            stmt.setString(8, system.getAllegiance());
            stmt.setString(9, system.getState());
            stmt.setString(10, system.getSecurity());
            stmt.setString(11, system.getPrimaryEconomy());
            stmt.setInt(12, system.getLastupdateEDDN());
            stmt.setInt(13, system.getNeedsPermit());
            stmt.setInt(14, system.getId());
            
            stmt.execute();
        }
        catch (SQLException e){
            logger.error("Error saving System data for " + system.getName() + " : " + e.getMessage() );
            return false;
        }
        return true;    
    }
    
    public boolean stationExists(Station station){
        boolean exists = false;
        try{
            PreparedStatement dbversion = db.prepareStatement("SELECT name FROM station WHERE id=?");
            dbversion.setInt(1, station.getId());
            ResultSet rs = dbversion.executeQuery();
            if (rs.next()) {
                logger.debug("Lookup success for station :" + rs.getString("name"));
                exists = true;
            }
        }
        catch (SQLException e){
            logger.error("station lookup error occured :" + e.getMessage());
        }
        return exists;
    }
    
    
    // ADD missing data
    public boolean saveStation(Station station){
        try{
            PreparedStatement stmt;
        
            if ( stationExists(station) ){
                stmt = db.prepareStatement("UPDATE station SET name=?, ls=? WHERE id=?");
            }
            else{
                stmt = db.prepareStatement("INSERT INTO station (name, ls, id) VALUES (?, ?, ?)");
            }
                    
            stmt.setString(1, station.getName());
            stmt.setInt(2, station.getStarDistance());
            stmt.setInt(3, station.getId());
            logger.debug("Saving station " + station.getName());
            stmt.execute();
        }
        catch (SQLException e){
            logger.error("Error saving station data for " + station.getName() + " : " + e.getMessage() );
            return false;
        }
        return true;    
    }
    
}
