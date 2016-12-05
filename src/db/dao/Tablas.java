package db.dao;

public class Tablas {
    public static class Extras {
        public static final int ID_EXTRAS = 1;
        public static final int NOMBRE = 2;
    }

    public static class Imagenes {
        public static final int ID_INMUEBLE = 1;
        public static final int ID_IMAGEN = 2;
        public static final int RUTA = 3;

    }

    public static class Inmueble {
        public static final int ID_INMUEBLE = 1;
        public static final int PRECIO = 2;
        public static final int SUPERFICIE = 3;
        public static final int PLANTA = 4;
        public static final int NUM_HABITACIONES = 5;
        public static final int NUM_BAGNOS = 6;
        public static final int DESCRIPCION = 7;
        public static final int SE_VENDE = 8;
        public static final int SE_ALQUILA = 9;
        public static final int ID_TIPO_INMUEBLE = 10;
        public static final int ID_USUARIO = 11;
        public static final int ID_PAIS = 12;
        public static final int ID_PROVINCIA = 13;
        public static final int POBLACION = 14;
        public static final int NOMBRE_DIR = 15;
        public static final int NUMERO_DIR = 16;
        public static final int ID_VIA = 17;
    }

    public static class InmuebleHasExtras {
        public static final int ID_INMUEBLE = 1;
        public static final int ID_EXTRAS = 2;
    }

    public static class TipoInmueble {
        public static final int ID_TIPO = 1;
        public static final int NOMBRE_TIPO = 2;

    }
}
