package com.groceteria.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name="User_Table")
public class User {
	
	//USER ID
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator1")
	@SequenceGenerator(name = "generator1", sequenceName = "gen1", initialValue = 1000)
	@Column(name="User_Id")
	private Integer userId;
	
	//USER NAME
	@Column(name="first_name" ,length=20)
	@NotEmpty
	@Size(min=3 , message="Name must contain atleast 3 characters")
	private String firstName;
	
	@Column(name="last_name" ,length=20)
	@NotEmpty
	@Size(min=3 , message="Name must contain atleast 3 characters")
	private String lastName;
	
	//DATE OF BIRTH
	@Column(name="DOB",length=30)
	private Date dateOfBirth;
	
	//GENDER
	@Column(name="Gender",length=30)
	@NotEmpty
	@Size(min=4,message="Gender must contain atleast 4 characters")
	private String gender;
	
	//EMAIL ID
	@Column(name="Email_Id",unique = true, length=30)
	@NotEmpty
	@Email(message = "Invalid EmailId")
	public String emailId;
	
	//PASSWORD
	@Column(name="password",length=20)
	@NotEmpty
	@Size(min=8, message="Password length must be 8 and contain uppercase,lowercase,digits")
	@Pattern(regexp="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")
	public String password;
	
	//CONTACT NUMBER
	@Column(name="phone_num",length=30)
	@NotEmpty
	@Pattern(regexp = "^[6-9][0-9]{9}$")
	private String phoneNumber;
	
	//DISTRICT
	@Column(name="District", length=30)
	@NotEmpty
	@Size(min=4,message = "District must contain atleast 4 character")
	private String district;
	
	//STATE
	@Column(name="state",length=20)
	@NotEmpty
	@Size(min=3 , message="district must contain atleast 3 characters")
    private String state;
	
	//ADDRESS
	@Column(name="Address",length=30)
	@NotEmpty
	@Size(min=3,message="Address is not proper,Give correct address")
	private String address;
	
	//ZIPCODE
	@Column(name="Zipcode",length=20)
	@NotEmpty
	@Size(min=6,max=6,message = "Zipcode should contains 6 digits compulsory")
	private String zipcode;
	
	//USER ROLE
	@Column(name="User_Role")
	@NotEmpty
	private String role;

}
