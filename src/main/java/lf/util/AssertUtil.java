package lf.util;

import lf.exception.BaseException;
import lf.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class AssertUtil {

    public static void hasText(String text, String message){
        if(!StringUtils.hasText(text)){
            throw new BusinessException(message);
        }
    }
    public static void notNull(Object object, String message){
        if(object == null){
            throw new BusinessException(message);
        }
    }
}
