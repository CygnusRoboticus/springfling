package com.example.springfling;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WreckController {
    @GetMapping("/wrecks")
    public WreckModel[] index() {
        var wreck1 = new WreckModel() {{
            Name = "Pants";
        }};
        var wreck2 = new WreckModel("Skirts");
        WreckModel[] wrecks = { wreck1, wreck2 };
        return wrecks;
    }
}