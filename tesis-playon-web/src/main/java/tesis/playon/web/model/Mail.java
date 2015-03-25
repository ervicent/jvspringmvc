package tesis.playon.web.model;

public class Mail {

    private String nombre;
    private String apellido;
    private String asunto;

    private String mensaje;

    private String destinatario;

    public Mail() {

    }

    public Mail(String asunto, String mensaje, String destinatario)

    {

	this.asunto = asunto;
	this.mensaje = mensaje;
	this.destinatario = destinatario;

    }

    public String getMensaje() {
	return mensaje;
    }

    public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
    }

    public String getDestinatario() {
	return destinatario;
    }

    public void setDestinatario(String destinatario) {
	this.destinatario = destinatario;
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

    public String getAsunto() {
	return asunto;
    }

    public void setAsunto(String asunto) {
	this.asunto = asunto;

    }

    public String getAsuntoContacto() {
	// TODO Auto-generated method stub
	return null;
    }

}
