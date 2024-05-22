package jpabook.start.ch08.model5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.start.ch08.model5.entity.Delivery;
import jpabook.start.ch08.model5.entity.Order;
import jpabook.start.ch08.model5.entity.OrderItem;
import jpabook.start.ch08.entity.Member;
import jpabook.start.ch08.entity.Team;

public class Model5Main {
    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpabook");

        //엔티티 매니저 - 생성
        EntityManager em = emf.createEntityManager();

        //트랜잭션 - 획득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin(); //Transaction - start

            Delivery delivery = new Delivery();
//            em.persist(delivery);

            OrderItem orderItem1 = new OrderItem();
            OrderItem orderItem2 = new OrderItem();
//            em.persist(orderItem1);
//            em.persist(orderItem2);

            Order order = new Order();
            order.setDelivery(delivery);
            order.addOrderItem(orderItem1);
            order.addOrderItem(orderItem2);

            //영속성 전이 사용시
            //delivery, orderItems 플러시 시점에 영속성 전이
            em.persist(order);

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
