package org.jitsi.videobridge.cc.allocation;

import okhttp3.*;
import org.json.simple.JSONObject;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class RestCallRL {
    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            } };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    }).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //    String result = "";
    public String callGET(String url) {
        String result = "";
        try {
            OkHttpClient client = getUnsafeOkHttpClient();

            Request.Builder builder = new Request.Builder().url(url).get();

//            builder.addHeader("content-type","application/json")

            Request request = builder.build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if(body != null) {
                    String responseString = body.string();
//                    System.out.println("[Resposne Body] : " + responseString);
                    result = responseString;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//            result = null;
        }
        return result;
    }

    public String callPOST(String url, JSONObject info) {
        String result = "";
        try {
            // Crete OkHttp object
            OkHttpClient client = getUnsafeOkHttpClient();
            String postContent = info.toJSONString();

            // Create ReqeustBody
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postContent);

            // Create Post object
            Request.Builder builder = new Request.Builder().url(url)
                    .post(requestBody);
            Request request = builder.build();

            // Send request
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if(body != null) {
                    String responseString = body.string();
//                System.out.println("[Resposne Body] : " + responseString);
                    result = responseString;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//            result = null;;
        }
        return result;
    }

    public String sendSignal(String url, String ep) {
        String result = "";
        try {
            OkHttpClient client = getUnsafeOkHttpClient();
            Request.Builder builder = new Request.Builder().url(url + ep).get();

//            builder.addHeader("content-type","application/json")

            Request request = builder.build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    String responseString = body.string();
//                    System.out.println("[Resposne Body] : " + responseString);
                    result = responseString;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//            result = null;
        }
        return result;
    }

//    String result = "";
    public String callGET_http(String url) {
        String result = "";
        try {
            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder().url(url).get();

//            builder.addHeader("content-type","application/json")

            Request request = builder.build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if(body != null) {
                    String responseString = body.string();
//                    System.out.println("[Resposne Body] : " + responseString);
                    result = responseString;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//            result = null;
        }
        return result;
    }

    public String callPOST_http(String url, JSONObject info) {
        String result = "";
        try {
            // Crete OkHttp object
            OkHttpClient client = new OkHttpClient();
            String postContent = info.toJSONString();

            // Create ReqeustBody
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postContent);

            // Create Post object
            Request.Builder builder = new Request.Builder().url(url)
                    .post(requestBody);
            Request request = builder.build();

            // Send request
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if(body != null) {
                    String responseString = body.string();
//                System.out.println("[Resposne Body] : " + responseString);
                    result = responseString;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//            result = null;;
        }
        return result;
    }


    public String sendSignal_http(String url, String ep) {
        String result = "";
        try {
            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder().url(url + ep).get();

//            builder.addHeader("content-type","application/json")

            Request request = builder.build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    String responseString = body.string();
//                    System.out.println("[Resposne Body] : " + responseString);
                    result = responseString;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//            result = null;
        }
        return result;
    }
}