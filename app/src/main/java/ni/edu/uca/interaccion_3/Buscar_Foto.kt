package ni.edu.uca.interaccion_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ni.edu.uca.interaccion_3.databinding.ActivityBuscarFotoBinding

class Buscar_Foto : AppCompatActivity() {
    private lateinit var binding:ActivityBuscarFotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityBuscarFotoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}