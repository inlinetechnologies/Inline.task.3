package ru.dev_server.billing;

import ru.dev_server.billing.model.TariffChangeRequest;
import ru.dev_server.billing.model.WorkflowRequestStatus;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/** Servlet for testing.*/
@WebServlet("/")
public class V2RequestEmulatorServlet extends HttpServlet {
    @Resource(name="jms/WFQueueConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(name="jms/V2WorkflowRequestQueue")
    private Destination destination;

    private Connection connection;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);

            TariffChangeRequest tariffChangeRequest = new TariffChangeRequest();
            tariffChangeRequest.setStatus(WorkflowRequestStatus.CREATED);
            tariffChangeRequest.setMsisdn("1");

            ObjectMessage message = session.createObjectMessage();

            message.setObject((Serializable) tariffChangeRequest);
            producer.send(message);
            session.close();
            connection.close();
             resp.getWriter().write("OK");

        } catch (Throwable ex) {
            ex.printStackTrace();
            resp.getWriter().write("ERROR");
        }
    }
}
