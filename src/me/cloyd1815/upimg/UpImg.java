package me.cloyd1815.upimg;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;


public class UpImg {
	public static String upimg = "http://upimg.me";
	public static void upimg(File file) throws IOException, URISyntaxException {
		URL url = new URL("http://upimg.me/upload");
	    HttpClientBuilder client = HttpClientBuilder.create();
	    HttpPost httpPost = new HttpPost(url.toURI()); //The POST request to send
	    
	    FileBody fileB = new FileBody(file, ContentType.create("image/png"), "screenshot.png");
	    
	    MultipartEntityBuilder request = MultipartEntityBuilder.create(); //The HTTP entity which will holds the different body parts, here the file
	    request.addPart("file", fileB);

	    httpPost.setEntity(request.build());
	    HttpResponse response = client.build().execute(httpPost); //Once the upload is complete (successful or not), the client will return a response given by the server

	    String str = null;
	        str = response.getFirstHeader("Location").getValue();
	        StringSelection selection = new StringSelection(upimg + str);
	        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	        clipboard.setContents(selection, selection);
	}
	
	public BufferedImage resize(BufferedImage image) {
		
		return image;
	}
}
