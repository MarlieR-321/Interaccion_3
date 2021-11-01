package ni.edu.uca.interaccion_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ni.edu.uca.interaccion_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val u = Usuario("Mar", "abc123", "1232423", "Marlie")

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnIniciar.setOnClickListener {
            if (binding.tvnUsuario.text.isNullOrBlank() && binding.tvCont.text.isNullOrBlank()) {

                Toast.makeText(this, "Por favor llena los campos", Toast.LENGTH_LONG).show()
            } else {
                if (binding.etNombreUsuario.text.toString() == u.Usuario.toString() && binding.etContrasena.text.toString() == u.contras.toString()) {
                    startActivity(Intent(this, Plano_Foto::class.java))
                    Toast.makeText(this, "Bienvenido: ${u.NombreReal}", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(this, "Usuarios y contraseña incorrectos", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}
