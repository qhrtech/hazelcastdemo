package com.qhrtech.platform.hazelcastdemo.controllers;


import com.qhrtech.platform.hazelcastdemo.domain.Gender;
import com.qhrtech.platform.hazelcastdemo.domain.Patient;
import com.qhrtech.platform.hazelcastdemo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Calendar;

@RestController
@RequestMapping(value = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {
    @Autowired
    private PatientService patientService;


    @RequestMapping(method = RequestMethod.GET, value = "/{patientId}")
    public ResponseEntity getPatient(@PathVariable String patientId){
        Patient patient = patientService.getPatient(patientId);
        if (patient == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PatientId:" + patientId + " does not exist");
        }else{
            return ResponseEntity.ok(patient);
        }

    }


    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity createPatient(@RequestBody Patient newPatient){
        String id = patientService.createPatient(newPatient);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }


    @RequestMapping(method = RequestMethod.PATCH, value = "/{patientId}")
    public ResponseEntity updatePatient(@PathVariable String patientId, @RequestBody Patient toUpdate){
        Patient patient = patientService.getPatient(patientId);
        if(patient != null){
            patient = patientService.updatePatient(toUpdate);
            return ResponseEntity.ok(patient);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PatientId:" + patientId + " does not exist");
        }
    }


}
