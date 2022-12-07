package tn.esprit.spring.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.google.zxing.WriterException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.utils.MyConstants;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.ParticipationProject;
import tn.esprit.spring.entities.Project;
import tn.esprit.spring.repository.EmployeeRepository;
import tn.esprit.spring.services.EntrepriseService;
import tn.esprit.spring.services.IEmployeeService;
import tn.esprit.spring.services.ProjectService;
import tn.esprit.spring.services.QrCodeGenerator;

@RestController
@Api(tags = "Gestion des projects")
@RequestMapping("/project")
public class ProjectRestController {
	@Autowired
	ProjectService projectService;
	@Autowired
	EntrepriseService entrepriseService;
	@Autowired
	IEmployeeService employeeService;
	@Autowired
	EmployeeRepository employeeRepository;
	 @Autowired
	  JavaMailSender emailSenderr;

	// http://localhost:8089/SpringMVC/project/retrieve-all-project
		@ApiOperation(value = "Récupérer la liste des projects")
		@GetMapping("/retrieve-all-project")
		public List<Project> getProject() {
			List<Project> listProject= projectService.retrieveAllProjects();
			return listProject;
		}
		//http://localhost:8089/SpringMVC/project/retrieve-getEmployeeStar
		@ApiOperation(value = "retourner l'employee qui a participer le plus ds des projets ")
		@GetMapping("/retrieve-getEmployeeStar")
		public List<Employee> getEmployeeStar() {
			return (List<Employee>)projectService.getEmployeeStar();
		}
		//http://localhost:8089/SpringMVC/project/getEmployeeForProject/1
				@ApiOperation(value = "getEmployeeForProject")
				@GetMapping("/getEmployeeForProject/{idProject}")
				public List<Employee> getEmployeeForProject(@PathVariable("idProject") int projectId) {
					return( List<Employee>)projectService.getEmployeeForProject(projectId);
				}
				//http://localhost:8089/SpringMVC/project/retrieve-nbreIntervenant/1
				@ApiOperation(value = "udpdate nbre intervenant dans un projet et recuperer le nbre d'intervenant par projet ")
				@GetMapping("/retrieve-nbreIntervenant/{idProject}")
				public int getNbreIntervenant(@PathVariable("idProject") int projectId) {
					return projectService.NbreIntervenant(projectId);
				}
		// http://localhost:8089/SpringMVC/project/retrieve-project/1
		@ApiOperation(value = "Récupérer un project par Id")
		@GetMapping("/retrieve-project/{project-id}")
//		@ApiResponses(value = {
//				@ApiResponse(code = 200, message = "Success|OK"),
//				@ApiResponse(code = 401, message = "Not Authorized!"),
//				@ApiResponse(code = 403, message = "Forbidden!"),
//				@ApiResponse(code = 404, message = "Not Found!") })
		public Project retrieveProject(@PathVariable("project-id") int projectId) {
			return projectService.retrieveProject(projectId);
		}
//http://localhost:8089/SpringMVC/project/add-project
		@PostMapping("/add-project/{idEntreprise}")
		public void addProject(@RequestBody Project p,@PathVariable("idEntreprise") List<Integer> idEntreprise) {
			 projectService.addProject(p,idEntreprise);
		}
		// http://localhost:8089/SpringMVC/project/assignProject/3/1
				@ApiOperation(value = "affecter un employee a un projet ")
		@PostMapping("/assignProject/{project-id}/{employee-id}")
		public void assignProjectToEmployee(@PathVariable("project-id") int projectId,@PathVariable("employee-id") int employeeId) {
			 projectService.AcceptAndAssignProjectToEmployees(projectId, employeeId);
		}
				// http://localhost:8089/SpringMVC/project/addPrime/1
				@ApiOperation(value = "ajouter une prime a un employee suivant sa participation ")
		@PostMapping("/addPrime/{employee-id}")
		public void addPrimeToEmployee(@PathVariable("employee-id") int employeeId) {
			 projectService.AddPrimeToEmployee(employeeId);
		}

				// http://localhost:8089/SpringMVC/project/rejectProject/1
				@ApiOperation(value = "refuser un projet ")
		@PostMapping("/rejectProject/{project-id}")
		public void assignProjectToEmployee(@PathVariable("project-id") int projectId) {
			 projectService.RecjectProject(projectId);
		}

		// http://localhost:8089/SpringMVC/project/remove-project/1
		@DeleteMapping("/remove-project/{project-id}")
		public void removeProject(@PathVariable("project-id") int projectId) {
			projectService.deleteProject(projectId);
		}

		// http://localhost:8089/SpringMVC/project/modify-project
		@PutMapping("/modify-project/{idEntreprise}")
		public Project modifyProject(@RequestBody Project p,@PathVariable("idEntreprise") List<Integer> idEntreprise) {
			return projectService.updateProject(p,idEntreprise);
		}
		// http://localhost:8089/SpringMVC/project/retrieve-all-participations
				@ApiOperation(value = "Récupérer la liste des participations")
				@GetMapping("/retrieve-all-participations")
				public List<ParticipationProject> getParticipations() {
					List<ParticipationProject> listParticipation= projectService.retrieveAllParticipations();
					return listParticipation;
				}
				// http://localhost:8089/SpringMVC/project/export/excel
	
