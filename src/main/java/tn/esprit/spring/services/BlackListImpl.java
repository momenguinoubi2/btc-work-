package tn.esprit.spring.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.BlackList;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Project;
import tn.esprit.spring.repository.BlackListRepository;
import tn.esprit.spring.repository.EmployeeRepository;

@EnableScheduling
@Service
@Slf4j
public class BlackListImpl implements BlackListService {
	@Autowired
	BlackListRepository blackListRepository;
	@Autowired
	ProjectService projectService;
	@Autowired
	IEmployeeService employeeService;
	@Autowired
	EmployeeRepository employeeRepository;

	//@Scheduled(cron = "*/2 * * * * *")
	@Override
	public void addEmployeeInBlackList() {
		BlackList b = new BlackList();
		// TODO Auto-generated method stub
		for (int idProject : blackListRepository.retrieveAllProjectsHasTasks()) {
			for (int idEmployee : blackListRepository.retrieveAllEmployeesHasTasks()) {
				Employee e = employeeRepository.findById(idEmployee).orElse(null);
				int nbreTaskEmployeeParprojet = blackListRepository.nbreTask(idEmployee, idProject);
				// float salaire= e.getSalaire()-100;
				// e.setSalaire(salaire);
				// employeeRepository.save(e);
				int nbreTaskLate = blackListRepository.nbreTaskLate(idEmployee, idProject);
				if (nbreTaskEmployeeParprojet > nbreTaskLate && nbreTaskLate != 0) {
					float salaire = e.getSalaire() - 100;
					e.setSalaire(salaire);
					employeeRepository.save(e);
					log.info("le salaire a ete diminuer ");
				} else {

					if (nbreTaskEmployeeParprojet == nbreTaskLate) {
						b.setIdEmployeeBlackList(e.getIdEmployee());
						b.setDateNaissance(e.getDateNaissance());
						b.setEmail(e.getEmail());
						b.setFirstName(e.getFirstName());
						b.setLastName(e.getLastName());
						b.setPassword(e.getPassword());
						b.setNbreProjet(e.getNbreProjet());
						b.setNumTel(e.getNumTel());
						b.setProfession(e.getProfession());
						b.setSpecialite(e.getSpecialite());
						b.setSalaire(e.getSalaire() + 100);

						blackListRepository.save(b);
						employeeRepository.deleteById(idEmployee);
					}
				}
			}
		}

	}
//
	//@Scheduled(cron = "*/5 * * * * *")
	@Override
	public void AffecterEmployee() {
		// TODO Auto-generated method stub
		for (BlackList b : retrieveAllEmployeesBlackList()) {
			int ide = b.getIdEmployeeBlackList();
			if (b.getFormation() != null) {
				Employee e = new Employee();
				e.setDateNaissance(b.getDateNaissance());
				e.setEmail(b.getEmail());
				e.setFirstName(b.getFirstName());
				e.setLastName(b.getLastName());
				e.setPassword(b.getPassword());
				e.setNbreProjet(b.getNbreProjet());
				e.setNumTel(b.getNumTel());
				e.setProfession(b.getProfession());
				e.setSpecialite(b.getSpecialite());
				e.setSalaire(b.getSalaire());
				employeeRepository.save(e);
				blackListRepository.deleteById(ide);
			}
		}
	}

	@Override
	public List<BlackList> retrieveAllEmployeesBlackList() {
		// TODO Auto-generated method stub
		return (List<BlackList>) blackListRepository.findAll();
	}

	@Override
	public void setFormation(int idBlackListEmployee) {
		// TODO Auto-generated method stub
		BlackList b = blackListRepository.findById(idBlackListEmployee).orElse(null);
		String QR_CODE_IMAGE_PATH = "./src/test/java/QRCode.png";
		b.setFormation(QR_CODE_IMAGE_PATH);
		blackListRepository.save(b);

	}

}
