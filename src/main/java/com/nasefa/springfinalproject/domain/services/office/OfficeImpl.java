package com.nasefa.springfinalproject.domain.services.office;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.CityRepository;
import com.nasefa.springfinalproject.domain.repositories.OfficeRepository;
import com.nasefa.springfinalproject.persistence.entities.City;
import com.nasefa.springfinalproject.persistence.entities.employee.Employee;
import com.nasefa.springfinalproject.persistence.entities.office.Office;

@Service
public class OfficeImpl implements IOffice {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<Office> findAll() {
        return (List<Office>) officeRepository.findAllwithCity();
    }

    @Override
    public Optional<Office> findById(int id) {
        return officeRepository.findById(id);
    }

    @Override
    public Office save(Office office, int idCity) {
        Optional<City> optionalCity = cityRepository.findById(idCity);
        office.setCity(optionalCity.get());
        return officeRepository.save(office);
    }

    @Override
    public Optional<Office> update(int id, Office updatedOffice, int idCity) {
        Optional<Office> optionalOffice = officeRepository.findById(id);
        if (optionalOffice.isEmpty()) {
            return Optional.empty();
        }
        Optional<City> cityOptional = cityRepository.findById(idCity);
        Office office = optionalOffice.get();
        office.setCity(cityOptional.get());
        office.setAddres(updatedOffice.getAddres());
        office.setTelephone(updatedOffice.getTelephone());
        if (updatedOffice.getEmployees() != null) {
            office.getEmployees().clear();
            if (!updatedOffice.getEmployees().isEmpty()) {
                office.getEmployees().addAll(updatedOffice.getEmployees());
            }
        }
        Office officeSaved = officeRepository.save(office);
        return Optional.of(officeSaved);
    }

    @Override
    public Optional<Office> delete(int id) {
        Optional<Office> optionalOffice = officeRepository.findById(id);
        if (optionalOffice.isEmpty()) {
            return Optional.empty();
        }
        for (Employee employee : optionalOffice.get().getEmployees()) {
            employee.setOffice(null);
        }
        officeRepository.delete(optionalOffice.get());
        return optionalOffice;
    }

}
