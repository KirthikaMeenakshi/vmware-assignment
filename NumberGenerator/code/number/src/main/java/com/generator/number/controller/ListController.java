package com.generator.number.controller;

import com.generator.number.service.TaskService;
import com.generator.number.util.ResultBuilder;
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
public class ListController {
  @Autowired
  TaskService ts;
  
  @GetMapping(path = {"/{UUID}/getNumList"}, produces = {"application/json"})
  private ResponseEntity<?> getNumList(@PathParam("UUID") UUID uuid) {
    ResultBuilder builder = new ResultBuilder();
    String response = this.ts.getNumList(uuid);
    if (response == null)
      response = "No Task has been generated with this UUID"; 
    builder.setResult(response);
    return ResponseEntity.ok().body(builder);
  }
  
  @GetMapping(path = {"/getAllTasks"}, produces = {"application/json"})
  private HashMap<String, String> getAllTask() {
    return this.ts.listAllTask();
  }
}
