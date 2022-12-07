package tn.esprit.spring.services;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import tn.esprit.spring.entities.Employee;
 
 
public class UserPDFExporter {
    private Employee employee;
     
    public UserPDFExporter(Employee employee) {
        this.employee = employee;
    }
 
    
     
    
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(24);
        font.setColor(Color.yellow);
        Font font2 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font2.setSize(18);
        font2.setColor(Color.black); 
        Paragraph p = new Paragraph("List of Users \n \n \n \n", font);
        Paragraph p2=new Paragraph("Conguratulation dear employee "+employee.getFirstName()+" "+ employee.getLastName()+". you have "
        		+ "earned the golden badge and and a behave of the company you were given this cirtaficate as proof of your acheivment and you were name the employeeof the month hope you keep doing well and excedding your limit");
        p.setAlignment(Paragraph.ALIGN_CENTER);
        p2.setAlignment(Paragraph.ALIGN_JUSTIFIED); 
        document.add(p);
        document.add(p2);
         
        
         
        
      
        document.close();
         
    }
}
