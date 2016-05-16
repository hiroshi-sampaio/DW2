/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiroZoid.servlet;

import hiroZoid.dao.AmbulatorioDAO;
import hiroZoid.dao.PacienteDAO;
import hiroZoid.entity.Ambulatorio;
import hiroZoid.entity.Paciente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hiroshi
 */
@WebServlet(name = "alterarPaciente", urlPatterns = {"/alterarPaciente"})
public class AlterarPaciente extends HttpServlet {

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

        System.out.println(request.getRequestURL().toString());

        Paciente paciente = new Paciente();
        paciente.setCodigoPaciente(Integer.parseInt(request.getParameter("codigoPaciente")));
        if (Helper.INSTANCE.isSet(request.getParameter("nome"))) {
            paciente.setNome(request.getParameter("nome"));
        }
        if (Helper.INSTANCE.isSet(request.getParameter("idade"))) {
            paciente.setIdade(Integer.parseInt(request.getParameter("idade")));
        }
        if (Helper.INSTANCE.isSet(request.getParameter("cidade"))) {
            paciente.setCidade(request.getParameter("cidade"));
        }
        if (Helper.INSTANCE.isSet(request.getParameter("plano"))) {
            paciente.setPlano(request.getParameter("plano"));
        }

        PacienteDAO pacienteDAO = new PacienteDAO();
        if (pacienteDAO.alterar(paciente) == 0) {
            pacienteDAO.inserir(paciente);
        }

        response.sendRedirect("listarPacientes.jsp");
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
        processRequest(request, response);
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
