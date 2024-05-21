package jpabook.start.ch07.entity.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity
public class Seller extends BaseEntity{

    //ID 상속
    //NAME 상속
    private String shopName;
}
