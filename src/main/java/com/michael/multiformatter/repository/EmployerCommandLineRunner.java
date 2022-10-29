package com.michael.multiformatter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EmployerCommandLineRunner implements CommandLineRunner {

    @Autowired
    EmployerRepository employerRepository;

    @Override
    public void run(String... args) throws Exception {
//        employerRepository.save(new Employer(1l, "firstName1", "lastName1", "address1"));
//        employerRepository.save(new Employer(2l, "firstName2", "lastName2", "address2"));
//        employerRepository.save(new Employer(3l, "firstName3", "lastName3", "address3"));
//        employerRepository.save(new Employer(4l, "firstName4", "lastName4", "address4"));
//        employerRepository.save(new Employer(4l, "firstName5", "lastName5", "address5"));
    }
}
