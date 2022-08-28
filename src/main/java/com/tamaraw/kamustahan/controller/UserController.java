package com.tamaraw.kamustahan.controller;

import com.tamaraw.kamustahan.utils.TrackExecutionTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private ClientRegistration clientRegistration;

    public UserController(ClientRegistrationRepository registrations) {
        this.clientRegistration = registrations.findByRegistrationId("okta");
    }

    @GetMapping("api/v1/user")
    @TrackExecutionTime
    public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if (oAuth2User == null) {
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            System.out.println(oAuth2User);
            return ResponseEntity.ok().body(oAuth2User.getAttributes());
        }
    }

    @PostMapping("api/v1/logout")
    @TrackExecutionTime
    public ResponseEntity<?> logout(HttpServletRequest request, @AuthenticationPrincipal(expression = "idToken") OidcIdToken idToken) {
        String logoutUrl = this.clientRegistration.getProviderDetails().getConfigurationMetadata()
                .get("end_session_endpoint").toString();
        Map<String, String> logoutDetails = new HashMap<>();
        logoutDetails.put("logoutUrl", logoutUrl);
        logoutDetails.put("idToken", idToken.getTokenValue());
        request.getSession(false).invalidate();
        return ResponseEntity.ok().body(logoutDetails);
    }
}
