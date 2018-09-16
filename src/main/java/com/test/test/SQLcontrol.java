package com.test.test;

import java.sql.*;

public class SQLcontrol {
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/datum", "me", "pass");
    }

    public void insertDatum(Object object) {
        String SQL = "INSERT INTO datum(datum) " + "VALUES(?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setDate(1, object.getDatum());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
