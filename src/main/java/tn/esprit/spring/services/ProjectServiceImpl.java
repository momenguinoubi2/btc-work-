package tn.esprit.spring.services;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.zxing.WriterException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.controller.ProjectRestController;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Etat;
import tn.esprit.spring.entities.ParticipationProject;
import tn.esprit.spring.entities.Project;
import tn.esprit.spring.repository.EmployeeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.ParticipationProjectRepository;
import tn.esprit.spring.repository.ProjectRepository;
import tn.esprit.spring.repository.TaskRepository;

@Slf4j
@Service
@EnableScheduling
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	ParticipationProjectRepository participationProjectRepository;
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	IEmployeeService employeeService;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	EntrepriseService entrepriseService;

	@Override
	public List<Project> retrieveAllProjects() {
		// TODO Auto-generated method stub
		return (List<Project>) projectRepository.findAll();
	}

	@Override
	public void addProject(Project p, List<Integer> idEntreprise) {
		// TODO Auto-generated method stub
		// p.setEtat(Etat.pending);
		// Entreprise entreprise=
		// entrepriseRepository.findById(idEntreprise).orElse(null);
		if (p.getDateDebut().before(p.getDateFin())) {
			projectRepository.save(p);
		} else
			log.info("la date finale est avantla date debut ");
		for (int ide : idEntreprise) {
			Entreprise entreprise = entrepriseRepository.findById(ide).orElse(null);
			entreprise.getProjects().add(p);
			entrepriseRepository.save(entreprise);
		}

	}

	@Override
	public void deleteProject(int id) {
		// TODO Auto-generated method stub
		projectRepository.deleteProjectAssociation(id);
		projectRepository.deleteById(id);

	}

	@Override
	public Project updateProject(Project p, List<Integer> idEntreprise) {
		// TODO Auto-generated method stub
		// int idProject=p.getIdProject();
		// p.setIntervenant(projectRepository.getNbreIntervenant(idProject));
		for (int ide : idEntreprise) {
			Entreprise entreprise = entrepriseRepository.findById(ide).orElse(null);
			entreprise.getProjects().add(p);
			entrepriseRepository.save(entreprise);
		}
		return projectRepository.save(p);
	}

	@Override
	public Project retrieveProject(int id) {
		// TODO Auto-generated method stub
		return projectRepository.findById(id).get();
	}

	@Override
	public int NbreIntervenant(int idProject) {
		// TODO Auto-generated method stub

		return projectRepository.getNbreIntervenant(idProject);
	}

	@Override
	public void AcceptAndAssignProjectToEmployees(int idProject, int idEmployee) {
		// TODO Auto-generated method stub
		Project p = projectRepository.findById(idProject).orElse(null);
		p.setEtat(Etat.todo);
		Employee e = employeeRepository.findById(idEmployee).orElse(null);
		ParticipationProject pp = new ParticipationProject();

		pp.setEmployees(e);
		pp.setProjects(p);
		if (participationProjectRepository.nbrParticip(idProject, idEmployee) == 1)
			log.info("la participation existe");
		else {
			participationProjectRepository.save(pp);
			updateNbreIntervenantProject();
		}
		// else

		// e.getParticipationProjects().add(pp);

	}

	@Override
	public void RecjectProject(int idp) {
		// TODO Auto-generated method stub
		Project p = projectRepository.findById(idp).orElse(null);

		p.setEtat(Etat.rejected);
		projectRepository.save(p);

	}

	@Override
	public void AddPrimeToEmployee(int ide) {
		// TODO Auto-generated method stub
		Employee e = employeeRepository.findById(ide).orElse(null);

		int participation = participationProjectRepository.nbreParticipationOfEmployee(ide);
		float prime = e.getPrime();
		e.setPrime(100 * participation);
		employeeRepository.save(e);
	}

	//@Scheduled(cron = "*/5 * * * * *")
	@Override
	public void updateNbreIntervenantProject() {
		// TODO Auto-generated method stub
		List<ParticipationProject> participation = retrieveAllParticipations();
		for (ParticipationProject pp : participation) {
			int idp = pp.getProjects().getIdProject();
			Project p = projectRepository.findById(idp).orElse(null);
			p.setIntervenant(NbreIntervenant(idp));
			projectRepository.save(p);
		}

	}

	@Override
	public List<ParticipationProject> retrieveAllParticipations() {
		// TODO Auto-generated method stub
		return (List<ParticipationProject>) participationProjectRepository.findAll();
	}

