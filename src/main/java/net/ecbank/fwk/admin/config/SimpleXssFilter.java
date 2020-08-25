package net.ecbank.fwk.admin.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class SimpleXssFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(
		HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
		throws ServletException, IOException {
		
		System.out.println(req.getContentType());
		
		if(req.getContentType() == null || req.getContentType().indexOf("json") > -1) {
			chain.doFilter(new SimpleXssFilterInternal(req), resp);
			//chain.doFilter(req,resp);
		}else {
			chain.doFilter(req,resp);
		}
	}

	protected static class SimpleXssFilterInternal extends HttpServletRequestWrapper {

		private byte[] body;

		public SimpleXssFilterInternal(HttpServletRequest request) {
			super(request);
			try {
				BufferedReader bufferedReader = null;

				InputStream is = request.getInputStream();
				if (is != null) {
					StringBuilder stringBuilder = new StringBuilder();

					bufferedReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			        char[] charBuffer = new char[128];
			        int bytesRead = -1;
			        while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
			            stringBuilder.append(charBuffer, 0, bytesRead);
			        }

					System.out.println("bio1215 : "+stringBuilder.toString());
					String result = doWork(stringBuilder.toString());
					//System.out.println(result);
					body = result.getBytes("UTF-8");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.body);
			return new ServletInputStream() {

				@Override
				public boolean isFinished() {
					return byteArrayInputStream.available() == 0;
				}

				@Override
				public boolean isReady() {
					return true;
				}

				@Override
				public void setReadListener(ReadListener readListener) {}

				@Override
				public int read() throws IOException {
					return byteArrayInputStream.read();
				}
			};
		}

		private String doWork(String job) {
			StringBuffer sb = new StringBuffer();
  			for (int i = 0; i < job.length(); i++) {
				switch (job.charAt(i)) {
					case '<':
						sb.append("&lt;"); break;
					case '>':
						sb.append("&gt;"); break;
					case '(':
						sb.append("&#40;"); break;
					case ')':
						sb.append("&#41;"); break;
					case '#':
						sb.append("&#35;"); break;
					case '$':
						sb.append("&#39;"); break;
					default:
						sb.append(job.charAt(i));
				}
			}
			return sb.toString();
		}
	}

}
