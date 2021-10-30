package ni.edu.uca.interaccion_3.data

import android.util.Log
import ni.edu.uca.interaccion_3.model.Usuario

class DataSource() {
    val pred= Usuario("mal","mal","2021-12-12","Marlie")

    var usuarios:MutableList<Usuario> = mutableListOf(pred)

    public fun insertar(usuario: Usuario): Boolean {
        return if(usuarios.add(usuario)){
            Log.e("Usuarios", usuarios.toString())
            true
        } else{
            false
        }
    }
}