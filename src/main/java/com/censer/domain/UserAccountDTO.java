package com.censer.domain;

import lombok.Data;

@Data
public class UserAccountDTO {
	private String account_name;
	private String account_password;
	private boolean account_activo;	
}
