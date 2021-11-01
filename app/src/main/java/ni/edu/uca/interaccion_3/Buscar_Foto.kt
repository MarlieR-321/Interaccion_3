package ni.edu.uca.interaccion_3

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import ni.edu.uca.interaccion_3.databinding.ActivityBuscarFotoBinding

class Buscar_Foto : AppCompatActivity() {
    private lateinit var binding:ActivityBuscarFotoBinding
    private var ruta =""

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityBuscarFotoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBuscarFoto.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                ruta = "${Environment.getExternalStoragePublicDirectory((Environment.DIRECTORY_PICTURES).toString())}/MyApp/${binding.etNombreFoto.text.toString()}.jpg"
            }else{
                ruta = "${Environment.getExternalStoragePublicDirectory((Environment.DIRECTORY_PICTURES).toString())}/${binding.etNombreFoto.text.toString()}.jpg"
            }

            binding.ivImagen.setImageURI(Uri.parse(ruta))
        }
    }
}