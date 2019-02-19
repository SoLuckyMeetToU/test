package com.lanou.dao;

import com.lanou.entity.Staff;

public interface Dao {
	boolean addStaff(Staff staff);
	
	void deleteStaff(String name);
	
	void updateStaff(String name);
	
	void selectStaff(String name);
}
