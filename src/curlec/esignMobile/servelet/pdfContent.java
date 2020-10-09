package curlec.esignMobile.servelet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.utils.URIBuilder;
import org.apache.commons.httpclient.*;

/**
 * Servlet implementation class pdfContent
 */
@WebServlet("/pdfContent")
public class pdfContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pdfContent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("New Request");
		
		String repoID = request.getParameter("repo_id");
		String taskUID = request.getParameter("task_uid");
		String workflowUID = request.getParameter("workflow_uid");
		String secToken = request.getParameter("secToken");
		
		String viewerAddress = "https://demo.ag-icloudsolutions.com/ESignViewer?json=";
		String jsonRequest = "\"method\":\"CONTENT_VIEW\"";
		jsonRequest += ",\"repo_id\":\"" + repoID + "\""; 
		jsonRequest += ",\"task_uid\":\"" + taskUID + "\""; 
		jsonRequest += ",\"workflow_uid\":\"" + workflowUID + "\"";
		jsonRequest += ",\"is_html5_viewer\":" + "true";
		jsonRequest += ",\"secToken\":\"" + secToken + "\"";
		jsonRequest = String.format("{%s}", jsonRequest);
		viewerAddress += URLEncoder.encode(jsonRequest, "UTF-8");
		
		System.out.println(jsonRequest);
		
		URIBuilder urlb = new URIBuilder()
				.setScheme("https")
				.setHost("demo.ag-icloudsolutions.com")
				.setPath("/ESignViewer")
				.addParameter("json", jsonRequest);
		System.out.println(urlb.toString());
		
		
		HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(urlb.toString());
        client.executeMethod(method);
        String body = method.getResponseBodyAsString();
        
        String cookie = method.getResponseHeader("Set-Cookie").getValue();
        String JSESSIONID = cookie.substring(11, cookie.indexOf(";"));
        System.out.println(JSESSIONID);
        String pdfAddress = body.substring(body.indexOf("PDFFile : ") + 11, body.indexOf("PDFFile : ") + 12 + 84);
        
        System.out.println(pdfAddress);
        method = new GetMethod("https://demo.ag-icloudsolutions.com/" + pdfAddress);
        client.executeMethod(method);
        System.out.println(method.getRequestHeader("Cookie"));
        byte[] pdf = method.getResponseBody();

        
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/pdf");
        response.setContentLength(pdf.length);
        response.getOutputStream().write(pdf, 0, pdf.length);
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
