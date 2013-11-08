package ru.dev_server.billing.workflow;

import ru.dev_server.billing.WorkflowRequest;
import ru.dev_server.billing.model.TariffChangeRequest;
import ru.dev_server.billing.model.WorkflowRequestStatus;
import stubs.V2.callbackservice.V2ForisResultWSClient;
import stubs.V2.callbackservice.V2WorkflowStatusReportWSClient;
import stubs.customerdata.wsclient.CustomerDataWSClient;
import stubs.foris.callbackwebservice.EnqueueForisWSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;

/*
 * Это класс конвейера обработки заявок от системы V2
 * Обогащает заявку данными, необходимыми для её выполнения
 * путём вызова клиентов нужных вебсервисов, сохраняет их в БД
 * вместе с меняющимся по ходу обраьотки статусом запроса
 */

/**
 * @author Administrator
 */
//@Stateless
public class V2WorkflowForis {

    private static Logger log = LoggerFactory.getLogger(V2WorkflowForis.class);

    // отправка статуса конвейера в V2
    private V2WorkflowStatusReportWSClient w2WorkflowStatusReportWSClient;

    private CustomerDataWSClient customerDataWSClient;
    // отправка результата в V2
    private V2ForisResultWSClient v2ForisResultWSClient;
    // постановка на очередь в Foris
    private EnqueueForisWSClient enqueueForisWSClient;


//    @PersistenceContext(unitName = "billing")
//    private EntityManager em;
    private static final RAMStorage em = new RAMStorage();

    public V2WorkflowForis() {
        this.w2WorkflowStatusReportWSClient = new V2WorkflowStatusReportWSClient();
        this.customerDataWSClient = new CustomerDataWSClient();
        this.v2ForisResultWSClient = new V2ForisResultWSClient();
        this.enqueueForisWSClient = new EnqueueForisWSClient();
    }


    public boolean startWorkflow(WorkflowRequest wr) {
        boolean result;
        log.info("Workflow started with request status : " + wr.getStatus());
        // далее идут вызовы фаз обработки
        WorkflowRequestStatus status = wr.getStatus();
        if (status.equals(WorkflowRequestStatus.CREATED)) {
            WorkflowRequest configuredRequest = startConfigurationPhase(wr);
            WorkflowRequest enqueuedRequest = startForisEnqueueingPhase(configuredRequest);
        }
        if (status.equals(WorkflowRequestStatus.CONFIGURED)) {
// запустить обработку в Foris -
            WorkflowRequest enqueuedRequest = startForisEnqueueingPhase(wr);
            // в случае успеха
            result = true;
            return result;
        }
        if (status.equals(WorkflowRequestStatus.ENQUEUED)) {
//  если вызов пришёл сюда - значит из вебсервиса обратного вызова
            wr = startSavingForisCallbackResultPhase(wr);
            status = wr.getStatus();
        }
        if (status.equals(WorkflowRequestStatus.PROCESSED)) {
// запустить сообщение результатов в V2
            WorkflowRequest configuredRequest = startReportingForisResultToV2Phase(wr);
            // присвоить статус завершено и сохранитьего в БД
        }
        if (status.equals(WorkflowRequestStatus.REPORTED)) {
//
            throw new UnsupportedOperationException("Reported requests shouldn't come here!!!");
//            WorkflowRequest configuredRequest = startFinalizationPhase(wr);
        }

        // в случае успеха
        result = true;
        return result;
    }



    private WorkflowRequest startConfigurationPhase(WorkflowRequest wr) {
        WorkflowRequest configuredRequest = customerDataWSClient.getForisConfigurationData(wr);
        configuredRequest.setStatus(WorkflowRequestStatus.CONFIGURED);
        doPersist(configuredRequest);
        log.info(" request configured.");
        return configuredRequest;
    }

    private WorkflowRequest startForisEnqueueingPhase(WorkflowRequest wr) {
        WorkflowRequest enqeuedRequest = enqueueForisWSClient.enqueueV2RequestToForis(wr);
        enqeuedRequest.setStatus(WorkflowRequestStatus.ENQUEUED);
        doPersist(enqeuedRequest);
        log.info(" request enqueued.");
        return enqeuedRequest;
    }

