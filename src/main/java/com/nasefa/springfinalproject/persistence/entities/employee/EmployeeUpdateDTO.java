package com.nasefa.springfinalproject.persistence.entities.employee;

public class EmployeeUpdateDTO {
    private Employee updatedEmployee;
    private int idBoss;
    private int idPosition;
    private int idOffice;
    
    public Employee getUpdatedEmployee() {
        return updatedEmployee;
    }
    public void setUpdatedEmployee(Employee updatedEmployee) {
        this.updatedEmployee = updatedEmployee;
    }
    public int getIdBoss() {
        return idBoss;
    }
    public void setIdBoss(int idBoss) {
        this.idBoss = idBoss;
    }
    public int getIdPosition() {
        return idPosition;
    }
    public void setIdPosition(int idPosition) {
        this.idPosition = idPosition;
    }
    public int getIdOffice() {
        return idOffice;
    }
    public void setIdOffice(int idOffice) {
        this.idOffice = idOffice;
    }

}
