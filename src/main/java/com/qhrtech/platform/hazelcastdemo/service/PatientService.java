package com.qhrtech.platform.hazelcastdemo.service;


import com.hazelcast.core.HazelcastInstance;
import com.qhrtech.platform.hazelcastdemo.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class PatientService {

    @Qualifier("hazelcastInstance")
    @Autowired
    private HazelcastInstance hazelcastInstance;

    private final static String HC_MAP = "HC_MAP";



    public String createPatient(Patient newPatient){

        String uuid = UUID.randomUUID().toString();
        newPatient.setId(uuid);
        Map<String, Object> hcMap = hazelcastInstance.getMap(HC_MAP);
        hcMap.put(uuid, newPatient);
        return uuid;
    }


    public Patient getPatient(String patientId) {
        Map<String, Object> hcMap = hazelcastInstance.getMap(HC_MAP);
        Patient patient = (Patient) hcMap.get(patientId);
        return patient;
    }


    public Patient updatePatient(Patient patient) {
        Map<String, Object> hcMap = hazelcastInstance.getMap(HC_MAP);
        hcMap.put(patient.getId(), patient);
        return patient;
    }
}
