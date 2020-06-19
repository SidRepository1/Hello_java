package in.co.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import in.co.classes.Hotel;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String>, QuerydslPredicateExecutor<Hotel> {
	Optional<Hotel> findById(String id);

	List<Hotel> findByPricePerNightLessThan(int maxPrice);

	// next line is same as= db.hotels.find({"address.city":"Paris"})
	@Query(value = "{'address.city':?0}")
	List<Hotel> findByCity(String city);
}