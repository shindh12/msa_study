package com.msa.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;

@Component
public class PreFilter extends ZuulFilter {
	private static Logger log = LoggerFactory.getLogger(PreFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = new HttpServletRequestWrapper(ctx.getRequest());

		log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

		String requestData = null;


			if (request.getContentLength() > 0) {
				try {
					requestData = CharStreams.toString(request.getReader());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (requestData == null) {
				return null;
			}

		log.info(String.format("%s request payload %s", request.getMethod(), requestData));
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
