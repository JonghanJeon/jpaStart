package jpabook.start.ch10;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;
import jpabook.start.ch10.entity.Member;

public class JPQLMain {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpabook");

        //엔티티 매니저 - 생성
        EntityManager em = emf.createEntityManager();

        //트랜잭션 - 획득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin(); //Transaction - start

            Member member1 = new Member();
            member1.setUsername("kim");
            Member member2 = new Member();
            member2.setUsername("park");
            em.persist(member1);
            em.persist(member2);

//            em.flush();
//            em.clear();

            String jpql = "select m from Member as m where m.username = 'kim'";
            List<Member> resultList =
                    em.createQuery(jpql, Member.class).getResultList();

            for(Member m : resultList){
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            tx.commit(); //Transaction - commit
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback(); //Transaction - Rollback\
        } finally {
            em.close(); //EntityManager - close
        }
        emf.close(); //EntityManagerFactory - close

    }
}
