package com.nasefa.springfinalproject.domain.services.office;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.CityRepository;
import com.nasefa.springfinalproject.domain.repositories.OfficeRepository;
import com.nasefa.springfinalproject.persistence.entities.City;
import com.nasefa.springfinalproject.persistence.entities.office.Office;

@Service
public class OfficeImpl implements IOffice {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired CityRepository cityRepository;

    @Override
    public List<Office> findAll() {
        return (List<Office>) officeRepository.findAllwithCity();
    }

    @Override
    public Optional<Office> findById(int id) {
        return officeRepository.findById(id);
    }

    @Override
    public Office save(Office office) {
        return officeRepository.save(office);
    }

    @Override
    public Optional<Office> update(int id, Office updatedOffice, int idCity) {
        Optional<Office> optionalOffice = officeRepository.findById(id);
        optionalOffice.ifPresentOrElse(
                office -> {
                    Optional<City> cityOptional = cityRepository.findById(idCity);
                    office.setCity(cityOptional.get());
                    office.setAddres(updatedOffice.getAddres());
                    office.setTelephone(updatedOffice.getTelephone());
                    if (updatedOffice.getEmployees() != null) {
                        // Limpiar la lista existente
                        office.getEmployees().clear();
                        // Agregar los productos nuevos (si no está vacía)
                        if (!updatedOffice.getEmployees().isEmpty()) {
                            office.getEmployees().addAll(updatedOffice.getEmployees());
                        }
                    }
                    officeRepository.save(office);
                }, () -> {
                    System.out.println("office not registered");
                });
        return optionalOffice;
    }

    @Override
    public Optional<Office> delete(int id) {
        Optional<Office> optionalProduct = officeRepository.findById(id);
        optionalProduct.ifPresentOrElse(
                product -> {
                    officeRepository.delete(optionalProduct.get());
                    ;
                },
                () -> {
                    System.out.println("office not registered");
                });
        return optionalProduct;
    }

}
