package com.nasefa.springfinalproject.domain.services.office;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasefa.springfinalproject.domain.repositories.OfficeRepository;
import com.nasefa.springfinalproject.persistence.entities.Office;

@Service
public class OfficeImpl implements IOffice {

    @Autowired
    private OfficeRepository repository;

    @Override
    public List<Office> findAll() {
        return (List<Office>) repository.findAllwithCity();
    }

    @Override
    public Optional<Office> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Office save(Office office) {
        return repository.save(office);
    }

    @Override
    public Optional<Office> update(int id, Office updatedOffice) {
        Optional<Office> optionalOffice = repository.findById(id);
        optionalOffice.ifPresentOrElse(
                office -> {
                    office.setCity(updatedOffice.getCity());
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
                    repository.save(office);
                }, () -> {
                    System.out.println("office not registered");
                });
        return optionalOffice;
    }

    @Override
    public Optional<Office> delete(int id) {
        Optional<Office> optionalProduct = repository.findById(id);
        optionalProduct.ifPresentOrElse(
                product -> {
                    repository.delete(optionalProduct.get());
                    ;
                },
                () -> {
                    System.out.println("office not registered");
                });
        return optionalProduct;
    }

}
