/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil.plugin.eddn.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author jurre
 */
public class EDDNData {
    private EDDNHeader header;
     @SerializedName("$schemaRef")private String schemaRef;
    private EDDNMessage message;
    
    private String hash;
    private String json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
    

    public EDDNHeader getHeader() {
        return header;
    }

    public void setHeader(EDDNHeader header) {
        this.header = header;
    }

    public String getSchemaRef() {
        return schemaRef;
    }

    public void setSchemaRef(String schemaRef) {
        this.schemaRef = schemaRef;
    }

    public EDDNMessage getMessage() {
        return message;
    }

    public void setMessage(EDDNMessage message) {
        this.message = message;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
    
}
