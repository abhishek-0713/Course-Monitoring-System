package com.learninghub.main.courseplan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.learninghub.dao.CoursePlanDao;
import com.learninghub.dao.CoursePlanDaoImpl;
import com.learninghub.exceptions.CoursePlanException;
import com.learninghub.extrafeatures.Style;
import com.learninghub.utility.DBUtil;

public class DeleteCoursePlan {
	
	public static void deletePlan(int facultyId) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn .prepareStatement("select batchId from batch where facultyId = ?");
			
			ps.setInt(1, facultyId);
			
			ResultSet rs = ps.executeQuery();
			List<String> arr = new ArrayList<>();
			while(rs.next()) {
				arr.add(rs.getString("batchId"));
			}
			
			if(arr.size()==0) {
				System.out.println(Style.YELLOW+"You Are Allocated to Any Batch"+Style.RESET);
				return;
			}
			
			System.out.println(Style.CYAN+"Choose Batch Id :"+Style.RESET);
			for(int i = 0; i < arr.size(); i++) {
				System.out.println((i+1)+". " +arr.get(i));
			}
			
			int ch = sc.nextInt();
			String batchId = arr.get(ch-1);
			
			System.out.println(Style.CYAN+"Enter the day no : "+Style.RESET);
			
			int dayNo = sc.nextInt();
			
			CoursePlanDao dao = new CoursePlanDaoImpl();
			
			try {
				String res = dao.deleteStatus(batchId, dayNo);
				System.out.println();
				System.out.println(res);
				System.out.println();
				
			} catch (CoursePlanException e) {
				System.out.println();
				System.out.println(Style.RED_BACKGROUND+ e.getMessage()+Style.RESET);
				System.out.println();
				
			}
			
		}catch (Exception e) {
			System.out.println();
			System.out.println(Style.RED+"Please Enter Right Input"+Style.RESET);
			System.out.println();
			
		}
		
	}
	
}