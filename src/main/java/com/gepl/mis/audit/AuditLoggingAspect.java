package com.gepl.mis.audit;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditLoggingAspect {
    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private HttpServletRequest request;

    @AfterReturning(
            "execution(* com.gepl.mis..*Controller.*(..))"
    )
    public void logAudit(JoinPoint joinPoint){
        String method=request.getMethod();
        if(!method.equals("POST") && !method.equals("PUT"))
            return;


        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth==null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName()))
            return;

        AuditLog log= new AuditLog();
        log.setUsername(auth.getName());

        String role=auth.getAuthorities().iterator().next().getAuthority();
        log.setRole(role.replace("ROLE_",""));

        String classname=joinPoint.getTarget().getClass().getSimpleName();
        log.setModule(classname.replace("Controller","").toUpperCase());

        log.setAction(method.equals("POST")? "CREATE": "UPDATE");

        if(role.contains("FOUNDER")){
            log.setNote("Founder override");
        }
        auditLogRepository.save(log);
    }

}
