/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import warEntity.Plainte;

/**
 *
 * @author RazOnTheFloor
 */
@WebServlet(name = "PlainteServlet", urlPatterns = {"/Plainte"})
public class PlainteServlet extends HttpServlet {

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
            out.println("<title>Servlet PlainteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PlainteServlet at " + request.getContextPath() + "</h1>");
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
        String path = "plainte/list-plainte.jsp";
        Olona user = (Olona) request.getSession().getAttribute("user");
        JsonApiClient jsonApiClient = new JsonApiClient();
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        if (request.getParameter("action") == null) {
            String url = "http://localhost:58974/plaintes/List";
            request.setAttribute("plainte", jsonApiClient.getPlainteList(url));
        } else if (request.getParameter("action").equals("creation")) {

            Localisation[] lieus = this.ombyEJB.getAll();
            request.setAttribute("lieu", lieus);
            Omby[] ombyTab = this.ombyEJB.getAllOmby(user);
            request.setAttribute("omby", ombyTab);
            if (request.getParameter("status") != null && request.getParameter("status").equals("done")) {
                this.ombyEJB.removeAssignementOf(request.getParameter("idOmby"));
            } else {
                path = "plainte/create-plainte.jsp";
            }
        } else if (request.getParameter("action").equals("update")) {
            if (request.getParameter("status") != null) {
                String idOmby = request.getParameter("idOmby");
                String idLoc = request.getParameter("idLoc");
                if (!(request.getParameter("idLoc").equals("null")&&request.getParameter("idOmby")==null)) {
                    this.ombyEJB.findAt(idOmby, idLoc);
                }
                String url = "http://localhost:58974/plaintes/List";
                request.setAttribute("plainte", jsonApiClient.getPlainteList(url));
            } else {
                path = "plainte/update-plainte.jsp";
                Localisation[] lieus = this.ombyEJB.getAll();
                request.setAttribute("lieu", lieus);
                Omby[] ombyTab = this.ombyEJB.getAllOmby(user);
                request.setAttribute("omby", ombyTab);
                request.setAttribute("plainte", jsonApiClient.getPlainte("http://localhost:58974/plaintes/Details/" + request.getParameter("idPlainte")));
            }
        } else if (request.getParameter("action").equals("delete")) {
            path = "plainte/delete-plainte.jsp";
            request.setAttribute("plainte", jsonApiClient.getPlainte("http://localhost:58974/plaintes/Details/" + request.getParameter("idPlainte")));
        } else if (request.getParameter("action").equals("fiche")) {
            path = "plainte/fiche-plainte.jsp";
            request.setAttribute("plainte", jsonApiClient.getPlainte("http://localhost:58974/plaintes/Details/" + request.getParameter("idPlainte")));
        } else if (request.getParameter("action").equals("resolu")) {
            this.ombyEJB.findAt(request.getParameter("idOmby"), request.getParameter("idLocalisation"));
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
