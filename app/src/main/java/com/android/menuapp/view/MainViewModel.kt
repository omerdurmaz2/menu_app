package com.android.menuapp.view

import android.content.Context
import android.content.Entity
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import java.util.stream.Collectors
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class MainViewModel @Inject constructor(
    private val gson: Gson
) : ViewModel() {

    private val fileName = "data.json"
    lateinit var menu: BaseModel
    private var foods = ArrayList<Fields>()
    var menuByDate = ArrayList<Pair<Date, List<Fields>>>()

    @RequiresApi(Build.VERSION_CODES.N)
    fun convertJsonToObject(context: Context, result: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val iStream = context.assets.open(fileName)
                val sizeOfFile = iStream.available()
                val bufferData = ByteArray(sizeOfFile)
                iStream.read(bufferData)
                iStream.close()
                val json = String(bufferData)
                menu = gson.fromJson(json, BaseModel::class.java)

                menu.value.forEach {
                    foods.add((it.fields))
                }
                var dates = (foods.stream().collect(Collectors.groupingBy { it.getDay() })).toList()
                    .map { it.first }
                dates = dates.sortedBy { it }

                dates.forEach { date ->
                    menuByDate.add(Pair(date, foods.filter { it.getDay() == date }))
                }




                result.invoke(true)
                Log.e("sss", "list$menuByDate")
            } catch (e: IOException) {
                result.invoke(false)
                Log.e("sss", "error converting json: $e")
            }
        }
    }


}