package com.nasefa.springfinalproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.nasefa.springfinalproject.domain.repositories.GammaRepository;
import com.nasefa.springfinalproject.domain.repositories.ProductRepository;
import com.nasefa.springfinalproject.persistence.entities.Gamma;


@SpringBootApplication
public class SpringfinalprojectApplication implements CommandLineRunner{

	@Autowired
	private GammaRepository gammaRepo;

	@Autowired
	private ProductRepository productRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringfinalprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		gammaList();
	}
	
	@Transactional
	public void gammaList(){
		List<Gamma> gammas = (List<Gamma>) gammaRepo.findAllWithProducts();

		gammas.stream().forEach(gamma -> {
			System.out.println(gamma.getProducts());
			System.out.println(gamma);
		});
	}

}
