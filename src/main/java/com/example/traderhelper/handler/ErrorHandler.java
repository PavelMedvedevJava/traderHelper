package com.example.traderhelper.handler;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class ErrorHandler implements ResponseErrorHandler{

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		HttpStatus statusCode = response.getStatusCode();
		return statusCode.series() == HttpStatus.Series.CLIENT_ERROR ||
			statusCode.series() == HttpStatus.Series.SERVER_ERROR;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		HttpStatus statusCode = response.getStatusCode();
		if (Series.CLIENT_ERROR == statusCode.series() || Series.SERVER_ERROR == statusCode.series()) {
		}
	}
}
