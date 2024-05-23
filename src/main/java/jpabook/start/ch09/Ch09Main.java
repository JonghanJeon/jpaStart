package jpabook.start.ch09;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.start.ch08.entity.Member;
import jpabook.start.ch08.entity.Team;

public class Ch09Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpabook");

        //엔티티 매니저 - 생성
        EntityManager em = emf.createEntityManager();

        //트랜잭션 - 획득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin(); //Transaction - start

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
