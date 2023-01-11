package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        // public <T> T find(Class<T> entityClass, Object primaryKey);
        return Optional.ofNullable(member); // Optional 로 반환 - 값이 없을 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();

        // return em.createQuery("select m from Member m where m.name = :name", Member.class)
        //                .setParameter("name", name)
        //                .getResultList().stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        // public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass);
    }
}
