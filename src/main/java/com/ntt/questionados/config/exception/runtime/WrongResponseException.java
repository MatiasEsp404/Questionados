package com.ntt.questionados.config.exception.runtime;

public class WrongResponseException extends RuntimeException{

  public WrongResponseException(String message) {
    super(message);
  }
  
}
