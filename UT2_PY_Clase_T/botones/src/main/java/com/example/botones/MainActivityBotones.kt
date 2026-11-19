package com.example.botones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.botones.ui.theme.ProyectoClaseTTheme

/**
 * Versión modificada del ejemplo realizado en clase.
 * Jueves 12/11/2026.
 */
class MainActivityBotones : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Permite dibujar detrás de las barras del sistema.
        enableEdgeToEdge()

        // Establecemos la interfaz de usuario con Compose.
        setContent {
            // Aplicamos el tema de nuestra aplicación.
            ProyectoClaseTTheme {
                // Mostramos la pantalla principal.
                PantallaFormulario()
            }
        }
    }
}

/**
 * MODIFICACIÓN:
 * Pantalla principal de la aplicación.
 * Contiene un Scaffold con un formulario de ejemplo (MiComposable).
 * Esta función no la hemos desarrollado en clase, pero la he añadido para no repetir
 * 2 veces el mismo código en la función MiComposable y en la vista previa.
 *
 */
@Composable
private fun PantallaFormulario() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        MiComposable(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun MiComposable(modifier: Modifier = Modifier) {
    // Estados que guardan los valores introducidos en los campos de texto.
    // remember conserva sus valores durante las recomposiciones.
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    //var nombre = ""

    // TODO: Continuar el próximo día con la validación de los campos.
    val nombreError = nombre.isBlank()

    // Column coloca los componentes verticalmente.
    // TODO: añadir a Column padding de 16dp y separar los componentes con un espacio de 8dp.
    // TODO: Explicar el uso de la propiedad isError y cómo mostrar un mensaje de error.
    Column(modifier = modifier) {
        OutlinedTextField(
            // Valor actual del campo.
            value = nombre,
            // Actualizamos el estado cuando cambia el texto (evento).
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            // Impedimos los saltos de línea.
            singleLine = true,
            // Indicamos que el campo de texto ocupe todo el ancho de Column.
            modifier = Modifier.fillMaxWidth()
        )
        // TODO: Explicar el uso de imeAction.
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            // Oculta los caracteres introducidos (con *).
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        //MiBoton(modifier)
        // [MODIFICACIÓN] Se quita el parámetro modifier. Ya no es necesario.
        MiBoton()
    }
}

// Composable reutilizable para mostrar un botón.
@Composable
//fun MiBoton(modifier: Modifier) {
// [MODIFICACIÓN] Se quita el parámetro modifier. Ya no es necesario porque
// lo hemos añadido a la Column de MiComposable.
fun MiBoton() {
    Button(
        // Acción que se ejecutará cuando se pulse.
        onClick = { /* Acción al pulsar */ },
        // modifier = modifier // [MODIFICACIÓN] Se quita el parámetro modifier. Ya no es necesario.
    ) {
        // Texto que aparece dentro del botón.
        Text("ACEPTAR")
    }
}

// Vista previa de la pantalla completa.
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MiComposablePreview() {
    ProyectoClaseTTheme {
        PantallaFormulario()
    }
}
