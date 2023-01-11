package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // JpaRepository<T, ID>
    // 인터페이스가 인터페이스를 받을 때 - implements 가 아닌 extends
    // 인터페이스 다중 상속 가능

    @Override
    Optional<Member> findByName(String name);
    // findByName
    // JPQL - select m from member m where m.name = ?
}
