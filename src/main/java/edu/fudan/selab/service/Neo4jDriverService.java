package edu.fudan.selab.service;

import org.neo4j.driver.v1.*;

import java.sql.SQLOutput;

public class Neo4jDriverService {
    //private final Driver driver;
    private final static String uri = "bolt://10.141.221.88:7687";
    private final static String username = "neo4j";
    private final static String password = "18212010012";

    public static Driver getDriver() {
        Driver driver = null;
        try {
            driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
            System.out.println("Connect Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver;
    }

    public static void closeDriver(Driver driver) {
        try {
            if (driver != null)
                driver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Session getSession(Driver driver) {
        Session session = null;
        try {
            if (driver != null)
                session = driver.session();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }

    public static void closeSession(Session session) {
        try {
            if (session != null)
                session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Driver driver = Neo4jDriverService.getDriver();
        Session session = Neo4jDriverService.getSession(driver);
        Transaction tx = session.beginTransaction();

//		List<Record> r = result.list();

        //System.out.println(result.list().size());

    }

}
