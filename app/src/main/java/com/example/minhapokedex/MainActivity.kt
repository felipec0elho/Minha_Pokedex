package com.example.minhapokedex

import android.os.Bundle
import android.telecom.Call
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.android.volley.Response
import com.example.minhapokedex.model.PokeApi
import com.example.minhapokedex.model.RetrofitInstance
import com.example.minhapokedex.view.Pokedex
import javax.security.auth.callback.Callback

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    Pokedex()
                }
            }
        }
    }
}
