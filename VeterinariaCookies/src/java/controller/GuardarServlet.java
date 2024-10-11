/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import configuration.ConnectionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author carla
 */
@WebServlet(name = "GuardarServlet", urlPatterns = {"/guardarServlet"})
public class GuardarServlet extends HttpServlet {

    Connection conn;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GuardarServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GuardarServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");

        Cookie[] cookies = request.getCookies();
        String nombreA = "Desconocido";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("datosVeterinaria")) {
                    try {
                        String datosVeterinaria = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        // Analizamos el contenido de la cookie como JSON
                        JSONObject json = new JSONObject(datosVeterinaria);
                        nombreA = json.getString("nombreA");
                    } catch (Exception e) {
                        request.setAttribute("error", "Error al leer la cookie: " + e.getMessage());
                        request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
                        return;
                    }
                    break;
                }
            }
        }

        try {
            ConnectionBD conexion = new ConnectionBD();
            conn = conexion.getConnectionBD();

            String sql = "SELECT * FROM animalr WHERE nombreA  = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombreA);

            rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("tipoA", rs.getString("tipoA"));
                request.setAttribute("nombreA", rs.getString("nombreA"));
                request.setAttribute("diaLLegada", rs.getString("diaLLegada"));
                request.setAttribute("HoraLLegada", rs.getString("HoraLLegada"));
                request.setAttribute("CostoConsulta", rs.getDouble("CostoConsulta"));
            } else {
                request.setAttribute("error", "Animalito no encontrado");
            }

            request.getRequestDispatcher("/jsp/mostrar.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "Error en la base de datos: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String tipoA = request.getParameter("tipoA");
        String nombreA = request.getParameter("nombreA");
        String diaLLegada = request.getParameter("diaLLegada");
        String HoraLLegada = request.getParameter("HoraLLegada");
        String CostoConsulta = request.getParameter("CostoConsulta");

        System.out.println(nombreA);

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            ConnectionBD conexion = new ConnectionBD();
            conn = conexion.getConnectionBD();

            String sql = "INSERT INTO animalr (tipoA, nombreA, HoraLLegada, diaLLegada, CostoConsulta) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, tipoA);
            ps.setString(2, nombreA);
            ps.setString(3, HoraLLegada);
            ps.setString(4, diaLLegada);
            ps.setString(5, CostoConsulta);

            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas > 0) {
                request.getRequestDispatcher("/jsp/mostrar.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Error al insertar los datos.");
                request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Muestra la traza completa del error en la consola
            request.setAttribute("error", "Error en la base de datos: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("datosVeterinaria")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        response.setStatus(HttpServletResponse.SC_OK);
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
