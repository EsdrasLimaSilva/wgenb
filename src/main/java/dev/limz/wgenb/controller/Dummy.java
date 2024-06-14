package dev.limz.wgenb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Dummy {

    @GetMapping("/")
    public Object getHi(){
        return new ResponseEntity<String>("Hi my friend", HttpStatus.OK);
    }
}
