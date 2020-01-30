package Client;

import java.io.InputStream;
import java.io.OutputStream;

public interface SpecificRequest {

	
	void handleRequest(final InputStream in, final OutputStream out);
	
}
