package com.company;

import org.fusesource.mqtt.client.*;

import java.net.URISyntaxException;
/**
 * MQTT moquette ��Client �q�Τ_??�D?�A�}�����D?�H��
 * ���Ϊ��릡 ??�D? 
 */
public class MQTTClient {
    private final static String CONNECTION_STRING = "tcp://192.168.0.1:1883";
    private final static boolean CLEAN_START = true;
    private final static short KEEP_ALIVE = 30;// �C���I?�A���O�S�ݭn��??��?�u�A�߸�30s
    public  static Topic[] topics = {
            new Topic("hello/world", QoS.EXACTLY_ONCE)};

    public final  static long RECONNECTION_ATTEMPT_MAX=6;
    public final  static long RECONNECTION_DELAY=2000;

    public final static int SEND_BUFFER_SIZE=2*1024*1024;//?�e�̤j???2M


    public static void main(String[] args)   {
        //?��MQTT?�H
        MQTT mqtt = new MQTT();
        BlockingConnection connection=null;
        try {
            //?�mmqtt broker��ip�M�ݤf
            mqtt.setHost(CONNECTION_STRING);
            //?���e�M��??�H��
            mqtt.setCleanSession(CLEAN_START);
            //?�m���s?������?
            mqtt.setReconnectAttemptsMax(RECONNECTION_ATTEMPT_MAX);
            //?�m��?��?�j??
            mqtt.setReconnectDelay(RECONNECTION_DELAY);
            //?�m�߸�??
            mqtt.setKeepAlive(KEEP_ALIVE);
            //?�m??���j�p
            mqtt.setSendBufferSize(SEND_BUFFER_SIZE);

            //?��mqtt��?��?�HBlockingConnection
            connection = mqtt.blockingConnection();
            //MQTT?����?��
            connection.connect();

            //?�ج�?��MQTT ���D?�C��
            Topic[] topics = {new Topic("hello/world", QoS.AT_LEAST_ONCE)};
//            Topic topic=new Topic(TEST_TOPIC,QoS.AT_MOST_ONCE);
            String payload1="Message from Benny";
            connection.publish(topics[0].name().toString(),payload1.getBytes(),QoS.AT_LEAST_ONCE,false);
            //??��?���D?�H��
            byte[] qoses = connection.subscribe(topics);
            //
            while(true){
                //����??������?�e
                Message message = connection.receive();
                //?��??������?�e
                byte[] payload = message.getPayload();
                // process the message then:
                System.out.println("MQTTClient Message  Topic="+message.getTopic()+" Content :"+new String(payload));
                //?���������^?
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