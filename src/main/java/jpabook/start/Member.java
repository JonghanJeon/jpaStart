package jpabook.start;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @Entity: 이 클래스를 테이블과 매핑한다고 JPA 에게 알려준다.
 * @Table: 엔티티 클래스에 매핑할 테이블 정보를 알려준다.
 * @Id: 엔티티 클래스의 필드를 테이블의 기본키(PK)에 매핑한다.
 * @Column: 필드를 컬럼에 매핑한다.
 */
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME")
    private String username;
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
