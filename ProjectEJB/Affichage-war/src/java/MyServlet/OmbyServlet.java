/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyServlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ombyEJB.OmbyEJB;
import ombyEntity.HistoAppartenance;
import ombyEntity.Localisation;
import ombyEntity.Olona;
import ombyEntity.Omby;
import varotraEJB.VarotraEJB;

/**
 *
 * @author ismae
 */
@WebServlet(name = "OmbyServlet", urlPatterns = {"/Omby"})
public class OmbyServlet extends HttpServlet {

    @EJB
    private OmbyEJB ombyEJB;

    @EJB
    private VarotraEJB varotra;

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
            out.println("<title>Servlet OmbyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OmbyServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String path = "omby/list-omby.jsp";
            Olona user = (Olona) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
            if (request.getParameter("action") == null) {
                Omby[] ombyTab = this.ombyEJB.getAllOmby(user);
                request.setAttribute("omby", ombyTab);
            } else if (request.getParameter("action").equals("vente")) {
                path = "omby/vente-omby.jsp";
                Omby[] ombyTab = this.ombyEJB.getAllOmby(user);
                Olona[] anotherUser = this.ombyEJB.getAlOtherUser();
                Localisation[] lieus = this.ombyEJB.getAll();
                request.setAttribute("another", anotherUser);
                request.setAttribute("omby", ombyTab);
                request.setAttribute("lieu", lieus);

                Gson gson = new Gson();
                out.println(gson.toJson(lieus));
            } else if (request.getParameter("action").equals("creation")) {
                path = "omby/create-omby.jsp";
                Olona[] anotherUser = this.ombyEJB.getAlOtherUser();
                Localisation[] lieus = this.ombyEJB.getAll();
                request.setAttribute("another", anotherUser);
                request.setAttribute("lieu", lieus);
            } else if (request.getParameter("action").equals("removeAssignment")) {
                try {
                    this.ombyEJB.removeAssignementOf(request.getParameter("id"));
                    response.setStatus(HttpServletResponse.SC_OK); // Code 200 (OK) pour une opération réussie
                    response.setContentType("application/json");
                    response.getWriter().write("{\"success\": true}");
                } catch (Exception ex) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Code 500 (Internal Server Error) pour une opération échouée
                    response.setContentType("application/json");
                    response.getWriter().write("{\"success\": false, \"error\": \"" + ex.getMessage() + "\"}");
                }
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Olona user = (Olona) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
            if (request.getParameter("action").equals("vente")) {
                String[] omby = request.getParameterValues("idOmby[]");
                String acheteur = request.getParameter("acheteur");
                Date dateAchat = Date.valueOf(request.getParameter("date"));
                Olona userAcheteur = this.ombyEJB.findById(acheteur);
                if (userAcheteur != null) {
                    this.ombyEJB.assignOmbyTo(omby, userAcheteur, dateAchat);
                    this.varotra.createMultipleMutation(omby, acheteur, user.getIdOlona(), dateAchat);

                    //                fafao aveo
                    HistoAppartenance[] histos = new HistoAppartenance[omby.length];
                    for (int i = 0; i < omby.length; i++) {
                        histos[i] = new HistoAppartenance(omby[i], userAcheteur, dateAchat);
                    }
//                atreto

                    Gson gson = new Gson();
                    out.println(gson.toJsonTree(histos));
                }
            }
        } catch (Exception e) {

        }
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
