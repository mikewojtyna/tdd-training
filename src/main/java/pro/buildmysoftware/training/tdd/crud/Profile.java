package pro.buildmysoftware.training.tdd.crud;


import java.util.Collection;
import java.util.Objects;

class Profile {
	private String name;
	private String lastName;
	private int age;
	private String email;
	private Collection<Address> adddresses;

	public Profile(String name, String lastName, int age, String email,
		       Collection<Address> adddresses) {
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.adddresses = adddresses;
	}

	public Profile() {
	}

	@Override
	public String toString() {
		return "Profile{" + "name='" + name + '\'' + ", lastName='" + lastName + '\'' + ", age=" + age + ", email='" + email + '\'' + ", adddresses=" + adddresses + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Profile profile = (Profile) o;
		return age == profile.age && Objects
			.equals(name, profile.name) && Objects
			.equals(lastName, profile.lastName) && Objects
			.equals(email, profile.email) && Objects
			.equals(adddresses, profile.adddresses);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, lastName, age, email, adddresses);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
