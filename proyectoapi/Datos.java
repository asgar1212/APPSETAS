
package proyectoapi;

/**
 *
 * @author vero
 */
class Datos {
    private String nombre;
    private String apellido;
    private String contraseña;
    private String user;

    public Datos() {
    }

    public Datos(String user) {
        this.user = user;
    }
    
    

    public Datos(String nombre, String apellido,String user,String contraseña) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.user = user;
        this.contraseña = contraseña;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    
    
}
