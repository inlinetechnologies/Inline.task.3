/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stubs.V2.callbackservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dev_server.billing.WorkflowRequest;

//import ru.inmetrix.imx.japi.*;
//import ru.inmetrix.imx.japi.eventtypes.*;

/**
 *
 * @author Administrator
 */
public class V2WorkflowStatusReportWSClient {
       private static Logger log = LoggerFactory.getLogger(V2WorkflowStatusReportWSClient.class);
// ������� ������ ������ �Service� - ���� ��� ����� ������
//    private static final MonitoringNotifier ntf = MonitoringNotifier.create("V2WorkflowStatusReportWSClient");

    public WorkflowRequest reportWorkflowStatus(WorkflowRequest wr){
         // ������� ��������� ������ �Method� (��� �������� ����)
//        SimpleMethodEventev = SimpleMethodEvent.startMethod(ntf, "reportWorkflowStatus");
        String msisdn = wr.getMsisdn();
        String debugString = wr.getDebugString();
        wr.setDebugString(debugString + " reportWorkflowStatus reported; " + wr.getStatus());
// ���������� ���������� � ��c������ ������� � V2       
log.info(msisdn + " " + wr.getStatus() + " received OK, now reported Foris response fulfilled...");
        // ����� ������� ��������. ��������� ������� KPI_RETURN=result
//        ev.completed(result);
       return wr;
    }
}