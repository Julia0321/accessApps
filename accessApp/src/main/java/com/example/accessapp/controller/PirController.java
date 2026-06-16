package com.example.accessapp.controller;

import com.example.accessapp.model.Pir;
import com.example.accessapp.service.PirServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pir")
public class PirController {

    private final PirServiceImpl pirServiceImpl;

    PirController(PirServiceImpl pirService) {
        this.pirServiceImpl = pirService;
    }

    @GetMapping("/{pirId}")
    public ResponseEntity<String> updatePirData(@PathVariable Long pirId, @RequestBody Pir pir) {
        pirServiceImpl.updatePirData(pirId, pir);
        return ResponseEntity.status(HttpStatus.OK).body("PIR STATUS UPDATED");
    }

}
