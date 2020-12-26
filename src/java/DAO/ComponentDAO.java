/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStaticBD.Conection;
import DataStaticBD.FileAccess;
import javax.swing.table.DefaultTableModel;
import models.Component;

/**
 *
 * @author tonyp
 * In this class the methods of querying, inserting, modifying and deleting components in the database will be performed.
 */
public class ComponentDAO {

    private Conection cnn;

    public ComponentDAO() {
        cnn = new Conection();
    }
    /** Get all the active components.
     * @param id_user String type variable, which contains the user ID.
     * @return a json with the data about the components.
     */
    public String getComponentesActive(String id_user) {
        String query = "SELECT id_com, name_component, description_component, active_component, email_user, pathimg_component, dateupload_component, pathparamports\n"
                + " FROM public.component as com inner join users as us on com.users_id_user=us.id_user"
                + " where com.users_id_user = " + id_user + " and com.active_component = 'A' order by com.id_com";

        DefaultTableModel dataResponseA = cnn.returnRecord(query);
        String dataAuxA = "[]";
        if (dataResponseA.getRowCount() > 0) {
            dataAuxA = "[";
            FileAccess fc = new FileAccess();
            for (int row = 0; row < dataResponseA.getRowCount(); row++) {
                dataAuxA += "{\"id_com\":\"" + dataResponseA.getValueAt(row, 0) + "\",\"name_component\":\"" + dataResponseA.getValueAt(row, 1) + "\","
                        + "\"description_component\":\"" + dataResponseA.getValueAt(row, 2) + "\",\"active_component\":\"" + dataResponseA.getValueAt(row, 3) + "\","
                        + "\"users_id_user\":\"" + dataResponseA.getValueAt(row, 4) + "\",\"pathimg_component\":\"" + dataResponseA.getValueAt(row, 5) + "\","
                        + "\"dateupload_component\":\"" + dataResponseA.getValueAt(row, 6) + "\",\"pathparamports\":\"" + dataResponseA.getValueAt(row, 7) + "\","
                        + "\"dataParamsPorts\":" + fc.readFileText(dataResponseA.getValueAt(row, 7).toString()) + "},";
            }
            dataAuxA += "]";
        }
        return dataAuxA.replace("},]", "}]");
    }
    /** Get the proposed components
     * @return a json with the data about the components.
     */
    public String getComponentsPropuesta() {
        String query = "select id_com, name_component, description_component, active_component, email_user, pathimg_component, dateupload_component, pathparamports "
                + "from component as c inner join users as u on c.users_id_user = u.id_user where c.active_component = 'I' order by c.id_com";

        DefaultTableModel dataResponseA = cnn.returnRecord(query);
        String dataAuxA = "[]";
        if (dataResponseA.getRowCount() > 0) {
            dataAuxA = "[";
            FileAccess fc = new FileAccess();
            for (int row = 0; row < dataResponseA.getRowCount(); row++) {
                dataAuxA += "{\"id_com\":\"" + dataResponseA.getValueAt(row, 0) + "\",\"name_component\":\"" + dataResponseA.getValueAt(row, 1) + "\","
                        + "\"description_component\":\"" + dataResponseA.getValueAt(row, 2) + "\",\"active_component\":\"" + dataResponseA.getValueAt(row, 3) + "\","
                        + "\"users_id_user\":\"" + dataResponseA.getValueAt(row, 4) + "\",\"pathimg_component\":\"" + dataResponseA.getValueAt(row, 5) + "\","
                        + "\"dateupload_component\":\"" + dataResponseA.getValueAt(row, 6) + "\",\"pathparamports\":\"" + dataResponseA.getValueAt(row, 7) + "\","
                        + "\"dataParamsPorts\":" + fc.readFileText(dataResponseA.getValueAt(row, 7).toString()) + "},";
            }
            dataAuxA += "]";
        }
        return dataAuxA.replace("},]", "}]");
    }
    /** Get the inactive components.
     * @param id_user String type variable, which contains the user ID.
     * @return a json with the data about the components.
     */
    public String getComponentsInactive(String id_user) {
        String query = "select id_com, name_component, description_component, active_component, email_user, pathimg_component, dateupload_component, pathparamports"
                + " from component as c inner join users as u on c.users_id_user = u.id_user where c.users_id_user = " + id_user + " and active_component = 'I' order by id_com";

        DefaultTableModel dataResponseA = cnn.returnRecord(query);
        String dataAuxA = "[]";
        if (dataResponseA.getRowCount() > 0) {
            dataAuxA = "[";
            FileAccess fc = new FileAccess();
            for (int row = 0; row < dataResponseA.getRowCount(); row++) {
                dataAuxA += "{\"id_com\":\"" + dataResponseA.getValueAt(row, 0) + "\",\"name_component\":\"" + dataResponseA.getValueAt(row, 1) + "\","
                        + "\"description_component\":\"" + dataResponseA.getValueAt(row, 2) + "\",\"active_component\":\"" + dataResponseA.getValueAt(row, 3) + "\","
                        + "\"users_id_user\":\"" + dataResponseA.getValueAt(row, 4) + "\",\"pathimg_component\":\"" + dataResponseA.getValueAt(row, 5) + "\","
                        + "\"dateupload_component\":\"" + dataResponseA.getValueAt(row, 6) + "\",\"pathparamports\":\"" + dataResponseA.getValueAt(row, 7) + "\","
                        + "\"dataParamsPorts\":" + fc.readFileText(dataResponseA.getValueAt(row, 7).toString()) + "},";
            }
            dataAuxA += "]";
        }
        return dataAuxA.replace("},]", "}]");
    }
    /** It gets the components and adds them to a file that already contains data.
     * @param ruta String type variable, contains the path of the components.
     * @return a json with the data about the components.
     */
    public String getComponents(String ruta) {
        String query = "select * from component order by id_com";
        DefaultTableModel dataResponse = cnn.returnRecord(query);
        String dataAux = "";
        if (dataResponse != null && dataResponse.getRowCount() > 0) {
            FileAccess fc = new FileAccess();
            dataAux += "[";
            for (int row = 0; row < dataResponse.getRowCount(); row++) {
//                System.out.println("ruta:"+(ruta + dataResponse.getValueAt(row, 7).toString()));
                dataAux += "{\"id_com\":\"" + dataResponse.getValueAt(row, 0) + "\",\"name_component\":\"" + dataResponse.getValueAt(row, 1) + "\","
                        + "\"description_component\":\"" + dataResponse.getValueAt(row, 2) + "\",\"active_component\":\"" + dataResponse.getValueAt(row, 3) + "\","
                        + "\"users_id_user\":\"" + dataResponse.getValueAt(row, 4) + "\",\"pathimg_component\":\"" + dataResponse.getValueAt(row, 5) + "\","
                        + "\"dateupload_component\":\"" + dataResponse.getValueAt(row, 6) + "\",\"pathparamports\":\"" + dataResponse.getValueAt(row, 7) + "\","
                        + "\"dataParamsPorts\":" + fc.readFileText(ruta + dataResponse.getValueAt(row, 7).toString()) + "},";
            }
            dataAux += "]";
            dataAux = dataAux.replace("},]", "}]");

        } else {
            dataAux = "[]";
        }
        return dataAux;
    }
    /** You get all the components.
     * @return a json with the data about the components.
     */
    public String getComponentsTotal() {
        String query = "select * from component";
        return cnn.getRecordsInJson(query);
    }
    /** Method for saving components.
     * @param comp Model variable of a component.
     * @return a json with the data about the components.
     */
    public String saveComponent(Component comp) {
        String query = String.format("select * from insertcomponent('%s')", comp.returnXmlForInsert());
        return cnn.fillString(query);
    }
    /** Method for approving a component.
     * @param idComponent String type variable, contains the component identifier.
     * @return Boolean that returns when the update is done.
     */
    public boolean aproveComponent(String idComponent) {
        String query = String.format("update component set active_component = 'A' where id_com = %s", idComponent);
        return (cnn.updateDB(query) == 1);
    }
    /** Method to verify the url.
     * @param url String type variable, contains the url.
     * @return It returns a string, which contains the verified url.
     */
    public String verifiUrl(String url) {
        DefaultTableModel table = cnn.returnRecord("SELECT pathparamports FROM public.component where pathparamports ilike '" + url + "%'");
        String result = "";
        if (table.getRowCount() > 0) {
            int i = 0, count = 0;
            for (i = 0; i < table.getRowCount(); i++) {
                if ((url + (count == 0 ? "" : count) + ".txt").equals(table.getValueAt(i, 0).toString())) {
                    count++;
                    i = 0;
                } else {
                    result = url + (count == 0 ? "" : count);
                }
            }
        } else {
            result = url;
        }
        return result;
    }
}
