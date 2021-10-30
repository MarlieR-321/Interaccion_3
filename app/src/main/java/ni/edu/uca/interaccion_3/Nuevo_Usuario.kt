package ni.edu.uca.interaccion_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ni.edu.uca.interaccion_3.databinding.ActivityNuevoUsuarioBinding

class Nuevo_Usuario : AppCompatActivity() {
    private lateinit var binding:ActivityNuevoUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityNuevoUsuarioBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnNnuevo.setOnClickListener {
            //Ingresar nuevo usuario
            //Si es posible te pasa a la pantalla del LogIn
        }
    }
}