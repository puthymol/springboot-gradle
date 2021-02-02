package com.softvider.provider.datasource.repository;

import com.softvider.provider.datasource.entity.EmployeeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Integer> {

    @Override
    List<EmployeeEntity> findAll();
}
