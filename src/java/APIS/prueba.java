/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIS;

import DataStaticBD.Conection;
import DataStaticBD.TemplateEmail;
import models.Users;

/**
 *
 * @author SOLIMAR
 */
public class prueba {
    public static void main(String[] arg){
        Conection c = new Conection();
        c.openConecction();
        TemplateEmail tempe = new TemplateEmail();
            tempe.userInsert(new Users("Jose", "Garcia", "CUVqwrcZDNrDGTFGTFOgARGYoqtWiYIFTdZr", "jgarcia24121996@gmail.com"));
    }
}
