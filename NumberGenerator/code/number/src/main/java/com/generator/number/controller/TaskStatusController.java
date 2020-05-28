package com.generator.number.controller;

import com.generator.number.service.TaskService;
import java.util.HashMap;
import java.util.UUID;
import javax.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/tasks"})
public class TaskStatusController {
  @Autowired
  TaskService tService;
  
  @GetMapping(path = {"/{UUID}/status"}, produces = {"application/json"})
  private ResponseEntity<?> getStatus(@PathParam("UUID") UUID uuid) {
    HashMap<String, String> response = new HashMap<>();
    String status = this.tService.getTaskUUID(uuid);
    response.put("result", status);
    return ResponseEntity.ok().body(response);
  }
}
