package ni.edu.uca.interaccion_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ni.edu.uca.interaccion_3.data.DataSource
import ni.edu.uca.interaccion_3.databinding.ActivityNuevoUsuarioBinding
import ni.edu.uca.interaccion_3.model.Usuario

class Nuevo_Usuario : AppCompatActivity() {
    private lateinit var binding:ActivityNuevoUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityNuevoUsuarioBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnNnuevo.setOnClickListener {
            val nUsuario = Usuario(
                binding.etUsuario.text.toString(),
                binding.etnContra.text.toString(),
                binding.etdtNac.text.toString(),
                binding.etNombreReal.text.toString()
            )

            if(DataSource().insertar(nUsuario)){
                startActivity(Intent(this,MainActivity::class.java))
            }
        }

    }
}