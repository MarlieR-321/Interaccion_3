package ni.edu.uca.interaccion_3.data

import android.util.Log
import ni.edu.uca.interaccion_3.model.Usuario

//DataSource para manipular los datos y hacer una lista de tipo Usuario

class DataSource() {
    //Valor predeterminado de un usuario
    val pred= Usuario("mal","mal","2021-12-12","Marlie")

    //para que siempre haya almenos un valor en la lista
    var usuarios:MutableList<Usuario> = mutableListOf(pred)

    public fun insertar(usuario: Usuario): Boolean {
        return if(usuarios.add(usuario)){  //Agrega el usuario en parametro a la lista
            Log.e("Usuarios", usuarios.toString())  //Solo es un log
            true
        } else{
            false
        }
    }
}