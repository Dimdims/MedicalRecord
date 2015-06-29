package com.rekam.medis.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.rekam.medis.domain.user.Role;
import com.rekam.medis.domain.user.User;
import com.rekam.medis.service.user.UserService;

/**
 * A custom service for retrieving users from a custom datasource, such as a database.
 * <p>
 * This custom service must implement Spring's {@link UserDetailsService}
 */
@Transactional(readOnly = true)
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private UserService userService;

	/**
	 * Retrieves a user record containing the user's credentials and access. 
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		// Declare a null Spring User
		System.out.println("username: "+username);
		UserDetails userDetails = null;
		try {
			System.out.println("username1: "+username);
			System.out.println("userService "+ userService);
			// Search database for a user that matches the specified username
			// You can provide a custom DAO to access your persistence layer
			// Or use JDBC to access your database
			// DbUser is our custom domain user. This is not the same as Spring's User
			User user = userService.getByUsername(username);
			// Populate the Spring User object with details from the dbUser
			// Here we just pass the username, password, and access level
			// getAuthorities() will translate the access level to the correct role type
			System.out.println("username2: "+username);
			System.out.println("user3: "+user);
			userDetails =  new org.springframework.security.core.userdetails.User(
					user.getUsername(), 
					user.getPassword().toLowerCase(),
					true,
					true,
					true,
					true,
					getAuthorities(user.getRoles()) );

		} catch (Exception e) {
			System.out.println("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		
		// Return user to Spring for processing.
		// Take note we're not the one evaluating whether this user is authenticated or valid
		// We just merely retrieve a user that matches the specified username
		return userDetails;
	}
	
	/**
	 * Retrieves the correct ROLE type depending on the access level, where access level is an Integer.
	 * Basically, this interprets the access value whether it's for a regular user or admin.
	 * 
	 * @param access an integer value representing the access of the user
	 * @return collection of granted authorities
	 */
	 public Collection<GrantedAuthority> getAuthorities(List<Role> userRoles) {
			
		 // Create a list of grants for this user
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
			
			//grant authorities to the user
			for(Role role : userRoles){
				authList.add(new GrantedAuthorityImpl(role.getAuthority()));
			}

			// Return list of granted authorities
			return authList;
	  }
}
