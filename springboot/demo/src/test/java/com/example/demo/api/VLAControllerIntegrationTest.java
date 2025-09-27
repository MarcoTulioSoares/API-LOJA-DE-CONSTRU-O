package com.example.demo.api;

import com.example.demo.api.service.VLAService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VLAControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private VLAService vlaService;

    @AfterEach
    void tearDown() {
        vlaService.stopScheduler();
    }

    @Test
    void startEndpointReturnsIdAndStartsScheduler() {
        ResponseEntity<Map> response = restTemplate.postForEntity("/api/VLA/start", null, Map.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("idLancamento"));
        assertNotNull(response.getBody().get("idLancamento"));
        assertTrue(vlaService.isSchedulerRunning(), "Scheduler should be running after start");
    }
}
