/*
 * Класс объекта заявки, обрабатыаемой конвейером
 * содержит данные заявки и обогащается данными конфигурации
 * и прочими, необходимыми для обработки внешнего запроса из V2
 */

package ru.dev_server.billing;
import ru.dev_server.billing.model.TariffChangeRequest;
import ru.dev_server.billing.model.WorkflowRequestStatus;
import ru.dev_server.billing.workflow.V2WorkflowForis;
import ru.dev_server.billing.workflow.RAMStorage;

//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
import java.util.Date;

/**
 *
 * @author Administrator
 */
//@Entity
public class WorkflowRequest {
//    @Id

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    //    @GeneratedValue(generator="system-uuid")
    private String  internalId;
    private String  externalId;

//    @Temporal(TemporalType.TIMESTAMP)
    private Date created=new Date();

    public WorkflowRequest(TariffChangeRequest tariffChangeRequest) {
        this.tariffChangeRequest = tariffChangeRequest;
        this.status = WorkflowRequestStatus.CREATED;
        RAMStorage em = V2WorkflowForis.getStorage();
        em.persist(this);
    }

    //    @OneToOne (cascade = CascadeType.ALL)
    private TariffChangeRequest tariffChangeRequest;

    private String errorCode;
    private String errorDescription;
    private String configurationData;
    private String forisResult;
    private String debugString;
    private WorkflowRequestStatus status;

    private String storageId;


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
        return this.status;
    }

    public void setStatus(WorkflowRequestStatus status) {
        this.status = status;
    }
     public String getMsisdn() {
        return tariffChangeRequest.getMsisdn();
    }

    public void setMsisdn(String s) {
        tariffChangeRequest.setMsisdn(s);
    }



}