				 @GetMapping("/export/excel")
				    public void exportToExcel(HttpServletResponse response) throws IOException {
				       
					 response.setContentType("text/csv");
				       
				          String headerKey = "Content-Disposition";
				        String headerValue = "attachment; filename=project.csv";
				        response.setHeader(headerKey, headerValue);
				         
				        List<Project> listProjects = projectService.retrieveAllProjects();
				 
				        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
				        String[] csvHeader = {"Project ID", "description", "nom", "dateDebut", "dateFin","intervenant","nbreTaskInTime","nbreTaskLate","nbreTask","etat","specialite","budget"};
				        String[] nameMapping = {"idProject", "description", "nom","dateDebut", "dateFin","intervenant","nbreTaskInTime","nbreTaskLate","nbreTask","etat","specialite","budget"};
				         
				        csvWriter.writeHeader(csvHeader);
				         
				        for (Project project : listProjects) {
				            csvWriter.write(project, nameMapping);
				        }
				         
				        csvWriter.close();
				
				    }  
					// http://localhost:8089/SpringMVC/project/exportpdf
				 @GetMapping(value = "/exportpdf/{project-id}/{entreprise-id}")
					public void employeeReports(Model model, HttpServletResponse response,@PathVariable("project-id") int projectId,@PathVariable("entreprise-id") int idEntreprise) throws IOException {
				        response.setContentType("application/pdf");
				        //DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
				        Entreprise e=entrepriseService.retrieveEntreprise(idEntreprise);
			        	  Project p=projectService.retrieveProject(projectId);

				        String headerKey = "Content-Disposition";
				        String headerValue = "attachment;  filename=rapport_id"+e.getIdEntreprise()+"/"+p.getIdProject()+".pdf";
				        response.setHeader(headerKey, headerValue);
getQRCode(model, idEntreprise,projectId);
				        projectService.generate(response, projectId, idEntreprise);
					
						
					}
				 private static final String QR_CODE_IMAGE_PATH = "./src/main/java/QRCode.png";
				 @GetMapping(value = "/qrcode/{entreprise-id}/{project-id}")
			        public String getQRCode( Model model,@PathVariable("entreprise-id") int entrepriseId,@PathVariable("project-id") int idProject){
			        	  Entreprise e=entrepriseService.retrieveEntreprise(entrepriseId);
			        	  Project p=projectService.retrieveProject(idProject);
			        	  int idEmployee= employeeRepository.employeeTaskInTime(idProject);
			        	   	Employee ee= employeeRepository.findById(idEmployee).orElse(null);

			        	    int idemployee= employeeRepository.employeeTaskInTimeMin(idProject);
			        	    Employee eee = employeeRepository.findById(idemployee).orElse(null);
			            String infoEntreprise="entreprise :"+e.getNomEntreprise()+"  email:  "+e.getEmail()+"  numTel  :"+e.getNumTel()+"  "+"information Project  :"+p.getNom()+" description "+p.getDescription()+"   nbreIntervenant"+p.getIntervenant()+"    nbreTask"+p.getNbreTask()+"   information Meilleur Employee"+ee.getFirstName()+"  "+ee.getLastName()+"   email:  " 
			+ee.getEmail()+"    nbre de projet qu il gere    "+ee.getNbreProjet()+"information employee paresseux    "+eee.getFirstName()+"  "+eee.getLastName()+"   email:  " 
					+eee.getEmail()+"    nbre de projet qu il gere    "+eee.getNbreProjet();
			            		String github="num :"+e.getNomEntreprise();

			            byte[] image = new byte[0];
			            try {

			                // Generate and Return Qr Code in Byte Array
			                image = QrCodeGenerator.getQRCodeImage(infoEntreprise,250,250);

			                // Generate and Save Qr Code Image in static/image folder
			                QrCodeGenerator.generateQRCodeImage(infoEntreprise,250,250,QR_CODE_IMAGE_PATH);

			            } catch (WriterException | IOException ex) {
			                ex.printStackTrace();
			            }
			            // Convert Byte Array into Base64 Encode String
			            String qrcode = Base64.getEncoder().encodeToString(image);

			            model.addAttribute("medium",infoEntreprise);
			            model.addAttribute("github",github);
			            model.addAttribute("qrcode",qrcode);

			            return "infoEntreprise";
			        }
				// http://localhost:8089/SpringMVC/project/sendHtmlEmail/3/6
				    @RequestMapping("/sendHtmlEmail/{entreprise-id}/{project-id}")
				    @ApiOperation(value = "envoyer un mail")
				    public String sendHtmlEmail(Model model,HttpServletResponse response,@PathVariable("entreprise-id") int idEntreprise,@PathVariable("project-id") int idProject) throws IOException, MessagingException  {
					employeeReports(model, response, idProject, idEntreprise);
					Entreprise e=entrepriseService.retrieveEntreprise(idEntreprise);
				        MimeMessage message = emailSenderr.createMimeMessage();

				        boolean multipart = true;
				        
				        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
				        
				        
				        
				        helper.setTo(e.getEmail());
				        helper.setSubject("Test email with attachments");
				        
				        helper.setText("Hello, Im testing email with attachments!");
				    
			        	  Project p=projectService.retrieveProject(idProject);
				      //  String path1 = "C:/Users/arafa/Downloads/rapport_id"+e.getIdEntreprise()+"/"+p.getIdProject()+".pdf";
			        	 
							
						FileSystemResource file = new FileSystemResource("C:/Users/momen/Downloads/rapport_id"+e.getIdEntreprise()+"_"+p.getIdProject()+".pdf");
						helper.addAttachment(file.getFilename(), file);
						
				        

				        
				        helper.setSubject("Test send HTML email");
				        
				    
				      emailSenderr.send(message);

				        return "Email Sent!";
				    }
		
				 
			
}
/*
 * 
 * 
 * 
 * 	 
 * */
