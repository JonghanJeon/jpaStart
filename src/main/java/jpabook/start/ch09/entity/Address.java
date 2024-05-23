package jpabook.start.ch09.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    //집 주소 표현
    @Column(name = "city") //매핑할 컬럼 정의 가능
    private String city;
    private String street;
    private String zipcode;
}
