package com.generator.number.controller;

import com.generator.number.model.TaskDataModel;
import com.generator.number.service.TaskService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class GenerateController {
  int goal;
  
  int step;
  
  @Autowired
  TaskService ts;
  
  @RequestMapping(value = {"/api/generate"}, produces = {"application/json"})
  public ResponseEntity<?> generateNumberge(@RequestBody TaskDataModel taskData) throws Exception {
    HashMap<String, String> response = new HashMap<>();
    String taskUUID = this.ts.generateNumber(taskData);
    response.put("task", taskUUID);
    return ResponseEntity.accepted().body(response);
  }
  
  @RequestMapping(value = {"/api/tasks/getFilePath"}, produces = {"application/json"})
  public String getOutputFilePath() {
    return System.getProperty("java.io.tmpdir");
  }
}
