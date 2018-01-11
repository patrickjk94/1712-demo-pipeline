import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.model.ManToRiePair;
import com.revature.model.Reimbursement;
import com.revature.model.User;

import org.junit.Assert; 

class DaoTest {
	
	static ArrayList<User> users; 
	static ArrayList<Reimbursement> reimbursements; 
	
	@Before
	void onSetUp() {
		UserDao  user_dao = new UserDaoImpl(); 
		users = user_dao.getAllUsers(); 
		
		ReimbursementDao rei_dao = new ReimbursementDaoImpl();

		TestingDao dao = new TestingDao(); 
		dao.initDataBase();
	}
	
	@Test
	void testGetAllUsers() 
	{
		onSetUp();
		System.out.println("=== All Users ===");
		for(User user : users) 
		{
			System.out.println(user);
		}
		Assert.assertEquals(3, users.size());
	}

	@Test 
	void testCreateReimbursementAndGetPendingReimbursementsForUser()
	{	
		onSetUp();
		User user = new User(); 
		for(User u : users) {
			if(u.getUsername().equals("user01")) {
				user = u; 
			}
		}
		
		ReimbursementDao dao = new ReimbursementDaoImpl(); 		

		Reimbursement new_rei = new Reimbursement(2003, 60, "Computer", null, null, null, user.getId(), 0, 3, 0); 
		dao.createReimbursement(user, new_rei); 
		
		ArrayList<Reimbursement> reimbursements = dao.getPendingReimbursementsForUser(user); 
		System.out.println("------ " + user.getUsername() + "PENDING REI  --------");
		for(Reimbursement re : reimbursements) 
		{
			System.out.println(re.getId() + " : " + re.getStatus() + " : auth: " + re.getAuthor_id());
		}
	}
	
	@Test
	void testApproveReimbursement() 
	{
		Assert.fail();
		User user = new User(); 
		user.setUsername("user02"); 
		user.setId(1007);
		
		ReimbursementDao dao = new ReimbursementDaoImpl(); 
//		dao.approveReimbursement(user, rei); 
	}
	
	@Test
	void testGetResolvedReimbursementsForUser() {
		Assert.fail();
	}
	
	@Test 
	void testGetResolvedReimbursements() {
		Assert.fail();
	}
		
	@Test
	void testDenyReimbursement()
	{
		Assert.fail();
	}
	
	@Test
	void testReadApprovedRiembursements() 
	{
		Assert.fail(); 
	}
		
}