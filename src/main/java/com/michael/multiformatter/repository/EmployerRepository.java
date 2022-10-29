package com.michael.multiformatter.repository;

import com.michael.multiformatter.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
    @Override
    List<Employer> findAll();

    @Override
    Optional<Employer> findById(Long aLong);
}
