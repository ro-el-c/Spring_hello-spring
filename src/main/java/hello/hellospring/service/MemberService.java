package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private  MemberRepository memberRepository = new MemoryMemberRepository();

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * */
    public Long join(Member member){
        // 이미 가입되어 있는 이름으로 가입 불가능 - 이름 중복 확인
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    // ifPresent: 값이 존재하면 동작 ; Optional 이기 때문에 사용할 수 있는 메소드
                    throw new IllegalStateException("이미 존재하는 이름입니다.");
        });
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            // ifPresent: 값이 존재하면 동작 ; Optional 이기 때문에 사용할 수 있는 메소드
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        });*/
    }

    /**
     * [전체] 회원 조회
     * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
