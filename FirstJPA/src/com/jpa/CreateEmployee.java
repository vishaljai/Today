package com.jpa;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateEmployee {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FirstJPA");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Employee emp = new Employee();
		
		System.out.println("Enter your Choice: ");
		System.out.println("1. Insert");
		System.out.println("1. Delete");
		System.out.println("1. Update");
		System.out.println("1. Display");
		
		switch(sc.nextInt()){
		
		case 1: System.out.println("Enter name and age of employee:");
		emp.setEmpName(sc.nextLine());
		emp.setAge(sc.nextInt());
		 em.getTransaction().commit();
		 em.close();
		 
			break;
			
		case 2: System.out.println("Enter employee id to delete: ");
		emp = em.find(Employee.class, sc.nextInt());
		em.remove(emp.getEmpId());
		
		}
	}

}
