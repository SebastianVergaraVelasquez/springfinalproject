package com.nasefa.springfinalproject.domain.services.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.EmployeePositionRepository;
import com.nasefa.springfinalproject.domain.repositories.EmployeeRepository;
import com.nasefa.springfinalproject.domain.repositories.OfficeRepository;
import com.nasefa.springfinalproject.persistence.entities.EmployeePosition;
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
    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> update(int id, Employee updatedEmployee, int idBoss, int idOffice, int idPosition) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        employeeOptional.ifPresentOrElse(
                employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setLastName(updatedEmployee.getLastName());
                    employee.setEmail(updatedEmployee.getEmail());
                    Optional<EmployeePosition> optionalPosition = emPositionRepository.findById(idPosition);
                    employee.setPosition(optionalPosition.get());
                    Optional<Office> optionalOffice = officeRepository.findById(idOffice);
                    employee.setOffice(optionalOffice.get());
                    Optional<Employee> optionalBoss = employeeRepository.findById(idBoss);
                    employee.setBoss(optionalBoss.get());

                    employeeRepository.save(employee);
                }, () -> {
                    System.out.println("office not registered");
                });
        return employeeOptional;
    }

    @Override
    public Optional<Employee> delete(int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        optionalEmployee.ifPresentOrElse(
                product -> {
                    employeeRepository.delete(optionalEmployee.get());
                    ;
                },
                () -> {
                    System.out.println("office not registered");
                });
        return optionalEmployee;
    }

    @Override
    public List<Employee> findAllByOffice(Office office) {
        Optional<Office> officeOptional = officeRepository.findById(office.getId());
        return employeeRepository.findByOffice(officeOptional.get());
    }

}
