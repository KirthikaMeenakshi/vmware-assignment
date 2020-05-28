package com.generator.number.service;

import com.generator.number.data.TaskData;
import com.generator.number.model.TaskDataModel;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
  @Autowired
  TaskData taskData;
  
  public String generateNumber(TaskDataModel taskData) throws IOException {
    int goal = taskData.getGoal();
    int step = taskData.getStep();
    UUID taskUUID = generateUUID();
    String taskID = "";
    String inputString = "Goal: " + Integer.toString(goal) + ", Step: " + Integer.toString(step);
    System.out.println(inputString);
    taskID = this.taskData.getTaskIDFromMap(inputString);
    if (taskID != null)
      return taskID; 
    String numberArray = createNumberList(goal, step);
    this.taskData.updateTaskAndNumber(taskUUID, numberArray);
    this.taskData.updateCaacheData(goal, step, taskUUID);
    return taskUUID.toString();
  }
  
  public HashMap<String, String> listAllTask() {
    return this.taskData.getAllTasks();
  }
  
  public String getNumList(UUID uuid) {
    return this.taskData.getNumListFromUUID(uuid);
  }
  
  public String getTaskUUID(UUID uuid) {
    return this.taskData.getTaskStatus(uuid);
  }
  
  private String createNumberList(int goal, int step) {
    int number = goal;
    String numberArray = "";
    numberArray = String.valueOf(Integer.toString(number)) + ", ";
    while (number > 0) {
      number -= step;
      if (number != 0) {
        numberArray = String.valueOf(numberArray) + Integer.toString(number) + ", ";
        continue;
      } 
      numberArray = String.valueOf(numberArray) + Integer.toString(number);
    } 
    return numberArray;
  }
  
  private UUID generateUUID() {
    UUID uuid = UUID.randomUUID();
    return uuid;
  }
}
