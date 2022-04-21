package ru.sold_out.serving_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sold_out.serving_web.entity.Cat;
import ru.sold_out.serving_web.entity.User;
import ru.sold_out.serving_web.entity.additional_info.CatColor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
	List<Cat> findByName(String name);
	List<Cat> findByDayOfBirth(Date dayOfBirth);
	List<Cat> findByBreed(String breed);
	List<Cat> findByColor(CatColor catColor);
	List<Cat> findByCatOwnerId(Long id);
	List<Cat> findByCatOwnerUser(User user);
	Optional<Cat> findByIdAndCatOwnerUser(Long id, User user);
}
