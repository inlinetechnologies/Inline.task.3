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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * Servlet for testing.
 */
@Stateful
@WebServlet("/")
public class V2RequestEmulatorServlet extends HttpServlet {

    @Resource(mappedName = "jms/WFQueueConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/V2WorkflowRequestQueue")
    private Destination destination;

    private Connection connection;

    @Remove
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LOG.log(Level.SEVERE, "Servlet started.");
            System.err.println("Servlet started.");
            connection = connectionFactory.createConnection();
            connection.start();
            System.err.println("connection started.");

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);

            TariffChangeRequest tariffChangeRequest;
            tariffChangeRequest = new TariffChangeRequest("1", "1234", "Tariff Change Request Parameters", WorkflowRequestStatus.CREATED);

            ObjectMessage message = session.createObjectMessage();

            message.setObject((Serializable) tariffChangeRequest);
            producer.send(message);
            LOG.log(Level.SEVERE, "producer.send(message); called");
            session.close();
            LOG.log(Level.SEVERE, "JMS session closed");
            connection.close();
             LOG.log(Level.SEVERE, "JMS connection closed");
           resp.getWriter().write("OK");
            resp.flushBuffer();

        } catch (Throwable ex) {
            ex.printStackTrace();
            resp.getWriter().write("ERROR");
        }
    }
    private static final Logger LOG = Logger.getLogger(V2RequestEmulatorServlet.class.getName());
}
