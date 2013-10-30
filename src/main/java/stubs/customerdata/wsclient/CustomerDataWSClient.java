/*
 * �������� ���������� ��������� ���������������� ������ �� CustomerData3.
 * ��� ������� ������ �� �������� ���������� CustomerData3 ����� ����
 * ��������� �������� ��������� � ��������� ������, 
 * ������������ ���������� ������, ����� ������ � ��� ������������� �� ��������
 */

package stubs.customerdata.wsclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dev_server.billing.WorkflowRequest;

//import ru.inmetrix.imx.japi.*;
//import ru.inmetrix.imx.japi.eventtypes.*;

/**
 *
 * @author Administrator
 */
public class CustomerDataWSClient {
       private static Logger log = LoggerFactory.getLogger(CustomerDataWSClient.class);
// ������� ������ ������ �Service� - ���� ��� ����� ������
//    private static final MonitoringNotifier ntf = MonitoringNotifier.create("CustomerDataWSClient");

    public WorkflowRequest getForisConfigurationData(WorkflowRequest wr){
         // ������� ��������� ������ �Method� (��� �������� ����)
//        SimpleMethodEventev = SimpleMethodEvent.startMethod(ntf, "getForisConfigurationData");
        String msisdn = wr.getMsisdn();
        String debugString = wr.getDebugString();
        wr.setDebugString(debugString + " ForisConfigurationData; ");
// ����������� ������� �������� id � ������ ������������        
log.info(msisdn + " received OK, now responding with ForisConfigurationData...");
        // ����� ������� ��������. ��������� ������� KPI_RETURN=result
//        ev.completed(result);
       return wr;
    }
    
}
