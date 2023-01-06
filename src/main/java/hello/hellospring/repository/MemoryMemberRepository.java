package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // store 에서 id를 통해 데이터 가져옴

        /** NULL 이 반환될 가능성이 있는 경우?
         * Optional 로 감싼 후에 반환
         * -> Optional.ofNullable(반환할 값);
         * */

    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)).findAny();
        /**
         * store.values().stream() -> loop 돌림
         *
         * filter(member -> member.getName().equals(name))
         * -> loop에서 member의 name이 parameter의 name과 같은지 확인한 후, 같은 경우만 필터링
         *
         * findAny - 어느 하나라도 찾으면 반환
         *
         * 끝까지 돌렸는데 아무것도 없으면, Optinoal에 NULL이 포함되어 반환
         * */
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
