package com.learninghub.main.facultyrights;

import java.util.Scanner;

import com.learninghub.dao.FacultyRightsDao;
import com.learninghub.dao.FacultyRightsDaoImpl;
import com.learninghub.exceptions.FacultyRightsException;
import com.learninghub.extrafeatures.Style;
import com.learninghub.model.Faculty;

public class FacultyLogin {
	
	public static void login() {
		Faculty faculty = null;
		
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		int i=4;
		for(; i>=0; i--) {
			System.out.println(Style.CYAN+"Enter Username:"+Style.RESET);
			String uname = sc.next();
			
			System.out.println(Style.CYAN+"Enter Password:"+Style.RESET);
			String pass = sc.next();
			
			FacultyRightsDao dao = new FacultyRightsDaoImpl();
			
			try {
				faculty = dao.loginFaculty(uname, pass);
//				System.out.println(faculty);
				if(faculty == null) {
					
				}else {				
					System.out.println();
					System.out.println(Style.PURPLE_BACKGROUND+" Welcome "+faculty.getFname()+" "+Style.RESET);
					System.out.println();
					FacultyLoginOptions.facultyOption(faculty);
					return;
				}
		 	
			}catch (FacultyRightsException e) {
				System.out.println();
				System.out.println(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);
				System.out.println();
				System.out.println(Style.YELLOW+i+" Attempts Left.."+Style.RESET);
				System.out.println();
				while(true) {
					System.out.println(Style.CYAN+"1. Forget Password");
					System.out.println("2. Want to try again?");
					System.out.println("3. Exit"+Style.RESET);
					
					String ch = sc.next();
					if(ch.equals("1")) {	
						boolean check = ForgetPassword.forgetPass();
						if(check)
							return;
						else
							break;
					}else if(ch.equals("2")) {	
						break;
					}else if(ch.equals("3")) {	
						return;
					}else {
						System.out.println();
						System.out.println(Style.RED+"Wrong Input...!"+Style.RESET);
						System.out.println();
					}
				}
				
			}
		}
		System.out.println();
		System.out.println(Style.YELLOW+"Try After 5 minutes.."+Style.RESET);
		System.out.println();
	}
		
}
