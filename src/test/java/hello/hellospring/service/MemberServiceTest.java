package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long savedId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(member.getId()).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
        //assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복회원예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        /** assertThrows(1. 발생 오류, 2. 로직);
         *  1.의 로직을 수행할 때, 2.의 오류가 발생해야 함
         * */
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");

        /* try-catch도 가능
         try{
             memberService.join(member2);
             fail("예외 발생 필요");
         }catch (IllegalStateException e ){
             assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다. 11");
         }*/
    }
}