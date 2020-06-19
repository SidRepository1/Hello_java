package in.co.DBOnStart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.co.classes.Address;
import in.co.classes.Hotel;
import in.co.classes.Review;
import in.co.repository.HotelRepository;

@Component
public class DBSetter implements CommandLineRunner {

	private HotelRepository hotelRepository;

	public DBSetter(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		Hotel merriot = new Hotel("asdff66", "merriot", 130, new Address("Paris", "France"),
				Arrays.asList(new Review("John", 8, true), new Review("Wick", 3, false)));

		Hotel Ibis = new Hotel("asdff67", "ibis", 90, new Address("Mumbai", "India"),
				Arrays.asList(new Review("Shan", 8, true)));
		Hotel sofitel = new Hotel("asdff69", "sofitel", 199, new Address("Rome", "Itly"), new ArrayList<>());

		// drop all Hotels
		this.hotelRepository.deleteAll();

		// add our hotels to DataBase
		List<Hotel> hotels = Arrays.asList(merriot, Ibis, sofitel);
		this.hotelRepository.saveAll(hotels); 
	}

}
