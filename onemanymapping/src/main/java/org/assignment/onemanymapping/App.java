package org.assignment.onemanymapping;

import org.assignment.entity.GamingOrganization;
import org.assignment.entity.Trophies;
import java.util.List;
import org.assignment.config.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        GamingOrganization organization1 = new GamingOrganization("Fnatic");

        Trophies trophy1 = new Trophies("VCT Masters Tokyo, 2021");
        trophy1.setGamingOrganization(organization1);

        Trophies trophy2 = new Trophies("CS:GO Champions 2020");
        trophy2.setGamingOrganization(organization1);
        
        Trophies trophy3 = new Trophies("PMCO Global 2022");
        trophy3.setGamingOrganization(organization1);

        organization1.getTrophies().add(trophy1);
        organization1.getTrophies().add(trophy2);
        organization1.getTrophies().add(trophy3);

        GamingOrganization organization2 = new GamingOrganization("Optic Gaming");

        Trophies trophy4 = new Trophies("VCT Masters NA, 2020");
        trophy4.setGamingOrganization(organization2);

        organization2.getTrophies().add(trophy4);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(organization1);
        session.persist(organization2);

        session.getTransaction().commit();
        session.close();
        printOrganizationsWithTrophies(sessionFactory);

        sessionFactory.close();
    }

    private static void printOrganizationsWithTrophies(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        List<GamingOrganization> organizations = session.createQuery("FROM GamingOrganization", GamingOrganization.class).list();

        for (GamingOrganization organization : organizations) {
            System.out.println("Organization: " + organization.getName());
            System.out.println("Trophies:");
            for (Trophies trophy : organization.getTrophies()) {
                System.out.println("- " + trophy.getName());
            }
            System.out.println();
        }

        session.close();
    }
}




