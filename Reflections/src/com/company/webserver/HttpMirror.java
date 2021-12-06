package com.company.webserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class HttpMirror {
    public static void start() {
        try {
            // Get the port to listen on
            int port = 8088;
            // Create a ServerSocket to listen on that port.
            ServerSocket serverSocket = new ServerSocket(port);
            // Now enter an infinite loop, waiting for & handling connections.
            while (true) {
                // Wait for a client to connect. The method will block;
                // when it returns the socket will be connected to the client
                Socket client = serverSocket.accept();

                // Get input and output streams to talk to the client
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream());

                // Start sending our reply, using the HTTP 1.1 protocol
                out.print("HTTP/1.1 200 \r\n"); // Version & status code
                out.print("Content-Type: text/html\r\n"); // The type of data
                out.print("Connection: close\r\n"); // Will close stream
                out.print("\r\n"); // End of headers

                // Now, read the HTTP request from the client, and send it
                // right back to the client as part of the body of our
                // response. The client doesn't disconnect, so we never get
                // an EOF. It does send an empty line at the end of the
                // headers, though. So when we see the empty line, we stop
                // reading. This means we don't mirror the contents of POST
                // requests, for example. Note that the readLine() method
                String line;
                ArrayList<String> httpRequest = new ArrayList<>();
                while ((line = in.readLine()) != null) {
                    System.out.printf(line + "\r\n");
                    if (line.length() == 0)
                        break;
                    httpRequest.add(line+"\r\n");
                    out.print(line + "\r\n");
                }

                parse(httpRequest);


                // Close socket, breaking the connection to the client, and
                // closing the input and output streams
                out.close(); // Flush and close the output stream
                in.close(); // Close the input stream
                client.close(); // Close the socket itself
            } // Now loop again, waiting for the next connection
        }
        // If anything goes wrong, print an error message
        catch (Exception e) {
            System.err.println(e);
            System.err.println("Usage: java HttpMirror <port>");
        }
    }

    private static HttpRequestParser parse(ArrayList<String> req) {
        if (req.isEmpty()) return null;

        //Handle Http Method
        int backslashIndex = req.get(0).indexOf("/");
        int questionMarkIndex = req.get(0).indexOf("?");

        String htpMethod = req.get(0).substring(0, backslashIndex).trim();
        System.out.println(htpMethod);

        String uri = req.get(0).substring(backslashIndex + 1, questionMarkIndex).trim();
        System.out.println(uri);

        //Handle params
        String requestParamsWithVersion = req.get(0).substring(questionMarkIndex + 1).trim();
        String params = requestParamsWithVersion.split(" ")[0];

        HashMap<String,String>paramsMap =  new HashMap<>();
        String[] allParams = params.split("&");
        for (String pair : allParams) {
            int idx = pair.indexOf("=");
            paramsMap.put(pair.substring(0, idx),pair.substring(idx + 1));
        }

        System.out.println(paramsMap);

        //Handle Headers
        HashMap<String,String>headers =  new HashMap<>();
        for(int i = 1;i<req.size();i++){
            String currHeader = req.get(i);
            int colonIndex = currHeader.indexOf(":");
            headers.put(currHeader.substring(0,colonIndex),currHeader.substring(colonIndex+1));
        }

        System.out.println(headers);
        return null;
    }
}