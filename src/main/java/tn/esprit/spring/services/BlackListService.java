package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.BlackList;

public interface BlackListService {
void addEmployeeInBlackList();
void AffecterEmployee();
List <BlackList> retrieveAllEmployeesBlackList();
void setFormation(int idBlackListEmployee);
}
