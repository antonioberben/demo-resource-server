package io.crpdevs.demo.rs.controller;

import io.crpdevs.demo.rs.RSApplication;
import io.crpdevs.demo.rs.representation.root.RootResourceOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = { RSApplication.class },
        webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = "server.port=8080")
public class RootResourceControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @DisplayName("Test 'findAll' : SUCCESS")
    @Test
    void testFindAllRootResource() {

        //HttpEntity<String> entity = new HttpEntity<>(null, headers);

        /*ResponseEntity<RootResourceOutput> response = restTemplate.exchange(
            createURLWithPort("/api/root-resources"),
            HttpMethod.GET, entity, RootResourceOutput.class);*/

        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + port + "/api/root-resources", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());

        /*RootResourceOutput expected = new RootResourceOutput();
        expected.setName("test");

        assertAll("Returns",
            () -> assertThat(expected).isEqualToComparingFieldByField(response.getBody())
        );*/
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
