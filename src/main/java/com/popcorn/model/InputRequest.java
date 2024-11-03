package com.popcorn.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputRequest {
    private Byte age;

    private Short height;

    private String name;

    private String email;

    private List<String> hobbies;

    private UUID transactionId;

    private Address address;
}