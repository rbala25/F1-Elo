package com.rishibala;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

class getDrivers {
    private HashMap<driver, Double> allDrivers = new HashMap<>();

    public void getDrivers() {

        for (int j=1950; j<=2023; j++) {
            try {
                URL obj = new URL("http://ergast.com/api/f1/" + j + "/drivers");
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");

                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { // success
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    String response1 = response.toString();

                    try {
                        InputStream xmlInput = new ByteArrayInputStream(response1.getBytes());

                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(xmlInput);

                        doc.getDocumentElement().normalize();

                        NodeList nodeList = doc.getElementsByTagName("Driver");

                        for (int i = 0; i < nodeList.getLength(); i++) {
                            Node node = nodeList.item(i);

                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                Element element = (Element) node;

                                String givenName = element.getElementsByTagName("GivenName").item(0).getTextContent();
                                String familyName = element.getElementsByTagName("FamilyName").item(0).getTextContent();

                                //check if driver is alr there

                                driver driver = new driver(givenName, familyName);
                                if(allDrivers.containsKey(driver)) {
                                    continue;
                                } else {
                                    allDrivers.put(driver, 1000.0);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    throw new RuntimeException("GET request failed: " + responseCode);
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public HashMap<driver, Double> getAllDrivers() {
        return allDrivers;
    }
}
