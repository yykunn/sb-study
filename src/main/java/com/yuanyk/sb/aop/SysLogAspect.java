package com.yuanyk.sb.aop;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
/**
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class SysLogAspect {

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.yuanyk.sb.aop.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        System.out.println("切面。。。。。");
        //保存日志

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();
            System.out.println("操作类型"+value);
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        System.out.println("method:"+className+"."+methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        System.out.println("params name:"+Arrays.toString(signature.getParameterNames()));
        System.out.println("params:"+params);

        //获取用户名
        //获取用户ip地址
        //获取用户ip地址
        //HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
       // sysLog.setIp(IPUtils.getIpAddr(request));
        // 获取session中当前用户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr(); // 获取IP地址
        System.out.println("ip:"+ip);


    }

}