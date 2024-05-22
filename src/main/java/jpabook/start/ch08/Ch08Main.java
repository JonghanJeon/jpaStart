package jpabook.start.ch08;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.start.ch08.entity.Member;
import jpabook.start.ch08.entity.Team;

public class Ch08Main {
    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpabook");

        //엔티티 매니저 - 생성
        EntityManager em = emf.createEntityManager();

        //트랜잭션 - 획득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin(); //Transaction - start

            Member member = new Member();
            member.setUsername("고민석");
            em.persist(member);

            Team team = new Team();
            team.setName("team1");
            em.persist(team);

            member.setTeam(team);

            // 트랜잭션 커밋하여 영속성 컨텍스트 초기화
            tx.commit();

            // 새로운 트랜잭션 시작
            em.clear(); // 영속성 컨텍스트 초기화

            tx.begin(); // 새로운 트랜잭션 시작

            // 여기서 강제로 SELECT 쿼리가 날아가게 됨
            Member findMember = em.find(Member.class, member.getId());
            Team proxyTeam = findMember.getTeam(); // 프록시 객체

            System.out.println("Oh!!!!!!!!!!");

            proxyTeam.getName();

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
