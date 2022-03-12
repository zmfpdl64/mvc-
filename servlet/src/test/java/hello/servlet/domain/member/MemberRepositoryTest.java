package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
// ./gradlew test 전체 테스트
// ./gradlew test --tests save 특정 모듈 테스트

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void getInstance() {
    }

    @Test
    void save() {
        Member member = new Member("hello", 20);
        Member savedMember = memberRepository.save(member);

        Member findmember = memberRepository.findById(member.getId());
        Assertions.assertThat(member).isEqualTo(findmember);
    }

    @Test
    void findAll() {
        Member member1 = new Member("kim", 20);
        Member member2 = new Member("Lee", 25);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();
        Assertions.assertThat(2).isEqualTo(result.size());
        Assertions.assertThat(result).contains(member1, member2);


    }

    @Test
    void clearStore() {
    }
}