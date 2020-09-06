package com.howay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howay.dao.RelationDao;

/**
 * 
 * @author howay
 *
 */
@Service
public class RelationServiceImp implements RelationService {

	@Autowired
	RelationDao relationDao;

	@Override
	public int insert(int recipient_id, int sender_id, String r_type, String s_type, int rt_id, int st_id,
			String time) {
		return relationDao.insert(recipient_id, sender_id, r_type, s_type, rt_id, st_id, time);
	}

}
