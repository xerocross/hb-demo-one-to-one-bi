package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

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

			Instructor tempInstructor = new Instructor("Jim", "Harris", "test@test.com");
			InstructorDetail instructorDetail = new InstructorDetail("youtube", "swimming");
			tempInstructor.setInstructorDetail(instructorDetail);
			
			session.save(tempInstructor);
			
			
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
