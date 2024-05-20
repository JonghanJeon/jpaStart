package jpabook.start.ch06;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ManyToOneMain {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin(); //Transaction - start

            Member member1 = new Member();
            Member member2 = new Member();
            Team team1 = new Team();
            team1.setName("team1");
            team1.getMembers().add(member1);
            team1.getMembers().add(member2);

            member1.setUsername("member1");
            member1.setTeam(team1);
            member2.setUsername("member2");
            member2.setTeam(team1);

            em.persist(member1);
            em.persist(member2);
            em.persist(team1);

            tx.commit(); //Transaction - commit
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback(); //Transaction - Rollback
        } finally {
            //영속 컨텍스트(EntityManager) 종료(준영속)
            em.close(); //EntityManager - close
        }
        emf.close(); //EntityManagerFactory - close
    }
}
