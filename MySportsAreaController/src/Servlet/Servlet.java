package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Utilisateur;
import dao.UtilisateurDAO;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur user;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		//response.getOutputStream().println(UtilisateurDAO.getUser(1).getIdUser());
		
		response.setContentType("text/html;charset=UTF-8"); 
		String action = request.getParameter("action");
		
		if ((action == null)) { // no action param
			// VUE = "/WEB-INF/error.jsp";
		} else if ((action == "login")) { // no action param
			String email = request.getParameter("email");
			String psw = request.getParameter("password");

		/*	try {
				validationEmail(email, email);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
			
			
			try {
				user = UtilisateurDAO.getUserLogin(email);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String response_to_as = "";
			if (user == null) {
				response_to_as = "Login incorrect";
			} else if (!user.getMdp().equals(psw)) {
				response_to_as = "Mot de passe incorrect";

			} else if (user.getMdp().equals(psw)) {
				response_to_as = "Connexion";
			}
			response.getOutputStream().println(response_to_as);
		}
		
		
		//response.getOutputStream().println("Servlet connecté");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void validationEmail(String email, String confirmMail) throws Exception {
        if ((email != null) && (email.trim().length() != 0)) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Merci de saisir une adresse mail valide ");
            }
        } else {
            throw new Exception("Merci de saisir une adresse mail.");
        }

        if (!email.equals(confirmMail)) {
            throw new Exception("Les adresses emails ne correspondent pas");
        }
    }

}
