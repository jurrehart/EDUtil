/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil.tests;

import java.nio.ByteBuffer;
import java.util.zip.Inflater;
import org.zeromq.ZMQ;

/**
 *
 * @author jurre
 */
public class JeromqTest {

    public static void main(String[] args){
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
        
        subscriber.setRcvHWM(0);
        subscriber.connect("tcp://eddn-relay.elite-markets.net:9500");
        subscriber.subscribe("".getBytes());
        
        while(true){
        try {
                
          // Receive compressed raw market data.
                System.out.println("PRE recv");
                byte[] receivedData = subscriber.recv(0);

                System.out.println("recv done");
                // We build a large enough buffer to contain the decompressed data.
                byte[] decompressed = new byte[receivedData.length * 16];

                // Decompress the raw market data.
                Inflater inflater = new Inflater();
                inflater.setInput(receivedData);
                int decompressedLength = inflater.inflate(decompressed);
                inflater.end();

                byte[] output = new byte[decompressedLength];
                System.arraycopy(decompressed, 0, output, 0, decompressedLength);

                // Transform data into JSON strings.
                String market_json = new String(output, "UTF-8");

                System.out.println(market_json);
        }
        catch ( Exception e){
            e.printStackTrace();
        }
        }
    }
}
