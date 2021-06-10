package jpabook.jpashop.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
