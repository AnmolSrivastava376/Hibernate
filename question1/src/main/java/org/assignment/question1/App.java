package org.assignment.question1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.assignment.configurations.HibernateUtils;
import org.assignment.entity.Employee;

public class App 
{
    public static void main( String[] args )
    {
    	Employee employee = new Employee();
    	employee.setEmpName("Anmol Srivastava");
    	employee.setEmpEmail("anmol@gmail.com");
    	Employee employee1 = new Employee();
    	employee1.setEmpName("Vishal Kumar Sahu");
    	employee1.setEmpEmail("vishal@gmail.com");
    	
    	SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	System.out.println("----------------------------------------------------------");
    	
    	//create
    	session.persist(employee);
    	session.persist(employee1);
    	session.createQuery("from Employee", Employee.class).list().forEach(System.out::println);
    	System.out.println("----------------------------------------------------------");
    	
    	//read
    	Employee emp = session.get(Employee.class, 1);
    	System.out.println("Read : "+ emp.getEmpName()+" "+emp.getEmpEmail());
    	
    	//update
    	emp.setEmpEmail("anmol@accolitedigital.com");
    	session.merge(emp);	
    	session.createQuery("from Employee", Employee.class).list().forEach(System.out::println);
    	System.out.println("----------------------------------------------------------");
    	
    	//delete
    	Employee e = session.get(Employee.class, 2);
    	System.out.println("To be deleted : "+e.getEmpName());
    	session.remove(e);
    	session.getTransaction().commit();
    	session.createQuery("from Employee", Employee.class).list().forEach(System.out::println);
    	System.out.println("----------------------------------------------------------");
    	session.close();
    }
}
;
