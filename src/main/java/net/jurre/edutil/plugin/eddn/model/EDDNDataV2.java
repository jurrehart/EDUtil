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
package net.jurre.edutil.plugin.eddn.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author jhart
 */
public class EDDNDataV2{ 
    
    private EDDNHeader header;
    @SerializedName("$schemaRef")private String schemaRef;
    private EDDNMessageV2 message;
    
    private String hash;
    private String json;

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

    public EDDNMessageV2 getMessage() {
        return message;
    }

    public void setMessage(EDDNMessageV2 message) {
        this.message = message;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
    
    
}
