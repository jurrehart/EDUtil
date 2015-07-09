/*
 * http://edstarcoordinator.com/api.html   
 */
package net.jurre.edutil.plugin.edsc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.jurre.edutil.plugin.edsc.model.EDSCRequest;
import net.jurre.edutil.plugin.edsc.model.RequestData;
import net.jurre.edutil.plugin.edsc.model.RequestFilter;
import net.jurre.edutil.plugin.edsc.model.CoordSphere;
import net.jurre.edutil.plugin.edsc.model.EDSCResponseData;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author jhart
 */
public class EDSCPlugin {

   
    public void getSystemInfo(String systemName) throws Exception{
      HttpClient client;
        client = HttpClients.createDefault();
      HttpPost post = new HttpPost("http://edstarcoordinator.com/api.asmx/GetSystems");
      post.setHeader("Content-Type", "application/json; charset=utf-8");
      
      RequestData system = new RequestData(false);
      RequestFilter filter = new RequestFilter();
      filter.setSystemname(systemName);
      filter.setCr(5);
      filter.setDate("2015-01-01");
      //filter.setCoordsphere(new CoordSphere(50.0, new double[]{0,0,0}));
      system.setFilter(filter);
      system.setOutputMode(2);
      EDSCRequest edscRequest = new EDSCRequest();
      
      edscRequest.setData(system);
      
      Gson gson = new Gson();
      
      
      
      String requestData = gson.toJson(edscRequest);
      
      System.out.println("Request Body:");
      System.out.println(requestData);
      
      
      HttpEntity entity = new ByteArrayEntity(requestData.getBytes("UTF-8"));
      post.setEntity(entity);
      HttpResponse response = client.execute(post);
      String result = EntityUtils.toString(response.getEntity());
      
      System.out.println("Response is:");
      System.out.println(result);
      
      JsonParser parser = new JsonParser();
      JsonObject root = parser.parse(result).getAsJsonObject();
      
      EDSCResponseData edscData = gson.fromJson(root.get("d"), EDSCResponseData.class);
      
      //System.out.println("Version = " + edscData.getVer() + " REceived " + edscData.getSystems().size() + " Systems");
      
      for ( int i = 0; i < edscData.getSystems().size(); i++){
          System.out.println(" System : "+ edscData.getSystems().get(i).getName() + " Entered By: " + edscData.getSystems().get(i).getCommandercreate());
          
      }
      
    }
           
}
