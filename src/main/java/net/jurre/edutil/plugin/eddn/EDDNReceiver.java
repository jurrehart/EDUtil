/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil.plugin.eddn;

import com.google.gson.Gson;
import java.util.zip.Inflater;
import net.jurre.edutil.persistence.PersistenceService;
import net.jurre.edutil.plugin.eddn.model.EDDNData;
import net.jurre.edutil.plugin.eddn.model.EDDNMessage;
import net.jurre.edutil.model.SystemData;
import net.jurre.edutil.persistence.MongoDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zeromq.ZMQ;

/**
 *
 * @author jurre
 */
public class EDDNReceiver implements Runnable{
     static final Logger logger = LogManager.getLogger(EDDNReceiver.class.getName());
     private PersistenceService persistence;
     private Inflater inflater;
     private MongoDB mongo;
     
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //this.persistence = PersistenceService.getInstance();
        this.mongo = new MongoDB();
        
        // Prepare our context and subscriber
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
                
        subscriber.setRcvHWM(1000);
        
        subscriber.connect("tcp://eddn-relay.elite-markets.net:9500");
        subscriber.subscribe("".getBytes(ZMQ.CHARSET));
        //subscriber.setTCPKeepAlive(1); //activate keep-alive
        //subscriber.setTCPKeepAliveInterval(600000); //set keep aive at 10min
        //subscriber.setReconnectIVL(900000);
        
        logger.info("Subscribed to EDDN");
        this.inflater = new Inflater();
        
        while(true){           
            try {
              // Receive compressed raw market data.
                    logger.debug("Waiting for data");
                    byte[] receivedData = subscriber.recv(0);
                    // We build a large enough buffer to contain the decompressed data.
                    byte[] decompressed = new byte[receivedData.length * 16];
                    // Decompress the raw market data.
                    this.inflater.setInput(receivedData);
                    int decompressedLength = inflater.inflate(decompressed);
                    //this.inflater.end();
                    inflater.reset();
                    byte[] output = new byte[decompressedLength];
                    System.arraycopy(decompressed, 0, output, 0, decompressedLength);
                    // Transform data into JSON strings.
                    String market_json = new String(output, "UTF-8");
                    logger.info(market_json);
                    logger.debug(subscriber.getBacklog() + " in backlog");
                   
                    Gson gson = new Gson();
                    EDDNData data = gson.fromJson(market_json, EDDNData.class);
                    data.setHash(market_json.hashCode()+"");
                    data.setJson(market_json);                    
                    this.mongo.saveEDDNData(market_json);
            }
            catch ( Exception e){
                e.printStackTrace();
            }
        }
        // never reached
    }
    
}
