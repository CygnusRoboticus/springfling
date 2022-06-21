package com.example.springfling;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class WreckController {
    @GetMapping("/wrecks")
    public WreckModel[] index() {
        var wreck1 = new WreckModel("Pants");
        var wreck2 = new WreckModel("Skirts");
        WreckModel[] wrecks = {wreck1, wreck2};

        return wrecks;
    }
}