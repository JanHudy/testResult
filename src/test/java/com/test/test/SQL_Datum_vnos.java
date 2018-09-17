package com.test.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

public class SQL_Datum_vnos {

    public void main(System[] args) throws IOException {
        ali_vrne_datum();
    }

    @Test
    public void ali_vrne_datum()  throws IOException {
        HttpUriRequest request = new HttpPost("http://localhost:8080/datum?datum=0001-1-10");
        HttpResponse response = HttpClientBuilder.create().build().execute( request );
        Date date = getDatum();

        Assert.assertEquals(Date.valueOf("0001-1-10"), date);

        deleteDatum();
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/datum", "postgres", "postgres");
    }

    public Date  getDatum() {
        String SQL = "SELECT * FROM datum";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery(SQL);
            rs.last();
            return rs.getDate("datum");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void deleteDatum() {
        String SQL = "DELETE FROM datum where DATE(datum) = '0001-01-10'";
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(SQL);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
