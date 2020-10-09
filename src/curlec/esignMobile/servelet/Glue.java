package curlec.esignMobile.servelet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;

import org.json.*;
import org.apache.commons.io.*;

/**
 * Servlet implementation class Glue
 */
@WebServlet("/Glue")
public class Glue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> tempStorage = new HashMap<String, String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Glue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("sessionid");
		String signature = tempStorage.get(id);
		System.out.println(id);
		
		if (signature != null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("image/png");
			response.getWriter().write(signature);
			tempStorage.remove(id);
		} else {
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("text/html");
			response.getWriter().write("No signature");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		JSONObject jsonObj = new JSONObject(IOUtils.toString(request.getReader()));
		
		String id = jsonObj.getString("sessionid");
	    String signature = jsonObj.getString("signature");
	    
	    tempStorage.put(id, signature);
	    
	    System.out.println("Signature added to temp storage");
		
	}

}
