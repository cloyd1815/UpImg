package me.cloyd1815.upimg;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

@SuppressWarnings("deprecation")
public class UpImg {
	public UpImg(File file) throws URISyntaxException, ClientProtocolException, IOException {
		URI url = new URI("http://boboman13.net:8080/upload");
	    MultipartEntity entity = new MultipartEntity();
	    entity.addPart("file", new FileBody(file));

	    HttpPost request = new HttpPost(url);
	    request.setEntity(entity);

	    HttpClient client = new DefaultHttpClient();
	    HttpResponse response = client.execute(request);
	}

}
