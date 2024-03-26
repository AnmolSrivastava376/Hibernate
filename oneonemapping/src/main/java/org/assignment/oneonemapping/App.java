package org.assignment.oneonemapping;

import java.util.List;

import org.assignment.config.HibernateUtil;
import org.assignment.entity.RiotAccount;
import org.assignment.entity.ValorantAccount;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class App {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            System.out.println("\n-------------------------------------------------------------------------------------------------\n"
                    + "Each riot account(riot is service provider for games) has id username and password. "
                    + "For one riot account, you can only create one valorant account, with any username but same riot id"
                    + "\n-------------------------------------------------------------------------------------------------");

            // First Sample Data
            RiotAccount riotAccount1 = new RiotAccount("Thunder376", "thunder123");
            session.persist(riotAccount1);
            ValorantAccount valorantAccount1 = new ValorantAccount("Anmol",riotAccount1.getId());
            session.persist(valorantAccount1);

            // Second Sample Data
            RiotAccount riotAccount2 = new RiotAccount("SkyKnight", "sky123");
            session.persist(riotAccount2);
            ValorantAccount valorantAccount2 = new ValorantAccount("Vishal", riotAccount2.getId());
            session.persist(valorantAccount2);

            // Third Sample Data
            RiotAccount riotAccount3 = new RiotAccount("FireBlaze", "fire123");
            session.persist(riotAccount3);
            ValorantAccount valorantAccount3 = new ValorantAccount("Ritk", riotAccount3.getId());
            session.persist(valorantAccount3);
            
            TypedQuery<Object[]> query = session.createQuery(
            	    "SELECT r, v FROM RiotAccount r INNER JOIN ValorantAccount v ON r.id = v.id", Object[].class);
            List<Object[]> results = query.getResultList();
            System.out.println("\n-----------------------------------------------------------------------------------------------\n");
            for (Object[] result : results) {
                RiotAccount riotAccount = (RiotAccount) result[0];
                ValorantAccount valorantAccount = (ValorantAccount) result[1];
                System.out.println("ID: " + riotAccount.getId() + ", Username: " + riotAccount.getUsername() + ", Valorant Username: " + valorantAccount.getValorantUsername());
            }
            
            try {
            	ValorantAccount copyAccount = new ValorantAccount("CopyOfRitik", riotAccount3.getId());
                session.persist(copyAccount);
            }
            catch(Exception e) {
            	System.out.println("Error : "+e);
            }

            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
