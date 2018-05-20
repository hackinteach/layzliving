//package io.muic.cs.ooc.lazyliving.backend.mqtt;
//
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class MQTTServiceTest {
//
//
//    MqttService mqttService;
//
//    @Before
//    public void seed(){
//        try {
//            mqttService = new MqttService();
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void publish() {
//        try {
//            mqttService.publish("test","ass");
//        } catch (MqttException me) {
//            System.out.println("reason "+me.getReasonCode());
//            System.out.println("msg "+me.getMessage());
//            System.out.println("loc "+me.getLocalizedMessage());
//            System.out.println("cause "+me.getCause());
//            System.out.println("excep "+me);
//            me.printStackTrace();
//        }
//    }
//}