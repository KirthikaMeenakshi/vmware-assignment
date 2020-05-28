package com.generator.number.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultBuilder {
  @JsonProperty
  private String result;
  
  @JsonProperty
  public String getResult() {
    return this.result;
  }
  
  public void setResult(String result) {
    this.result = result;
  }
}
