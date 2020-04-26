package com.feiwangSpring.config;

import com.feiwangSpring.entity.SysLog;
import com.feiwangSpring.service.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/26 16:11
 */
@Aspect
@Component
public class LogAsPect {

    private final static Logger log = org.slf4j.LoggerFactory.getLogger(LogAsPect.class);

    @Autowired
    private ISysLogService sysLogService;

    //表示匹配带有自定义注解的方法
    @Pointcut("@annotation(com.feiwangSpring.config.Log)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result =null;
        long beginTime = System.currentTimeMillis();

        try {
            log.info("我在目标方法之前执行！");
            result = point.proceed();
            long endTime = System.currentTimeMillis();
            insertLog(point,endTime-beginTime);
        } catch (Throwable e) {
            // TODO Auto-generated catch block
        }
        return result;
    }

    private void insertLog(ProceedingJoinPoint point ,long time) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        SysLog sys_log = new SysLog();

        Log userAction = method.getAnnotation(Log.class);
        if (userAction != null) {
            // 注解上的描述
            sys_log.setUserAction(userAction.value());
        }

        // 请求的类名
        String className = point.getTarget().getClass().getName();
        // 请求的方法名
        String methodName = signature.getName();
        // 请求的方法参数值
        String args = Arrays.toString(point.getArgs());

        //从session中获取当前登陆人id
//      Long useride = (Long)SecurityUtils.getSubject().getSession().getAttribute("userid");

        Integer userid = 1;//应该从session中获取当前登录人的id，这里简单模拟下

        sys_log.setUserId(userid);

        sys_log.setCreateTime(new java.sql.Timestamp(new Date().getTime()));

        log.info("当前登陆人：{},类名:{},方法名:{},参数：{},执行时间：{}",userid, className, methodName, args, time);

        sysLogService.insertLog(sys_log);
    }
}
