package com.iefihz.springcloud.conf.econf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解标记的Controller，将进行异常的增强以及返回值的增强处理
 * 异常的增强：把异常信息封装到ResultVO里
 * 返回值的增强：把返回值封装到ResultVO里，如果返回值本来就是ResultVO类型，则不使用增强
 *
 * @author He Zhifei
 * @date 2020/6/6 13:30
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerExceptionHandlerAnnotation {
}
