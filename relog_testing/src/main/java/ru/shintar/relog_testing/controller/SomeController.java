package ru.shintar.relog_testing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shintar.relog_testing.model.SomeModel;

@RestController
@RequestMapping("/testing")
public class SomeController {

    @PostMapping
    public ResponseEntity<SomeModel> addSomething(@RequestBody SomeModel someModel) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("testHeader", "testing")
                .body(someModel);
    }

    @GetMapping
    public ResponseEntity<SomeModel> getSomething(@RequestParam(name = "id", required = false) Long id,
                                                  @RequestParam(name = "description", required = false) String description
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .header("testHeaderOk", "ok")
                .body(new SomeModel(id, description));
    }
}
