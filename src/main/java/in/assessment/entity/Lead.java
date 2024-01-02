package in.assessment.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "leads")
public class Lead {

    @Id
    @NotNull(message = "Lead ID cannot be null")
    @Positive(message = "Lead ID must be a positive integer")
    private Integer leadId;

    @NotBlank(message = "First name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name should only contain alphabets")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "Middle name should only contain alphabets")
    private String middleName;

    @NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should only contain alphabets")
    private String lastName;

    @NotBlank(message = "Mobile number is mandatory")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
    private String mobileNumber;

    @NotBlank(message = "Gender is mandatory")
    private String gender;

    @NotNull(message = "Date of Birth is mandatory")
    @Past(message = "Date of Birth should be in the past")
    private Date dob;

    @Email(message = "Invalid email address")
    private String email;

	public Lead(
			@NotNull(message = "Lead ID cannot be null") @Positive(message = "Lead ID must be a positive integer") Integer leadId,
			@NotBlank(message = "First name is mandatory") @Pattern(regexp = "^[a-zA-Z]+$", message = "First name should only contain alphabets") String firstName,
			@Pattern(regexp = "^[a-zA-Z]*$", message = "Middle name should only contain alphabets") String middleName,
			@NotBlank(message = "Last name is mandatory") @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should only contain alphabets") String lastName,
			@NotBlank(message = "Mobile number is mandatory") @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number") String mobileNumber,
			@NotBlank(message = "Gender is mandatory") String gender,
			@NotNull(message = "Date of Birth is mandatory") @Past(message = "Date of Birth should be in the past") Date dob,
			@Email(message = "Invalid email address") String email) {
		super();
		this.leadId = leadId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
	}

	public Integer getLeadId() {
		return leadId;
	}

	public void setLeadId(Integer leadId) {
		this.leadId = leadId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
