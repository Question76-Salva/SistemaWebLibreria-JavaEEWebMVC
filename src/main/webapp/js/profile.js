// === capturar el valor del "username" que pasamos a través de la url del navegador ===

// === variable global | con el usuario que esta logado en la aplicación ===
var username = new URL(location.href).searchParams.get("username");

// === variable global | guardar todos los datos del usuario ===
var user;

$(document).ready(function() {
	
	// === obtener los datos del usuario a partir del "username" ===
	fillUsuario().then(function() {
		
		$("#user-saldo").html(user.saldo.toFixed(2)+"€");
		
		// === función | obtener todas las reservas ===
		getAlquiladas(user.username);
		
	});
	
	$("#reservar-btn").attr("href", `home.html?username=${username}`);
	
	// === función | modificar datos del usuario ===
	$("#form-modificar").on("submit", function(event) {
		
		event.preventDefault();
		modificarUsuario();
	});
	
	$("#aceptar-eliminar-cuenta-btn").click(function(){
		
		eliminarCuenta().then(function(){
			location.href = "index.html";
		})
	})
	
});


async function fillUsuario() {
	
	await $.ajax({
		type: "GET",
		dataType: "html",
		url: "./ServletUsuarioPedir",
		data: $.param({
			username: username,

		}),
		success: function(result) {
			let parsedResult = JSON.parse(result);

			if (parsedResult != false) {
				user = parsedResult;
				
				// establecer el valor de los datos del formulario
				// con esos valores
				$("#input-username").val(parsedResult.username);
				$("#input-contrasena").val(parsedResult.contrasena);
				$("#input-nombre").val(parsedResult.nombre);
				$("#input-apellidos").val(parsedResult.apellidos);
				$("#input-email").val(parsedResult.email);
				$("#input-saldo").val(parsedResult.saldo.toFixed(2));
				$("#input-premium").prop("checked",parsedResult.premium);
				
			} else {
				console.log("Error al recuperar los datos del usuario");
			}
		}

	});
	
}


function getAlquiladas(username) {
	
	$.ajax({
		type: "GET",
		dataType: "html",
		url: "./ServletAlquilerListar",
		data: $.param({
			username: username,
		}),
		success: function(result) {
			let parsedResult = JSON.parse(result);

			if (parsedResult != false) {
								
				mostrarHistorial(parsedResult);
								
			} else {
				console.log("Error al recuperar los datos de las reservas");
			}
		}

	});
}


function mostrarHistorial(libros) {
	
	// === variable | generando el código html que voy a mostrar en el cuerpo de la tabla ===	
	let contenido = "";
	
	if (libros.length >= 1) {		
		// recorrer los libros e ir formando las siguientes filas
		// si hay más de un libro en el array | recorrerlo los JSON
		$.each(libros, function(index, libro) {
			// convertir el JSON a objeto
			libro = JSON.parse(libro);
			// voy formando la variable contenido
			contenido += '<tr><th scope="row">' + libro.id + '</th>' +
				'<td>' + libro.titulo + '</td>' +
				'<td>' + libro.genero + '</td>' +
				'<td><input type="checkbox" name="novedad" id="novedad"' + libro.id + '" disabled "';
				if (libro.novedad){
					contenido += 'checked'
				}
				contenido += '></td><td>' + libro.fechaAlquiler + '</td>' +
				'<td><button id="devolver-btn" onclick= "devolverLibro(' + libro.id + ');" class="btn btn-danger">Devolver libro</button></td></tr>';
		});
		
		// cargar el contenido html
		$("#historial-tbody").html(contenido);
		$("#historial-table").removeClass("d-none");
		$("#historial-vacio").addClass("d-none");
		
	} else {
		// mostrar la tabla vacía
		$("#historial-vacio").removeClass("d-none");
		$("#historial-tabla").addClass("d-none");
	}
		
	
}


function devolverLibro(id) {
	
	$.ajax({
		type: "GET",
		dataType: "html",
		url: "./ServletLibroDevolver",
		data: $.param({
			username: username,
			id: id,
		}),
		success: function(result) {
			//let parsedResult = JSON.parse(result);

			if (result != false) {
								
				// recargar la pagina
				location.reload();
								
			} else {
				console.log("Error devolviendo el libro");
			}
		}

	});
	
}


function modificarUsuario() {
	
	// === capturar/leer los campos del formulario | editar usuario ===
	let username = $("#input-username").val();
	let contrasena = $("#input-contrasena").val();
	let nombre = $("#input-nombre").val();
	let apellidos = $("#input-apellidos").val();
	let email = $("#input-email").val();
	let saldo = $("#input-saldo").val();
	let premium = $("#input-premium").prop("checked");
	
	$.ajax({
		type: "GET",
		dataType: "html",
		url: "./ServletUsuarioModificar",
		data: $.param({
			username: username,
			contrasena: contrasena,
			nombre: nombre,
			apellidos: apellidos,
			email: email,
			saldo: saldo,
			premium: premium,
		}),
		success: function(result) {
			//let parsedResult = JSON.parse(result);

			if (result != false) {
								
				// ocultar div de mensaje de  error
				$("#modificar-error").addClass("d-none");
				// mostrar div de mensaje correcto
				$("#modificar-exito").removeClass("d-none");
								
			} else {
				// si se ha producido algún error
				$("#modificar-error").removeClass("d-none");
				$("#modificar-exito").addClass("d-none");
			}
			
			// recargar la página a los 3 segundos
			setTimeout(function() {
				location.reload();
			}, 3000)
		}

	});
	
}


async function eliminarCuenta(){
	
	await $.ajax({
			type:"GET",
			dataType:"html",
			url:"./ServletUsuarioEliminar",
			data: $.param({
				username: username
			}),
			success: function(result){
				
				if (result != false){
					
					console.log("Usuario eliminado")
					
				}
				else{
					console.log("Error eliminando el usuario");
				}
			}
		});
}