package ni.edu.uca.interaccion_3

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ni.edu.uca.interaccion_3.databinding.ActivityPlanoFotoBinding

class Plano_Foto : AppCompatActivity() {
    private lateinit var binding:ActivityPlanoFotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPlanoFotoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            startActivity(Intent(this,Guardar_Foto::class.java))
        }

        binding.btnBuscarFoto.setOnClickListener {
            startActivity(Intent(this,Buscar_Foto::class.java))
        }
    }

}