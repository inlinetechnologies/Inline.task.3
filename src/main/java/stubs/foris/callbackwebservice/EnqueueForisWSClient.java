/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stubs.foris.callbackwebservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dev_server.billing.WorkflowRequest;

//import ru.inmetrix.imx.japi.*;
//import ru.inmetrix.imx.japi.eventtypes.*;
/**
 *
 * @author Administrator
 */
public class EnqueueForisWSClient {

    private static Logger log = LoggerFactory.getLogger(EnqueueForisWSClient.class);
// ������� ������ ������ �Service� - ���� ��� ����� ������
//    private static final MonitoringNotifier ntf = MonitoringNotifier.create("CustomerDataWSClient");

    public WorkflowRequest enqueueV2RequestToForis(WorkflowRequest wr) {
        // ������� ��������� ������ �Method� (��� �������� ����)
//        SimpleMethodEventev = SimpleMethodEvent.startMethod(ntf, "enqueueV2RequestToForis");
        String msisdn = wr.getMsisdn();
        String debugString = wr.getDebugString();
        wr.setDebugString(debugString + " Enqueued to Foris; ");
// ������ ������ �� ������� � Foris        
        log.info(msisdn + " now enqueued request to Foris...");
        // ����� ������� ��������. ��������� ������� KPI_RETURN=result
//        ev.completed(result);
        return wr;
    }
}
