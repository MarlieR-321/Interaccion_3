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
import android.widget.Toast
import androidx.core.app.ActivityCompat
import ni.edu.uca.interaccion_3.databinding.ActivityGuardarFotoBinding
import java.io.*

class Guardar_Foto : AppCompatActivity() {
    private lateinit var binding:ActivityGuardarFotoBinding
    private val REQUEST_PERMISSION_CAMERA =100
    private val REQUEST_IMAGE_CAMERA =101
    private lateinit var imgBitmap:Bitmap

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
            imgBitmap = data?.extras?.get("data") as Bitmap
            binding.ivImagen.setImageBitmap(imgBitmap)
        }
    }

    //tomar y mostrar la foto
    private fun goToCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAMERA)
            }
        }
    }

    private fun savePic(): Uri? {
        var fos: OutputStream? = null
        var file: File? = null
        var uri: Uri? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            val resolver = this.contentResolver
            val fileName = ("${binding.etNombreFoto.text.toString()}")
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp")
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }

            uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

            try {
                fos = uri?.let { resolver.openOutputStream(it) }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

            values.clear()
            values.put(MediaStore.Images.Media.IS_PENDING, 0)

            if (uri != null) {
                resolver.update(uri, values, null, null)
            }

        } else {
            val imageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString()
            val fileName = ("${System.currentTimeMillis()}${".jpg"}")

            file = File(imageDir, fileName)
            try {
                fos = FileOutputStream(file)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        val save: Boolean = imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        if (save) {
            Toast.makeText(this, "Picture save successfully", Toast.LENGTH_SHORT).show()
        }

        fos?.run {
            flush()
            close()
        }

        if (fos != null) {
            try {
                fos.flush()
                fos.close()

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        if (file != null) {
            //Metodo para buscar nuevos directorios en la galeria y colocar los datos de la img antes descritos
            MediaScannerConnection.scanFile(this, arrayOf(file.toString()), null, null)
        }

        return uri
    }
}
