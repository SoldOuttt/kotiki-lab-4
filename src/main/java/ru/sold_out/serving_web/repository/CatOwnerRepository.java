package ru.sold_out.serving_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sold_out.serving_web.entity.CatOwner;
import ru.sold_out.serving_web.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CatOwnerRepository extends JpaRepository<CatOwner, Long> {
	List<CatOwner> findByFullName(String fullName);
	List<CatOwner> findByDayOfBirth(Date dayOfBirth);
	Optional<CatOwner> findByUserId(Long userId);
	Optional<CatOwner> findByUser(User user);
}