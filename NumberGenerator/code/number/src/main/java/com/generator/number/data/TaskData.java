package com.generator.number.data;

import com.generator.number.util.CheckOperatingSystem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

@Repository
public class TaskData {
	
  @Autowired
  CheckOperatingSystem operatingSystem;
  
  @Value("${output.directory}")
  private StringBuilder directory;
  
  @Value("${output.file}")
  private String fileSuffix;
  
  Map<UUID, String> taskNumberMap = new HashMap<>();
  
  Map<String, String> inputTaskMap = new HashMap<>();
  
  Map<UUID, String> statusMap = new HashMap<>();
  
  String classpathStr = System.getProperty("java.io.tmpdir");
  
  public void updateTaskAndNumber(UUID taskUUID, String numberArray) {
    boolean status = false;
    if (!this.taskNumberMap.containsKey(taskUUID)) {
      status = updateOutputFile(taskUUID, numberArray);
      this.taskNumberMap.put(taskUUID, numberArray);
    } 
    if (status) {
      this.statusMap.put(taskUUID, "SUCCESS");
    } else {
      this.statusMap.put(taskUUID, "ERROR");
    } 
  }
  
  public void updateCaacheData(int goal, int step, UUID taskUUID) {
    String inputString = "Goal: " + Integer.toString(goal) + ", Step: " + Integer.toString(step);
    this.inputTaskMap.put(inputString, taskUUID.toString());
  }
  
  @ConfigurationProperties
  private boolean updateOutputFile(UUID uuid, String numberArray) {
    boolean writeflag = false;
    File f = new File(String.valueOf(this.classpathStr) + uuid.toString() + fileSuffix);
    f.getParentFile().mkdirs();
    try {
      this.statusMap.put(uuid, "IN_PROGRESS");
      f.createNewFile();
      FileWriter fw = new FileWriter(f);
      fw.write(String.valueOf(uuid.toString()) + " : " + numberArray);
      writeflag = true;
      fw.close();
      this.statusMap.put(uuid, "SUCCESS");
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return writeflag;
  }
  
  public HashMap<String, String> getAllTasks() {
    return (HashMap<String, String>)this.inputTaskMap;
  }
  
  public String getNumListFromUUID(UUID uuid) {
    String numData = "";
    if (this.taskNumberMap.containsKey(uuid)) {
      numData = this.taskNumberMap.get(uuid);
    } else if (numData.length() == 0) {
      numData = checkInOutputFiles(uuid);
    } else {
      numData = "No Task has been generated with this UUID";
    } 
    return numData;
  }
  
  private String checkInOutputFiles(UUID uuid) {
    File list = new File(this.classpathStr);
    String[] fileList = list.list();
    String line = "";
    byte b;
    int i;
    String[] arrayOfString1;
    for (i = (arrayOfString1 = fileList).length, b = 0; b < i; ) {
      String file = arrayOfString1[b];
      if (file.endsWith("_output.txt") && file.startsWith(uuid.toString()))
        try {
          file = String.valueOf(this.classpathStr) + file;
          FileReader reader = new FileReader(file);
          BufferedReader read = new BufferedReader(reader);
          while ((line = read.readLine()) != null) {
            line = line.toString();
            if (line.contains(uuid.toString())) {
              line = line.replace(uuid + " : ", " ");
              break;
            } 
          } 
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }  
      b++;
    } 
    return line;
  }
  
  public String getTaskStatus(UUID uuid) {
    String status = "";
    status = this.statusMap.get(uuid);
    if (status == null || status.isEmpty())
      status = "No such task ID exists yet."; 
    return status;
  }
  
  public String getTaskIDFromMap(String inputStr) {
    boolean idFound = false;
    String taskID = null;
    idFound = this.inputTaskMap.containsKey(inputStr);
    if (idFound)
      taskID = this.inputTaskMap.get(inputStr); 
    return taskID;
  }
}
