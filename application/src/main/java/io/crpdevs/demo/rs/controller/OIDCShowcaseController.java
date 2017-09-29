package io.crpdevs.demo.rs.controller;

import io.crpdevs.demo.rs.exception.view.ApiSimpleErrorsView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static io.crpdevs.demo.rs.controller.ControllerConstants.REST_BASE_PATH;
import static java.net.HttpURLConnection.*;


@RestController
@RequestMapping(REST_BASE_PATH + "/oidc")
@Data
@Slf4j
@Api(
    value = REST_BASE_PATH + "/oidc",
    description = "Endpoint for OpenId Connect Showcase"
)
public class OIDCShowcaseController {

    /**
     * Bearer-only Examples.
     *
     * @return String
     */
    @ApiOperation(
        value = "Bearer-only Example",
        responseContainer = "String"
    )
    @ApiResponses(value = {
        @ApiResponse(code = HTTP_OK, message = "Successful"),
        @ApiResponse(code = HTTP_UNAUTHORIZED, message = "Unauthorized"),
        @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "Internal server error", response = ApiSimpleErrorsView.class)
    })
    @RequestMapping(
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/bearer-only"
    )
    public ResponseEntity getInformation() {
        return ResponseEntity.ok("The logged-in user is: " + principal.getName());
    }


}
