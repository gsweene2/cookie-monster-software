package com.garrett.firstwebsite;

import com.garrett.firstwebsite.role.Role;
import com.garrett.firstwebsite.role.RoleRepository;
import com.garrett.firstwebsite.user.User;
import com.garrett.firstwebsite.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstWebsiteApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;


	@Test
	public void createUser_withCorrectUserObject_shouldInsertUser(){
		Set<Role> roles = new HashSet<Role>();
		Role role = new Role(1,"USER");
		roles.add(role);
		roleRepository.save(role);
		User user1 = new User(1,"g@mail.com","pass","Gar","Swe","5403144892",1,roles);
		userService.saveUser(user1);
	}

	@Test
	public void createUser_withIncorrectUserObject_thatIsMissingValues_shouldThrowAnError(){

	}

}
