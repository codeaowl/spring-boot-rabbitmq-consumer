package com.microservice.consumermicroservice.facade;

import com.microservice.consumermicroservice.entity.Product;

import org.springframework.data.repository.CrudRepository;

public interface ConsumerRepository extends CrudRepository<Product, Integer>{

}
