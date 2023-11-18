/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myUtil.JsonApiClient;
import ombyEJB.OmbyEJB;
import ombyEntity.Localisation;
import ombyEntity.Olona;
import ombyEntity.Omby;
import varotraEJB.VarotraEJB;
import varotraEntity.Mutation;

/**
 *
 * @author RazOnTheFloor
 */
@WebServlet(name = "MutationServlet", urlPatterns = {"/Mutation"})
public class MutationServlet extends HttpServlet {

    @EJB
    private VarotraEJB varotraEjb;
    @EJB
    private OmbyEJB ombyEJB;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MutationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MutationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = "mutation/list-mutation.jsp";
        Olona user = (Olona) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        if (request.getParameter("action") == null) {
            request.setAttribute("mutation", this.varotraEjb.getAll());
        } else if (request.getParameter("action").equals("getAllMade")) {
            request.setAttribute("mutation", this.varotraEjb.getAllSellsMadeBy(user.getIdOlona()));
        } else if (request.getParameter("action").equals("getAllSell")) {
            request.setAttribute("mutation", this.varotraEjb.getAllSellsOf(user.getIdOlona()));
        } else if (request.getParameter("action").equals("update") && request.getParameter("id")!= null) {
            path = "mutation/update-mutation.jsp";
            Omby[] ombyTab = this.ombyEJB.getAllOmby(user);
            Olona[] anotherUser = this.ombyEJB.getAlOtherUser();
            Localisation[] lieus = this.ombyEJB.getAll();
            request.setAttribute("another", anotherUser);
            request.setAttribute("omby", ombyTab);
            request.setAttribute("lieu", lieus);
            Mutation mutation = null;
            try {
                mutation = this.varotraEjb.detailsOfMutation(request.getParameter("id"));
            } catch (Exception ex) {
                Logger.getLogger(MutationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("mutation", mutation);
        }else if (request.getParameter("action").equals("delete") && request.getParameter("id")!= null) {
            try {
                this.varotraEjb.deleteMutation(request.getParameter("id"));
            } catch (Exception ex) {
                Logger.getLogger(MutationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Olona user = (Olona) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        String path = "mutation/list-mutation.jsp";
        if (request.getParameter("action").equals("update") && request.getParameter("id")!= null) {
            String id = request.getParameter("action");
            String idOmby= request.getParameter("ombyb");
            String idAcheteur = request.getParameter("acheteur");
            String idVendeur = request.getParameter("vendeur");
            String lieu = request.getParameter("lieuVente");
            Date date = Date.valueOf(request.getParameter("date"));
            Mutation mutation = new Mutation(idOmby,idAcheteur,idVendeur,date,lieu);
            try {
                this.varotraEjb.updateMutation(mutation);
            } catch (Exception ex) {
                Logger.getLogger(MutationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
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
