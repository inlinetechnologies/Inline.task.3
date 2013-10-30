package ru.dev_server.billing.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dev_server.billing.WorkflowRequest;
import ru.dev_server.billing.model.WorkflowRequestStatus;
import stubs.V2.callbackservice.V2ForisResultWSClient;
import stubs.V2.callbackservice.V2WorkflowStatusReportWSClient;
import stubs.customerdata.wsclient.CustomerDataWSClient;
import stubs.foris.callbackwebservice.EnqueueForisWSClient;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/*
 * Это класс конвейера обработки заявок от системы V2
 * Обогащает заявку данными, необходимыми для её выполнения
 * путём вызова клиентов нужных вебсервисов, сохраняет их в БД
 * вместе с меняющимся по ходу обраьотки статусом запроса
 */
/**
 *
 * @author Administrator
 */
@Stateless
public class V2WorkflowForis {

    private static Logger log = LoggerFactory.getLogger(V2WorkflowForis.class);

    // отправка статуса конвейера в V2
    private V2WorkflowStatusReportWSClient w2WorkflowStatusReportWSClient;
    
    private CustomerDataWSClient customerDataWSClient;
    // отправка результата в V2
    private V2ForisResultWSClient v2ForisResultWSClient;
    // постановка на очередь в Foris
    private EnqueueForisWSClient enqueueForisWSClient;


    @PersistenceContext(unitName = "billing")
    private EntityManager em;

    public V2WorkflowForis() {
        this.w2WorkflowStatusReportWSClient = new V2WorkflowStatusReportWSClient();
        this.customerDataWSClient = new CustomerDataWSClient();
        this.v2ForisResultWSClient = new V2ForisResultWSClient();
        this.enqueueForisWSClient = new EnqueueForisWSClient();
    }


    public boolean startWorkflow(WorkflowRequest wr) {
        boolean result;
        // далее идут вызовы фаз обработки
        WorkflowRequestStatus status = wr.getTariffChangeRequest().getStatus();
        if (status.equals(WorkflowRequestStatus.CREATED)) {
            WorkflowRequest configuredRequest = startConfigurationPhase(wr);
            WorkflowRequest enqueuedRequest = startForisEnqueueingPhase(configuredRequest);
        }
        if (status.equals(WorkflowRequestStatus.CONFIGURED)) {
            WorkflowRequest enqueuedRequest = startForisEnqueueingPhase(wr);
        }
         if (status.equals(WorkflowRequestStatus.ENQUEUED)) {
// запустить обработку в Foris - 
         }
         if (status.equals(WorkflowRequestStatus.PROCESSED)) {
            WorkflowRequest configuredRequest = startReportingForisResultToV2Phase(wr);
       }
         if (status.equals(WorkflowRequestStatus.REPORTED)) {
// 
//                     throw new UnsupportedOperationException("We shouldn't get here? all done."); 
         }
          
         // в случае успеха
        result = true;
        return result;
    }

    private WorkflowRequest startConfigurationPhase(WorkflowRequest wr) {
        WorkflowRequest configuredRequest = getConfigurationData(wr);
        configuredRequest.setStatus(WorkflowRequestStatus.CONFIGURED);
        doPersist(configuredRequest);
        return configuredRequest;
    }

    private WorkflowRequest startForisEnqueueingPhase(WorkflowRequest wr) {
        WorkflowRequest enqeuedRequest = enqueueRequestToForis(wr);
        enqeuedRequest.setStatus(WorkflowRequestStatus.ENQUEUED);
        doPersist(enqeuedRequest);
        return enqeuedRequest;
    }
 
        public WorkflowRequest startReportingForisResultToV2Phase(WorkflowRequest wr) {
        WorkflowRequest reportededRequest = reportResultToForis(wr);
        reportededRequest.setStatus(WorkflowRequestStatus.REPORTED);
            doPersist(reportededRequest);
        return reportededRequest;
    }

    private WorkflowRequest getConfigurationData(WorkflowRequest wr) {
        WorkflowRequest cr = customerDataWSClient.getForisConfigurationData(wr);
        return cr;
    }

    private WorkflowRequest enqueueRequestToForis(WorkflowRequest wr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private WorkflowRequest reportResultToForis(WorkflowRequest wr) {
        v2ForisResultWSClient.reportForisResultToV2(wr);
        return wr;
    }

    public void doPersist(WorkflowRequest workflowRequest){
        em.merge(workflowRequest);
    }
    public WorkflowRequest findByAboId(String  msisdn){
        String s ="select from WorkflowRequest  where tariffChangeRequest.msisdn=:msisdn AND created IN  ( select max(created) from WorkflowRequest WHERE tariffChangeRequest.msisdn=:msisdn)";
        TypedQuery<WorkflowRequest > query = em.createQuery(s,WorkflowRequest .class);
        query.setParameter(1, msisdn);
        query.setMaxResults(1);
        return query.getSingleResult();


    }

}
