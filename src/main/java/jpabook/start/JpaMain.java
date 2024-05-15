package jpabook.start;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        //엔티티 매니저 팩토리 - 생성
        //META-INF/persistence.xml에서 이름이 jpabook인 영속성유닛을 찾아서 엔티티 매니저 팩토리를 생성한다.
        //비용이 아주 많이 든다
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpabook");

        //엔티티 매니저 - 생성
        EntityManager em = emf.createEntityManager();

        //트랜잭션 - 획득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin(); //Transaction - start
            logic(em); //BusinessLogic - start
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

    //BusinessLogic
    public static void logic(EntityManager em) {

        //엔티티를 생성한 상태(비영속)
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        //등록
        //엔티티를 영속
        em.persist(member);

        //수정
        member.setAge(20);

        //한 건 조회
        //먼저 1차 캐시에서 엔티티를 찾고, 만약 찾는 엔티티가 1차 캐시에 없으면 데이터베이스에서 조회한다.
        //데이터베이스를 조회해서 엔티티를 생성하고, 1차 캐시에 저장한 후에 영속 상태의 엔티티를 반환한다.
        Member findMember = em.find(Member.class, "id1");
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        //목록 조회 - JPQL
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        //삭제
        //객체를 삭제한 상태(삭제)
        em.remove(member);

    }
}
