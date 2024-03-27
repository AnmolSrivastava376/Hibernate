package org.assignment.manyonemapping;

import org.assignment.config.HibernateUtils;
import org.assignment.entity.Platform;
import org.assignment.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.*;

public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Platform youtube = new Platform("YouTube");
        Player player1 = new Player("TenZ", youtube);
        Player player2 = new Player("Derke", youtube);
        Player player3 = new Player("T3xture", youtube);
        Player player4 = new Player("Boaster", youtube);

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(youtube);
            session.persist(player1);
            session.persist(player2);
            session.persist(player3);
            session.persist(player4);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        printTablesCombined(sessionFactory);
        sessionFactory.close();
    }
    private static void printTablesCombined(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        try {
            List<Object[]> resultList = session.createQuery(
                    "SELECT p.name, pl.name FROM Player p JOIN p.platform pl", Object[].class)
                    .getResultList();

            Map<String, List<String>> platformPlayersMap = new HashMap<>();

            for (Object[] result : resultList) {
                String playerName = (String) result[0];
                String platformName = (String) result[1];
                if (platformPlayersMap.containsKey(platformName)) {
                    platformPlayersMap.get(platformName).add(playerName);
                } else {
                    List<String> players = new ArrayList<>();
                    players.add(playerName);
                    platformPlayersMap.put(platformName, players);
                }
            }

            for (Map.Entry<String, List<String>> entry : platformPlayersMap.entrySet()) {
                System.out.println("Platform: " + entry.getKey());
                System.out.println("Players:");
                for (String playerName : entry.getValue()) {
                    System.out.println("- " + playerName);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
