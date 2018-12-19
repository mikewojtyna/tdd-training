package pro.buildmysoftware.training.tdd.crud;

import java.util.Objects;

public class Address {
	private String street;

	public Address(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "Address{" + "street='" + street + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Address address = (Address) o;
		return Objects.equals(street, address.street);
	}

	@Override
	public int hashCode() {
		return Objects.hash(street);
	}
}
