package ru.dev_server.billing;

/*
*/

import ru.dev_server.billing.model.TariffChangeRequest;
import ru.dev_server.billing.model.WorkflowRequestStatus;
import ru.dev_server.billing.workflow.RAMStorage;
import ru.dev_server.billing.workflow.V2WorkflowForis;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@WebServlet(name = "PostRequest", urlPatterns = {"/PostRequest"})
public class V2RequestSendServlet extends HttpServlet {

    @Resource(mappedName = "jms/WFQueueConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/V2WorkflowRequestQueue")
    private Queue queue;
    private static RAMStorage em;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Add the following code to send the JMS message
        String id = request.getParameter("id");
        String msisdn = request.getParameter("msisdn");
        String changeTariffPlanParameters = request.getParameter("changeTariffPlanParameters");
        String status = request.getParameter("status");
        if ((id != null) && (msisdn != null) && (changeTariffPlanParameters != null)) {
            try {
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(queue);

                ObjectMessage message = session.createObjectMessage();
                // here we create RequestEntity, that will be sent in JMS message

                TariffChangeRequest req = new TariffChangeRequest(id, msisdn, changeTariffPlanParameters, WorkflowRequestStatus.valueOf(status));
//                id, String msisdn, String changeTariffPlanParameters, WorkflowRequestStatus status

                message.setObject(req);
                messageProducer.send(message);
                messageProducer.close();
                connection.close();
                response.sendRedirect("ListRequests");

            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet V2RequestSendServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet V2RequestSendServlet at " + request.getContextPath() + "</h1>");

            // The following code adds the form to the web page
            out.println("<form>");
            out.println("Id: <input type='text' name='id'><br/>");
            out.println("MSISDN: <input type='text' name='msisdn'><br/>");
            out.println("Parameters: <textarea name='changeTariffPlanParameters'></textarea><br/>");

            out.println("<input type=submit name=\"\" value=\"Add\">");
            out.println("checked channels to Tab ");
            out.println("<select name=\"status\">");

            for (WorkflowRequestStatus wrs : WorkflowRequestStatus.values())
                out.println("<option>" + wrs.name() + "</option>");
            out.println("<option selected>" + WorkflowRequestStatus.CREATED + "</option>");

            out.println("</select>");

            out.println("<input type=submit name=\"\" value=\"Send request\">");
            out.println("</form>");


            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
