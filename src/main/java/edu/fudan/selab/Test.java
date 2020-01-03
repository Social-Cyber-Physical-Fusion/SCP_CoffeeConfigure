package edu.fudan.selab;

import edu.fudan.selab.ontology.HttpRequestor;
import net.sf.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Test extends SpringBootServletInitializer {
    public static void getServiceDescription() throws Exception {
        String serviceId = "Human_Machine_Thing-1_PerformGetCoffeeMachineStatus";
        String serviceDescriptionContoller = "http://192.168.1.148:5000/find_service_by_name/"+serviceId;
        String serviceDescription = new HttpRequestor().doGet(serviceDescriptionContoller);

        JSONObject jb = JSONObject.fromObject(serviceDescription);
        String type = jb.get("type").toString();
        String serviceURL = jb.get("url").toString();
        String requestMethod = jb.get("method").toString();

        //JSONObject serviceInput = JSONObject.fromObject(jb.get("input"));
        //JSONObject serviceOutput = JSONObject.fromObject(jb.get("output"));
        String serviceInput = jb.get("input").toString();
        String serviceOutput = jb.get("output").toString();

        System.out.println(serviceInput.toString());
        System.out.println(serviceOutput.toString());
        Map<String, String> serviceInputMap = new HashMap<String, String>();
        Map<String, String> serviceOutputMap = new HashMap<String, String>();
        if(!serviceInput.equals("")){
            String[] temp = serviceInput.split("&");
            for(int i = 0;i < temp.length;i++){
                if(temp[i].contains("=")) {
                    String key = temp[i].split("=")[0];
                    String val = temp[i].split("=")[1];
                    serviceInputMap.put(key,val);
                }
                else
                    serviceInputMap.put(temp[i],"");
            }
        }
        if(!serviceOutput.equals("")){
            String[] temp = serviceOutput.split("&");
            for(int i = 0;i < temp.length;i++){
                if(temp[i].contains("=")) {
                    String key = temp[i].split("=")[0];
                    String val = temp[i].split("=")[1];
                    serviceOutputMap.put(key,val);
                }
                else
                    serviceOutputMap.put(temp[i],"");
            }
        }

        Map<String, String> serviceInputMap1 = serviceInputMap;
        Map<String, String> serviceOutputMap1 = serviceOutputMap;
        //Map<String, String> serviceInputMap = (Map<String, String>)serviceInput;
        //Map<String, String> serviceOutputMap = (Map<String, String>)serviceOutput;



    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(Test.class);
    }

    public static void main(String[] args) throws Exception {
        //getServiceDescription();
        SpringApplication.run(Test.class, args);
    }
}
