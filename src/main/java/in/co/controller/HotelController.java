package in.co.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import in.co.classes.Hotel;
import in.co.classes.QHotel;
import in.co.repository.HotelRepository;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	private HotelRepository hotelRepository;

	public HotelController(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	@GetMapping("/all")
	public List<Hotel> getAll(HttpSession session) {
		int count = 0;
		session.setAttribute("o", 1000);
		count = (Integer) session.getAttribute("o");
		System.out.println(count);
		List<Hotel> hotels = this.hotelRepository.findAll();
		return hotels;
	}

	@PutMapping
	public void insert(@RequestBody Hotel hotel) {
		this.hotelRepository.insert(hotel);
	}

	// if ID is exist then save method will update data otherwise it will create
	// data. while insert will create data every time
	@PostMapping
	public void update(@RequestBody Hotel hotel) {
        System.out.println("inside controller ...............");
        System.out.println("inside controller ...............");
        System.out.println("inside controller ...............");
        System.out.println("inside controller ...............");
		this.hotelRepository.save(hotel);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		this.hotelRepository.deleteById(id);
	}

	@GetMapping("/{id}")
	public Optional<Hotel> getById(@PathVariable("id") String id) {
		Optional<Hotel> hotel = this.hotelRepository.findById(id);
		return hotel;
	}

	@GetMapping("/price/{maxPrice}")
	public List<Hotel> getLessPriceHotelLessThan(@PathVariable("maxPrice") int price) {
		List<Hotel> hotels = this.hotelRepository.findByPricePerNightLessThan(price);
		return hotels;
	}

	@GetMapping("/address/{city}")
	public List<Hotel> getByCity(@PathVariable("city") String city) {
		List<Hotel> hotels = this.hotelRepository.findByCity(city);
		return hotels;
	}

	// http://www.querydsl.com
	@GetMapping("/country/{country}")
	public List<Hotel> getByCountry(@PathVariable("country") String country) {
		// create a Query Class QHotel
		QHotel qhotel = new QHotel("hotel");
		// using the query class to create filter
		BooleanExpression filterByCountry = qhotel.address.country.eq(country);
		// now we can pass the filter to FilterAll() method
		List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByCountry);
		return hotels;
	}

	@GetMapping("/andCondition")
	public List<Hotel> andCondition() {

		final int maxPrice = 100;
		final int minRating = 7;

		QHotel qhotel = new QHotel("hotel");
		BooleanExpression filterByPrice = qhotel.pricePerNight.lt(maxPrice);
		BooleanExpression filterByRate = qhotel.reviews.any().rating.gt(minRating);
		List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByPrice.and(filterByRate));
		return hotels;
	}

@GetMapping()
public String geeet(){
return "hello world";
}
	@GetMapping("/orCondition")
	public List<Hotel> orCondition() {

		final String hotelName = "merriot";
		final int rating = 8;

		QHotel qhotel = new QHotel("hotel");

		BooleanExpression filterByName = qhotel.name.eq(hotelName);
		BooleanExpression filterByRating = qhotel.reviews.any().rating.eq(rating);
		List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByName.or(filterByRating));
		return hotels;
	}

}
