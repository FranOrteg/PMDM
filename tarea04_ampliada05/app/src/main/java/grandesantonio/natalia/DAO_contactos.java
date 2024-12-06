package grandesantonio.natalia;


import java.util.ArrayList;

public class DAO_contactos {

    private static ArrayList<Contacto> contactos;

    // método estático para recuperar la lista de contactos
    public static ArrayList<Contacto> getContactos(){
        if(contactos == null){
            contactos = new ArrayList<>();
        }
        // public Contacto(String nombre, String apellido, String password, int edad, int telefono){

        contactos.add(new Contacto("uno","uno", "+++++++", 18, 60000000 , R.drawable.foto1));
        contactos.add(new Contacto("dos","uno", "+++++++", 18, 60000000 , R.drawable.foto1));

        contactos.add(new Contacto("tres","uno", "+++++++", 18, 60000000,  R.drawable.foto2));

        contactos.add(new Contacto("cuatro","uno", "+++++++", 18, 60000000, R.drawable.foto1 ));

        contactos.add(new Contacto("cuatro","uno", "+++++++", 18, 60000000, R.drawable.foto3 ));

        contactos.add(new Contacto("cuatro","uno", "+++++++", 18, 60000000 , R.drawable.foto1));

        contactos.add(new Contacto("cuatro","uno", "+++++++", 18, 60000000 , R.drawable.foto4));

        contactos.add(new Contacto("cuatro","uno", "+++++++", 18, 60000000 , R.drawable.foto1));

        contactos.add(new Contacto("cuatro","uno", "+++++++", 18, 60000000 , R.drawable.foto1));

        contactos.add(new Contacto("cuatro","uno", "+++++++", 18, 60000000, R.drawable.foto1 ));

        contactos.add(new Contacto("cuatro","uno", "+++++++", 18, 60000000, R.drawable.foto1 ));

        contactos.add(new Contacto("cuatro","uno", "+++++++", 18, 60000000, R.drawable.foto1 ));

        return contactos;
    }}