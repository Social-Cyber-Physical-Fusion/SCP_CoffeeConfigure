package edu.fudan.selab.globle;

public class Constants {
    //public static final String broker_url = "http://192.168.1.102:8082";
    public static final String local_url = "http://139.196.228.210:32766"; // 下单服务地址（即本地地址）
    //public static final String App_url = "http://192.168.1.168:8080";
    public static final String SC_url = "http://139.224.128.113:8888"; // 微信发消息接口地址
    public static final String process_proxy_url = "http://106.15.102.123:5001"; // k8s 流程代理服务地址
    public static final String Proxy_url = "http://ordercoffee-service-proxy.default:8888/receive_result";// 代理地址

    public static String url_back = "";
    public static String userId = "";
    public static String workflow_instance_id = "";

    public static String instance_id = "";
}
