package jpabook.start.ch07.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//상속 매핑은 부모 클래스 에 @Inheritance 를 사용 해야 한다.
//매핑 전략 조인 전략 으로 지정
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DRTPE")
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    private String name; //이름
    private int price;   //가격
}
