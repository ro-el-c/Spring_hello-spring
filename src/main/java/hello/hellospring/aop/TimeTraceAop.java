package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
// -> @Component 어노테이션 사용보다는, SpringConfig 에서 스프링 빈으로 등록해서 사용하는 것을 선호
public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))") // hello.hellospring 패키지 하위에 모두 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toLongString());
        try{
            return joinPoint.proceed(); // 다음 메소드로 진
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
