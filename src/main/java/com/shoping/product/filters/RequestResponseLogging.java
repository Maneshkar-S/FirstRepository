package com.shoping.product.filters;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/*import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.shoping.product.controller.ProductController;

//@Slf4j
@Component
@Order
public class RequestResponseLogging implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		MyCustomHttpRequestWrapper requestWrapper = new MyCustomHttpRequestWrapper((HttpServletRequest) request);
		logger.info("Request URI: {}"+requestWrapper.getRequestURI());
		logger.info("Request Method: {}"+requestWrapper.getMethod());
		//logger.info("Request Body: {}"+new String(IOUtils.toByteArray(httpServletRequest.getInputStream())));
		logger.info("Request Body: {}"+new String(requestWrapper.getByteArray()).replaceAll("\n", " "));
		
		
		MyCustomHttpResponseWrapper responseWrapper = new MyCustomHttpResponseWrapper((HttpServletResponse) response);
		
		chain.doFilter(requestWrapper, responseWrapper);
		
		logger.info("Request Status - {}"+responseWrapper.getStatus());
		logger.info("Request Body: {}"+new String(responseWrapper.getBaos().toByteArray()));
		
	}

	
	private class MyCustomHttpRequestWrapper extends HttpServletRequestWrapper {

		private byte[] byteArray;
		
		public MyCustomHttpRequestWrapper(HttpServletRequest request) {
			super(request);
			try {
				//byteArray = IOUtils.toByteArray(request.getInputStream());
				request.getInputStream();
			}catch(IOException ex) {
				throw new RuntimeException("Issue while reading request stream");
			}
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			return new MyDelegatingServletInputStream(new ByteArrayInputStream(byteArray));
			//return super.getInputStream();
		}
		
		public byte[] getByteArray() {
			return byteArray;
		}
		
	}
	
	private class MyCustomHttpResponseWrapper extends HttpServletResponseWrapper {

		private byte[] byteArray;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		private PrintStream printStream = new PrintStream(baos);
		
		public MyCustomHttpResponseWrapper(HttpServletResponse response) {
			super(response);
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			return new MyDelegatingServletOutputStream(super.getOutputStream());
			//return super.getInputStream();
		}
		
		@Override
		public PrintWriter getWriter() throws IOException {
			return new PrintWriter(super.getOutputStream());
			//return super.getWriter();
		}
		
		public ByteArrayOutputStream getBaos() {
			return baos;
		}
		
	}
}
