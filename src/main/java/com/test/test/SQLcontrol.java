package com.test.test;

import java.sql.*;

public class SQLcontrol {
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/datum",
                "postgres",
                "postgres");
    }

    public Connection connect2() throws  SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/",
                "postgres",
                "postgres");
    }


    public void insertDatum(Object object) {
        String SQL = "INSERT INTO datum(datum) " + "VALUES(?)";

        try (Connection conn = connect()) {

            PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS);

            pstmt.setDate(1, object.getDatum());

            pstmt.executeUpdate();

            } catch (SQLException ex) {
                //System.out.println(ex.getMessage());
                ustvariBazo(object);
        }

    }

    public void ustvariBazo(Object object) {
        try (Connection conn = connect2()) {

            Statement st =conn.createStatement();
            st.executeUpdate("CREATE DATABASE datum");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn = connect()) {
            Statement st = conn.createStatement();
            st.executeUpdate("CREATE TABLE datum(id SERIAL, datum DATE)");
            st.close();
            insertDatum(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
