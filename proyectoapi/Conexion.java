package proyectoapi;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Conexion {

    private static final String URL = "http://localhost:4030/modificar_usuario"; 
    private static final String URL_loguin = "http://localhost:4030/loguin";
    private static final String URL_listausuarios= "http://localhost:4030/listar_usuarios";
    private static final String URL_eliminar= "http://localhost:4030/eliminar_usuarios";
    String tokenadmin ;
    ArrayList listausers = new ArrayList();

    public String modificarUsuario(Datos usuario,String nuevouser, String token) {
        String response = null;
        StringBuilder respuesta = new StringBuilder();
        String nombre=usuario.getNombre();
        String apellido=usuario.getApellido();
        String user=usuario.getUser();
        String password= usuario.getContraseña();

        try {
            //System.out.println("nombre"+nombre+"user"+user+"apellido"+apellido);
            StringBuilder parametros = new StringBuilder("token=" + URLEncoder.encode(token, "UTF-8"));
            
            parametros.append("&user=" + URLEncoder.encode(user, "UTF-8"));

            if (nombre != null) {
                parametros.append("&nombre=" + URLEncoder.encode(nombre, "UTF-8"));
            }

            if (apellido != null && !apellido.isEmpty()) {
                parametros.append("&apellido=" + URLEncoder.encode(apellido, "UTF-8"));
            }

            if (password != null && !password.isEmpty()) {
                parametros.append("&password=" + URLEncoder.encode(password, "UTF-8"));
            }
            if (nuevouser != null && !nuevouser.isEmpty()) {
                parametros.append("&nuevouser=" + URLEncoder.encode(nuevouser, "UTF-8"));
            }
            
            // Crear la URL
            URL url = new URL(URL + "?" + parametros.toString());
            //System.out.println("?"+"parametros que mando"+parametros.toString());
            
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            int responseCode = conexion.getResponseCode();
            // Si la respuesta es 200 (OK)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bf = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String inputLine;

                while ((inputLine = bf.readLine()) != null) {
                    respuesta.append(inputLine);
                }
                String respuestaJson = respuesta.toString();
                JSONObject jsonResponse = new JSONObject(respuestaJson);

                if (jsonResponse.has("mensaje")) {
                    response = jsonResponse.getString("mensaje");
                } else {
                    System.out.println("No se encontró el campo 'respuesta' en la respuesta.");
                }
                bf.close();
            } else {
                // En caso de que la respuesta no sea 200, imprimir el error
                System.out.println("Error en la conexión a la API." + respuesta.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
    
    
        public String loguin(String user,String password) {
        StringBuilder respuesta = new StringBuilder();

        try {
           //System.out.println("user"+user+"password"+password);
            StringBuilder parametros = new StringBuilder();
           

            if (user != null) {
                 parametros.append("&user=" + URLEncoder.encode(user, "UTF-8"));
            }

            if (password != null) {
                parametros.append("&password=" + URLEncoder.encode(password, "UTF-8"));
            }

            URL url = new URL(URL_loguin + "?" + parametros.toString());
            
            //System.out.println("?"+"parametros que mando"+parametros.toString());

            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            int responseCode = conexion.getResponseCode();
            //System.out.println("Respuesta HTTP: " + responseCode);
            
            // Si la respuesta es 200 (OK), procesar la respuesta
            if (responseCode == HttpURLConnection.HTTP_OK) { // HTTP 200
                BufferedReader bf = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String inputLine;

                while ((inputLine = bf.readLine()) != null) {
                    respuesta.append(inputLine);
                }
                bf.close();
            } else {
                System.out.println("Error en la conexión a la API.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String respuestaJson = respuesta.toString();
        //System.out.println("Respuesta JSON completa: " + respuestaJson);

        JSONObject jsonResponse = new JSONObject(respuestaJson);

        if (jsonResponse.has("token")) {
            tokenadmin = jsonResponse.getString("token");
            //System.out.println("Token extraído: " + tokenadmin);
        } else {
            System.out.println("No se encontró el token en la respuesta.");
        }
                return tokenadmin;
                
                
            }//fin metodo loguin
        
        
        public ArrayList<String> listar_usuarios(String token) {
        StringBuilder respuesta = new StringBuilder();

        try {
           //System.out.println("token"+token);
            StringBuilder parametros = new StringBuilder();
           
            if (token != null) {
                 parametros.append("&token=" + URLEncoder.encode(token, "UTF-8"));
            }

            URL url = new URL(URL_listausuarios + "?" + parametros.toString());
            
            //System.out.println("?"+"parametros que mando"+parametros.toString());

            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            
            int responseCode = conexion.getResponseCode();
            //System.out.println("Respuesta HTTP: " + responseCode);
            
            // Si la respuesta es 200 (OK), procesar la respuesta
            if (responseCode == HttpURLConnection.HTTP_OK) { // HTTP 200
                BufferedReader bf = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String inputLine;

                while ((inputLine = bf.readLine()) != null) {
                    respuesta.append(inputLine);
                }
                bf.close();
            } else {
                System.out.println("Error en la conexión a la API.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String respuestaJson = respuesta.toString();
        //System.out.println("Respuesta JSON completa: " + respuestaJson);

        try {
            JSONArray listadojson = new JSONArray(respuestaJson);

            for (int i = 0; i < listadojson.length(); i++) {
      
                String usuario = listadojson.getString(i);
                listausers.add(usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listausers;
    
                
    }//fin metodo listarusuario
        
        
        
        public String eliminar_usuarios(String user, String token) {
        String response = null;
        StringBuilder respuesta = new StringBuilder();

        try {
            //System.out.println("nombre"+nombre+"user"+user+"apellido"+apellido);
            StringBuilder parametros = new StringBuilder("token=" + URLEncoder.encode(token, "UTF-8"));
            
            parametros.append("&user=" + URLEncoder.encode(user, "UTF-8"));

            URL url = new URL(URL_eliminar + "?" + parametros.toString());
            System.out.println("?"+"parametros que mando"+parametros.toString());
            
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("DELETE");

            int responseCode = conexion.getResponseCode();
            // Si la respuesta es 200 (OK)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bf = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String inputLine;

                while ((inputLine = bf.readLine()) != null) {
                    respuesta.append(inputLine);
                }
                String respuestaJson = respuesta.toString();
                JSONObject jsonResponse = new JSONObject(respuestaJson);

                if (jsonResponse.has("mensaje")) {
                    response = jsonResponse.getString("mensaje");
                     System.out.println("aversielimino" + respuesta.toString());
                } else {
                    System.out.println("No se encontró el campo 'respuesta' en la respuesta.");
                }
                bf.close();
            } else {
                // En caso de que la respuesta no sea 200, imprimir el error
                System.out.println("Error en la conexión a la API." + respuesta.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }//finn eliminarusuario
    
}//fin class