//	@Scheduled(cron = "*/5 * * * * *")
	@Override
	public List<Employee> getEmployeeStar() {
		// TODO Auto-generated method stub
		List<ParticipationProject> employees = retrieveAllParticipations();
		for (ParticipationProject pp : employees) {
			int idEmployee = pp.getEmployees().getIdEmployee();

			Employee employee = employeeRepository.findById(idEmployee).orElse(null);
			employee.setNbreProjet(projectRepository.getnbreProjetOfEmployee(idEmployee));
			employeeRepository.save(employee);

		}
		int nbreProject = employeeRepository.getMaxProject();
		List<Employee> employeesWithMaxProjects = employeeRepository.getEmployeeMaxProject(nbreProject);
		log.info("**************0" + employeesWithMaxProjects);
		return employeesWithMaxProjects;
	}

//	@Scheduled(cron = "*/5 * * * * *" )
//deux listes secialite et nbre de max participation // specialite seulement
	@Override
	public List<Employee> getEmployeeForProject(int idp) {
		// TODO Auto-generated method stub
		List<Employee> employeesFilter = new ArrayList<Employee>();
		List<Integer> Idemployees = employeeRepository.getEmployeeProjectSpecialite(idp);
		for (int ide : Idemployees) {
			Employee e = employeeRepository.findById(ide).orElse(null);
			List<Employee> employeeSpecialite = new ArrayList<Employee>();
			employeeSpecialite.add(e);
			if (getEmployeeStar().contains(e))
				employeesFilter.add(e);
			else
				employeesFilter.addAll(employeeSpecialite);
		}
		return employeesFilter;

	}

