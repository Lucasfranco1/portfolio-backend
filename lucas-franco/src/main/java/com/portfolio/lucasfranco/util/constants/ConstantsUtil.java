package com.portfolio.lucasfranco.util.constants;

public interface ConstantsUtil {

    /*
        Utilizo constantes para que el codigo quede más prolijo y reutilizar cosas.
        Lo subo a git para que puedan evaluar, planeo después hacer uso del git ignore.
     */

    // PersonID
    public final static String PERSON_ID = "01813f01-d6e7-4de2-91df-76667db53488";
    //Cross
    public final static String CROSS_ORIGIN = "https://portfoliofrontend-dev.web.app";

    //PATH
    public final static String PATH_PERSON = "/person";
    public final static String PATH_GET_ALL = "/all";
    public final static String PATH_LIST = "/list";
    public final static String PATH_FILE = "/file";
    public final static String PATH_FILE_PROJECT = "/fileProject/{id}";
    public final static String PATH_EXPERIENCE = "/experience";
    public final static String PATH_EDUCATION = "/education";
    public final static String PATH_SKILL = "/skill";
    public final static String ALL_PROFILE = "/allProfile";
    public final static String CREATE = "/create";
    public final  static String UPDATE = "/update/{id}";
    public final  static String UPDATE_PHOTO = "/updatePhoto/{id}";
    public final  static String UPDATE_BANNER = "/updateBanner/{id}";
    public final static String DETAIL_ID = "/detail/{id}";
    public final static String DELETE_EDUCATION = "/delete_education/{id}";
    public final static String DELETE = "/delete/{id}";
    public final static String PATH_UPLOAD = "/upload";

    public final static String ID = "id";
    public final static String PARAM_FILE = "file";
    public final static String ERROR_IMAGE = "error in Image";
    public final static String ORIGINAL_FILENAME = "original_filename";
    public final static String URL = "url";
    public final static String PUBLIC_ID = "public_id";
    public final static String ERROR_BY_ID = "No se encontró el id";
    public final static String IMAGE_UPLOAD = "Image upload successfully!";

    //UUID
    public final static String UUID = "uuid";
    public final static String UUID2 = "uuid2";

    //Exceptions
    public final static String MESSAGE_NOT_NULL = "can't be null";
    public final static String MESSAGE_NOT_EMPTY = "can't be empty";
    public final static String MESSAGE_SIZE_LIMIT = "Unfulfilled length";

    //table
    public final static String PERSON = "person";
    public final static String EDUCATION = "education";
    public final static String EXPERIENCE = "work_experience";
    public final static String IMAGE = "image";
    public final static String SKILL = "skill";

    //ROLES
    public final static String ADMIN = "hasRole('ADMIN')";

    //Cloudinary
    public final static String CLOUD_NAME = "cloud_name";
    public final static String API_KEY = "api_key";
    public final static String API_SECRET = "api_secret";
    public final static String NAME_CLOUD = "lucasfranco1";
    public final static String KEY_API = "221248742812656";
    public final static String SECRET_API = "1B7M7bEaAPLo-dVoIxxQ4JlsLn8";

}
