package com.feiwangSpring.utils;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/26 21:20
 */
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final String POINT_CUT = "execution(public * com.feiwangSpring.controller.*.*(..))";

    @Pointcut(POINT_CUT)
    public void pointCut(){}

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint){
        logger.info("@Before通知执行");
        //获取目标方法参数信息
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).forEach(arg->{  // 大大
            try {
                logger.info("方法参数值:"+OBJECT_MAPPER.writeValueAsString(arg));
            } catch (JsonProcessingException e) {
                logger.info(" 异常了 方法参数值:"+arg.toString());
            }
        });


        //aop代理对象
        Object aThis = joinPoint.getThis();
        logger.info("before时 getthis对象："+ aThis.toString()); //com.xhx.springboot.controller.HelloController@69fbbcdd

        //被代理对象
        Object target = joinPoint.getTarget();
        logger.info("before时 getTarget对象"+target.toString()); //com.xhx.springboot.controller.HelloController@69fbbcdd

        //获取连接点的方法签名对象
        Signature signature = joinPoint.getSignature();
        logger.info("before  方法签名对象 toLongString全称："+ signature.toLongString()); //public java.lang.String com.xhx.springboot.controller.HelloController.getName(java.lang.String)
        logger.info("before  方法签名对象 toShortString："+signature.toShortString()); //HelloController.getName(..)
        logger.info("before 方法签名对象 toString:" +signature.toString()); //String com.xhx.springboot.controller.HelloController.getName(String)
        //获取方法名
        logger.info("before 方法名:" +signature.getName()); //getName
        //获取声明类型名
        logger.info("before 声明类型名:" +signature.getDeclaringTypeName()); //com.xhx.springboot.controller.HelloController
        //获取声明类型  方法所在类的class对象
        logger.info("before 获取声明类型  方法所在类的class对象:"+signature.getDeclaringType().toString()); //class com.xhx.springboot.controller.HelloController
        //和getDeclaringTypeName()一样
        logger.info("同上:"+signature.getDeclaringType().getName());//com.xhx.springboot.controller.HelloController

        //连接点类型
        String kind = joinPoint.getKind();
        logger.info("before 连接点类型"+kind);//method-execution

        //返回连接点方法所在类文件中的位置  打印报异常
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        logger.info("before 返回连接点方法所在类文件中的位置"+sourceLocation.toString());
        //logger.info(sourceLocation.getFileName());
        //logger.info(sourceLocation.getLine()+"");
        //logger.info(sourceLocation.getWithinType().toString()); //class com.xhx.springboot.controller.HelloController

        ///返回连接点静态部分
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
        logger.info("before 返回连接点静态部分:"+staticPart.toLongString());  //execution(public java.lang.String com.xhx.springboot.controller.HelloController.getName(java.lang.String))


        //attributes可以获取request信息 session信息等
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("before request URL:"+ request.getRequestURL().toString()); //http://127.0.0.1:8080/hello/getName
        logger.info("before 远程地址:"+ request.getRemoteAddr()); //127.0.0.1
        logger.info("before  Method:"+request.getMethod()); //GET

        logger.info("before通知执行结束");
    }


    /**
     * 后置返回
     *      如果第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法参数类型匹配时才能执行后置返回通知，否则不执行，
     *            参数为Object类型将匹配任何目标返回值
     */
    @AfterReturning(value = POINT_CUT,returning = "result")
    public void doAfterReturningAdvice1(JoinPoint joinPoint,Object result){
        logger.info(" 第一个后置返回通知的返回值："+result);
        //aop代理对象
        Object aThis = joinPoint.getThis();
        logger.info("AfterReturning时 getthis对象："+ aThis.toString()); //com.xhx.springboot.controller.HelloController@69fbbcdd

        //被代理对象
        Object target = joinPoint.getTarget();
        logger.info("AfterReturning时 getTarget对象"+target.toString()); //com.xhx.springboot.controller.HelloController@69fbbcdd

        //获取连接点的方法签名对象
        Signature signature = joinPoint.getSignature();
        logger.info("AfterReturning  方法签名对象 toLongString全称："+ signature.toLongString()); //public java.lang.String com.xhx.springboot.controller.HelloController.getName(java.lang.String)
        logger.info("AfterReturning  方法签名对象 toShortString："+signature.toShortString()); //HelloController.getName(..)
        logger.info("AfterReturning 方法签名对象 toString:" +signature.toString()); //String com.xhx.springboot.controller.HelloController.getName(String)
        //获取方法名
        logger.info("AfterReturning 方法名:" +signature.getName()); //getName
        //获取声明类型名
        logger.info("AfterReturning 声明类型名:" +signature.getDeclaringTypeName()); //com.xhx.springboot.controller.HelloController
        //获取声明类型  方法所在类的class对象
        logger.info("AfterReturning 获取声明类型  方法所在类的class对象:"+signature.getDeclaringType().toString()); //class com.xhx.springboot.controller.HelloController
        //和getDeclaringTypeName()一样
        logger.info("AfterReturning 同上:"+signature.getDeclaringType().getName());//com.xhx.springboot.controller.HelloController

        //连接点类型
        String kind = joinPoint.getKind();
        logger.info("AfterReturning 连接点类型"+kind);//method-execution

        //返回连接点方法所在类文件中的位置  打印报异常
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        logger.info("AfterReturning 返回连接点方法所在类文件中的位置"+sourceLocation.toString());
        //logger.info(sourceLocation.getFileName());
        //logger.info(sourceLocation.getLine()+"");
        //logger.info(sourceLocation.getWithinType().toString()); //class com.xhx.springboot.controller.HelloController

        ///返回连接点静态部分
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
        logger.info("AfterReturning 返回连接点静态部分:"+staticPart.toLongString());  //execution(public java.lang.String com.xhx.springboot.controller.HelloController.getName(java.lang.String))


        //attributes可以获取request信息 session信息等
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("AfterReturning request URL:"+ request.getRequestURL().toString()); //http://127.0.0.1:8080/hello/getName
        logger.info("AfterReturning 远程地址:"+ request.getRemoteAddr()); //127.0.0.1
        logger.info("AfterReturning  Method:"+request.getMethod()); //GET

        logger.info("AfterReturning通知执行结束");
    }
//这个其实没啥意义  这个就是匹配返回参数是String的  但上面那个Object其实全包括了

    @AfterReturning(value = POINT_CUT,returning = "result",argNames = "result")
    public void doAfterReturningAdvice2(String result){
        logger.info("第二个后置返回通知的返回值："+result);
    }
    //第一个后置返回通知的返回值：姓名是大大
    //第二个后置返回通知的返回值：姓名是大大
    //第一个后置返回通知的返回值：{name=小小, id=1}


    /**
     * 后置异常通知
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     *  throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *            对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = POINT_CUT,throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        logger.info("AfterThrowing :"+joinPoint.getSignature().getName());
        if(exception instanceof NullPointerException){
            logger.info("发生了空指针异常!!!!!");
        }
    }

    @After(value = POINT_CUT)
    public void doAfterAdvice(JoinPoint joinPoint){
        logger.info("后置通知执行了!");
    }

    /**
     * 环绕通知：
     *   注意:Spring AOP的环绕通知会影响到AfterThrowing通知的运行,不要同时使用
     *
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around(value = POINT_CUT)
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        logger.info("@Around环绕通知："+proceedingJoinPoint.getSignature().toString());
        Object obj = null;
        try {
            obj = proceedingJoinPoint.proceed(); //可以加参数
            //手动再加个参数

            logger.info("Around 获取方法返回参数 "+obj.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.info("@Around环绕通知执行结束");
        return obj;
    }
}
