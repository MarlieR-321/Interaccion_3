package ni.edu.uca.interaccion_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ni.edu.uca.interaccion_3.databinding.ActivityPlanoFotoBinding

class Plano_Foto : AppCompatActivity() {
    private lateinit var binding:ActivityPlanoFotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plano_foto)
    }
}