package servlets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Lieux;
import beans.Sport;
import beans.Utilisateur;
import dao.LieuxDAO;
import dao.SportDAO;
import dao.UtilisateurDAO;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur user;
	private Lieux lieu;
	private ArrayList<Lieux> lieux = new ArrayList<>();
	private ArrayList<Sport> sports = new ArrayList<>();
       
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
		response.setContentType("text/html;charset=UTF-8");
		
		String action = request.getParameter("action");
		if ((action == null)) { // no action param
			// VUE = "/WEB-INF/error.jsp";
			response.getOutputStream().println("Erreur");
		
		} else if (action.equals("connexion")) { //Connexion
			String login = request.getParameter("login");
			String psw = request.getParameter("password");

		/*	try {
				validationEmail(email, email);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
			
			try {
				user = UtilisateurDAO.getUserByLogin(login);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String response_to_as = "";
			if (user.getLogin() == "") {
				response_to_as = "Login inexistant.";
			} else if (!user.getMdp().equals(psw)) {
				System.out.println("Login : " + user + " et mdp : " + user.getMdp());
				response_to_as = "Mot de passe incorrect.";

			} else if (user.getMdp().equals(psw)) {
				response_to_as = "Connexion";
			}
			response.getOutputStream().println(response_to_as);
		
		}else if (action.equals("log")) { //POST METHOD
			
	        Enumeration paramNames = request.getParameterNames();
	        String params[]= new String[20];
	        int i=0;
	        while(paramNames.hasMoreElements()) {
	            String paramName = (String)paramNames.nextElement();

	            System.out.println(paramName );
	            String[] paramValues = request.getParameterValues(paramName);
	            params[i] = paramValues[0];

	            System.out.println(params[i]);
	            i++;

	        }
	        
	        try {
				user = UtilisateurDAO.getUserByLogin(params[2]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        System.out.println("Param 0 : " + params[0]);
	        System.out.println("Param 1 : " + params[1]);
	        System.out.println("Param 2 : " + params[2] + " ,user = " + user.getLogin());
	        String response_to_as = "";

	        if(params[2].equals(user.getLogin()))
	        {

	            if(params[1].equals(user.getMdp()))
	            {
	                //out.writeObject("success");
	            	response.getOutputStream().println("Connexion...");
	            	System.out.println("Connecté");
	            	
	            }
	            else
	            {
	                //out.writeObject("wrong password");
	            	response.getOutputStream().println("Mot de passe incorrect.");
	            }
	        }
	        else
	        {
	            //out.writeObject("wrong username"); 
	        	//response.getOutputStream().println("Mauvais login.");
	       
	        }
	     
		}else if (action.equals("lieux")) { //GetLieux
			String sport = request.getParameter("sport");
			String status = request.getParameter("status");
			String type = request.getParameter("type");
			try {
				lieux = LieuxDAO.getAllSpecificLieux(sport, status, type);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String json = new Gson().toJson(lieux);

		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);

		}else if (action.equals("sports")) { //GetSports
			try {
				sports = SportDAO.getAllSport();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String json = new Gson().toJson(sports);

		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		
		}else if (action.equals("infosLieux")) { // Get Lieu by Title & Description
			String title = request.getParameter("title");
			//String description = request.getParameter("description");
			
			try {
				lieux = LieuxDAO.getLieuxByTitleAndDescription(title);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String json = new Gson().toJson(lieux);

		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
			
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
	
	/**
	 * Valide l'adresse mail saisie.
	 * 
	 * @param email
	 * @param confirmMail
	 */
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
