package com.qhrtech.platform.hazelcastdemo.controllers;


import com.qhrtech.platform.hazelcastdemo.hzcast.HazelcastClusterInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HazelcastHealthControllerITest {

    private URL base;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;


    @Before
    public void setUp() throws Exception{
        this.base = new URL("http://localhost:" + port + "/hazelcast/");
    }


    @Test
    public void isLeader() throws Exception {
        ResponseEntity<Boolean> response = template.getForEntity(base.toString() + "leader",Boolean.class);
        assertThat(response.getBody(), equalTo(true));
    }

    @Test
    public void isQuorum() throws Exception {
        ResponseEntity<Boolean> response = template.getForEntity(base.toString() + "quorum",Boolean.class);
        assertThat(response.getBody(), equalTo(true));
    }


    @Test
    public void getClusterInfo() throws Exception {
        ResponseEntity<HazelcastClusterInfo> response = template.getForEntity(base.toString() + "cluster",HazelcastClusterInfo.class);
        HazelcastClusterInfo cluster = response.getBody();
        assertThat(cluster.getClusterSize(), equalTo(1));

    }


}
