/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil.plugin.eddn.model;

/**
 *
 * @author jurre
 */
public class EDDNHeader {
    private String softwareVersion;
    private String gatewayTimestamp;
    private String softwareName;
    private String uploaderID;

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getGatewayTimestamp() {
        return gatewayTimestamp;
    }

    public void setGatewayTimestamp(String gatewayTimestamp) {
        this.gatewayTimestamp = gatewayTimestamp;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getUploaderID() {
        return uploaderID;
    }

    public void setUploaderID(String uploaderID) {
        this.uploaderID = uploaderID;
    }
    
    
    
}


