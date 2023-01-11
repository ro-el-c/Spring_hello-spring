package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) { // 스프링 부트에서 application.properties 를 바탕으로 스프링 빈 만듦
        this.dataSource = dataSource;
        this.em = em;
    }*/

    @Bean // 스프링 빈으로 등록
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }
    /*@Bean // 스프링 빈으로 등록
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }*/

//    @Bean
//    public MemberRepository memberRepository(){
//        // return new MemoryMemberRepository();// MemoryMemberRepository 가 MemberRepository 의 구현체 - 인터페이스는 new 가 안 됨.
//        // return new JdbcMemberRepository(dataSource);
//        // return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
