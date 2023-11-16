package org.example;

public interface HttpHelper {
    String makeHttpRequest(String url, boolean useApiKey) throws Exception;
}
