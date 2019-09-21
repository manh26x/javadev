package com.mike.sbsocialjpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mike.sbsocialjpa.entity.UserConnection;

@Repository
@Transactional
public class UserConnectionDAO {

	@Autowired
	private EntityManager entityManager;
	
	public UserConnection findUserConnectionByUserProviderId(String userProviderId) {
		try {
			String sql = "Select e from " + UserConnection.class.getName() + " e " //
					+ " where e.userProviderId = :userProviderId";
			
			Query query = entityManager.createQuery(sql, UserConnection.class);
			query.setParameter("userProviderId", userProviderId);
			return (UserConnection) query.getSingleResult();
			
		} catch(NoResultException e) {
			return null;
		}
	}
}
