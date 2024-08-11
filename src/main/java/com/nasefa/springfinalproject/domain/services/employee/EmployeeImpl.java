package com.nasefa.springfinalproject.domain.services.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.EmployeePositionRepository;
import com.nasefa.springfinalproject.domain.repositories.EmployeeRepository;
import com.nasefa.springfinalproject.domain.repositories.OfficeRepository;
import com.nasefa.springfinalproject.persistence.entities.EmployeePosition;
import com.nasefa.springfinalproject.persistence.entities.client.Client;
import com.nasefa.springfinalproject.persistence.entities.employee.Employee;
import com.nasefa.springfinalproject.persistence.entities.office.Office;

@Service
public class EmployeeImpl implements IEmployee {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeePositionRepository emPositionRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllWithOfficeAndPosition();
    }

    @Override
    public List<Employee> findSalesRep() {
        return employeeRepository.salesReps();
    }

    @Override
    public List<Employee> findAllByOffice(Office office) {
        Optional<Office> officeOptional = officeRepository.findById(office.getId());
        return employeeRepository.findByOffice(officeOptional.get());
    }

    @Override
    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee, int idBoss, int idOffice, int idPosition) {
        Optional<Employee> bossOpt = employeeRepository.findById(idBoss);
        Optional<Office> officeOptional = officeRepository.findById(idOffice);
        Optional<EmployeePosition> positionOptional = emPositionRepository.findById(idPosition);

        if (bossOpt.isEmpty()) {
            return new Employee();
        }
        employee.setBoss(bossOpt.get());
        employee.setOffice(officeOptional.get());
        employee.setPosition(positionOptional.get());

        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> update(int id, Employee updatedEmployee, int idBoss, int idOffice, int idPosition) {

        Optional<Employee> bossOpt = employeeRepository.findById(idBoss);
        if (bossOpt.isEmpty()) {
            return Optional.empty();
        }
        Employee employee = employeeRepository.findById(id).get();

        employee.setName(updatedEmployee.getName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Optional<EmployeePosition> optionalPosition = emPositionRepository.findById(idPosition);
        employee.setPosition(optionalPosition.get());
        Optional<Office> optionalOffice = officeRepository.findById(idOffice);
        employee.setOffice(optionalOffice.get());
        employee.setBoss(bossOpt.get());

        Employee savedEmployee = employeeRepository.save(employee);
        return Optional.of(savedEmployee);
    }

    @Override
    public Optional<Employee> delete(int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            return Optional.empty();
        }
        List<Employee> subordinates = employeeRepository.findByBoss(optionalEmployee.get());
        for (Employee subordinate : subordinates) {
            subordinate.setBoss(null);
            employeeRepository.save(subordinate);
        }
        
        for (Client client : optionalEmployee.get().getClients()) {
            client.setSalesRep(null);
        }
        optionalEmployee.get().getClients().clear();
        employeeRepository.delete(optionalEmployee.get());
        return optionalEmployee;
    }

    @Override
    public List<Employee> findEmployeesWithPendingOrders() {
        return employeeRepository.findEmployeesWithPendingOrders();
    }

}
