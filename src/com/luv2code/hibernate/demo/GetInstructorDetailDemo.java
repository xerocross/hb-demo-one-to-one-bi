package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
	
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();

			int id = 1;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, id);
			System.out.println("InstructorDetail: " + tempInstructorDetail);
			
			System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
			
			// now let's delete the instructor detail
			
			System.out.println("deleting tempInstructorDetail: " + tempInstructorDetail);
			session.delete(tempInstructorDetail);
			
			session.getTransaction().commit();
		} catch (Exception e ) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}