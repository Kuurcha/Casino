package com.dbtest.demotest.controller;

import com.dbtest.demotest.model.CasinoBuilding;
import com.dbtest.demotest.model.DTO.SlotMachineDTO;
import com.dbtest.demotest.model.SlotMachine;
import com.dbtest.demotest.model.SlotMachine;
import com.dbtest.demotest.model.mapper.DTOToEntity;
import com.dbtest.demotest.repository.CasinoBuildingRepository;
import com.dbtest.demotest.repository.SlotMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SlotMachineController extends LinkController<SlotMachine, CasinoBuilding>{
    @Autowired
    public  SlotMachineController(SlotMachineRepository slotMachineRepository, CasinoBuildingRepository casinoBuildingRepository){
        this.repository = slotMachineRepository;
        this.linkRepository = casinoBuildingRepository;
    }



    @PostMapping(value = "/SlotMachine")
    public ResponseEntity<?> createCasinoBuilding(@RequestBody SlotMachineDTO slotMachineDTO){
        System.out.println(slotMachineDTO.toString());
        SlotMachine result = DTOToEntity.SlotMachineFromDTO(slotMachineDTO);
        Integer fkcasino_id = slotMachineDTO.getfk_casino_id();
        ResponseEntity<?> responseEntity = this.getLinkedObjectById(fkcasino_id);
        System.out.println(responseEntity.getStatusCode());
        CasinoBuilding casinoBuilding = (CasinoBuilding) responseEntity.getBody();
        result.setCasinoBuildingSlot(casinoBuilding);
        return this.create(result);
    }

    @GetMapping(value = "/SlotMachine")
    public ResponseEntity<List<SlotMachine>> readCasinoBuildings(){
        return this.read();
    }
    @GetMapping(value = "/SlotMachine/{id}")
    public ResponseEntity<SlotMachine> readCasinoBuildingById(@PathVariable(name = "id") int id) {
        return read((id));
    }
    @PutMapping(value = "/SlotMachine/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCasinoBuilding(@PathVariable(name = "id") int id, @RequestBody SlotMachineDTO slotMachineDTO) {
       SlotMachine slotMachine = DTOToEntity.SlotMachineFromDTO(slotMachineDTO);
        Integer fk_casino_id = slotMachineDTO.getfk_casino_id();
        System.out.println("update !! " + slotMachineDTO.toString());
        ResponseEntity<?> responseEntityLink = this.getLinkedObjectById(fk_casino_id);
        CasinoBuilding casinoBuilding = (CasinoBuilding) responseEntityLink.getBody();
        slotMachine.setCasinoBuildingSlot(casinoBuilding);
        slotMachine.setId(id);
        ResponseEntity<?> responseEntity = update(id, slotMachine);
        System.out.println("id" + id);
        return responseEntity;
    }
    @DeleteMapping(value = "/SlotMachine/{id}")
    public ResponseEntity<?> deleteCasinoBuildingById(@PathVariable(name = "id") int id) {
        return delete(id);
    }
}
