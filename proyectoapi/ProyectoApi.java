package proyectoapi;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author vero
 * Este proyecto permite al administrador de la api, modificar usuarios y eliminarlos 
 * desde java.
 */
public class ProyectoApi {

    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        boolean menu = false;
        int opcion = 0;
        Scanner teclado = new Scanner(System.in);
        String nuevonombre;
        String nuevoapellido;
        String nuevopassword;
        String user;
        String useradmin;
        String passadmin;
        String nuevouser;
        String eliminouser;
        String tokenadmin = null;
        Conexion conexion = new Conexion();
        Datos usuario = new Datos();
        ArrayList lista = new ArrayList();
        Boolean logueado = false;

        while (logueado == false) {
            System.out.println("Para Loguearse introduce tu user");
            useradmin = teclado.nextLine();
            System.out.println("Ahora tu contraseña de administrador");
            passadmin = teclado.nextLine();

            if (!useradmin.equals("admin") || !passadmin.equals("admin")) {
                System.out.println("Has introducido mal el usuario o la contraseña");
            } else {
                tokenadmin = conexion.loguin(useradmin, passadmin);
                if (tokenadmin == null) {
                    System.out.println("error de logueo");
                } else {
                    System.out.println("Bienvenido Administrador");
                    logueado = true;
                }
            }//fin else

        }//fin while

        while (menu == false) {
            lista.clear();

            System.out.println("Lista de Users");
            lista = conexion.listar_usuarios(tokenadmin);

            for (int i = 0; i < lista.size(); i++) {
                System.out.println(i + "- :" + lista.get(i));
            }

            System.out.println("Intrdoduce el user que quieres cambiar");
            user = teclado.nextLine();

//            if (!lista.contains(user)) {
//                System.out.println("Ese usuario no existe, prueba otro");
//                System.out.println("Intrdoduce el user que quieres cambiar");
//                user = teclado.nextLine();
//            }
            System.out.println("-----MENU------");
            System.out.println("1-Cambiar nombre ");
            System.out.println("2-Cambiar apellido ");
            System.out.println("3-Cambiar contraseña ");
            System.out.println("4-Cambiar todo al vez ");
            System.out.println("5-Cambiar el User");
            System.out.println("6-Eliminar usuario");
            System.out.println("7-salir");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Introduce el nuevo nombre");
                    nuevonombre = teclado.nextLine();
                    nuevoapellido = null;
                    nuevopassword = null;
                    nuevouser = null;

                    usuario = new Datos(nuevonombre, nuevoapellido, user, nuevopassword);
                    String respuesta = conexion.modificarUsuario(usuario,nuevouser,tokenadmin);
                    System.out.println(respuesta);

                    break;

                case 2:
                    System.out.println("Introduce el apellido");
                    nuevoapellido = teclado.nextLine();
                    nuevonombre = null;
                    nuevopassword = null;
                    nuevouser = null;

                    usuario = new Datos(nuevonombre, nuevoapellido, user, nuevopassword);
                    respuesta = conexion.modificarUsuario(usuario,nuevouser,tokenadmin);
                    System.out.println(respuesta);

                    break;

                case 3:
                    System.out.println("Introduce la nueva contraseña");
                    nuevopassword = teclado.nextLine();
                    nuevonombre = null;
                    nuevoapellido = null;
                    nuevouser = null;

                    usuario = new Datos(nuevonombre, nuevoapellido, user, nuevopassword);
                    respuesta = conexion.modificarUsuario(usuario,nuevouser,tokenadmin);
                    System.out.println(respuesta);

                    break;

                case 4:
                    System.out.println("Introduce nuevo nombre");
                    nuevonombre = teclado.nextLine();
                    System.out.println("Introduce el apellido");
                    nuevoapellido = teclado.nextLine();
                    System.out.println("Introduce la nueva contraseña");
                    nuevopassword = teclado.nextLine();
                    nuevouser = null;
                    
                    usuario = new Datos(nuevonombre, nuevoapellido, user, nuevopassword);
                    respuesta = conexion.modificarUsuario(usuario,nuevouser,tokenadmin);
                    System.out.println(respuesta);
                    break;
                case 5:
                    System.out.println("Introduce el nuevo nombre de usuario que quieres cambiar");
                    nuevouser = teclado.nextLine();
                    nuevonombre = null;
                    nuevoapellido = null;
                    nuevopassword = null;
                    if(lista.contains(nuevouser)){
                        System.out.println("Este usuario ya existe, elige otro");
                        break;
                    }else{
                       usuario = new Datos(nuevonombre, nuevoapellido, user, nuevopassword);
                       respuesta = conexion.modificarUsuario(usuario,nuevouser,tokenadmin);
                       System.out.println(respuesta);
                    }

                    break;
                case 6:
                    System.out.println("estas seguro que quieres eliminar a este usuario?:"+user +" si/no");
                    String aceptar= teclado.nextLine();;
            
                    if (user.equals("admin")) {
                        System.out.println("Este usuario no se puede eliminar");
                    } else {
                        if(aceptar.equals("si")){
                             respuesta = conexion.eliminar_usuarios(user, tokenadmin);
                             System.out.println(respuesta);
                        }else{
                            System.out.println("ok, no lo eliminamos");
                            break;
                        }
                    }
                    break;

                case 7:
                    menu = true;
                    break;
            }//fin switch

        }//fin menu

    }//fin main
}//fin clase

