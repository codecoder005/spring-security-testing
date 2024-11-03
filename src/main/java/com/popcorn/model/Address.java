package com.popcorn.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private String line;
    private String city;
    private String state;
    private String country;
}
