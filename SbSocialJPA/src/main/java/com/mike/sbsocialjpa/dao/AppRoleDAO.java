package com.mike.sbsocialjpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mike.sbsocialjpa.entity.AppRole;
import com.mike.sbsocialjpa.entity.AppUser;
import com.mike.sbsocialjpa.entity.UserRole;

@Repository
@Transactional
public class AppRoleDAO {
	/*
	 * 25/7 - 10h35 
	 */
	@Autowired
	private EntityManager enityManager;

	public List<String> getRoleNames(Long userId) {
		String sql = "Select ur.appRole.roleName from " + UserRole.class.getName() + " ur " //
				+ " where ur.appUser.userId = :userId";
		
		Query query = this.enityManager.createQuery(sql, String.class);
		query.setParameter("userId", userId);
		
		return 	query.getResultList();
	}
	
	public AppRole findAppRoleByName(String roleName) {
		try {
			String sql = "Select e from " + AppRole.class.getName() + " e " //
					+ " where e.roleName = :roleName";
			
			Query query = this.enityManager.createQuery(sql, AppRole.class);
			
			query.setParameter("roleName", roleName);
			return (AppRole) query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	public void createRoleFor(AppUser appUser, List<String> roleNames) {
		//
		for (String roleName: roleNames) {
			AppRole role = this.findAppRoleByName(roleName);
			if(role == null) {
				role = new AppRole();
				role.setRoleName(AppRole.ROLE_USER);
				this.enityManager.persist(role);
				this.enityManager.flush();
			}
			UserRole userRole = new UserRole();
			userRole.setAppRole(role);
			userRole.setAppUser(appUser);
			this.enityManager.persist(userRole);
			this.enityManager.flush();
		}
		
		
	}
}
