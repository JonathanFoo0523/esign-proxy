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
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.client.utils.URIBuilder;

/**
 * Servlet implementation class esignDetail
 */
@WebServlet("/esignDetail")
public class esignDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public esignDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("New Request");
		
		String repoID = request.getParameter("repo_id");
		String taskUID = request.getParameter("task_uid");
		String workflowUID = request.getParameter("workflow_uid");
		String secToken = request.getParameter("secToken");
		
		String jsonRequest = "\"method\":\"GET_E_SIGNATURE_WORKFLOW\"";
		jsonRequest += ",\"repo_id\":\"" + repoID + "\""; 
		jsonRequest += ",\"task_uid\":\"" + taskUID + "\""; 
		jsonRequest += ",\"workflow_uid\":\"" + workflowUID + "\"";
		jsonRequest += ",\"secToken\":\"" + secToken + "\"";
		jsonRequest = String.format("{%s}", jsonRequest);
		
		System.out.println(jsonRequest);
		
		URIBuilder urlb = new URIBuilder()
				.setScheme("https")
				.setHost("demo.ag-icloudsolutions.com")
				.setPath("/ESignViewer")
				.addParameter("json", jsonRequest);
		
		HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(urlb.toString());
        response.addHeader("Access-Control-Allow-Origin", "*");
        client.executeMethod(method);
        String body = method.getResponseBodyAsString();
        System.out.println(body);
        response.getWriter().write(body);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
