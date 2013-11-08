/*
 * Servlet to list requests in the workflow storage
 */

package ru.dev_server.billing;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.dev_server.billing.model.TariffChangeRequest;
import ru.dev_server.billing.workflow.V2WorkflowForis;

/**
 * @author Administrator
 */
@WebServlet(name = "ListRequests", urlPatterns = {"/ListRequests"})
public class V2RequestListServlet extends HttpServlet {
//    @EJB
//    private TariffChangeRequest requestEntityFacade;

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
        request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet V2RequestListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet V2RequestListServlet at " + request.getContextPath() + "</h1>");

            Collection<WorkflowRequest> requests = V2WorkflowForis.getStorage().findAll();
            for (Iterator it = requests.iterator(); it.hasNext(); ) {
                WorkflowRequest elem = (WorkflowRequest) it.next();
                out.println(" <b>" + elem.getMsisdn() + " </b><br />");
                out.println(elem.getStorageId() + "<br /> ");
                out.println(elem.getMsisdn() + "<br /> ");
                out.println(elem.getStatus() + "<br /> ");
            }
            out.println("<a href='PostRequest'>Add new request</a>");

            out.println("<br><br>");

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
        return "TariffChangeRequest List";
    }// </editor-fold>

}
