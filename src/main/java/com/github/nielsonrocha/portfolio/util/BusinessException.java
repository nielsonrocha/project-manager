package com.github.nielsonrocha.portfolio.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BusinessException extends RuntimeException {

  public BusinessException(String message) {
    super(message);
  }

}
