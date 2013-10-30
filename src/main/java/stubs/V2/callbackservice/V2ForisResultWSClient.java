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
public class V2ForisResultWSClient {

    private static Logger log = LoggerFactory.getLogger(V2ForisResultWSClient.class);
// ������� ������ ������ �Service� - ���� ��� ����� ������
//    private static final MonitoringNotifier ntf = MonitoringNotifier.create("V2ForisResultWSClient");

    public WorkflowRequest reportForisResultToV2(WorkflowRequest wr) {
        // ������� ��������� ������ �Method� (��� �������� ����)
//        SimpleMethodEventev = SimpleMethodEvent.startMethod(ntf, "reportForisResultToV2");
        String msisdn = wr.getMsisdn();
        String debugString = wr.getDebugString();
        wr.setDebugString(debugString + " Foris Result sent; ");
// ���������� ����� �� ������ �� Foris  � V2       
        log.info(msisdn + ", now reporting Foris Result to V2...");
        // ����� ������� ��������. ��������� ������� KPI_RETURN=result
//        ev.completed(result);
        return wr;

    }
}
