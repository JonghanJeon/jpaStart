package jpabook.start.ch07.entity.mappedsuperclass;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/*
    부모 에게 상속 받은 속성을 재정의 하려면 @AttributeOverride 또는 @AttributeOverrides 를 사용
    부모 에게 상속 받은 연관 관계를 재정의 하려면 @AssociationOverride 또는 @AssociationOverrides 를 사용
    참고) @Entity 는 @Entity 이거나 @MappedSuperclass 로 지정한 클래스 만 상속 받을 수 있다.
 */
@Entity
//@AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID"))
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID")),
        @AttributeOverride(name = "name", column = @Column(name = "MEMBER_NAME"))
})
public class Member extends BaseEntity{

    //ID 상속
    //NAME 상속
    private String email;
}
