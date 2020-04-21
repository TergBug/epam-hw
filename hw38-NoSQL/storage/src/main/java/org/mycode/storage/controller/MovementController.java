package org.mycode.storage.controller;

import org.mycode.storage.dto.MovementDto;
import org.mycode.storage.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/movements")
public class MovementController {
    private MovementService service;

    @Autowired
    public MovementController(MovementService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MovementDto>> getAll(){
        return new ResponseEntity<>(service.getAllMovements(), HttpStatus.OK);
    }
}
