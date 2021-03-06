package net.itattractor;

import net.itattractor.utils.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class ConnectionProvider {
    private String username;
    private String password;
    private String host;

    public ConnectionProvider(String host, String username, String password) {
        this.username = username;
        this.password = password;
        this.host = StringUtils.trim(host, "/");
    }

    public DefaultHttpClient getHttpClient() {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        httpClient.setCredentialsProvider(credentialsProvider);
        return httpClient;
    }

    public String getUsername() {
        return username;
    }

    public String getHost() {
        return host;
    }

    public boolean isAuthenticated() {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        httpClient.setCredentialsProvider(credentialsProvider);
        HttpGet httpGet = new HttpGet(host);

        StatusLine statusLine;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            statusLine = response.getStatusLine();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return statusLine.getStatusCode() == 200;
    }
}
