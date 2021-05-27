package jpabook.jpashop.repository;

import jpabook.jpashop.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    // testcase에 있는 Transactional은 테스트 종료 후 rollback시킨다. Rolled back transaction for test
    @Transactional
    // transactional이 테스트 종료 후 rollback하지 않도록 하는 옵션
    @Rollback(false)
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("memberA");
        //when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        // 같은 transaction = 같은 영속성 context = 같은 entity
        Assertions.assertThat(findMember).isEqualTo(member);


        org.junit.jupiter.api.Assertions.assertEquals(findMember.getId(), member.getId());
        org.junit.jupiter.api.Assertions.assertEquals(findMember.getUsername(), member.getUsername());
        org.junit.jupiter.api.Assertions.assertEquals(findMember, member);

    }
}