package jpabook.start.ch07.model4.entity;

import jakarta.persistence.MappedSuperclass;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    private Date createDate; //등록일
    private Date lastModifiedDate; //수정일
}
