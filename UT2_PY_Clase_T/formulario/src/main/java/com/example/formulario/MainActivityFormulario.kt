package com.example.formulario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.formulario.ui.theme.ProyectoClaseTTheme

class MainActivityFormulario : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PantallaRegistro()
        }
    }
} // Fin de la clase MainActivityFormulario

@Preview (
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PantallaRegistroPreview() {
    PantallaRegistro()
}

@Composable
fun FormularioRegistro(modifier: Modifier = Modifier) {

    var mensaje by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Registro", style = MaterialTheme.typography.headlineMedium)
        TextField(
            // Valor actual del campo.
            value = nombre,
            // Actualizamos el estado cuando cambia el texto (evento).
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            placeholder = { Text("Introduce tu nombre...") },
            // Impedimos los saltos de línea.
            singleLine = true,
            // Indicamos que el campo de texto ocupe todo el ancho de Column.
            modifier = Modifier.fillMaxWidth()
        )
        Text("Hola, $nombre")
        OutlinedTextField(
            // Valor actual del campo.
            value = apellidos,
            // Actualizamos el estado cuando cambia el texto (evento).
            onValueChange = { apellidos = it },
            label = { Text("Apellidos") },
            // Impedimos los saltos de línea.
            singleLine = true,
            // Indicamos que el campo de texto ocupe todo el ancho de Column.
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
            )
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            // Oculta los caracteres introducidos (con ·).
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
            )
        )
        Button(onClick = { mensaje = "Botón pulsado por Juan L."}) {
            Text("Registrarse")
        }
        Text(mensaje)
        Button(onClick = { mensaje = ""}) {
            Text("Reiniciar")
        }

    }
}

@Composable
private fun PantallaRegistro() {
    ProyectoClaseTTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            FormularioRegistro(modifier = Modifier.padding(innerPadding))
        }
    }
}


