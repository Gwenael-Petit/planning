package sae.planning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import sae.planning.pojo.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	@Query("Select a from User a where a.email= :email and a.pwd= :pwd")
    public User findUserByEmailAndPwd(@Param("email") String email,@Param("pwd") String pwd);

	@Query("Select a from User a where a.email= :email")
	public Optional<User> findByEmail(@Param("email")String email);
	
}
