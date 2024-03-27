package org.assignment.oneonemapping;

import org.assignment.config.HibernateUtil;
import org.assignment.entity.RiotAccount;
import org.assignment.entity.ValorantAccount;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class App {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            System.out.println("\n-------------------------------------------------------------------------------------------------\n"
                    + "Each riot account(riot is service provider for games) has id username and password. "
                    + "For one riot account, you can only create one valorant account, with any username but same riot id"
                    + "\n-------------------------------------------------------------------------------------------------");

            // First Sample Data
            RiotAccount riotAccount1 = new RiotAccount("t#001","Thunder376", "thunder123");
            ValorantAccount valorantAccount1 = new ValorantAccount("Anmol");
            riotAccount1.setValorantAccount(valorantAccount1);
            valorantAccount1.setRiotAccount(riotAccount1);
            session.persist(riotAccount1);
            session.persist(valorantAccount1);

            // Second Sample Data
            RiotAccount riotAccount2 = new RiotAccount("s#005","SkyKnight", "sky123");
            ValorantAccount valorantAccount2 = new ValorantAccount("Vishal");
            riotAccount1.setValorantAccount(valorantAccount1);
            valorantAccount2.setRiotAccount(riotAccount2);
            session.persist(riotAccount2);
            session.persist(valorantAccount2);

            // Third Sample Data
            RiotAccount riotAccount3 = new RiotAccount("f#013","FireBlaze", "fire123");
            ValorantAccount valorantAccount3 = new ValorantAccount("Ritk");
            riotAccount1.setValorantAccount(valorantAccount1);
            valorantAccount3.setRiotAccount(riotAccount3);
            session.persist(riotAccount3);
            session.persist(valorantAccount3);

            List<Object[]> results = session.createQuery(
                    "SELECT r, v FROM RiotAccount r JOIN r.valorantAccount v", Object[].class)
                    .getResultList();

            System.out.println("\n-----------------------------------------------------------------------------------------------\n");
            for (Object[] result : results) {
                ValorantAccount valorantAccount = (ValorantAccount) result[1];
                System.out.println(
                		"Riot Account ID: " + valorantAccount.getRiotAccount().getRiotUniqueId() +
                        ", Username: " + valorantAccount.getRiotAccount().getUsername() +
                        ", Valorant Account ID: " + valorantAccount.getId() +
                        ", Valorant Username: " + valorantAccount.getValorantUsername());
            }

            System.out.println("\n-----------------------------------------------------------------------------------------------\n");
            
            RiotAccount riotAccountToDelete = session.get(RiotAccount.class, riotAccount1.getRiotUniqueId());
            session.remove(riotAccountToDelete);

            results = session.createQuery(
                    "SELECT r, v FROM RiotAccount r JOIN r.valorantAccount v", Object[].class)
                    .getResultList();

            System.out.println("\n-----------------------------------------------------------------------------------------------\n");
            for (Object[] result : results) {
                ValorantAccount valorantAccount = (ValorantAccount) result[1];
                System.out.println(
                		"Riot Account ID: " + valorantAccount.getRiotAccount().getRiotUniqueId() +
                        ", Username: " + valorantAccount.getRiotAccount().getUsername() +
                        ", Valorant Account ID: " + valorantAccount.getId() +
                        ", Valorant Username: " + valorantAccount.getValorantUsername());
            }
            

            System.out.println("\n-----------------------------------------------------------------------------------------------\n");
            
            // Trying to attach 2 riot accounts to same valorant id
            try {
            	RiotAccount riotAccountCopy = new RiotAccount("z#201","Zero123", "024csf");
                riotAccountCopy.setValorantAccount(valorantAccount1);
                session.persist(riotAccountCopy);
            	
            }catch(Exception e) {
            	System.out.println("Error : "+e);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
