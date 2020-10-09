package curlec.esignMobile.servelet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * Servlet implementation class Sign
 */
@WebServlet("/Sign")
public class Sign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sign() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String repoID = request.getParameter("repo_id");
		String taskUID = request.getParameter("task_uid");
		String workflowUID = request.getParameter("workflow_uid");
		String accessCode = request.getParameter("access_code");
		String ebikoSessionId = null;
		String username = "";
		String password = "";
		String rejectionReason = "";
		String secToken = request.getParameter("secToken");
		Boolean changeSignature = false;
		
		
		String viewerAddress = "https://demo.ag-icloudsolutions.com/ESignViewer?json=";
		String jsonRequest = "\"method\":\"SIGN\"";
		jsonRequest += ",\"repo_id\":\"" + repoID + "\""; 
		jsonRequest += ",\"task_uid\":\"" + taskUID + "\""; 
		jsonRequest += ",\"workflow_uid\":\"" + workflowUID + "\"";
		jsonRequest += ",\"secToken\":\"" + secToken + "\"";
		jsonRequest = String.format("{%s}", jsonRequest);
		viewerAddress += URLEncoder.encode(jsonRequest, "UTF-8");
		
		System.out.println(jsonRequest);

		String address = "https://demo.ag-icloudsolutions.com/SigGenerator?repo_id=" + repoID +
				"&task_uid=" + taskUID + "&workflow_uid=" + workflowUID;
		System.out.println(address);
		HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(address);
        client.executeMethod(method);
        byte[] body = method.getResponseBody();
        
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("image/png");
        response.setContentLength(body.length);
        response.getOutputStream().write(body, 0, body.length);
        return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
