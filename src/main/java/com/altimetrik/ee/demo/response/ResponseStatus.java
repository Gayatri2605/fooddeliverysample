package com.altimetrik.ee.demo.response;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseStatus implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  private Integer code;

  @NotBlank
  private String message;

  private Long timestamp = new Date().getTime();

  private Map<String, String> errors;

  public ResponseStatus() {
    super();
  }

  public ResponseStatus(ResponseCode statusCode) {
    this(statusCode.value(), statusCode.getReasonPhrase());
  }

  public ResponseStatus(ResponseCode statusCode, String actualErrorMessage) {
    this(statusCode.value(), actualErrorMessage);
  }

  public ResponseStatus(ResponseCode statusCode, Map<String, String> errors) {
    this(statusCode.value(), statusCode.getReasonPhrase());
    this.errors = errors;
  }

  public ResponseStatus(Integer code, String message) {
    super();
    this.code = code;
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public Map<String, String> getErrors() {
    return errors;
  }

  public void setErrors(Map<String, String> errors) {
    this.errors = errors;
  }

  @Override
  public String toString() {
    return "ResponseStatus [code=" + code + ", message=" + message + ", timestamp=" + timestamp
        + ", errors=" + errors + "]";
  }

}
