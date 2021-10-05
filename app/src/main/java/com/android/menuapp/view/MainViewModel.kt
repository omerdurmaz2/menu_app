package com.android.menuapp.view

import android.content.Context
import android.content.Entity
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.menuapp.model.BaseModel
import com.android.menuapp.model.Fields
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class MainViewModel @Inject constructor(
    val gson: Gson
) : ViewModel() {

    val fileName = "data.json"
    lateinit var menu: BaseModel
    var foods = ArrayList<Fields>()
    var daysOfFood = ArrayList<Map<String, List<Fields>>>()

    fun convertJsonToObject(context: Context, result: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val iStream = context.assets.open(fileName)
                val sizeOfFile = iStream.available()
                val bufferData = ByteArray(sizeOfFile)
                iStream.read(bufferData)
                iStream.close()
                var json = String(bufferData)
                menu = gson.fromJson(json, BaseModel::class.java)

                menu.value.forEach {
                    foods.add((it.fields))
                }

                Collections.sort(foods, Comparator.comparing(Fields::itemStartDate))
                Log.e("sss", "foods: $foods")
                result(true)
            } catch (e: IOException) {
                result(false)
                Log.e("sss", "error converting json: $e")
            }
        }
    }


}