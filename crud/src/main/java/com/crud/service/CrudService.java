package com.crud.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.repository.CrudRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CrudService {
	private final CrudRepository crudRepository;
}
