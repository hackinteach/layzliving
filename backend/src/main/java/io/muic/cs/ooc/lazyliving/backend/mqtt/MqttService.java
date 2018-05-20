package io.muic.cs.ooc.lazyliving.backend.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

public class MqttService {

    private final MqttClient mqttClient;
    private final MqttConnectOptions connectOptions;

    private static final int mqttPort = 1883;
    private static final String mqttBroker = "tcp://mqtt.nuttapat.me:" + Integer.toString(mqttPort);
    private static final String mqttUsername = "ooc";
    private static final String mqttPassword = "muiccium";
    private static final String mqttClientId = "SpringClient"+ UUID.randomUUID();
    private static final MemoryPersistence persistence = new MemoryPersistence();

    public MqttService() throws MqttException {
        mqttClient = new MqttClient(mqttBroker,mqttClientId,persistence);
        connectOptions = new MqttConnectOptions();

        connectOptions.setCleanSession(true);
        connectOptions.setUserName(mqttUsername);
        connectOptions.setPassword(mqttPassword.toCharArray());

        mqttClient.connect(connectOptions);
    }
// "1409"   "led0 led1
    public void publish(String topic, String msg) throws MqttException {
        MqttMessage message = new MqttMessage();
        message.setPayload(msg.getBytes());

        mqttClient.publish(topic,message);
    }

    public MqttClient getMqttClient() {
        return mqttClient;
    }

}