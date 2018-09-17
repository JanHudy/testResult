package com.test.test;


import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.bind.annotation.*;


@RestController
public class Server {
    @RequestMapping(value = "/HelloWorld!", method = RequestMethod.GET)
    public String hello() {
        return JSONObject.quote("Hello World!");
    }

    @RequestMapping(value = "/datum", method = RequestMethod.POST)
    public @ResponseBody String updateUser( @RequestParam String datum) {
        java.sql.Date dateSQL = Methods.parse(datum);
        if(dateSQL == null) {
            return JSONObject.quote("Prosimo uporabite format yyyy-MM-dd");
        } else {
            Methods.createObject(dateSQL);
            return JSONObject.quote("Ukaz je bil oddan");
        }
    }
}
