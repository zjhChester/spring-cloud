package cn.zjh.spring.serverauth.auth.config;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;
@Slf4j
@ControllerAdvice
public class ValidExceptionHandler {
    private final static String EXCEPTION_MSG_KEY = "Exception message : ";

    @ResponseBody
    @ExceptionHandler({org.springframework.validation.BindException.class})
    public ResponseEntity<R<String>> handleValidException(BindException e) {
        //日志记录错误信息
        log.error(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        //将错误信息返回给前台
//        return R.restResult(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage(), ApiErrorCode.FAILED);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(R.restResult(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage(), ApiErrorCode.FAILED));
    }

    //    MethodArgumentNotValidException.class
    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<R<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        //日志记录错误信息
        log.error(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        //将错误信息返回给前台
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(R.restResult(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage(), ApiErrorCode.FAILED));

    }
}
