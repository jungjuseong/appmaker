package com.clbee.appmaker.dao.impl;

import com.clbee.appmaker.dao.DeviceDao;
import com.clbee.appmaker.model.Device;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceDaoImpl implements DeviceDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int insertDeviceInfo(Device device) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			session.save(device);
			
			tx.commit();
		}
		catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}
		finally {
			session.close();
		}
		
		return device.getDeviceSeq();
	}

	@Override
	public int updateDeviceInfo(Device updated) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			Device device = session.get(Device.class, updated.getDeviceSeq());
			
			if(updated.getDeviceInfo() != null && !"".equals(updated.getDeviceInfo()))
				device.setDeviceInfo(updated.getDeviceInfo());
			if(updated.getDeviceType() != null && !"".equals(updated.getDeviceType()))
				device.setDeviceType(updated.getDeviceType());
			if(updated.getDeviceUuid() != null && !"".equals(updated.getDeviceUuid()))
				device.setDeviceUuid(updated.getDeviceUuid());
			if(updated.getUseGb() != null && !"".equals(updated.getUseGb()))
				device.setUseGb(updated.getUseGb());
			if(updated.getRegUserSeq() != null)
				device.setRegUserSeq(updated.getRegUserSeq());

			session.update(device);
			tx.commit();
			return 1;
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
			return -1;
		}finally {
			session.close();
		}
	}

	@Override
	public List<Device> selectDeviceList(int startNo, int companySeq, String searchType, String searchValue) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List list = null;
		
		try {
			tx = session.beginTransaction();
	
			Criteria cr = session.createCriteria(Device.class);
			cr.add(
				Restrictions.eq("companySeq", companySeq)
			)
			.setFirstResult(startNo)
			.setMaxResults(10)
			.addOrder(Order.desc("regDt"));
	
			/*
			 * 1. ���� �˻�
			 * 2. ����� �˻�
			 * 3. �׷�1 �˻�
			 * 4. �׷�2 �˻�
			*/
			if(searchType != null && !"".equals(searchType)) {
				switch(Integer.parseInt(searchType)) {
					case 1 :
						cr.add(Restrictions.like("deviceInfo", "%"+searchValue+"%"));
						break;
					case 2 :
						cr.createCriteria("memberVO").add(Restrictions.like("userId", "%"+searchValue+"%"));
						break;
					case 3 :
						cr.createCriteria("memberVO").add(Restrictions.like("onedepartmentName", "%"+searchValue+"%"));
						break;
					case 4 :
						cr.createCriteria("memberVO").add(Restrictions.like("twodepartmentName", "%"+searchValue+"%"));
						break;
				}
			}
			list = cr.list();
	
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}

		return list;
	}

	@Override
	public int selectDeviceListCount( int companySeq, String searchType, String searchValue) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List list = null;
		
		try {
			tx = session.beginTransaction();
	
			Criteria cr = session.createCriteria(Device.class);
			cr.add(
				Restrictions.eq("companySeq", companySeq)
			);
	
			/*
			 * 1. ���� �˻�
			 * 2. ����� �˻�
			 * 3. �׷�1 �˻�
			 * 4. �׷�2 �˻�
			*/
			if(searchType != null && !"".equals(searchType)) {
				switch(Integer.parseInt(searchType)) {
					case 1 :
						cr.add(Restrictions.like("deviceInfo", "%"+searchValue+"%"));
						break;
					case 2 :
						cr.createCriteria("memberVO").add(Restrictions.like("userId", "%"+searchValue+"%"));
						break;
					case 3 :
						cr.createCriteria("memberVO").add(Restrictions.like("onedepartmentName", "%"+searchValue+"%"));
						break;
					case 4 :
						cr.createCriteria("memberVO").add(Restrictions.like("twodepartmentName", "%"+searchValue+"%"));
						break;
				}
			}
			
			list = cr.list();
			
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		return list.size();
	}

	@Override
	public Device selectDeviceInfo(int deviceSeq) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Device device = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(Device.class);
			cr.add(
				Restrictions.eq("deviceSeq", deviceSeq)
			);
			
			device = (Device)cr.uniqueResult();
	
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		
		return device;
	}

	@Override
	public int checkIfExistUUID(String deviceUuid, int companySeq) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Device> list = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(Device.class);
			cr.add(
				Restrictions.eq("deviceUuid", deviceUuid)
			);
			list = cr.list();
	
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		/*
		 * 1 : �ش��ϴ� UUID�� �������� ���� ( UUID�� ����ؾߵ� )
		 * 2 : �ش��ϴ� UUID�� 1�� �̻� ������. ( Duplicated )
		 * 3 : �ش��ϴ� UUID�� 1�� �����ϴµ� ����� �� ���� UUID ( Rejected ) 
		 * 4 : �ش��ϴ� UUID�� 1�� �����ϰ� ����Ҽ� �ִ� UUID�� ( Success !! )
		 * 5 : �ش��ϴ� UUID�� 1�� �����ϰ� ����� �� �ִ� UUID�ε�, companySeq�� �ٸ�
		 */

		
		
		if(list.size() == 0) {
			return 1;
		}else if(list.size() == 1 ) {
			if(companySeq != list.get(0).getCompanySeq() ){
				return 5;
			}else{
				if("2".equals(list.get(0).getUseGb()))return 3;
				else return 4;
			}
			
		}else {//if(list.size() > 1 ) �Ѱ� �̻��ϰ��
			int deviceCnt = 0;
			int [] useGbCnt ;
			useGbCnt = new int[ list .size () ];

			for(int i=0; i< list.size() ; i++){
				
				if(companySeq == list.get(i).getCompanySeq()){
					useGbCnt[deviceCnt] = Integer.parseInt(list.get(i).getUseGb());
					System.out.println();
					deviceCnt++;
				}
			}
			
			for(int i=0; i< useGbCnt.length;i++){
				System.out.println("useGbCnt = ["+i+"] = "+useGbCnt[i]);
			}
			
			System.out.println("deviceCnt = " + deviceCnt);
			switch(deviceCnt){
				case 0 : return 1;
					
				case 1 : 
					if( 2  == useGbCnt[0]) return 3;
					else return 4;
					
				default :return 2;
			}
			/*if (deviceCnt > 1 ) return 2;
			if ( devi)*/
		}
	}

	@Override
	public int countDeviceIsAvailable(int companySeq) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Device> list = null;
		
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Device.class);
	
			cr.add(
				Restrictions.and(
					Restrictions.eq("companySeq", companySeq),
					Restrictions.eq("useGb", "1")
				)
			);
			list = cr.list();
	
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
	
		return list.size();
	}
}
