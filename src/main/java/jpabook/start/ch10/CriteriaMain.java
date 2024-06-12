package jpabook.start.ch10;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import jpabook.start.ch10.entity.Member;

public class CriteriaMain {

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

            //Criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            //루트 클래스(조회를 시작할 클래스)
            Root<Member> m = query.from(Member.class);

            //쿼리 생성
            CriteriaQuery<Member> cq =
                    query.select(m).where(cb.equal(m.get("username"), "kim"));
            List<Member> resultList = em.createQuery(cq).getResultList();

            for (Member member : resultList) {
                System.out.println("member.getUsername() = " + member.getUsername());
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
