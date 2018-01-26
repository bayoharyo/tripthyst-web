package com.tripthyst.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	
	private long id;
	private String username;
	private String password;
	private String fullName;
	private String phoneNumber;
	private String ktpNumber;
	private String email;
	private String role;
	
}
