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
        if(checkDatabase()) {
            ustvariBazo();
        }
        if(checkTables()) {
            ustvariTable();
        }

        String SQL = "INSERT INTO datum(datum) " + "VALUES(?)";

        try (Connection conn = connect()) {

            PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS);

            pstmt.setDate(1, object.getDatum());
            pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
        }
    }

    public void ustvariBazo() {
        try (Connection conn = connect2()) {
            Statement st =conn.createStatement();
            st.executeUpdate("CREATE DATABASE datum");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ustvariTable() {
        try (Connection conn = connect()) {
            Statement st = conn.createStatement();
            st.executeUpdate("CREATE TABLE datum(id SERIAL, datum DATE)");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkTables() {
        boolean TF = true;
        try (Connection conn = connect()) {
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                if(rs.getString(3).equals("datum")) {
                    TF = false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return TF;
    }

    public boolean checkDatabase() {
        boolean TF = true;
        try (Connection conn = connect()) {
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getCatalogs();
            while (rs.next()) {
                if(rs.getString(1).equals("datum")) {
                    TF = false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return TF;
    }
}
