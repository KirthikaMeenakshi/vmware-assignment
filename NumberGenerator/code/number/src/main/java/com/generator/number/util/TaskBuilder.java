package com.generator.number.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskBuilder {
  @JsonProperty
  private String task;
  
  @JsonProperty
  public String getTask() {
    return this.task;
  }
  
  public void setTask(String result) {
    this.task = this.task;
  }
}
