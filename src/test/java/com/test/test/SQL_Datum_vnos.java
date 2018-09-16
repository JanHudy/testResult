package com.test.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
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
        HttpUriRequest request = new HttpPut("http://localhost:8080/datum?datum=1111-11-11");
        HttpResponse response = HttpClientBuilder.create().build().execute( request );
        Date date = getDatum();

        Assert.assertEquals(Date.valueOf("1111-11-11"), date);
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/datum", "me", "pass");
    }

    public Date  getDatum() {
        String SQL = "SELECT * FROM datum";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            return rs.getDate("datum");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
