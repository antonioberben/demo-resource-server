package io.crpdevs.demo.rs.controller;

import io.crpdevs.demo.rs.exception.ResourceNotFoundException;
import io.crpdevs.demo.rs.exception.view.ApiExtendedErrorsView;
import io.crpdevs.demo.rs.exception.view.ApiSimpleErrorsView;
import io.crpdevs.demo.rs.mapper.root.RootResourceMapper;
import io.crpdevs.demo.rs.persistence.entity.root.RootResource;
import io.crpdevs.demo.rs.representation.root.RootResourceInput;
import io.crpdevs.demo.rs.representation.root.RootResourceOutput;
import io.crpdevs.demo.rs.service.RootResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static io.crpdevs.demo.rs.controller.ControllerConstants.REST_BASE_PATH;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;


@RestController
@RequestMapping(REST_BASE_PATH + "/root-resources")
@Data
@Slf4j
@Api(
    value = REST_BASE_PATH + "/root-resources",
    description = "Endpoint for Root Resource listing"
)
public class RootResourceController {

    private static final String WILDCARD_ATTR_NAME_RESOURCE_ID = "root-resource-id";
    private static final String WILDCARD_REQUEST_MAPPING_VALUE = "/{" + WILDCARD_ATTR_NAME_RESOURCE_ID + "}";

    @Autowired
    private RootResourceMapper rootResourceMapper;

    @Autowired
    private RootResourceService rootResourceService;

    /**
     * Find all resources.
     *
     * @return List of resources
     */
    @ApiOperation(
        value = "Lists all RootResources",
        notes = "Lists all RootResources",
        response = RootResourceOutput.class,
        responseContainer = "List",
        nickname = "findAllRootResources"
    )
    @ApiResponses(value = {
        @ApiResponse(code = HTTP_OK, message = "Successful retrieval of RootResource", response = RootResourceOutput.class,
            responseContainer = "List"),
        @ApiResponse(code = HTTP_UNAUTHORIZED, message = "Unauthorized"),
        @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "Internal server error", response = ApiSimpleErrorsView.class)
    })
    @RequestMapping(
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity findAll() {
        log.info("This is a Log trace test");
        List<RootResource> rootResources = rootResourceService.findAll();
        List<RootResourceOutput> rootResourceOutputs = rootResourceMapper.mapEntitiesTo(rootResources);
        return ResponseEntity.ok(rootResourceOutputs);
    }

    /**
     * Find one resource.
     *
     * @param id ID of resource
     * @return {@link ResponseEntity}
     */
    @ApiOperation(
        value = "Retrieves a RootResource",
        response = RootResourceOutput.class,
        nickname = "findOneRootResource"
    )
    @ApiResponses(value = {
        @ApiResponse(code = HTTP_OK, message = "Successful retrieval of RootResource", response = RootResourceOutput.class),
        @ApiResponse(code = HTTP_NOT_FOUND, message = "Unsuccessful retrieval of RootResource", response = ApiSimpleErrorsView.class),
        @ApiResponse(code = HTTP_UNAUTHORIZED, message = "Unauthorized"),
        @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "Internal servererror", response = ApiSimpleErrorsView.class)
    })
    @RequestMapping(
        method = RequestMethod.GET, value = WILDCARD_REQUEST_MAPPING_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity findOne(
        @ApiParam(name = WILDCARD_ATTR_NAME_RESOURCE_ID, value = "ID of RootResource that needs to be fetched",
            required = true)
        @PathVariable(name = WILDCARD_ATTR_NAME_RESOURCE_ID) String id) {
        return rootResourceService.findOne(id)
            .map(rootResource -> {
                RootResourceOutput rootResourceOutput = rootResourceMapper.mapEntityTo(rootResource);
                return ResponseEntity.ok(rootResourceOutput);
            })
            .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /**
     * Creates resource.
     *
     * @param input {@link RootResourceInput}
     * @return {@link ResponseEntity}
     */
    @ApiOperation(
        value = "Inserts a new RootResource",
        response = RootResourceOutput.class,
        nickname = "CrateRootResource"
    )
    @ApiResponses(value = {
        @ApiResponse(
            code = HTTP_CREATED,
            message = "Successful creation of RootResource record",
            response = RootResourceOutput.class,
            responseHeaders = @ResponseHeader(
                name = "Location",
                description = "URI to new resource",
                response = String.class)
        ),
        @ApiResponse(code = HTTP_BAD_REQUEST, message = "Wrong parameters", response = ApiExtendedErrorsView.class),
        @ApiResponse(code = HTTP_UNAUTHORIZED, message = "Unauthorized"),
        @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "Internal server error", response = ApiSimpleErrorsView.class)
    })
    @RequestMapping(
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity create(
        @ApiParam(value = "RootResource object that needs to be added", required = true)
        @Valid @RequestBody RootResourceInput input) {
        RootResource rootResource = rootResourceMapper.mapRepresentationTo(input);
        return rootResourceService.create(rootResource)
            .map(newRootResource -> {
                RootResourceOutput rootResourceOutput = rootResourceMapper.mapEntityTo(newRootResource);
                URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path(WILDCARD_REQUEST_MAPPING_VALUE)
                    .buildAndExpand(rootResourceOutput.getId()).toUri();
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(location);
                return new ResponseEntity<RootResourceOutput>(rootResourceOutput, headers, HttpStatus.CREATED);
            })
            .orElse(ResponseEntity.badRequest().build());
    }

    /**
     * Updates the resource.
     *
     * @param id      {@link String}
     * @param input {@link RootResourceInput}
     * @return {@link ResponseEntity}
     */
    @ApiOperation(
        value = "Update a RootResource record",
        nickname = "updateRootResource"
    )
    @ApiResponses(value = {
        @ApiResponse(
            code = HTTP_OK,
            message = "Successful update of RootResource record"
        ),
        @ApiResponse(code = HTTP_BAD_REQUEST, message = "Wrong parameters", response = ApiExtendedErrorsView.class),
        @ApiResponse(code = HTTP_NOT_FOUND, message = "Unsuccessful retrieval of RootResource", response = ApiSimpleErrorsView.class),
        @ApiResponse(code = HTTP_UNAUTHORIZED, message = "Unauthorized"),
        @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "Internal server error", response = ApiSimpleErrorsView.class)
    })
    @RequestMapping(
        method = RequestMethod.PUT,
        value = WILDCARD_REQUEST_MAPPING_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity update(
        @ApiParam(name = WILDCARD_ATTR_NAME_RESOURCE_ID, value = "ID of Platform that needs to be updated",
            required = true)
        @PathVariable(name = WILDCARD_ATTR_NAME_RESOURCE_ID) String id,
        @ApiParam(value = "RootResource object that will be used in update", required = true)
        @Valid @RequestBody RootResourceInput input) {
        RootResource mappedRootResource = rootResourceMapper.mapRepresentationTo(input);
        return rootResourceService.findOne(id)
            .map(rootResource -> {
                RootResource mergedRootResource = rootResourceMapper.mergeEntities(mappedRootResource, rootResource);
                return rootResourceService.update(mergedRootResource)
                    .map(newRootResource -> {
                        RootResourceOutput rootResourceOutput = rootResourceMapper.mapEntityTo(newRootResource);
                        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .build().toUri();
                        HttpHeaders headers = new HttpHeaders();
                        headers.setLocation(location);
                        return new ResponseEntity<RootResourceOutput>(rootResourceOutput, headers, HttpStatus.OK);

                    })
                    .orElse(ResponseEntity.badRequest().build());

            })
            .orElseThrow(() -> new ResourceNotFoundException(id));
    }

}
