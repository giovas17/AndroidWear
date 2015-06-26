package wear.darkgeat.utilities;

/**
 * Created by darkgeat on 20/03/15.
 */
public class Item {

    private int imagen;
    private String mensaje;

    public Item(){
        setImagen(0);
        setMensaje("");
    }

    public Item(int img, String mes){
        setImagen(img);
        setMensaje(mes);
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
