
package com.repository;

import com.model.Loan;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JdbcLoanRepository implements LoanRepository {

    @Inject
    DataSource dataSource;



    @Override
    public List<Loan> findAll() {
        String sql = "SELECT * FROM loan";
        List<Loan> list = new ArrayList<>();
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }



    private Loan map(ResultSet rs) throws SQLException {
        Loan l = new Loan();
        //l.setId(rs.getString("id"));
        l.setCodeProduct(rs.getInt("codeProduct"));
        l.setQuantity(rs.getInt("quantity"));
        l.setUnitPrice(rs.getDouble("unitPrice"));
        return l;
    }
}