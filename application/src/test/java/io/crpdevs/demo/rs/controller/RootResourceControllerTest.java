package io.crpdevs.demo.rs.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import io.crpdevs.demo.rs.RSApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = { RSApplication.class },
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class RootResourceControllerTest {

    @LocalServerPort
    private int serverPort;

    TestRestTemplate restTemplate = new TestRestTemplate();

    //HttpHeaders headers = new HttpHeaders();

    //@DisplayName("Test 'findAll' : SUCCESS")
    @Test
    public void testFindAllRootResource() {

        //HttpEntity<String> entity = new HttpEntity<>(null, headers);

        /*ResponseEntity<RootResourceOutput> response = restTemplate.exchange(
            createURLWithPort("/api/root-resources"),
            HttpMethod.GET, entity, RootResourceOutput.class);*/

        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + serverPort + "/api/root-resources", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());

        /*RootResourceOutput expected = new RootResourceOutput();
        expected.setName("test");

        assertAll("Returns",
            () -> assertThat(expected).isEqualToComparingFieldByField(response.getBody())
        );*/
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + serverPort + uri;
    }
}
