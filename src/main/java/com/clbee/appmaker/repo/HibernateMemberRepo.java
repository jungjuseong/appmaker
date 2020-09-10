package com.clbee.appmaker.repo;

import com.clbee.appmaker.dao.Member;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.hibernate.SessionFactory;

import javax.transaction.Transactional;

@Repository
@Transactional
public class HibernateMemberRepo implements MemberRepo {

	private final SessionFactory sessionFactory;

	HibernateMemberRepo(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Member> findAll() {
		return getSession().createQuery("SELECT m FROM Member m", Member.class).getResultList();
	}

	@Override
	public Member save(Member member) {
		getSession().persist(member);
		return member;
	}

	@Override
	public Member findById(long id) {
		return getSession().find(Member.class, id);
	}

	@Override
	public Member findByUserName(String username){

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Member memberVO = null;

		try {
			tx = session.beginTransaction();

			Criteria cr = session.createCriteria(Member.class);
			Criterion user = Restrictions.eq("userId", username);

			cr.add(user);
			memberVO = (Member) cr.uniqueResult();

			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}

		return memberVO;
	}
}
