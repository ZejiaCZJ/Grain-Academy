package com.grainacademy.backend.servicebase.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackEndException extends RuntimeException{
    private Integer code; //status code
    private String msg; //exception message
}
