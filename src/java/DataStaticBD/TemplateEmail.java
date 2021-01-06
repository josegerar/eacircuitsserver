/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStaticBD;

import javax.swing.table.DefaultTableModel;
import models.Projects;
import models.Users;

/**
 *
 * @author tonyp
 */
public class TemplateEmail {

    private Conection conex;
    private final String sentency = "select * from extra";

    public TemplateEmail() {
        conex = new Conection();
    }

    {
    /*
    <div style="border-radius: 10px;">
	<table border="0" style="text-align:center; 
		   width:400px;">
		<tbody>
			<tr>
				<td >
					<div style="background-color: #ECF5FE;border-radius: 10px;">
						<img style="
							 border-radius: 10px 10px 0px 0px;
							 " width="400" 
							 Height="240" 
							 alt="Image Program"
							 src="${paramimg}" /> 
						<div style="
							 padding: 5px;
							 font-size:13px;
							 ">
							<p>
								Hello ${paramnames}.
								${paramsubject} in <a href="${paramindex}" target="_blank"> EsCircuits </a> application.
							</p>
                                                        <p>{paramdetail}</p>
						</div>
						<span style="
							  border-radius: 4px;
							  font-size:30px;
							  background-color: #0082D7;
							  padding: 5px;
							  ">
							<a href="${paramurl}" target="_blank" style="color: #FFF;">${paramlabel}</a>
						</span>
						<br>
						<br>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
     */
    }
        
    public void userInsert(Users usr) {
        Thread tr = new Thread(() -> {
            System.out.println("userInsertEmail()");
            pUserInsert(usr);
        });
        tr.start();
    }
    
    public void requestCodeForChangePassword(Users usr) {
        Thread tr = new Thread(() -> {
            System.out.println("requestCodeForChangePasswordEmail()");
            pRequestCodeForChangePassword(usr);
        });
        tr.start();
    }
    
    public void requestCodeForConfirmAccount(Users usr) {
        Thread tr = new Thread(() -> {
            System.out.println("requestCodeForConfirmAccount()");
            pRequestCodeForConfirmAccount(usr);
        });
        tr.start();
    }
    
    public void collaborativeWork(String receiver, String codeproject) {
        Thread tr = new Thread(() -> {
            System.out.println("collaborativeWorkEmail()");
            pCollaborativeWork(receiver, codeproject);
        });
        tr.start();
    }
    
    private String recorre(DefaultTableModel table, String param) {
        String result = "";
        for (int index = 0; index < table.getRowCount(); index++) {
            if (table.getValueAt(index, 0).toString().equals(param)) {
                result = table.getValueAt(index, 1).toString();
            }
        }
        return result;
    }
    
    
    private void pUserInsert(Users usr){
        DefaultTableModel table = conex.returnRecord(sentency);
        String respon = recorre(table, "splantilla");
        
        respon = respon.replace("${paramimg}", recorre(table, "imgplantilla1"));
        
        respon = respon.replace("${paramnames}", usr.getNames_user() + " " + usr.getLastname_user());
        
        String urlx = recorre(table, "urlaplication");
        respon = respon.replace("${paramsubject}", "You have been registered");
        respon = respon.replace("${paramindex}", urlx + "index.html");
        respon = respon.replace("${paramdetail}", "Confirm your account by accessing the following hyperlink");
        
        respon = respon.replace("${paramlabel}", "Click here!");
        respon = respon.replace("${paramurl}", urlx + "notverified.html?tk=" + usr.getCodeverification_user()+"&usr="+usr.getEmail_user());
        
        Email em = new Email();
        em.setmyEmailFrom(recorre(table, "email"), recorre(table, "emailpass"));
        em.setContentEmail(usr.getEmail_user(), "Welcome to the EaCircuits community.", respon);
        
        boolean status = em.sendmyEmail();
        System.out.println("resultado de envío: " +status);
    }
    
    private void pRequestCodeForChangePassword(Users usr){
        DefaultTableModel table = conex.returnRecord(sentency);
        String respon = recorre(table, "splantilla");
        
        respon = respon.replace("${paramimg}", recorre(table, "imgplantilla1"));
        
        respon = respon.replace("${paramnames}", usr.getNames_user() + " " + usr.getLastname_user());
        
        String urlx = recorre(table, "urlaplication");
        respon = respon.replace("${paramsubject}", "You have requested a password change");
        respon = respon.replace("${paramindex}", urlx + "index.html");
        respon = respon.replace("${paramdetail}", "This code is only valid for the next 60 minutes");
        
        respon = respon.replace("${paramlabel}", usr.getCodeverification_user());
        respon = respon.replace("${paramurl}", urlx + "login.html?op=reset"+"&usr="+usr.getEmail_user()+"&code=" + usr.getCodeverification_user());
        
        Email em = new Email();
        em.setmyEmailFrom(recorre(table, "email"), recorre(table, "emailpass"));
        em.setContentEmail(usr.getEmail_user(), "Code to change your password.", respon);
        
        boolean status = em.sendmyEmail();
        System.out.println("resultado de envío: " +status);
    }
    
    private void pRequestCodeForConfirmAccount(Users usr){
        DefaultTableModel table = conex.returnRecord(sentency);
        String respon = recorre(table, "splantilla");
        
        respon = respon.replace("${paramimg}", recorre(table, "imgplantilla1"));
        
        respon = respon.replace("${paramnames}", usr.getNames_user() + " " + usr.getLastname_user());
        
        String urlx = recorre(table, "urlaplication");
        respon = respon.replace("${paramsubject}", "You have requested a confirm account");
        respon = respon.replace("${paramindex}", urlx + "index.html");
        respon = respon.replace("${paramdetail}", "Confirm your account by accessing the following hyperlink");
        
        respon = respon.replace("${paramlabel}", "Click here!");
        respon = respon.replace("${paramurl}", urlx + "notverified.html?tk=" + usr.getCodeverification_user()+"&usr="+usr.getEmail_user());
        
        Email em = new Email();
        em.setmyEmailFrom(recorre(table, "email"), recorre(table, "emailpass"));
        em.setContentEmail(usr.getEmail_user(), "Welcome to the EaCircuits community.", respon);
        
        boolean status = em.sendmyEmail();
        System.out.println("resultado de envío: " +status);
    }
    
    private void pCollaborativeWork(String receiver, String codeProject){
        DefaultTableModel table = conex.returnRecord(sentency);
        String respon = recorre(table, "splantilla");
        
        respon = respon.replace("${paramimg}", recorre(table, "imgplantilla2"));
        
        respon = respon.replace("${paramnames}", "Circuiters");
        
        String urlx = recorre(table, "urlaplication");
        respon = respon.replace("${paramsubject}", "You have been invited to participate in a collaborative project");
        respon = respon.replace("${paramindex}", urlx + "index.html");
        respon = respon.replace("${paramdetail}", "Go to the app and start working.");
        
        respon = respon.replace("${paramlabel}", codeProject);
        respon = respon.replace("${paramurl}", urlx + "home.html");
        
        Email em = new Email();
        em.setmyEmailFrom(recorre(table, "email"), recorre(table, "emailpass"));
        em.setContentEmail(receiver, "Invitation to collaborate.", respon);
        
        boolean status = em.sendmyEmail();
        System.out.println("resultado de envío: " +status);
    }

}
