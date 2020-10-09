package curlec.esignMobile.servelet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * Servlet implementation class DisplayQRCode
 */
@WebServlet("/DisplayQRCode")
public class DisplayQRCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayQRCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/png");
		
		ServletOutputStream out = response.getOutputStream();
		String sessionid = request.getParameter("sessionid");
		
		String barcodeURL = "http://192.168.0.158:3001/?sessionid=001";
		
		BitMatrix matrix = null;
		try {
			matrix = new MultiFormatWriter().encode(barcodeURL, BarcodeFormat.QR_CODE,
			        200, 200);
		} catch (WriterException e) {
			out.close();
			e.printStackTrace();
		}
		
		MatrixToImageWriter.writeToStream(matrix, "png", out);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


