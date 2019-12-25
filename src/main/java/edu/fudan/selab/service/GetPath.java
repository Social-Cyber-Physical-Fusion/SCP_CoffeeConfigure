package edu.fudan.selab.service;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import static org.neo4j.driver.v1.Values.parameters;

public class GetPath {
    public static StatementResult TwoPointgetAllPath(String userId,String endId){
        Driver driver = Neo4jDriverService.getDriver();
        Session session = Neo4jDriverService.getSession(driver);
        Transaction tx = session.beginTransaction();
        StatementResult result = tx.run("match p=(n:Person)-[*0..]->(s:Coffee_maker) where n.name ={name1} and s.name ={name2} return p",parameters("name1",userId,"name2",endId));
        return result;
    }
}
