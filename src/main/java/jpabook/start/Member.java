package jpabook.start;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import java.util.Date;

/**
 * @Entity: 이 클래스를 테이블과 매핑한다고 JPA 에게 알려준다.
 * @Table: 엔티티 클래스에 매핑할 테이블 정보를 알려준다.
 * @Id: 엔티티 클래스의 필드를 테이블의 기본키(PK)에 매핑한다.
 * @Column: 필드를 컬럼에 매핑한다.
 */
@Entity
@Table(name = "MEMBER", uniqueConstraints = {
        @UniqueConstraint(
                name = "NAME_AGE_UNIQUE",
                columnNames = {"NAME", "AGE"}
        )
})
public class Member {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME", nullable = false, length = 10)
    private String username;
    private Integer age;

    /*
        enum을 사용해서 회원 타입을 구분
        일반 회원은 USER, 관리자는 ADMIN
        enum을 사용하려면 @Enumerated 어노테이션으로 매핑.
     */
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;

    /*
        자바의 날짜 타입은 @Temporal을 사용해서 매핑.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /*
        회원을 설명하는 필드는 길이 제한이 없다.
        따라서 데이터베이스의 VARCHAR 타입 대신 CLOB 타입으로 저장해야 한다.
        @Lob 을 사용하면 CLOB, BLOB 타입을 매핑할 수 있다.
     */
    @Lob
    private String description;

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
