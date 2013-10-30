/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.mts.tender.inline.asynch;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Administrator
 */
@MessageDriven(mappedName = "jms/V2MessagesQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class V2MessageQueueManager implements MessageListener {
    
    public V2MessageQueueManager() {
    }
    
    @Override
    public void onMessage(Message message) {
    }
    
}
