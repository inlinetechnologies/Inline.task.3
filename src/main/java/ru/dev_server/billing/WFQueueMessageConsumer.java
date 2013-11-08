package ru.dev_server.billing;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dev_server.billing.model.TariffChangeRequest;
import ru.dev_server.billing.workflow.V2WorkflowForis;
import ru.dev_server.billing.workflow.RAMStorage;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**.*/
@MessageDriven(mappedName = "jms/V2WorkflowRequestQueue")
public class WFQueueMessageConsumer implements MessageListener {

    private static Logger log = LoggerFactory.getLogger(WFQueueMessageConsumer.class);
//    @PersistenceContext (unitName = "billing")
//private EntityManager em;
    private RAMStorage em;

    @EJB
    V2WorkflowForis v2WorkflowForis;

    @Override
    public void onMessage(Message message) {

        log.info("message received.");
        try {

            ObjectMessage oMsg = (ObjectMessage) message;

            TariffChangeRequest changeRq =(TariffChangeRequest )oMsg.getObject();
            WorkflowRequest workflowRequest = new WorkflowRequest(changeRq);
            workflowRequest.setTariffChangeRequest(changeRq);

            em.persist(workflowRequest);
            log.info("message saved.");
            v2WorkflowForis.startWorkflow(workflowRequest);

        } catch (Exception e) {
            log.error("message NOT saved.");
        }



    }
}

