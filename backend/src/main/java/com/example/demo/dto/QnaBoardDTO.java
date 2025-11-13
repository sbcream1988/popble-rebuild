package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QnaBoardDTO extends BoardDTO{

	private String password;
}
