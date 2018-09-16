package com.test.test;


import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.bind.annotation.*;


@RestController
public class Server {
    @RequestMapping(value = "/HelloWorld", method = RequestMethod.GET)
    public String hello() {
        return JSONObject.quote("Hello World");
    }

    @RequestMapping(value = "/datum", method = RequestMethod.PUT)
    public @ResponseBody String updateUser( @RequestParam String datum) {
        java.sql.Date dateSQL = Methods.parse(datum);
        Methods.createObject(dateSQL);
        return JSONObject.quote("Ukaz je bil oddan");
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public String options () {
        return JSONObject.quote("PUT: Prosimo uporabite format: datum?datum=dd-MM-1991");
    }
}
