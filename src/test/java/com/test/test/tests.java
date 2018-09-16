package com.test.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class tests {

    public void main(System[] args) throws IOException {
        ali_vrne_HelloWorld();
    }
    @Test
    public void ali_vrne_HelloWorld() throws IOException {

        HttpUriRequest request = new HttpGet( "http://localhost:8080/HelloWorld" );

        HttpResponse response = HttpClientBuilder.create().build().execute( request );
        String responseString = new BasicResponseHandler().handleResponse(response);

        Assert.assertEquals("\"Hello World\"", responseString);
    }
}
