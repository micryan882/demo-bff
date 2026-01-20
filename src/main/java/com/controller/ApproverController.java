// File: `src/main/java/com/controller/ApproverController.java`
package com.controller;

import com.repository.LoanRepository;
import com.model.Loan;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import java.util.Optional;

@Path("/approvals")
public class ApproverController {

    @Inject
    LoanRepository loanRepository;

    @POST
    @Path("/{codeProduct}/approve")
    @Transactional
    public Response approve(@PathParam("codeProduct") Integer codeProduct) {
/*        int updated = loanRepository.updateStatusByCodeProduct(codeProduct, "APPROVED");
        if (updated == 0) {
            Optional<Loan> maybe = loanRepository.findByCodeProduct(codeProduct);
            if (maybe.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            Loan loan = maybe.get();
            loan.setStatus("APPROVED");
            loanRepository.saveOrUpdate(loan);
        }*/
        return Response.ok().build();
    }
}