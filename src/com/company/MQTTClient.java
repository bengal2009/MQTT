package com.company;

import org.fusesource.mqtt.client.*;

import java.net.URISyntaxException;
/**
 * MQTT moquette 的Client 段用于??主?，并接收主?信息
 * 采用阻塞式 ??主? 
 */
public class MQTTClient {
    private final static String CONNECTION_STRING = "tcp://192.168.0.1:1883";
    private final static boolean CLEAN_START = true;
    private final static short KEEP_ALIVE = 30;// 低耗网?，但是又需要及??取?据，心跳30s
    public  static Topic[] topics = {
            new Topic("hello/world", QoS.EXACTLY_ONCE)};

    public final  static long RECONNECTION_ATTEMPT_MAX=6;
    public final  static long RECONNECTION_DELAY=2000;

    public final static int SEND_BUFFER_SIZE=2*1024*1024;//?送最大???2M


    public static void main(String[] args)   {
        //?建MQTT?象
        MQTT mqtt = new MQTT();
        BlockingConnection connection=null;
        try {
            //?置mqtt broker的ip和端口
            mqtt.setHost(CONNECTION_STRING);
            //?接前清空??信息
            mqtt.setCleanSession(CLEAN_START);
            //?置重新?接的次?
            mqtt.setReconnectAttemptsMax(RECONNECTION_ATTEMPT_MAX);
            //?置重?的?隔??
            mqtt.setReconnectDelay(RECONNECTION_DELAY);
            //?置心跳??
            mqtt.setKeepAlive(KEEP_ALIVE);
            //?置??的大小
            mqtt.setSendBufferSize(SEND_BUFFER_SIZE);

            //?取mqtt的?接?象BlockingConnection
            connection = mqtt.blockingConnection();
            //MQTT?接的?建
            connection.connect();

            //?建相?的MQTT 的主?列表
            Topic[] topics = {new Topic("hello/world", QoS.AT_LEAST_ONCE)};
//            Topic topic=new Topic(TEST_TOPIC,QoS.AT_MOST_ONCE);
            String payload1="Message from Benny";
            connection.publish(topics[0].name().toString(),payload1.getBytes(),QoS.AT_LEAST_ONCE,false);
            //??相?的主?信息
            byte[] qoses = connection.subscribe(topics);
            //
            while(true){
                //接收??的消息?容
                Message message = connection.receive();
                //?取??的消息?容
                byte[] payload = message.getPayload();
                // process the message then:
                System.out.println("MQTTClient Message  Topic="+message.getTopic()+" Content :"+new String(payload));
                //?收消息的回?
                message.ack();
                Thread.sleep(2000);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}