package com.example.accessapp.controller;

import com.example.accessapp.model.Kontrakton;
import com.example.accessapp.service.KontraktonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kontrakton")
public class KontraktonController {

    KontraktonService kontraktonService;

    KontraktonController(KontraktonService kontraktonService) {
        this.kontraktonService = kontraktonService;
    }

    @GetMapping("/{kontraktonId}")
    public ResponseEntity<String> updateKontraktonData(@PathVariable Long kontraktonId, @RequestBody Kontrakton kontrakton) {
        kontraktonService.updateKontraktonData(kontraktonId, kontrakton);
        return ResponseEntity.status(HttpStatus.OK).body("KONTAKTRON STATUS WAS UPDATED");
    }
}
