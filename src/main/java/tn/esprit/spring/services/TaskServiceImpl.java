package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Etat;
import tn.esprit.spring.entities.EtatRemise;
import tn.esprit.spring.entities.Project;
import tn.esprit.spring.entities.Task;
import tn.esprit.spring.repository.EmployeeRepository;
import tn.esprit.spring.repository.ParticipationProjectRepository;
import tn.esprit.spring.repository.ProjectRepository;
import tn.esprit.spring.repository.TaskRepository;

@Slf4j
@Service
@EnableScheduling
public class TaskServiceImpl implements TaskService {
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	ParticipationProjectRepository participationProjectRepository;
	@Autowired
	ProjectService projectService;

	@Override
	public List<Task> retrieveAllTasks() {
		// TODO Auto-generated method stub

		return (List<Task>) taskRepository.findAll();
	}

	@Override
	public Task addTask(Task t) {
		// TODO Auto-generated method stub

		return taskRepository.save(t);
	}

	@Override
	public void deleteTask(int id) {
		// TODO Auto-generated method stub
		taskRepository.deleteById(id);
	}

	@Override
	public Task updateTask(Task t) {
		// TODO Auto-generated method stub

		return taskRepository.save(t);
	}

	@Override
	public Task retrieveTask(int id) {
		// TODO Auto-generated method stub
		return taskRepository.findById(id).get();
	}

	@Override
	public void addAndassignTaskToEmployee(int idTask, int idEmployee) {
		// TODO Auto-generated method stub
		Employee e = employeeRepository.findById(idEmployee).orElse(null);
		Task t = taskRepository.findById(idTask).orElse(null);
		t.setEmployees(e);
		int idproject = t.getProjects().getIdProject();

		t.setEtat(Etat.todo);
		taskRepository.save(t);
		Project p = projectRepository.findById(idproject).orElse(null);
		p.setEtat(Etat.inprogress);
		projectRepository.save(p);

		projectService.AcceptAndAssignProjectToEmployees(idproject, idEmployee);

	}

	@Override
	public void addAndassignTaskToProject(Task t, int idProject) {
		// TODO Auto-generated method stub
		t.setEtat(Etat.todo);
		Project project = projectRepository.findById(idProject).orElse(null);
		for (Project p : projectRepository.findAll()) {
			if (project.getEtat() == Etat.todo || project.getEtat() == Etat.inprogress) {
				t.setProjects(project);
				t.setEtat(Etat.todo);
				taskRepository.save(t);
			} else if (project.getEtat() == Etat.done)
				log.info("le projet est terminé");
			else if (project.getEtat() == Etat.rejected)
				log.info("le projet est refusé");
			else
				log.info("le projet n est pas encore accepte ");
		}

	}

	@Override
	public int nbreTaskOfProject(int idp) {
		// TODO Auto-generated method stub
		int nbre = taskRepository.nbrTaskByProject(idp);

		log.info("nbre" + nbre);
		return nbre;
	}

	@Override
	//@Scheduled(cron = "*/5 * * * * *")
// modifier l etat de projet si tous les taches sont done sinon in progress
	public void nbreTaskEtatOfProject() {
		List<Task> tasks = retrieveAllTasks();
		for (Task t : tasks) {

			int idp = t.getProjects().getIdProject();
			Project p = projectRepository.findById(idp).orElse(null);
			p.setNbreTask(taskRepository.nbrTaskByProject(idp));
			if (taskRepository.nbrTaskByProject(idp) == taskRepository.nbrTaskDoneByProject(idp))
				p.setEtat(Etat.done);
			else
				p.setEtat(Etat.inprogress);
			projectRepository.save(p);
			log.info("tasks" + taskRepository.nbrTaskByProject(idp));
		}
	}

	@Override
	public void EndTask(int idTask) {
		// TODO Auto-generated method stub
		Task task = taskRepository.findById(idTask).orElse(null);
		Date dateRemise = new Date(System.currentTimeMillis());
		task.setDateRemise(dateRemise);
		if (task.getDateRemise().before(task.getDateFinTask()))
			task.setEtatRemise(EtatRemise.inTime);
		else
			task.setEtatRemise(EtatRemise.delayed);
		task.setEtat(Etat.done);
		taskRepository.save(task);
	}

	// retourner le nbre de tache in time of project
///	@Scheduled(cron = "*/5 * * * * *")
	public void getNbreTacheInTimeOfProject() {
		List<Task> tasks = retrieveAllTasks();
		for (Task task : tasks) {
			int idproject = task.getProjects().getIdProject();
			Project p = projectRepository.findById(idproject).orElse(null);
			int nbreTaskInTime = taskRepository.nbrTaskInTimeByProject(idproject);
			int nbreTask = taskRepository.nbrTaskByProject(idproject);

			if (nbreTaskInTime == nbreTask) {
				p.setNbreTaskInTime(nbreTaskInTime);
			} else if (nbreTaskInTime < nbreTask || task.getEtatRemise() != null) {
				p.setNbreTaskInTime(nbreTaskInTime);
				p.setNbreTaskLate(nbreTask - nbreTaskInTime);
			}
			projectRepository.save(p);
			log.info("tasks" + taskRepository.nbrTaskByProject(idproject));
		}
	}

}
