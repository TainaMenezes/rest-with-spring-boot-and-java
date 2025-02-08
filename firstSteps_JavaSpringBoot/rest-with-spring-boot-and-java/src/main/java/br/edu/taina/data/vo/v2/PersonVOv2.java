package br.edu.taina.data.vo.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PersonVOv2 implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String genre;
	private Date birthday;

	public PersonVOv2() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, birthday, firstName, genre, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVOv2 other = (PersonVOv2) obj;
		return Objects.equals(address, other.address) && Objects.equals(birthday, other.birthday)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(genre, other.genre)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName);
	}

}
