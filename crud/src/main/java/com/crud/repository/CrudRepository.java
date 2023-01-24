package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.CrudEntity;

public interface CrudRepository extends JpaRepository<CrudEntity, Long> {

}
