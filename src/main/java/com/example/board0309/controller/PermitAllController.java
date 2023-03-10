package com.example.board0309.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permitAll")

public class PermitAllController {
    @GetMapping("/test")
    @ApiOperation(value = "Swagger TEST Method Name")
    public String permitAllTest(){
        return ResponseEntity.ok().body("test").toString();
    }
}
