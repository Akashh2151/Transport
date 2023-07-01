package transportapk.dao;


 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import transportapk.entity.user;

 

 

@Repository
public interface UserRepository extends JpaRepository<user,Integer> {
	
	@Query("select u from user u Where u.username = :username")
	public user  getUsername(@Param("username")String username);
	
//	
	@Query("select u from user u Where u.password = :password")
	public user  getpassword(@Param("password")String password);
	
	
	@Query("select u from user u Where u.email = :email")
	public user  getemail(@Param("email")String email);
	
	
	
 


 
 


 


 


 
	
	
//	@Query("update user u set u.password = :password Where u.password = :password")
//	public user updatepassword(@Param("password")String password);

 
	
 
	
	
 
                     
 

	
	

}
