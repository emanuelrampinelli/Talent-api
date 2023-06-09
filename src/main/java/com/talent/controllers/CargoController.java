package com.talent.controllers;

import com.talent.dto.CargoDTO;
import com.talent.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ResponseEntity<Object> findByCargo(@RequestBody CargoDTO cargoDTO){
        return ResponseEntity.status(HttpStatus.OK).body(cargoService.findByCargo(cargoDTO));
    }

    @PostMapping
    public ResponseEntity<Object> saveCargo(@RequestBody CargoDTO cargoDTO){
        return ResponseEntity.status(HttpStatus.OK).body(cargoService.saveCargo(cargoDTO));
    }

    @PutMapping
    public ResponseEntity<Object> updateCargo(@RequestBody CargoDTO cargoDTO){
        return ResponseEntity.status(HttpStatus.OK).body(cargoService.updateCargo(cargoDTO));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCargo(@RequestBody CargoDTO cargoDTO){
        return ResponseEntity.status(HttpStatus.OK).body(cargoService.deleteCargo(cargoDTO));
    }

}
