/*
 * Класс объекта заявки, обрабатыаемой конвейером
 * содержит данные заявки и обогащается данными конфигурации
 * и прочими, необходимыми для обработки внешнего запроса из V2
 */

package ru.dev_server.billing;
import ru.dev_server.billing.model.TariffChangeRequest;
import ru.dev_server.billing.model.WorkflowRequestStatus;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 *
 * @author Administrator
 */
@Entity
public class WorkflowRequest {
    @Id
    @GeneratedValue(generator="system-uuid")
    private String  internalId;
    private String  externalId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created=new Date();

    @OneToOne (cascade = CascadeType.ALL)
    private TariffChangeRequest tariffChangeRequest;

    private String errorCode;
    private String errorDescription;
    private String configurationData;
    private String forisResult;
    private String debugString;


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getDebugString() {
        return debugString;
    }

    public void setDebugString(String debugString) {
        this.debugString = debugString;
    }

    public TariffChangeRequest getTariffChangeRequest() {
        return tariffChangeRequest;
    }

    public void setTariffChangeRequest(TariffChangeRequest tariffChangeRequest) {
        this.tariffChangeRequest = tariffChangeRequest;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getConfigurationData() {
        return configurationData;
    }

    public void setConfigurationData(String configurationData) {
        this.configurationData = configurationData;
    }

    public String getForisResult() {
        return forisResult;
    }

    public void setForisResult(String forisResult) {
        this.forisResult = forisResult;
    }


    public WorkflowRequestStatus getStatus() {
        return tariffChangeRequest.getStatus();
    }

    public void setStatus(WorkflowRequestStatus status) {
        tariffChangeRequest.setStatus(status);
    }
     public String getMsisdn() {
        return tariffChangeRequest.getMsisdn();
    }

    public void setStatus(String status) {
        tariffChangeRequest.setMsisdn(status);
    }
    
    

}
