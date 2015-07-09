/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil;

import net.jurre.edutil.GUI.MainGUI;
import net.jurre.edutil.persistence.PersistenceService;
import net.jurre.edutil.plugin.eddb.EDDBImport;
import net.jurre.edutil.plugin.eddn.EDDNReceiver;
import net.jurre.edutil.plugin.edsc.EDSCPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jhart
 */
public class EDUtil {
    static final Logger logger = LogManager.getLogger(EDUtil.class.getName());
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.setProperty("java.util.logging.manager","org.apache.logging.log4j.jul.LogManager");
       
       EDSCPlugin edsc = new EDSCPlugin();
       
        try{
            edsc.getSystemInfo(null);
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
       
       
       (new Thread(new EDDNReceiver(), "EDDN_Receiver")).start();
       EDDBImport eddb = new EDDBImport();
       
       //PersistenceService persistence = PersistenceService.getInstance();
       
      // MainGUI main = new MainGUI();
       //main.setVisible(true);
    }
    
}
