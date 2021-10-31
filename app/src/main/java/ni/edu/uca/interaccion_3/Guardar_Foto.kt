package ni.edu.uca.interaccion_3

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import ni.edu.uca.interaccion_3.databinding.ActivityGuardarFotoBinding
import java.io.*

class Guardar_Foto : AppCompatActivity() {
    private lateinit var binding:ActivityGuardarFotoBinding
    private val REQUEST_PERMISSION_CAMERA =100
    private val REQUEST_IMAGE_CAMERA =101
    private lateinit var imgBitmap:Bitmap
    private var ruta = ""
    val cameraintent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityGuardarFotoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnTomarFoto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    goToCamera()
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.CAMERA),
                        REQUEST_PERMISSION_CAMERA
                    )
                }
            } else {
                goToCamera()
            }
        }

        binding.btnGuardarFoto.setOnClickListener {
            savePic()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==REQUEST_PERMISSION_CAMERA){
            if(permissions.isNotEmpty()&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                goToCamera()
            }else{
                Toast.makeText(this,"Necesita habilitar", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode,resultCode,data)
        if (requestCode == REQUEST_IMAGE_CAMERA && resultCode == RESULT_OK) {
            //imgBitmap = data?.extras?.get("data") as Bitmap
                // Uri.parse(ruta)
            binding.ivImagen.setImageURI(Uri.parse(ruta))
        }
    }

    //tomar y mostrar la foto
    private fun goToCamera() {

        if(cameraintent.resolveActivity(packageManager)!=null){

            /*var imagenArchivo:File? = null

            try {
                imagenArchivo = crearImagen()
            }catch (e:IOException){
                Log.e("Error: ",e.toString())
            }

            if(imagenArchivo!=null){
                val foto: Uri = FileProvider.getUriForFile(this,"ni.edu.uca.interaccion_3",imagenArchivo)
                cameraintent.putExtra(MediaStore.EXTRA_OUTPUT,foto)
            }*/

            startActivityForResult(cameraintent, REQUEST_IMAGE_CAMERA)

        }
    }

    //guardar la imagen con nombre
    private fun savePic() {
        var imagenArchivo:File? = null

        try {
            imagenArchivo = crearImagen()
        }catch (e:IOException){
            Log.e("Error: ",e.toString())
        }

        if(imagenArchivo!=null){
            val foto: Uri = FileProvider.getUriForFile(this,"ni.edu.uca.interaccion_3",imagenArchivo)
            cameraintent.putExtra(MediaStore.EXTRA_OUTPUT,foto)
        }
    }

    private fun crearImagen():File{
        val nmbImagen = binding.etNombreFoto.text.toString()
        val descrImagen = binding.etDescripcion.text.toString()
        val directorio: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imagen = File.createTempFile(nmbImagen,".jpg",directorio)
        ruta = imagen.absolutePath
        return imagen
    }
}