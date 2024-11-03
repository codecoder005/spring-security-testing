package com.popcorn.controller;

import com.google.gson.Gson;
import com.popcorn.model.InputRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final Gson jsonHelper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getUsers(
            Principal principal
    ) {
        log.info("UserController::getUsers");
        if(principal instanceof UsernamePasswordAuthenticationToken token) {
            log.info(jsonHelper.toJson(token));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of(
                        "status", HttpStatus.OK.value(),
                        "timestamp", ZonedDateTime.now(),
                        "data", Map.of("users", List.of(Map.of("uuid", UUID.randomUUID())))
                ));
    }

    @GetMapping(path = "/access", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasAnyRole('CLIENT')")
    public ResponseEntity<Map<String, Object>> onlyClient(Principal principal) {
        log.info("UserController::onlyClient");
        if(principal instanceof UsernamePasswordAuthenticationToken token) {
            log.info(jsonHelper.toJson(token));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of(
                        "status", HttpStatus.OK.value(),
                        "timestamp", ZonedDateTime.now(),
                        "data", Map.of("users", List.of(Map.of("uuid", UUID.randomUUID())))
                ));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody InputRequest user) {
        log.info("UserController::createUser ");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                        "data", user,
                        "message", "User created",
                        "status", HttpStatus.CREATED.value(),
                        "timestamp", ZonedDateTime.now()
                ));
    }
}
