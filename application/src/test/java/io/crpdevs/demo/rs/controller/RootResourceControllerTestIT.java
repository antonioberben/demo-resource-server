package io.crpdevs.demo.rs.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.crpdevs.demo.rs.RSApplication;
import io.crpdevs.demo.rs.persistence.entity.root.RootResource;
import io.crpdevs.demo.rs.persistence.repository.RootResourceRepository;
import io.crpdevs.demo.rs.representation.root.RootResourceInput;
import io.crpdevs.demo.rs.representation.root.RootResourceOutput;


@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = { RSApplication.class },
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class RootResourceControllerTestIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RootResourceRepository rootResourceRepository;

    @LocalServerPort
    private int localPort;

    private HttpHeaders headers = new HttpHeaders();

    @BeforeEach
    void setUp() {
        mongoTemplate.dropCollection("root-resource");
    }

    @Nested
    @DisplayName("RootResource: Create")
    class RootResourceCreate {
        @Test
        @DisplayName("Create Root Resource and success")
        void createRootResource() {
            //Given
            RootResourceInput input = new RootResourceInput();
            input.setName("test - Create");
            HttpEntity<RootResourceInput> httpEntity = new HttpEntity<>(input, headers);

            //When
            ResponseEntity<RootResourceOutput> response = restTemplate
                    .postForEntity(createURLWithPort("/api/root-resources"), httpEntity, RootResourceOutput.class);

            //Then
            RootResourceOutput expected = new RootResourceOutput();
            expected.setName(input.getName());
            assertAll("Response",
                    () -> assertEquals(response.getStatusCode(), CREATED),
                    () -> assertThat(response.getBody()).isEqualToIgnoringGivenFields(expected,"id")
            );
        }

        @Test
        @DisplayName("Create RootResource with wrong input and fail")
        void createRootResourceNullName() {
            //Given
            RootResourceInput input = new RootResourceInput();
            input.setName(null);
            HttpEntity<RootResourceInput> httpEntity = new HttpEntity<>(input, headers);

            //When
            ResponseEntity<RootResourceOutput> response = restTemplate
                .postForEntity(createURLWithPort("/api/root-resources"), httpEntity, RootResourceOutput.class);

            //Then
            RootResourceOutput expected = new RootResourceOutput();
            expected.setName(input.getName());
            assertAll("Response",
                () -> assertEquals(response.getStatusCode(), BAD_REQUEST)
            );
        }
    }

    @Nested
    @DisplayName("RootResource: FindAll")
    class RootResourceFindAll {

        @Test
        @DisplayName("Find all resources and success")
        public void findAllRootResource() {
            //Given
            RootResource persisted = new RootResource();
            persisted.setName("test - FindAll");
            rootResourceRepository.save(persisted);
            //When
            ResponseEntity<RootResourceOutput[]> response = restTemplate
                    .getForEntity(createURLWithPort("/api/root-resources"), RootResourceOutput[].class);
            //Then
            RootResourceOutput expected = new RootResourceOutput();
            expected.setName(persisted.getName());
            RootResourceOutput responseResource = (RootResourceOutput) response.getBody()[0];
            assertAll("Response",
                    () -> assertEquals(response.getStatusCode(), OK),
                    () -> assertThat(expected).isEqualToIgnoringGivenFields(responseResource, "id"),
                    () -> assertEquals(response.getBody().length, 1)
            );
        }
    }

    @Nested
    @DisplayName("RootResource: FindOne")
    class RootResourceFindOne {

        @Test
        @DisplayName("Find one resource and success")
        public void findOneRootResource() {
            //Given
            RootResource persisted = new RootResource();
            persisted.setName("test - FindOne");
            RootResource saved = rootResourceRepository.save(persisted);
            //When
            ResponseEntity<RootResourceOutput> response = restTemplate
                    .getForEntity(createURLWithPort("/api/root-resources/" + saved.getId()), RootResourceOutput.class);
            //Then
            RootResourceOutput expected = new RootResourceOutput();
            expected.setName(persisted.getName());
            RootResourceOutput responseResource = (RootResourceOutput) response.getBody();
            assertAll("Response",
                    () -> assertEquals(response.getStatusCode(), OK),
                    () -> assertThat(expected).isEqualToIgnoringGivenFields(responseResource, "id")
            );
        }

        @Test
        @DisplayName("Find one resource with non existing ID and fail")
        public void findOneRootResourceWithNonExistingId() {
            //Given
            RootResource persisted = new RootResource();
            persisted.setName("test - FindOne");
            RootResource saved = rootResourceRepository.save(persisted);
            String nonExistingId = "XXX";
            //When
            ResponseEntity<RootResourceOutput> response = restTemplate
                .getForEntity(createURLWithPort("/api/root-resources/" + nonExistingId), RootResourceOutput.class);
            //Then
            assertAll("Response",
                () -> assertEquals(response.getStatusCode(), NOT_FOUND)
            );
        }
    }

    @Nested
    @DisplayName("RootResource: Update")
    class RootResourceUpdate {

        @Test
        @DisplayName("Test 'findAll' : SUCCESS")
        public void updateRootResource() {
            //Given
            RootResource persisted = new RootResource();
            persisted.setName("test - Update");
            RootResource saved = rootResourceRepository.save(persisted);
            RootResourceInput input = new RootResourceInput();
            input.setName("test - Update new");
            HttpEntity<RootResourceInput> httpEntity = new HttpEntity<>(input, headers);
            //When
            ResponseEntity<RootResourceOutput> response = restTemplate
                .exchange(createURLWithPort("/api/root-resources/" + saved.getId()),
                    HttpMethod.PUT, httpEntity, RootResourceOutput.class);
            //Then
            RootResourceOutput expected = new RootResourceOutput();
            expected.setName(input.getName());
            RootResourceOutput responseResource = (RootResourceOutput) response.getBody();
            assertAll("Response",
                    () -> assertEquals(response.getStatusCode(), OK),
                    () -> assertThat(expected).isEqualToIgnoringGivenFields(responseResource, "id"),
                    () -> assertThat(rootResourceRepository.findOne(saved.getId()))
                        .isEqualToIgnoringGivenFields(responseResource, "id")
            );
        }
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + localPort + uri;
    }
}
