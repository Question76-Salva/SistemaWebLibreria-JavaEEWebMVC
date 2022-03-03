/**
 * === AJAX ===
 */

// === seleccionar el documento y cuando este listo invocar una función anónima ===
$(document).ready(function() {
	
	// === capturar formulario de login | y detectar cuando hago click en el botón de "Entrar" ===
	$("#form-login").submit(function(event) {
		// === captura el evento | para que no se refresque/envie por defecto ===
		// evita el comportamiento normal que tiene el botón de tipo "submit", el
		// formulario no se va a enviar, solo se envian los inputs | no se va a
		// pasar a otra pantalla hasta que no haga la petición AJAX
		event.preventDefault();
		// === llamar al método "registrarUsuario" ===
		autenticarUsuario();
	})
	
	// === capturar formulario de registro | y detectar cuando hago click en el botón de "Entrar" ===
	$("#form-register").submit(function(event) {
		// === captura el evento | para que no se refresque/envie por defecto ===
		// evita el comportamiento normal que tiene el botón de tipo "submit", el
		// formulario no se va a enviar, solo se envian los inputs | no se va a
		// pasar a otra pantalla hasta que no haga la petición AJAX
		event.preventDefault();
		// === llamar al método "autenticarUsuario" ===
		registrarUsuario();
	})
});

function autenticarUsuario() {
	
	// === captura el valor del "username" del formulario de login ===
	let username = $("#usuario").val(); 
	// === captura el valor del "contrasena" del formulario de login ===
	let contrasena = $("#contrasena").val();
	
	// === una vez capturados los valores | hacer la llamada AJAX ===
	$.ajax({
		type: "GET",
		dataType: "html",
		url: "./ServletUsuarioLogin",
		data: $.param({
			// parámetros a enviar desde cliente al servidor (Servlet)
			username: username,
			contrasena: contrasena
		}),
		success: function(result) {
			// transformar el data que estoy recibiendo | para poder recorrerlo
			// de json a objeto (array de objetos)
			let parsedResult = JSON.parse(result);
			
			// === si el usuario existe ===
			if (parsedResult != false) {
				// si vuelvo a introducir (después de error) usuario y contrasena
				// y esta vez SI es correcto, tengo que volver a poner la clase de
				// bootstrap d-none al alert, para quitarlo de la pantalla
				$("#login-error").addClass("d-none");
				// acceder/capturar al username que nos está llegando
				let username = parsedResult['username']; 
				// ir al siguiente html/pantalla | home.html ===
				document.location.href = "home.html?username=" + username;
			} else {
				// si el usuario o contraseña son incorrectos | mostrar alert bootstrap
				// seleccionar la clase "login-error" y quitarle la clase d-none
				// para mostrarlo
				$("#login-error").removeClass("d-none");
			}
		}
		
	});	
		
}

function registrarUsuario() {
	
	// === captura el valor del "username" del formulario de registro ===
	let username = $("#input-username").val(); 
	// === captura el valor del "contrasena" del formulario de registro ===
	let contrasena = $("#input-contrasena").val();
	// === captura la confirmación de la contraseña ===
	let contrasenaConfirmacion = $("#input-contrasena-repeat").val();
	// === captura el valor del nombre ===
	let nombre = $("#input-nombre").val();
	// === captura el valor de los apellidos ===
	let apellidos = $("#input-apellidos").val();
	// === captura el valor del email ===
	let email = $("#input-email").val();
	// === captura el valor del saldo ===
	let saldo = $("#input-saldo").val();
	// === captura el valor del premium ===
	let premium = $("#input-premium").prop("checked");
	
	// === validar | contraseña y confirmación sean iguales ===
	if (contrasena == contrasenaConfirmacion) {
		// validación correcta
		// haremos petición AJAX al servidor, le pasaremos todos los campos
		// del usuario que se va a registrar y el back (servidor) hará un 
		// insert en la bbdd				
		// volver a ocultar el div de los errores
		// === una vez capturados los valores | hacer la llamada AJAX ===
		$.ajax({
			type: "GET",
			dataType: "html",
			url: "./ServletUsuarioRegister",
			data: $.param({
				// parámetros a enviar desde cliente al servidor (Servlet)
				username: username,
				contrasena: contrasena,
				nombre: nombre,
				apellidos: apellidos,
				email: email,
				saldo: saldo,
				premium: premium
			}),
			success: function(result) {
				// parsear el JSON que nos va a llegar | transformar el JSON 
				// para poder recorrerlo
				let parsedResult = JSON.parse(result);
				
				// si se ha conseguido la inserción en la bbdd
				if (parsedResult != false) {
					// quitar mensaje de error
					$("#register-error").addClass("d-none");
					// acceder/capturar al username que nos está llegando
					let username = parsedResult['username'];
					// redireccionar a la pantalla de "home.html"
					document.location.href = "home.html?username=" + username;
				} else {
					// en caso contrario | ha habido algún error
					$("#register-error").removeClass("d-none");
					// añadir mensaje de error
					$("#register-error").html("Error en el registro del usuario");
				}
			}
		});
		
	} else {
		// en caso de validación incorrecta | mostrar mensaje de error
		// eliminar la clase d-none del div oculto para mostrar el error
		// en el formulario de registro
		$("#register-error").removeClass("d-none");
		// añadir mensaje de error
		$("#register-error").html("Las contraseñas no coinciden");
	}
	
	
	
	
}