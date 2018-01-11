import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.model.User;

class UserDaoTest {

	@Test
	void testAllUsers() 
	{
		UserDao dao = new UserDaoImpl(); 
		for(User user: dao.getAllUsers())
			System.out.println(user);
	}
	
	
}
