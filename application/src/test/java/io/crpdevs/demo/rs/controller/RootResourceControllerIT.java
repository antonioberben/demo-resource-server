package io.crpdevs.demo.rs.controller;

import io.crpdevs.demo.rs.RSApplication;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = RSApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RootResourceControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    @DisplayName("Test 'findAll' : SUCCESS")
    public void testFindAllRootResource() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/students/Student1/courses/Course1"),
            HttpMethod.GET, entity, String.class);

        String expected = "{id:Course1,name:Spring,description:10 Steps}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
