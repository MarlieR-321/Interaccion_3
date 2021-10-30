package ni.edu.uca.interaccion_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ni.edu.uca.interaccion_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnIniciar.setOnClickListener {
            startActivity(Intent(this,Plano_Foto::class.java))
        }

        binding.btnNuevo.setOnClickListener {
            startActivity(Intent(this,Nuevo_Usuario::class.java))
        }
    }
}