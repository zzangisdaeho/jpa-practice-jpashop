//package jpabook.jpashop.service;
//
//import jpabook.jpashop.entity.Member;
//import jpabook.jpashop.entity.Order;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import java.time.LocalDateTime;
//
//@SpringBootTest
//@Transactional
//public class Temp {
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    public void cascadeTest() throws Exception{
//        //given
//        Order order = new Order();
//        LocalDateTime now1 = LocalDateTime.now();
//        System.out.println("now1 : " + now1);
//        order.setOrderDate(now1);
//
//        Member member = new Member();
//        member.setName("member1");
//
//        order.setMember(member);
//
//        em.persist(member);
//        em.persist(order);
////        em.flush();
//
//
//        //when
//        System.out.println(order.getId());
//
//        Member findMember = em.find(Member.class, member.getId());
//
//        LocalDateTime now2 = LocalDateTime.now();
//        System.out.println("now2 : " + now2);
//
//        // !!!!!!!!!! 부모쪽에 cascade옵션이 없어도 update는 가능 !!!!!!!!!!!!!//
//        findMember.getOrders().get(0).setOrderDate(now2);
//
//        em.flush();
//
//        //then
//    }
//}
