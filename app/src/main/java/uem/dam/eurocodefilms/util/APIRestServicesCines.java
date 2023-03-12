package uem.dam.eurocodefilms.util;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import uem.dam.eurocodefilms.apidata.CineRes;

public interface APIRestServicesCines {

    public static final String BASE_URL = "https://datos.madrid.es/egob/catalogo/";

    public static final String CLAVE_KEY = "208862-7650164-ocio_salas.json";

    @GET("{key}")
    Call<CineRes> obtenerCines(
            @Path("key") String key);

}
