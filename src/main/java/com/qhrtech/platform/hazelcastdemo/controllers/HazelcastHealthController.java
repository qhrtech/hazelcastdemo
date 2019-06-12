package com.qhrtech.platform.hazelcastdemo.controllers;


import com.qhrtech.platform.hazelcastdemo.hzcast.HazelcastClusterInfo;
import com.qhrtech.platform.hazelcastdemo.service.HazelcastHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hazelcast", produces = MediaType.APPLICATION_JSON_VALUE)
public class HazelcastHealthController {

    @Autowired
    private HazelcastHealthService hhs;

    @RequestMapping(method = RequestMethod.GET, value = "/cluster")
    public ResponseEntity getClusterInfo(){
        HazelcastClusterInfo clusterInfo = hhs.getClusterDetails();
        return ResponseEntity.ok(clusterInfo);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/leader")
    public ResponseEntity isLeader(){
        boolean isLeader = hhs.isLeader();
        return ResponseEntity.ok(isLeader);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/quorum")
    public ResponseEntity isQuorum(){
        boolean quorum = hhs.isQuorum();
        return ResponseEntity.ok(quorum);
    }

}
