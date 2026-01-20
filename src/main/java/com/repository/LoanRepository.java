package com.repository;

import com.model.Loan;
import java.util.List;
import java.util.Optional;

public interface LoanRepository {

    //int updateStatusByCodeProduct(Integer codeProduct, String status);
    //Optional<Loan> findByCodeProduct(Integer codeProduct);
    //Loan saveOrUpdate(Loan loan);
    List<Loan> findAll();

}