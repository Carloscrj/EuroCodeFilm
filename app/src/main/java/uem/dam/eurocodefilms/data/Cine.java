package uem.dam.eurocodefilms.data;

public class Cine {

    private String direccion;
    private String url;
    private String peliculas;
    private String sinopsis;


    public Cine() {
    }

    public Cine(String direccion, String url, String peliculas, String sinopsis) {
        this.direccion = direccion;
        this.url = url;
        this.peliculas = peliculas;
        this.sinopsis = sinopsis;

    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(String peliculas) {
        this.peliculas = peliculas;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
