package com.example.formulario

import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.formulario.ui.theme.ProyectoClaseTTheme

// Clase jueves 19/11/2026 (student02t). Modificación del formulario
class MainActivityFormulario : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PantallaRegistro() // Pongo la P mayúscula (es una función Composable)
        }
    }
} //Fin de la clase MainActivityFormulario


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioRegistro(modifier: Modifier = Modifier) {

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    //var mensaje by remember() { mutableStateOf("") }
    var acepta by remember { mutableStateOf(false) }
    val opciones = listOf("Presencial", "Online", "Mixta")
    var modalidad by remember { mutableStateOf(opciones[0]) }

    // DEFINICIÓN DE ERRORES Y VALIDACIÓN DEL FORMULARIO
    // El nombre se considera erróneo si está vacío o solo contiene espacios.
    var nombreError = nombre.isBlank()
    // Se quitan espacios al principio y al final antes de comprobar el formato del email.
    var emailError = !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    // La contraseña debe tener al menos 6 caracteres.
    var errorPassword = password.length < 6

    // Control de la edad
    //val edadInt = edad.toIntOrNull()
    var edadError =  edad.toIntOrNull() == null || edad.toInt() < 16 || edad.toInt() > 99

    // Para enviar correctamente, nombre, email y contraseña deben ser válidos y hay que aceptar los términos.
    var formularioValido = !nombreError && !errorPassword && !emailError && !edadError && acepta
    // Los errores se muestran después de pulsar «Registrarse», no mientras se empieza a escribir.
    var intentoEnvio by remember { mutableStateOf(false) }

    var registroCorrecto by remember { mutableStateOf(false) }

    var curso by remember { mutableStateOf("") }
    var expandido by remember { mutableStateOf(false) }
    val cursos = listOf("1º DAM", "2º DAM")


    Box(modifier = modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.fondomovil),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Registro de tu nombre", style = MaterialTheme.typography.headlineLarge)
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre *") },
                // El campo se marca como erróneo solo tras intentar enviar y si el nombre está vacío.
                isError = intentoEnvio && nombreError,

                supportingText = {
                    // El mensaje de nombre obligatorio aparece bajo la misma condición que el error visual.
                    if (nombreError && intentoEnvio) Text("Nombre obligatorio")
                    else Text("* Obligatorio")
                },
                placeholder = { Text("Introduce tu nombre") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
//        Text("Hola $nombre $apellido")
            OutlinedTextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text("Apellido") },
                placeholder = { Text("Introduce tus apellidos") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text("Edad *") },
                placeholder = { Text("Introduce tu edad *") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                isError = intentoEnvio && edadError,
                supportingText = {
                    // Se explica el error únicamente después del intento de envío.
                    if (edadError && intentoEnvio) Text("La edad no es válida (debe ser un número entre 16 y 99)")
                    else Text("* Obligatorio")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono") },
                placeholder = { Text("Introduce tu edad") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico *") },
                placeholder = { Text("Introduce tu correo electrónico") },
                // Se marca el email como erróneo tras intentar enviar si no tiene un formato válido.
                isError = intentoEnvio && emailError,

                supportingText = {
                    // Se explica el error únicamente después del intento de envío.
                    if (emailError && intentoEnvio) Text("El email no es válido")
                    else Text("* Obligatorio")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña *") },
                // La contraseña se señala como errónea tras intentar enviar si tiene menos de 6 caracteres.
                isError = intentoEnvio && errorPassword,

                supportingText = {
                    // El texto indica el requisito mínimo cuando la contraseña no lo cumple.
                    if (errorPassword && intentoEnvio) Text("La contraseña debe contener mínimo 6 caracteres")
                    else Text("* Obligatorio")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                placeholder = { Text("Introduce tu contraseña") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                // El checkbox guarda si se han aceptado los términos; sin marcarlo, el formulario no es válido.
                Checkbox(checked = acepta, onCheckedChange = { acepta = it })
                Text("Acepto los términos *")
            }
            if (intentoEnvio && !acepta) {
                Text("Debes aceptar los términos y condiciones", color = MaterialTheme.colorScheme.error)
            }
            Text("Modalidad: ")
            for (opcion in opciones) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = opcion == modalidad, onClick = { modalidad = opcion })
                    Text(opcion)
                }
            }
            Text("Modalidad elegida: $modalidad")

            // Menú desplegable para seleccionar el curso. (1º o 2º DAM)
            ExposedDropdownMenuBox(
                expanded = expandido,
                onExpandedChange = { expandido = it }
            ) {
                OutlinedTextField(
                    value = curso,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Curso") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido)
                    },
                    modifier = Modifier
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandido,
                    onDismissRequest = { expandido = false }
                ) {
                    for (opcion in cursos) {
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                curso = opcion
                                expandido = false
                            }
                        )
                    }
                }
            }

            // Al pulsar, se habilita la presentación de errores y se evalúa formularioValido.
            Button(
                onClick = {
                    intentoEnvio = true
                    registroCorrecto = formularioValido
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse")
            }

            if (registroCorrecto) Text("Registro correcto")

        }

    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PantallaRegistroPreview() {
    PantallaRegistro()

}

@Composable
private fun PantallaRegistro() {
    ProyectoClaseTTheme() {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            FormularioRegistro(modifier = Modifier.padding(innerPadding))
        }
    }
}