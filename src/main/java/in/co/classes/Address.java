package in.co.classes;

public class Address {
	private String city;
	private String country;

	public Address(String city, String country) {
		super();
		this.city = city;
		this.country = country;
	}

	public Address() {
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}
}