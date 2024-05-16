package jpabook.start.ch05;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;
import jpabook.start.ch05.entity.Member;
import jpabook.start.ch05.entity.Team;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Team team = new Team();
            team.setTeamName("TeamA");
            em.persist(team);

            Member member1 = new Member();
            member1.setUserName("member1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUserName("member2");
            member2.setTeam(team);
            em.persist(member2);

            Member findMember1 = em.find(Member.class, member1.getId());
            Member findMember2 = em.find(Member.class, member2.getId());
            System.out.println("findMember1.getUserName() = " + findMember1.getUserName());
            System.out.println("findMember2.getUserName() = " + findMember2.getUserName());

            String findTeamNameByMember1 = findMember1.getTeam().getTeamName();
            String findTeamNameByMember2 = findMember2.getTeam().getTeamName();
            System.out.println("findTeamNameByMember1 = " + findTeamNameByMember1);
            System.out.println("findTeamNameByMember2 = " + findTeamNameByMember2);

            Team findTeam = em.find(Team.class, team.getId());
            System.out.println("findTeam.getTeamName() = " + findTeam.getTeamName());
            List<Member> members = findTeam.getMembers();
            for (Member member : members) {
                System.out.println("member.getUserName() = " + member.getUserName());
            }

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
