package lf.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class BaseException extends RuntimeException implements Serializable {

   private Integer statusCode;
   private String errorCode;
    private String detailMessage;
   private Object[] parameters;
   private String details;

   public BaseException(String message){
       super(message,null, false, false);
   }

}
