package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) { // 스프링 부트에서 application.properties 를 바탕으로 스프링 빈 만듦
        this.dataSource = dataSource;
    }

    @Bean // 스프링 빈으로 등록
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        // return new MemoryMemberRepository();// MemoryMemberRepository 가 MemberRepository 의 구현체 - 인터페이스는 new 가 안 됨.
        return new JdbcMemberRepository(dataSource);
    }
}
