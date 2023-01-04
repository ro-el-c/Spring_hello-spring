package hello.hellospring.repository;

import hello.hellospring.domain.Member;
// import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("ro-el");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // .get(): Optional에서 값을 꺼냄 (좋은 방법은 아님)
        assertThat(result).isEqualTo(member);
        // 1) Assertions: static import를 통해 없앨 수 있음 ; Option + Enter
        /**Assertions.assertThat(actual: 실제(현재 값)).isEqualTo(expected: 기대하는 것(답));
         * O - 같으면 문제 없이 작동
         * X - 다르면 오류처럼 빨간 불 ex. assertThat(result).isEqualTo(null)
         * */
        // 2) Assertions.assertEquals(member, result);
        /* Assertions.assertEquals(expected: 기대하는 것(답), actual: 실제(현재 값));
         * O - 같으면 문제 없이 작동
         * X - 다르면 오류처럼 빨간 불 ex. (member, null)
         * */
        // 3) System.out.println("result = " + (result == member));
        // Assertions을 사용하는 이유: println 으로 하면 기대값과 실제값이 같은지 다른지, 매번 출력 값을 확인해야 함
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1); // 통과
        // assertThat(result).isEqualTo(member2); // 멈춤 - 다른 객체이다
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
