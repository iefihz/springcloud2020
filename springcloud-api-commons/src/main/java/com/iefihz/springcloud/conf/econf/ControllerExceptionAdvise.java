package com.iefihz.springcloud.conf.econf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iefihz.springcloud.exception.ApiException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 说明：
 * 该注解标记的Controller，将进行异常的增强以及返回值的增强处理
 * 异常的增强：把异常信息封装到ResultVO里
 * 返回值的增强：把返回值封装到ResultVO里，如果返回值本来就是ResultVO类型，则不使用增强
 *
 * 一般情况两者结合使用，但要实现controller下某个类要进行异常处理，但不需要返回值增强，则把这个类拆分，分别使用即可
 *
 * Controller异常处理使用：
 * basePackages-包级别，指定哪个包下的类需要增强
 * annotations-类级别，需要在该类上使用@ControllerExceptionHandlerAnnotation
 *
 * 请求参数校验（校验结果统一到exceptionHandler方法处理）：
 * 1.Content-Type为application/json参数校验，pojo校验，pojo参数上加上@RequestBody @Valid，并在pojo需要校验的属性下加上具体校验的注解
 * 2.Content-Type为application/x-www-form-urlencode参数绑定，pojo校验，pojo参数上加上@Valid，并在pojo需要校验的属性下加上具体校验的注解
 * 3.单个参数校验，用法：需要在Controller类上加上@Validated，并在参数上加上@NotEmpty之类的注解
 * 4.pojo校验分组（新增需要密码，更新用户一般信息时不支持密码修改）：
 *   <p>
 *       因此，这里共两个组，分别为新建的Insert（这里只有用户密码为Insert组）和默认的Default小组（不指定group属性的小组），在新增的接口pojo参数上使用
 *   @Validated({Default.class, Insert.class}) 就包括了所有要校验的属性。而在更新时，只需要使用@Validated，也就是指校验默认小组的属性，这样就能达到
 *   新增时校验密码，而修改时不校验密码的效果了。
 *   </p>
 * 5.在校验pojo情况下：@Valid 等效于 @Validated 等效于@Validated(Default.class)
 *
 * <p>
 * 总结：
 * 把econf文件夹和ApiException.class拷贝到SpringBoot项目中，并且在当前类的注解上指定basePackages或者annotations（需要在Controller使用该注解），
 * 在Controller类上使用@Validated进行单个请求参数校验，在具体方法的形参上使用@Validated，必要时进行pojo属性分组校验。同一个pojo属性
 * 的不同校验注解分组一般要保持一致，比如User.sex属性的@Pattern和@NotNull的分组都为Insert.class。
 * @Pattern好像只能用于匹配String类型？？？？？
 * </p>
 *
 * @author He Zhifei
 * @date 2020/6/6 13:30
 */
@RestControllerAdvice(basePackages = {"com.iefihz.springcloud.controller"}
        /*annotations = {ControllerExceptionHandlerAnnotation.class}*/)
public class ControllerExceptionAdvise implements ResponseBodyAdvice<Object> {

    /**
     * Controller层的所有异常处理
     * @param e 异常类
     * @return 异常信息
     */
    @ExceptionHandler(Exception.class)
    public ResultVO<String> exceptionHandler(Exception e) {

        if (e instanceof MethodArgumentNotValidException) {
            /**
             * Content-Type为application/json参数校验异常
             */

            // 从异常对象中拿到ObjectError对象
            ObjectError objectError = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0);

            // 然后提取错误提示信息进行返回
            return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());

        } else if (e instanceof BindException) {

            /**
             * Content-Type为application/x-www-form-urlencode参数绑定异常
             */
            ObjectError objectError = ((BindException) e).getBindingResult().getAllErrors().get(0);
            return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());

        } else if (e instanceof ConstraintViolationException) {

            /**
             * 单个参数校验
             */
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
            for (ConstraintViolation<?> constraintViolation : constraintViolations) {
                return new ResultVO<>(ResultCode.VALIDATE_FAILED, constraintViolation.getMessage());
            }

        } else if (e instanceof ApiException) {

            /**
             * 自定义ApiException异常处理
             */
            return new ResultVO<>(ResultCode.FAILED, e.getMessage());

        }

        /**
         * 其它异常统一处理
         */
        return new ResultVO<>(ResultCode.ERROR, e.toString());

    }


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果接口返回的类型原本就是ResultVO，则不需要增强
        return !returnType.getParameterType().equals(ResultVO.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        // 有小问题。。。。
        if (returnType.getGenericParameterType().equals(String.class)) {
            try {
                /**
                 * 因为是字符串类型，所以返回的Content-Type为text/plain，所以把字符串封装到
                 * ResultVO对象后，要手动把对象转为字符串，再返回前端
                 */
                return new ObjectMapper().writeValueAsString(new ResultVO<>(data));
            } catch (JsonProcessingException e) {
                throw new ApiException("返回String类型错误");
            }
        }

        // 将原本的数据包装在ResultVO里
        return new ResultVO<>(data);
    }
}
