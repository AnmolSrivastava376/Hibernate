package org.assignment.manymanymapping;

import org.assignment.config.HibernateUtils;
import org.assignment.entity.Guns;
import org.assignment.entity.Skins;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Set;

public class App {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		insertData(sessionFactory);
		printTables(sessionFactory);
		sessionFactory.close();
	}

	private static void insertData(SessionFactory sessionFactory) {
		Guns vandal = new Guns();
		vandal.setName("Vandal");

		Guns phantom = new Guns();
		phantom.setName("Phantom");

		Guns spectre = new Guns();
		spectre.setName("Spectre");

		Guns operator = new Guns();
		operator.setName("Operator");

		Skins reaver = new Skins();
		reaver.setName("Reaver");

		Skins prime = new Skins();
		prime.setName("Prime");

		Skins champions = new Skins();
		champions.setName("Champions");

		Skins ion = new Skins();
		ion.setName("Ion");

		Skins spline = new Skins();
		spline.setName("Spline");

		vandal.getSkins().add(reaver);
		vandal.getSkins().add(prime);
		vandal.getSkins().add(champions);

		phantom.getSkins().add(reaver);
		phantom.getSkins().add(prime);
		phantom.getSkins().add(ion);
		phantom.getSkins().add(champions);

		spectre.getSkins().add(prime);
		spectre.getSkins().add(reaver);

		operator.getSkins().add(reaver);
		operator.getSkins().add(ion);
		operator.getSkins().add(spline);

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			session.persist(vandal);
			session.persist(phantom);
			session.persist(spectre);
			session.persist(operator);
			session.persist(reaver);
			session.persist(prime);
			session.persist(champions);
			session.persist(ion);
			session.persist(spline);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printTables(SessionFactory sessionFactory) {
		try (Session session = sessionFactory.openSession()) {
			Query<Guns> gunQuery = session.createQuery("FROM Guns", Guns.class);
			List<Guns> guns = gunQuery.list();
			System.out.println("Guns Table:");
			for (Guns gun : guns) {
				System.out.println("Gun ID: " + gun.getId() + ", Name: " + gun.getName());
				System.out.println("Associated Skins:");
				Set<Skins> skins = gun.getSkins();
				for (Skins skin : skins) {
					System.out.println("- Skin ID: " + skin.getId() + ", Name: " + skin.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
