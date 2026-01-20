package com.service;

import com.model.Loan;
import com.repository.LoanRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class LoanApprovalService implements LoanApprovalServiceInterface {

    @Inject
    LoanRepository loanRepository;

    @Override
    public Loan approveLoan(Loan loan) {
        if (loan == null) return null;
        System.out.println("Prestito approvato: " + loan);
        return loan;
    }

    // Metodo aggiuntivo per ottenere tutti i loan dal repository
    public List<Loan> findAllLoans() {
        return loanRepository.findAll();
    }
}