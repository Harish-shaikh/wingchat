package wingchat.wingchat.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import wingchat.wingchat.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.mobile = :mobile")
    public User userdetails(@Param("mobile") String mobile);

}