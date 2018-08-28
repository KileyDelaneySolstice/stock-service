package com.kileydelaney.repository;

import com.kileydelaney.model.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {

}
