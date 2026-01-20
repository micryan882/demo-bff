package com.queries;

import com.model.Loan;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;


/**
Contenitore su cui girano le regole. Kogito userà la lista di loan quando esegue regole
 */

/**
 In questo progetto non viene chiamato.. In tal caso modificare drl con unit e toglere variabili di processo dal bpmn2. */
public class LoanUnitData  implements RuleUnitData {
    private DataStore<Loan> loan;

    public LoanUnitData() {
        this(DataSource.createStore());

    }

    public LoanUnitData(DataStore<Loan> loan) {
        this.loan = loan;
    }

    public DataStore<Loan> getLoan() {
        return loan;
    }

    public void setLoan(DataStore<Loan> loan) {
        this.loan = loan;
    }
}
