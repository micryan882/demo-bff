
package com.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.model.Loan;
import com.queries.LoanUnitData;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.RuleUnit;
import org.drools.ruleunits.api.RuleUnitInstance;
@ApplicationScoped
public class LoanRulesServiceImpl implements LoanRulesService {

    @Inject
    RuleUnit<LoanUnitData> loanUnit;

    @Override
    public Loan applyRules(Loan loan) {
        if (loan == null) {
            return null;
        }

        System.out.println("LoanRulesServiceImpl.applyRules: before fire, totalImport=" + loan.getTotalImport());

        LoanUnitData data = new LoanUnitData(DataSource.createStore());
        data.getLoan().add(loan);

        RuleUnitInstance<LoanUnitData> instance = loanUnit.createInstance(data);
        try {
            instance.fire();
        } catch (Exception e) {
            System.err.println("Errore esecuzione regole: " + e.getMessage());
            throw e;
        }

        System.out.println("LoanRulesServiceImpl.applyRules: after fire, totalImport=" + loan.getTotalImport());
        return loan;
    }
}