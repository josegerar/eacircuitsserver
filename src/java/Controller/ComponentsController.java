/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ComponentDAO;
import DataStaticBD.FileAccess;
import models.Component;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author tonyp
 * Respective validations of the components.
 */
public class ComponentsController {

    private ComponentDAO cDao;

    public ComponentsController() {
        cDao = new ComponentDAO();
    }

    /**
     * simple method we use in classes to show (get)
     * @param id_user  User identifier.
     * @return a string vector containing the check.
     */
    public String[] getComponentsActive(String id_user) {
        if (!id_user.equals("")) {
            String resp = cDao.getComponentesActive(id_user);
            if (!resp.equals("[]")) {
                return new String[]{"2", "information found.", resp};
            } else {
                return new String[]{"3", "Data not found.", "[]"};
            }
        } else {
            return new String[]{"3", "Error int he params.", "[]"};
        }
    }

    /** simple method we use in classes to show (get)
     * @param id_user  User identifier.
     * @return a string vector containing the check.
     */
    public String[] getComponentsInactive(String id_user) {
        if (!id_user.equals("")) {
            String resp = cDao.getComponentsInactive(id_user);
            if (!resp.equals("[]")) {
                return new String[]{"2", "information found.", resp};
            } else {
                return new String[]{"3", "Data not found.", "[]"};
            }
        } else {
            return new String[]{"3", "Error int he params.", "[]"};
        }
    }
    /** simple method we use in classes to show (get)
     * @return a string vector containing the check.
     */
    public String[] getComponentsPropuesta() {
        String resp = cDao.getComponentsPropuesta();
        if (!resp.equals("[]")) {
            return new String[]{"2", "information found.", resp};
        } else {
            return new String[]{"3", "Data not found.", "[]"};
        }
    }
    /** simple method we use in classes to show (get)
     * @param ruta Contains the component's path 
     * @return a string vector containing the check.
     */
    public String[] getComponents(String ruta) {
        String resp = cDao.getComponents(ruta);
        if (!resp.equals("[]")) {
            return new String[]{"2", "information found.", resp};
        } else {
            return new String[]{"4", "Data not found.", "[]"};
        }
    }
    /** It contains all the components.
     * simple method we use in classes to show (get)
     * @return a string vector containing the check.
     */
    public String[] getComponentsTotal() {
        String resp = cDao.getComponentsTotal();
        if (!resp.equals("[]")) {
            return new String[]{"2", "information found.", resp};
        } else {
            return new String[]{"4", "Data not found.", "[]"};
        }
    }
    /** This method is used to save a component.
     * @param nameComponent String type variable, contains the name of the component.
     * @param descriptionComponennt String type variable, contains the component description.
     * @param status String type variable, contains the state of the component.
     * @param users_id_user  String type variable, contains the identifier of the user linked to the component.
     * @param pathimg_component String type variable, contains the dynamics of the component.
     * @param dataParamPorts  String type variable, contains the information of the parameters of the ports of each component.
     * @param ruta Contains the component's path 
     * @return  a string vector containing the check.
     */
    public String[] saveComponent(
            String nameComponent, String descriptionComponennt,
            String status, String users_id_user,
            String pathimg_component, String dataParamPorts,
            String ruta) {
        if (!nameComponent.equals("") && !descriptionComponennt.equals("")
                && !status.equals("") && !users_id_user.equals("")
                && !pathimg_component.equals("") && !dataParamPorts.equals("")
                && !ruta.equals("")) {
            Component comp = new Component();
            comp.setName_component(nameComponent);
            comp.setDescription_component(descriptionComponennt);
            comp.setActive_component(status);
            comp.setUsers_id_user(users_id_user);
            comp.setPathimg_component(pathimg_component);

            String filename = cDao.verifiUrl(RandomStringUtils.randomAlphanumeric(10)) +".txt";

            comp.setPathparamports(filename);

            String resp = cDao.saveComponent(comp);
            if (!resp.equals("") && !resp.equals("0")) {
                Thread th = new Thread(() -> {
                    FileAccess fa = new FileAccess();
                    fa.writeFileText(ruta + "\\" + filename, dataParamPorts);
                });
                System.out.println(ruta + "\\" + filename);
                th.start();
                return new String[]{"2", "The component was saved.", "[]"};
            } else {
                return new String[]{"4", "Data not found.", "[]"};
            }
        } else {
            return new String[]{"3", "Error int he params.", "[]"};
        }
    }
    /** Method for approving a component.
     * @param idComponent String type variable, contains the component identifier.
     * @return a string vector containing the check.
     */
    public String[] aproveComponent(String idComponent) {
        if (!idComponent.equals("")) {
            if (cDao.aproveComponent(idComponent)) {
                return new String[]{"2", "information found.", "[]"};
            } else {
                return new String[]{"4", "Data not found.", "[]"};
            }
        } else {
            return new String[]{"3", "Error int he params.", "[]"};
        }
    }
    
}
