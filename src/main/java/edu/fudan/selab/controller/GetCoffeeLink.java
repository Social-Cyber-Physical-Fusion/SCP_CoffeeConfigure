package edu.fudan.selab.controller;

import edu.fudan.selab.entity.Coffee;
import edu.fudan.selab.ontology.HttpRequestor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static edu.fudan.selab.globle.Constants.App_url;
import static edu.fudan.selab.globle.Constants.local_url;
import static edu.fudan.selab.globle.Constants.userId;
import static edu.fudan.selab.globle.Constants.url_back;

@RestController
public class GetCoffeeLink {
    @RequestMapping(value = "/getcoffeelink", method = RequestMethod.GET)
    public String getLink(HttpServletRequest request) throws Exception{
        String link = local_url + "/coffee";
        //String node_id = "";
        url_back = request.getParameter("callbackUrl");
/*
        if(!url_back.equals("")){
            String temp = url_back.split("\\?")[1];
            task_id = temp.split("=")[1];
            //node_id = temp[1].split("=")[1];
        }

*/
        String task_id = request.getParameter("processIdFromApp");
        userId = request.getParameter("userId");
        System.out.println(userId+"__________________________________________________________________");
        String RedirectApp = App_url + "/task/saveTaskNodeMessage";
        String notifyApp = App_url + "/user/sendMessageToMPOne";

        Map<String,String> map = new HashMap<String,String>();
        //map.put("userId",userId);
        map.put("taskId",task_id);
        map.put("nodeId","Human_Machine_Thing-1_PerformSelectCoffeeService");
        map.put("url",link);
        map.put("content","Please select the type of Coffee!");


        Map<String,String> map1 = new HashMap<String,String>();
        map1.put("userId",userId);
        map1.put("content","You have a task of selecting coffee to continue!");
        map1.put("url","www.baidu.com");
        new Thread() {
            public void run() {
                try {
                    String res = new HttpRequestor().doPost(RedirectApp,map);
                    String res1 = new HttpRequestor().doPost(notifyApp,map1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //String res1 = new HttpRequestor().doPost(notifyApp,map1);
            }
        }.start();


        return link;
    }
}
