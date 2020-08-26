package com.altimetrik.ee.demo.response;


public enum ResponseCode {

  SUCCESS(1000, "SUCCESS"),

  UNKNOWN_ERROR_OCCURRED(1001, "UNKNOWN_ERROR_OCCURRED"),

  INVALID_REQUEST_PARAMETER(1002, "INVALID_REQUEST_PARAMETER"),

  REQUEST_PARAMETER_TYPE_MISMATCH(1003, "REQUEST_PARAMETER_TYPE_MISMATCH"),

  MISSING_REQUEST_PARAMETER(1004, "MISSING_REQUEST_PARAMETER"),

  INVALID_CONTENT_TYPE(1005, "INVALID_CONTENT_TYPE"),

  RESTAURANT_DOESNT_EXITS(1006, "RESTAURANT_DOESNT_EXITS"), 
  
  FOOD_ITEMS_UNAVAILABLITY(1007, "FOOD_ITEMS_UNAVAILABLITY");

  private final int value;

  private final String reasonPhrase;

  ResponseCode(int value, String reasonPhrase) {
    this.value = value;
    this.reasonPhrase = reasonPhrase;
  }

  public int value() {
    return this.value;
  }

  public String getReasonPhrase() {
    return this.reasonPhrase;
  }

  public static ResponseCode valueOf(int value) {
    ResponseCode responseCode = resolve(value);
    if (responseCode == null) {
      throw new IllegalArgumentException("No matching constant for [" + value + "]");
    }
    return responseCode;
  }

  public static ResponseCode resolve(int value) {
    for (ResponseCode responseCode : values()) {
      if (responseCode.value == value) {
        return responseCode;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return this.value + " " + name();
  }

}