//	@Scheduled(cron = "*/5 * * * * *" )
	public List<Integer> sizeList() {
		List<Integer> size = new ArrayList();
		for (Project p : retrieveAllProjects()) {
			int idp = p.getIdProject();
			int sizee = getEmployeeForProject(idp).size();
			size.add(sizee);
		}
		log.info("hhhhhhhhhhhhhhhhhhhhhh" + size);
		return size;

	}

	/*
	 * @Override public void EnvoyerRapport(HttpServletResponse response) { // TODO
	 * Auto-generated method stub
	 * 
	 * for (Project project: retrieveAllProjects()) { int id=project.getIdProject();
	 * if(projectRepository.getEtatOfProject(id)==Etat.done) { Project pp=
	 * projectRepository.findById(id).orElse(null); List<Integer> idEntreprises =
	 * projectRepository.getEntrepriseEtatProjetDone(id); for (int identreprise:
	 * idEntreprises) { Entreprise entreprise=
	 * entrepriseRepository.findById(identreprise).orElse(null);
	 * 
	 * } }else log.info("le projet n est pas encore termine"); }
	 * 
	 * }
	 */

	public void generate(HttpServletResponse response, int idProject, int idEntreprise) {
		Project p = projectRepository.findById(idProject).orElse(null);
		Entreprise entreprise = entrepriseRepository.findById(idEntreprise).orElse(null);
		if (p.getEtat() == Etat.done) {
			// Employee employee = employeeRepository.findById(idEmployee).orElse(null);
			Document document = new Document(PageSize.A4);
			try {
				PdfWriter.getInstance(document, response.getOutputStream());

				document.open();
				Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, Font.DEFAULTSIZE, Font.UNDERLINE,
						new Color(0xFF, 0x00, 0x00));
				fontTitle.setSize(18);

				Paragraph paragraph = new Paragraph("Rapport", fontTitle);
				paragraph.setAlignment(Paragraph.ALIGN_CENTER);

				Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
				fontParagraph.setSize(12);

				Font red = FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,
						new Color(0xFF, 0x00, 0x00));
				Font blue = FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,
						new Color(0x00, 0x00, 0xFF));
				Font green = FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.ITALIC,
						new Color(0x00, 0x80, 0x00));
				Font yellow = FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.ITALIC,
						new Color(0xFF, 0xFF, 0x00));
				Font titre = FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.UNDERLINE,
						new Color(0x69, 0x69, 0x69));
				Paragraph paragraph1 = new Paragraph("information Entreprise", titre);
				paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
				paragraph1.spacingAfter();
				// paragraph1.add(new Chunk("red", red));
				Paragraph paragraph2 = new Paragraph("Nom Entreprise: " + entreprise.getNomEntreprise(), fontParagraph);
				paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph3 = new Paragraph("Domaine Entreprise: " + entreprise.getDomain(), fontParagraph);
				paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph4 = new Paragraph("Email Entreprise: " + entreprise.getEmail(), fontParagraph);
				paragraph4.setAlignment(Paragraph.ALIGN_LEFT);

				Paragraph paragraph5 = new Paragraph("information Project", titre);
				paragraph5.setAlignment(Paragraph.ALIGN_LEFT);
				paragraph5.spacingAfter();

				Paragraph paragraph6 = new Paragraph("Nom Project: " + p.getNom(), fontParagraph);
				paragraph6.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph7 = new Paragraph("Description Project: " + p.getDescription(), fontParagraph);
				paragraph7.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph8 = new Paragraph("Nombre de task: " + p.getNbreTask(), fontParagraph);
				paragraph8.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph9 = new Paragraph("Nombre de task in time: " + p.getNbreTaskInTime(), fontParagraph);
				paragraph9.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph10 = new Paragraph("Nombre de task en retard: " + p.getNbreTaskLate(),
						fontParagraph);
				paragraph10.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph11 = new Paragraph("Date du debut du projet: " + p.getDateDebut(), fontParagraph);
				paragraph11.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph12 = new Paragraph("Date du fin du projet: " + p.getDateFin(), fontParagraph);
				paragraph12.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph13 = new Paragraph("Nbre d intervenant: " + p.getIntervenant(), fontParagraph);
				paragraph13.setAlignment(Paragraph.ALIGN_LEFT);
				int idEmployee = employeeRepository.employeeTaskInTime(idProject);

				Paragraph paragraph135 = new Paragraph("Meilleur Employee", titre);
				paragraph135.setAlignment(Paragraph.ALIGN_LEFT);
				Employee e = employeeRepository.findById(idEmployee).orElse(null);
				Paragraph paragraph14 = new Paragraph("Nom: " + e.getFirstName(), green);
				paragraph14.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph15 = new Paragraph("Prenom: " + e.getLastName(), green);
				paragraph15.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph16 = new Paragraph("email: " + e.getEmail(), green);
				paragraph16.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph17 = new Paragraph("Nbre de Projet qu il gere: " + e.getNbreProjet(), green);
				paragraph17.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph18 = new Paragraph("Numero de telephone: " + e.getNumTel(), green);
				paragraph18.setAlignment(Paragraph.ALIGN_LEFT);

				int idemployee = employeeRepository.employeeTaskInTimeMin(idProject);
				Employee ee = employeeRepository.findById(idemployee).orElse(null);
				Paragraph paragraph136 = new Paragraph(" Employee le plus paresseux ", titre);
				paragraph136.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph19 = new Paragraph("Nom: " + ee.getFirstName(), yellow);
				paragraph19.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph20 = new Paragraph("Prenom: " + ee.getLastName(), yellow);
				paragraph20.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph21 = new Paragraph("email: " + ee.getEmail(), yellow);
				paragraph21.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph22 = new Paragraph("Nbre de Projet qu il gere: " + ee.getNbreProjet(), yellow);
				paragraph22.setAlignment(Paragraph.ALIGN_LEFT);
				Paragraph paragraph23 = new Paragraph("Numero de telephone: " + ee.getNumTel(), yellow);
				paragraph23.setAlignment(Paragraph.ALIGN_LEFT);
				document.add(paragraph);

				document.add(paragraph1);
				document.add(paragraph2);
				document.add(paragraph3);
				document.add(paragraph4);
				document.add(paragraph5);
				document.add(paragraph6);
				document.add(paragraph7);
				document.add(paragraph8);
				document.add(paragraph9);
				document.add(paragraph10);
				document.add(paragraph11);
				document.add(paragraph12);
				document.add(paragraph13);
				document.add(paragraph135);
				document.add(paragraph14);
				document.add(paragraph15);
				document.add(paragraph16);
				document.add(paragraph17);
				document.add(paragraph18);
				document.add(paragraph136);
				document.add(paragraph19);
				document.add(paragraph20);
				document.add(paragraph21);
				document.add(paragraph22);
				document.add(paragraph23);
				String QR_CODE_IMAGE_PATH = "./src/main/java/QRCode.png";
				Image gif = Image.getInstance(QR_CODE_IMAGE_PATH);
				document.add(gif);
				document.close();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			log.info("le projet n est pas encore terminer ");
	}

}
