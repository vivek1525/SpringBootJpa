package com.shris.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shris.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUid(int uid);
	
	@Query(value="SELECT * FROM user limit :limit", nativeQuery = true)
	List<User> findUsersByLimit(@Param("limit") int limit);

	@Query(value = "SELECT u FROM User u WHERE u.name =:name")
	List<User> findByName(@Param("name") String name);
	
	//@Query(value = "SELECT u FROM User u WHERE u.name =:name LIMIT =:limit")
	@Query(value="SELECT * FROM user WHERE name = :name limit :limit", nativeQuery = true)
	List<User> findByNameWithLimit(@Param("name") String name,@Param("limit") int limit);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.name = :name WHERE u.uid = :uid")
	void updateNameByUid(@Param("uid") int uid, @Param("name") String name);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.flag = 'N' WHERE u.uid in :uids")
	void updateMultipleFlagsByUids(@Param("uids") List<Integer> uids);

	@Transactional
	@Modifying
	@Query(value = "delete User u WHERE u.uid =:uid")
	void deleteByUId(@Param("uid") int uid);
}