    private WorkflowRequest startSavingForisCallbackResultPhase(WorkflowRequest wr) {
        wr.setStatus(WorkflowRequestStatus.PROCESSED);
        doPersist(wr);
        log.info("Callback result saved.");
        return wr;  //To change body of created methods use File | Settings | File Templates.
    }
    public WorkflowRequest startReportingForisResultToV2Phase(WorkflowRequest wr) {
        log.info("Start request reporting to V2.");
        WorkflowRequest reportededRequest = v2ForisResultWSClient.reportForisResultToV2(wr);
        reportededRequest.setStatus(WorkflowRequestStatus.REPORTED);
        log.info(" request reported.");
        doPersist(reportededRequest);
        log.info(" request state saved in database.");
        return reportededRequest;
    }

//    private WorkflowRequest startFinalizationPhase(WorkflowRequest wr) {
//        wr.setStatus(WorkflowRequestStatus.COMPLETED);
//        doPersist(wr);
//        log.info(" request completed.");
//        return configuredRequest;
//    }

    public void doPersist(WorkflowRequest workflowRequest) {
        log.info("On merging to storage request status is : " +
                workflowRequest.getStorageId() + " : " +
                workflowRequest.getMsisdn() + " : " +
                workflowRequest.getStatus());
        em.merge(workflowRequest);
    }

    // public WorkflowRequest findByAboId(String  msisdn){
//
//        String s ="select wr from WorkflowRequest wr where wr.tariffChangeRequest.msisdn=:msisdn AND wr.created IN  ( " +
//                "select max(wr2.created) from WorkflowRequest wr2 WHERE wr2.tariffChangeRequest.msisdn=:msisdn" +
//                ")";
//        TypedQuery<WorkflowRequest > query = em.createQuery(s,WorkflowRequest .class);
//        query.setParameter("msisdn", msisdn);
//        query.setMaxResults(1);
//        WorkflowRequest singleResult = query.getSingleResult();
//        return singleResult;
//
//
//    }
    public static void main(String[] args) {
//        System.setProperty(org.slf4j.helpers. BasicMarker. DEFAULT_LOG_LEVEL_KEY, "TRACE");
        System.setProperty("org.slf4j.simpleLogger.logLevel", "TRACE");

//        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");


//        final org.slf4j.Logger log = LoggerFactory.getLogger(App.class);

        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warning");
        log.error("error");


        WorkflowRequestStatus s = WorkflowRequestStatus.CONFIGURED;
        s = WorkflowRequestStatus.CREATED;
        log.info(String.valueOf(s));
        System.out.println("System.out log.");
        log.trace("log.trace");
        log.debug("log.debug");
        log.info("log.info");
        log.warn("log.warn");
        log.error("log.error.");
        V2WorkflowForis workflowForis = new V2WorkflowForis();

        TariffChangeRequest tariffChangeRequest = new TariffChangeRequest("1", "1234", "tariff plan parameters", WorkflowRequestStatus.CREATED);
        WorkflowRequest request = new WorkflowRequest(tariffChangeRequest);
        request.setTariffChangeRequest(tariffChangeRequest);
        request.setStatus(WorkflowRequestStatus.CREATED);
        workflowForis.startWorkflow(request);

        TariffChangeRequest secondTariffChangeRequest = new TariffChangeRequest("1", "1256", "tariff plan parameters 2 ", WorkflowRequestStatus.CREATED);
        WorkflowRequest secondWFRequest = new WorkflowRequest(secondTariffChangeRequest);
        secondWFRequest.setTariffChangeRequest(secondTariffChangeRequest);
        secondWFRequest.setStatus(WorkflowRequestStatus.CREATED);
        workflowForis.startWorkflow(secondWFRequest);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public static RAMStorage getStorage() {
        return V2WorkflowForis.em;  //To change body of created methods use File | Settings | File Templates.
    }
}
