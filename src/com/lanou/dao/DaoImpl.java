package com.lanou.dao;

import com.lanou.entity.Staff;

public class DaoImpl extends BaseDao implements Dao 	{

	@Override
	public boolean addStaff(Staff staff) {
		String sql = "insert into staff values('" +staff.getName() +"',"+ staff.getAge() + ",'" + staff.getSex() + "')";
		int upDataSql = super.upDataSql(sql);
		return upDataSql == 0 ? false : true;
	}

	@Override
	public void deleteStaff(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStaff(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectStaff(String name) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
	