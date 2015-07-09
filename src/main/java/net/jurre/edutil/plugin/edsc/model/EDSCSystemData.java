/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jurre.edutil.plugin.edsc.model;
//{
//id: 222,
//name: "10 Canum Venaticorum",
//coord: [
//  -9.375,
//  55.4375,
//  -7
//],
//cr: 5,
//commandercreate: "",
//createdate: "2014-11-07 13:38:07",
//commanderupdate: "",
//updatedate: "2014-11-07 13:38:07"
//},
/**
 *
 * @author jhart
 */
public class EDSCSystemData {
    private int id;
    private String name;
    private double[] coord;
    private int cr;
    private String commandercreate;
    private String createdate;
    private String commanderupdate;
    private String updatedate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getCoord() {
        return coord;
    }

    public void setCoord(double[] coord) {
        this.coord = coord;
    }

    public int getCr() {
        return cr;
    }

    public void setCr(int cr) {
        this.cr = cr;
    }

    public String getCommandercreate() {
        return commandercreate;
    }

    public void setCommandercreate(String commandercreate) {
        this.commandercreate = commandercreate;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getCommanderupdate() {
        return commanderupdate;
    }

    public void setCommanderupdate(String commanderupdate) {
        this.commanderupdate = commanderupdate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }
    
    
}
