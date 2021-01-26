/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStaticBD;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tonyp * This java class contains the methods used within the back-end
 * of the application.
 */
public final class Methods {

    /**
     * Object to work json
     */
    public static final Gson gson = new Gson();

    public static String[] getDataToJwt(String jwt) {
        String[] response;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("digiclave")
                    .parseClaimsJws(jwt).getBody();
            response = new String[]{claims.get("user").toString(), claims.get("permit").toString()};
        } catch (Exception e) {
            System.out.println("error JWT: " + e.getMessage());
            response = new String[]{"", ""};
        }
        return response;
    }

    /**
     * This method is for the security application.
     *
     * @param request Processes HTTP type requests
     * @param param String type variable, contains the information obtained to
     * the method.
     * @param defaulx String type variable, return variable
     * @return a String, for the security request.
     */
    public static String securRequest(HttpServletRequest request, String param, String defaulx) {
        try {
            String res = request.getParameter(param);
            return res != null ? res : defaulx;
        } catch (Exception e) {
            return defaulx;
        }
    }

    /**
     * This method is for the security application.
     *
     * @param email String type variable, contains the email.
     * @return a String, for the security request.
     */
    public static Boolean comprobeEmail(String email) {
        Pattern pat = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");//".*@uteq.edu.ec"
        Matcher mat = pat.matcher(email);
        return mat.matches();
    }

    /**
     * Convert from string to json.
     *
     * @param json String type variable, contains the json to be converted.
     * @return a json.
     */
    public static JsonObject stringToJSON(String json) {
        try {
            JsonParser parser = new JsonParser();
            JsonObject Jso = parser.parse(json).getAsJsonObject();
            return Jso;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new JsonObject();
        }
    }

    /**
     * Convert from string to json.
     *
     * @param json String type variable, contains the json to be converted.
     * @return a json.
     */
    public static JsonElement stringToJSON2(String json) {
        try {
            JsonElement parser = new JsonPrimitive(json);
            System.out.println(parser.getAsString());
            //JsonObject Jso = new JsonObject();
            //Jso =  (JsonObject) parser.p(json);
            return parser;
        } catch (Exception e) {
            return new JsonObject();
        }
    }

    /**
     * Get a part of the json.
     *
     * @param jso Variable type json, contains the information.
     * @param param String type variable, contains the name of the json
     * parameter to be divided.
     * @return a json, divided.
     */
    public static JsonElement securGetJSON(JsonObject jso, String param) {
        try {
            JsonElement res = jso.get(param);//request.getParameter(param);
            return res;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method to divide a json.
     *
     * @param jso Variable type json, contains the information.
     * @param param String type variable, contains the name of the json
     * parameter to be divided.
     * @param defaulx String type variable, return variable
     * @return Return a String, with the json divided.
     */
    public static String JsonToSub(JsonObject jso, String param, String defaulx) {
        try {
            JsonElement res = securGetJSON(jso, param);
            if (res != null) {
                return res.toString();
            } else {
                return defaulx;
            }
        } catch (Exception e) {
            return defaulx;
        }
    }

    /**
     * A sub json of a json.
     *
     * @param jso Variable type json, contains the information.
     * @param param String type variable, contains the name of the json
     * parameter to be divided.
     * @return a json.
     */
    public static JsonObject JsonToSubJSON(JsonObject jso, String param) {
        try {
            JsonElement res = securGetJSON(jso, param);
            if (res != null) {
                return res.getAsJsonObject();
            } else {
                return new JsonObject();
            }
        } catch (Exception e) {
            return new JsonObject();
        }
    }

    /**
     * From json to array.
     *
     * @param jso Variable type json, contains the information.
     * @param param String type variable, contains the name of the json
     * parameter to be divided.
     * @return a jsonArray, with data loaded
     */
    public static JsonArray JsonToArray(JsonObject jso, String param) {
        try {
            JsonArray jarr = jso.get(param).getAsJsonArray();
            if (jarr != null) {
                return jarr;
            } else {
                return new JsonArray();
            }
        } catch (Exception e) {
//            System.out.println("erro json a string");
            return new JsonArray();
        }
    }

    /**
     * From json to String
     *
     * @param jso Variable type json, contains the information.
     * @param param String type variable, contains the name of the json
     * parameter to be divided.
     * @param defaulx String type variable, return variable
     * @return a String, with data loaded from the json.
     */
    public static String JsonToString(JsonObject jso, String param, String defaulx) {
        try {
            JsonElement res = securGetJSON(jso, param);
            if (res != null) {
                return res.getAsString();
            } else {
                return defaulx;
            }
        } catch (Exception e) {
//            System.out.println("erro json a string");
            return defaulx;
        }
    }

    /**
     * Obtain an element from a Json, and store it in a String variable.
     *
     * @param jse The variable type JsonElement, contains the information.
     * @param defaulx String type variable, contains the element of the selected
     * json.
     * @return a variable of type String, selected element of the json.
     */
    public static String JsonElementToString(JsonElement jse, String defaulx) {
        try {
            if (jse != null) {
                return jse.getAsString();
            } else {
                return defaulx;
            }
        } catch (Exception e) {
            return defaulx;
        }
    }

    /**
     * from JsonElement to json.
     *
     * @param jse Variable type jsonElement, contains an element of another
     * json.
     * @return an object-type json
     */
    public static JsonObject JsonElementToJSO(JsonElement jse) {
        try {
            if (jse != null) {
                return jse.getAsJsonObject();
            } else {
                return new JsonObject();
            }
        } catch (Exception e) {
            return new JsonObject();
        }
    }

    /**
     * from json to Integer.
     *
     * @param jso Variable type json, contains the information
     * @param param String type variable, contains the name of the json
     * parameter to be divided.
     * @param defaulx String type Integer, return variable
     * @return an integer, the variable is defaulx.
     */
    public static int JsonToInteger(JsonObject jso, String param, int defaulx) {
        try {
            JsonElement res = securGetJSON(jso, param);
            if (res != null) {
                return res.getAsInt();
            } else {
                return defaulx;
            }
        } catch (Exception e) {
            return defaulx;
        }
    }

    /**
     * from json to boolean
     *
     * @param jso Variable type json, contains the information
     * @param param String type variable, contains the name of the json
     * parameter to be divided.
     * @param defaulx String type Boolean, return variable
     * @return an Boolean, the variable is defaulx.
     */
    public static Boolean JsonToBoolean(JsonObject jso, String param, boolean defaulx) {
        try {
            JsonElement res = securGetJSON(jso, param);
            if (res != null) {
                return res.getAsBoolean();
            } else {
                return defaulx;
            }
        } catch (Exception e) {
            return defaulx;
        }
    }

    /**
     * From table to json.
     *
     * @param table Variable of type DefaultTableModel, table with loaded data
     * @return a String, contains a json with data.
     */
    public static String tableToJson(DefaultTableModel table) {
        String resul = "[";
        if (table.getRowCount() > 0) {
            int columCount = table.getColumnCount();
            for (int row = 0; row < table.getRowCount(); row++) {
                String line = "";
                for (int colum = 0; colum < columCount; colum++) {
                    line += "\"" + table.getColumnName(colum) + "\":\"" + table.getValueAt(row, colum).toString() + "\"";
                    if (colum < columCount - 1) {
                        line += ",";
                    }
                }
                if (line.length() > 0) {
                    resul += "{" + line + "}";
                    if (row < table.getRowCount() - 1) {
                        resul += ",";
                    }
                }
            }
            resul += "]";
        } else {
            resul = "[]";
        }
        return resul;
    }

    /**
     * Convert from a table to an html5 table
     *
     * @param table Variable of type DefaultTableModel, table with loaded data
     * @return a String, with an html5 table with data.
     */
    public static String tableToHtmlTable(DefaultTableModel table) {
        String resul = "<table>";
        if (table != null) {
            int columCount = table.getColumnCount();
            resul += "<thead><tr>";
            for (int fol = 0; fol < table.getColumnCount(); fol++) {
                resul += String.format("<th>%s</th>", table.getColumnName(fol));
            }
            resul += "</tr></thead>";
            resul += "<tbody>";
            for (int row = 0; row < table.getRowCount(); row++) {
                resul += "<tr>";
                for (int colum = 0; colum < columCount; colum++) {
                    resul += String.format("<td>%s</td>", table.getValueAt(row, colum));
                }
                resul += "</tr>";
            }
            resul += "</tbody>";
        }
        resul += "</table>";
        return resul;
    }

    /**
     * Convert from string to integer, and then convert back to string.
     *
     * @param number String type variable, contains an integer to validate.
     * @return Returns a string, validating if it is integer.
     */
    public static String StringToIntegerString(String number) {
        int num;
        try {
            num = Integer.parseInt(number);
        } catch (Exception e) {
            num = -1;
        }
        return String.valueOf(num);
    }

    /**
     *
     * @param xml the xml string to validate how xml
     * @return returns a boolean
     */
    public static boolean xmlvalidPG(String xml) {
        String result = new Conection().fillString("select xml_valid('" + xml + "')");
//        System.out.println("dbsaid:"+result);
        return result.equals("t");
    }

    /**
     *
     * @param obj
     * @return
     */
    public static String objectToJsonString(Object obj) {
        String result;
        result = gson.toJson(obj);
        return result;
    }

    /**
     *
     * @param ruta
     * @return res
     */
    public static String readJsonFile(String ruta) {
        String res = "";
        JsonParser jsonParser = new JsonParser();

        try (FileReader reader = new FileReader(ruta)) {
            //Read JSON file
            JsonElement obj = jsonParser.parse(reader);
            res = obj.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     *
     * @param json
     * @param ruta
     */
    public static void writeJsonFile(String json, String ruta) {
        //Write JSON file
        try (FileWriter file = new FileWriter(ruta)) {

            file.write(json);
            file.flush();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param carpeta
     * @param extensionActual
     * @param extensionNueva
     */
    public static void renameExtFileInDirectory(File carpeta, String extensionActual, String extensionNueva) {
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
                Methods.renameExtFileInDirectory(ficheroEntrada, extensionActual, extensionNueva);
            } else {
                if (Methods.getExtensionFileName(ficheroEntrada.getName()).equals(extensionActual)) {
                    File nuevonombre = new File(carpeta.getAbsolutePath()
                            .concat("\\")
                            .concat(Methods.getNameFileName(ficheroEntrada.getName()))
                            .concat(".")
                            .concat(extensionNueva));
                    if (!nuevonombre.exists()) {
                        ficheroEntrada.renameTo(nuevonombre);
                    }
                }
            }
        }
    }

    /**
     *
     * @param fileName
     * @return fe
     */
    public static String getExtensionFileName(String fileName) {
        String fe = "";
        final Pattern PATTERN = Pattern.compile("(.*)\\.(.*)");
        Matcher m = PATTERN.matcher(fileName);
        if (m.find()) {
            fe = m.group(2);
        }
        return fe;
    }
    
    /**
     *
     * @param fileName
     * @return fe
     */
    public static String getNameFileName(String fileName) {
        String fe = "";
        final Pattern PATTERN = Pattern.compile("(.*)\\.(.*)");
        Matcher m = PATTERN.matcher(fileName);
        if (m.find()) {
            fe = m.group(1);
        }
        return fe;
    }
}
