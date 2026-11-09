package com.example.contenedores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contenedores.ui.theme.PMDM_ClaseTTheme

class MainActivityContenedores : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDM_ClaseTTheme {
                MiScaffold()
                MiTarjeta()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MiScaffold() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Mis Tareas") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        MiContenedor(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun MiContenedor(modifier : Modifier = Modifier) {
//    Column(
//        modifier = Modifier.fillMaxSize().padding(48.dp),
//        verticalArrangement = Arrangement.SpaceEvenly,
//        horizontalAlignment = Alignment.Start,
//        ) {
//        Text("Primero")
//        Text("Segundo")
//        Text("Tercero")
//        Text("Cuarto")
//    }
//    Row(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(48.dp),
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        Text("Primero", modifier = Modifier.background
//            (Color.Green))
//        Text("Segundo", modifier = Modifier.background
//            (MaterialTheme.colorScheme.primary))
//        Text("Tercero")
//        Text("Cuarto")
//    }
//    Box(
//        modifier = Modifier.size(120.dp).background(Color.Magenta),
//        contentAlignment = Alignment.Center,
//    ){
//        Text("Centro")
//        Text("Abajo")
//    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.DarkGray)
//            //.verticalScroll(rememberScrollState())
//    ) {
//        Text("1/3 Aupa", modifier = Modifier
//            //.weight(1f)
//            .background(Color.Red)
//            .fillMaxSize()
//        )
//        Spacer(modifier = Modifier.height(100.dp))
//        Text("2/3 Albacete", modifier = Modifier
//            //.weight(2f)
//            .background(Color.Blue),
//            color = MaterialTheme.colorScheme.onPrimary,
//            fontSize = 32.sp)
//        repeat(30){i ->
//            Text("Elemento $i", modifier = Modifier.padding(8.dp))
//        }
//
//    }
    Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
        repeat(20){i ->
            Text("Elemento $i", modifier = modifier.padding(8.dp))
        }
    }
}
@Composable
fun MiTarjeta(){
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Titulo", style = MaterialTheme.typography.titleLarge)
            Text("Descripción de la tarjeta")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MiContenedorPreview() {
    PMDM_ClaseTTheme {
        MiScaffold()
        MiTarjeta()
    }
}