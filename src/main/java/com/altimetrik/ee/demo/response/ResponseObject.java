package com.altimetrik.ee.demo.response;

import java.util.Map;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObject<T> {

  @NotNull
  private ResponseStatus status;

  private T response;

  public ResponseObject() {
    super();
  }

  public ResponseObject(ResponseCode statusCode) {
    this.status = new ResponseStatus(statusCode);
  }

  public ResponseObject(ResponseCode statusCode, String actualErrorMessage) {
    this.status = new ResponseStatus(statusCode, actualErrorMessage);
  }

  public ResponseObject(ResponseCode statusCode, Map<String, String> errors) {
    this.status = new ResponseStatus(statusCode, errors);
  }

  public ResponseObject(ResponseStatus status) {
    this.status = status;
  }

  public ResponseObject(T response, ResponseCode statusCode) {
    this.response = response;
    this.status = new ResponseStatus(statusCode);
  }

  public T getResponse() {
    return response;
  }

  public void setResponse(T response) {
    this.response = response;
  }

  public ResponseStatus getStatus() {
    return status;
  }

  public void setStatus(ResponseStatus status) {
    this.status = status;
  }

  public void setStatusByEnum(ResponseCode statusCode) {
    this.status = new ResponseStatus(statusCode);
  }

  /**
   * Success response shorthand
   *
   * @return ResponseObject with SUCCESS status
   */
  public static <T> ResponseObject<T> success(T response) {
    return new ResponseObject<>(response, ResponseCode.SUCCESS);
  }

  @Override
  public String toString() {
    return "ResponseObject [status=" + status + ", response=" + response + "]";
  }

}
