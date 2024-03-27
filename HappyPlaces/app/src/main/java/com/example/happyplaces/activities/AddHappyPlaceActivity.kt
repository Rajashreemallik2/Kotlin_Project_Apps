package com.example.happyplaces.activities

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.example.happyplaces.R
import com.example.happyplaces.database.DatabaseHandler
import com.example.happyplaces.models.HappyPlaceModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

class AddHappyPlaceActivity : AppCompatActivity(), View.OnClickListener {
    private var cal = Calendar.getInstance()
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    private lateinit var et_date: EditText
    private lateinit var tv_add_image:TextView
    private lateinit var iv_place_image: ImageView
    private lateinit var btn_save: Button
    private lateinit var et_title: EditText
    private lateinit var et_description: EditText
    private lateinit var et_location: EditText


    private var saveImageToInternalStorage: Uri? = null
    private var mLatitude: Double = 0.0
    private var mLongitude:Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_happy_place)
        et_date = findViewById(R.id.et_date)
        tv_add_image = findViewById(R.id.tv_add_image)
        iv_place_image = findViewById(R.id.iv_place_image)
        btn_save = findViewById(R.id.btn_save)
        et_title = findViewById(R.id.et_title)
        et_description = findViewById(R.id.et_description)
        et_location = findViewById(R.id.et_location)


        val toolbarAddPlace:Toolbar = findViewById(R.id.toolbar_add_place)
        setSupportActionBar(toolbarAddPlace)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbarAddPlace.setNavigationOnClickListener {
            onBackPressed()
        }

        dateSetListener = DatePickerDialog.OnDateSetListener {
                view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR,year)
            cal.set(Calendar.MONTH,month)
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateDateInView()
        }
        updateDateInView()
        et_date.setOnClickListener(this)
        tv_add_image.setOnClickListener(this)
        btn_save.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.et_date ->{
                DatePickerDialog(
                    this@AddHappyPlaceActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

            R.id.tv_add_image ->{
                val pictureDialog= AlertDialog.Builder(this)
                pictureDialog.setTitle("Select Action")
                val pictureDialogItems= arrayOf("Select photo from Gallery",
                "Capture photo from camera")
                pictureDialog.setItems(pictureDialogItems){
                    _,which ->
                    when(which){
                        0-> choosePhotoFromGallery()
                        1-> takePhotoFromCamera()
                    }
                }
                pictureDialog.show()
            }

            R.id.btn_save -> {
                when{
                    et_title.text.isNullOrEmpty() -> {
                        Toast.makeText(this,"please enter title", Toast.LENGTH_SHORT).show()
                    }
                    et_description.text.isNullOrEmpty() -> {
                        Toast.makeText(this,"please enter a description", Toast.LENGTH_SHORT).show()
                    }
                    et_location.text.isNullOrEmpty() -> {
                        Toast.makeText(this,"please enter a location", Toast.LENGTH_SHORT).show()
                    }
                    saveImageToInternalStorage == null ->{
                        Toast.makeText(this,"please select an image", Toast.LENGTH_SHORT).show()
                    }else -> {
                        val happyPlaceModel = HappyPlaceModel(
                            0,
                            et_title.text.toString(),
                            saveImageToInternalStorage.toString(),
                            et_description.text.toString(),
                            et_date.text.toString(),
                            et_location.text.toString(),
                            mLatitude,
                            mLongitude
                        )
                    val dbHandler = DatabaseHandler(this)
                    val addHappyPlace = dbHandler.addHappyPlace(happyPlaceModel)
                    if (addHappyPlace > 0) {
                        setResult(Activity.RESULT_OK)
//                        Toast.makeText(
//                            this,
//                            "The happy place details are inserted successfully.",
//                            Toast.LENGTH_SHORT
//                        ).show()

                        finish();//finishing activity

                    }

                    }
                }
            }
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            if (requestCode == GALLERY){
                if (data != null){
                    val contentURI = data.data
                    try {
                        val selectImageBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)

                        saveImageToInternalStorage = saveImageToInternalStorage(selectImageBitmap)
                        Log.e("Saved Image", "path :: $saveImageToInternalStorage")

                        iv_place_image.setImageBitmap(selectImageBitmap)
                    }catch(e: IOException){
                        e.printStackTrace()
                        Toast.makeText(
                            this@AddHappyPlaceActivity,
                            "Failed to load image from the Gallery",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }else if (requestCode == CAMERA){
                val thumbNail: Bitmap = data!!.extras!!.get("data") as Bitmap

                saveImageToInternalStorage = saveImageToInternalStorage(thumbNail)
                Log.e("Saved Image", "path :: $saveImageToInternalStorage")

                iv_place_image.setImageBitmap(thumbNail)
            }
        }
    }
    private fun takePhotoFromCamera(){
        Dexter.withActivity(this).withPermissions(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
        ).withListener(object : MultiplePermissionsListener{

            override fun onPermissionsChecked(
                report: MultiplePermissionsReport?){
                if (report!!.areAllPermissionsGranted()){

                    val galleryIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(galleryIntent, CAMERA)
//                        Toast.makeText(
//                            this@AddHappyPlaceActivity,
//                            "Storage READ/WRITE permission are granted," +
//                                    "Now you can select an image from GALLERY",
//                            Toast.LENGTH_SHORT
//                        ).show()
                }
            }
            override fun onPermissionRationaleShouldBeShown(
                permissions: MutableList<PermissionRequest>,token: PermissionToken){
                showRationaleDialogForPermissions()
            }

        }).onSameThread().check()

    }

    private fun choosePhotoFromGallery(){
        Dexter.withActivity(this).withPermissions(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener{

                override fun onPermissionsChecked(
                    report: MultiplePermissionsReport?){
                    if (report!!.areAllPermissionsGranted()){

                        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        startActivityForResult(galleryIntent, GALLERY)
//                        Toast.makeText(
//                            this@AddHappyPlaceActivity,
//                            "Storage READ/WRITE permission are granted," +
//                                    "Now you can select an image from GALLERY",
//                            Toast.LENGTH_SHORT
//                        ).show()
                    }
                }
                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>,token: PermissionToken){
                    showRationaleDialogForPermissions()
                }

        }).onSameThread().check()
    }
    private fun showRationaleDialogForPermissions(){
        AlertDialog.Builder(this).setMessage("" +
                "It's looks like you turned off permission required" +
                "for this feature. It can be enabled under the" +
                "Applications Settings")
            .setPositiveButton("GO TO SETTINGS")
            { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }catch (e:ActivityNotFoundException){
                    e.printStackTrace()
                }
            }.setNegativeButton("Cancel"){ dialog, _ ->
                dialog.dismiss()
            }.show()
    }


    private fun updateDateInView(){
        val myFormat="dd/MM/yyyy"
        val sdf= SimpleDateFormat(myFormat, Locale.getDefault())
        et_date.setText(sdf.format(cal.time).toString())
    }

    private fun saveImageToInternalStorage(bitmap: Bitmap): Uri{
        val wrapper = ContextWrapper(applicationContext)
        var file = wrapper.getDir(IMAGE_DIRECTORY, Context.MODE_PRIVATE)
        file = File(file,"${UUID.randomUUID()}.jpg")

        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100, stream)
            stream.flush()
            stream.close()
        }catch (e:IOException){
            e.printStackTrace()
        }
        return Uri.parse(file.absolutePath)
    }

    companion object{
        private const val GALLERY = 1
        private const val CAMERA = 2
        private const val IMAGE_DIRECTORY = "HappyPlacesImages"
    }
}

