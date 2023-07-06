package com.cyn.dynamic.aspect;

import com.alibaba.druid.util.StringUtils;
import com.cyn.dynamic.anno.DynamicDataSource;
import com.cyn.dynamic.utils.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * @author Godc
 * @description:
 * @date 2023/6/19/0019 11:01
 */
@Component
@Aspect
public class DynamicDataSourceAspect {
    /**
     * @annotation:方法上有相应注解就拦截
     * @within:类上有相应注解就拦截
     */
    @Pointcut("@annotation(com.cyn.dynamic.anno.DynamicDataSource) || @within(com.cyn.dynamic.anno.DynamicDataSource)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        DynamicDataSource dynamicDataSource = getDataSource(proceedingJoinPoint);
        if (dynamicDataSource != null) {
            // 获取注解信息
            String dataSource = dynamicDataSource.dataSource();
            if (!StringUtils.isEmpty(dataSource)) {
                // 设置ThreadLocal值
                DynamicDataSourceContextHolder.setDataSource(dataSource);
            }
        }
        Object proceed = null;
        try {
            proceed = proceedingJoinPoint.proceed();
            return proceed;
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            DynamicDataSourceContextHolder.removeDataSource();
        }
        return proceed;
    }

    /**
     * 通过pjp获取注解
     * 优先从方法上查找、若方法上不存在注解便去类上查找
     *
     * @param proceedingJoinPoint
     * @return
     */
    private DynamicDataSource getDataSource(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
//        return signature.getMethod().getAnnotation(DynamicDataSource.class);
        DynamicDataSource annotation = AnnotationUtils.findAnnotation(signature.getMethod(), DynamicDataSource.class);
        if (annotation == null) {
            annotation = AnnotationUtils.findAnnotation(signature.getDeclaringType(), DynamicDataSource.class);
        }
        return annotation;
    }
}
