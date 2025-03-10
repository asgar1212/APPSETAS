# import http.server
import json
import hashlib

from MySQLdb.constants.ER import GLOBAL_VARIABLE

from conexion import guardar_usuario, loguear, compruebotoken, consultasetas, comprueborol, elimino_usuario, soyuser, \
    modifico_usuario, listo_usuarios
from flask import Flask, jsonify, request

app = Flask(__name__)


# Ruta para agregar un nuevo usuario. Esto lo que hace con el run es como usar py apisetas/principal/ y el metodo que quiero
# como el puerto que tengo está siempre escuchando, lo que le llega lo rutea directamente
@app.route('/agregar_usuario', methods=['POST'])
def agregar_usuario():
    print("peticion recibida",request.url)
    nuevo_usuario = request.get_json()

    # Extraer las variables del JSON
    nombre = nuevo_usuario.get('nombre')
    apellido = nuevo_usuario.get('apellido')
    usuario = nuevo_usuario.get('user')
    password = nuevo_usuario.get('password')
    passcifrado = hashlib.sha256(str(password).encode()).hexdigest()


    # Llamar a la función con las variables extraídas
    respuesta = guardar_usuario(nombre, apellido, usuario, passcifrado)
    return jsonify(respuesta), 201 if 'mensaje' in respuesta else 400

# Ruta para loguear un usuario específico por ID
@app.route('/loguin', methods=['GET'])
def loguin():
    user = request.args.get('user')  # Obtiene el parámetro 'user' de la URL
    password = request.args.get('password')  # Obtiene el parámetro 'password'

    if not user or not password:
        return jsonify({"error": "Faltan parámetros."}), 400

    passcifrado = hashlib.sha256(str(password).encode()).hexdigest()
    respuesta=loguear(user,passcifrado)

    return jsonify(respuesta), 200 if 'mensaje' in respuesta else 400


# Ruta para comprobar si puede haber confusion de setas
@app.route('/comprobar_seta', methods=['GET'])
def comprobar_seta():
    token = request.args.get('token')  # Obtiene el parámetro 'token' de la URl
    if compruebotoken(token):

        tipo = request.args.get('tipo')  # Obtiene el parámetro 'tipo'
        habitat = request.args.get('habitat')  # Obtiene el parámetro 'habitat'
        temporada = request.args.get('temporada')  # Obtiene el parámetro 'temporada'
        tamaño_seta = request.args.get('tamaño_seta')  # Obtiene el parámetro 'tamaño_seta'
        tamaño_pie = request.args.get('tamaño_pie')  # Obtiene el parámetro 'tamaño_pie'
        color_pie = request.args.get('color_pie')  # Obtiene el parámetro 'color_pie'
        anillo = request.args.get('anillo')  # Obtiene el parámetro 'anillo'
        volva = request.args.get('volva')  # Obtiene el parámetro 'volva'
        tamaño_sombrero = request.args.get('tamaño_sombrero')  # Obtiene el parámetro 'tamaño_sombrero'
        color_sombrero = request.args.get('color_sombrero')  # Obtiene el parámetro 'color_sombrero'
        color_laminas = request.args.get('color_laminas')  # Obtiene el parámetro 'color_laminas'
        esporada = request.args.get('esporada')  # Obtiene el parámetro 'esporada'
        tipo_laminas = request.args.get('tipo_laminas')

        seconfunde=consultasetas(tipo, habitat, temporada, tamaño_seta, tamaño_pie, color_pie, anillo, volva, tamaño_sombrero, color_sombrero, color_laminas, esporada, tipo_laminas)

        if seconfunde is not None:
            return seconfunde, 200  # Código 200 para OK

    else:
        return jsonify({'mensaje': 'No se encontraron coincidencias de sesion'}), 404  # Código 404 si no se encontró nada


# Ruta para eliminar un usuario
@app.route('/eliminar_usuarios', methods=['DELETE'])
def eliminar_usuarios():
    token = request.args.get('token')  # Obtiene el parámetro 'user' de la URL
    user = request.args.get('user')  # Obtiene el parámetro 'user' de la URL

    if not token or not user:
        return jsonify({"mensaje": "Faltan parámetros"}), 400

    if compruebotoken(token):
        rol = comprueborol(token)
        if rol == 1:
            try:
                respuesta = elimino_usuario(user)

                if respuesta:
                    return jsonify({"mensaje": "Usuario eliminado"})  # Eliminación exitosa
                else:
                    return jsonify({"mensaje": "Usuario no encontrado"}), 400  # Usuario no encontrado

            except Exception as e:
                return jsonify({"mensaje": "Error al eliminar usuario", "error": str(e)}), 500

        elif rol == 2:
            if soyuser(token) == user:
                try:
                    print("sdfad",token,user)
                    respuesta = elimino_usuario(user)

                    if respuesta:
                        print("Usuario eliminado")
                        return jsonify({"mensaje": "Usuario eliminado"}) ,200 # Eliminación exitosa
                    else:
                        return jsonify({"mensaje": "Usuario no encontrado"}), 400  # Usuario no encontrado

                except Exception as e:
                    return jsonify({"mensaje": "Error al eliminar usuario", "error": str(e)}), 500
            else:
                print("aleluya")
                return jsonify({"mensaje": "No tiene permisos para hacer esta operacion"}), 400
        else:
            return jsonify({"mensaje": "No hay rol asignado"}), 401

    else:
        print("cagada")
        return jsonify({"mensaje": "Token inválido"}), 401

# Ruta para modificar un usuario
@app.route('/modificar_usuario', methods=['GET'])
def modificar_usuario():
    token = request.args.get('token')  # Obtiene el parámetro 'user' de la URL
    nombre = request.args.get('nombre')  # Obtiene el parámetro 'user' de la URL
    apellido = request.args.get('apellido')  # Obtiene el parámetro 'user' de la URL
    user = request.args.get('user')  # Obtiene el parámetro 'user' de la URL
    password = request.args.get('password')  # Obtiene el parámetro 'user' de la URL
    nuevouser = request.args.get('nuevouser')  # Obtiene el parámetro 'user' de la URL
    passcifrado = hashlib.sha256(str(password).encode()).hexdigest()
    print("PARAMETROS",token,nombre,apellido,user,password,nuevouser)

    if not token or not user:
        return jsonify({"mensaje": "Faltan parámetros"}), 400

    if compruebotoken(token):
        rol = comprueborol(token)

        if rol == 1:
            try:
                respuesta = modifico_usuario(
                    nombre=nombre if nombre is not None else None,
                    apellido=apellido if apellido is not None else None,
                    user=user,
                    rol=rol,
                    passcifrado=passcifrado if passcifrado is not None else None,
                    nuevouser = nuevouser if nuevouser is not None else None
                )

                if respuesta:
                    print("Usuario modificado")
                    return jsonify({"mensaje": "Usuario modificado"})  # Eliminación exitosa
                else:
                    return jsonify({"mensaje": "Usuario no encontrado"}), 404  # Usuario no encontrado

            except Exception as e:
                return jsonify({"mensaje": "Error al modificar usuario", "error": str(e)}), 500

        elif rol == 2:
            if soyuser(token) == user:
                try:
                    respuesta = modifico_usuario(
                        nombre=nombre if nombre is not None else None,
                        apellido=apellido if apellido is not None else None,
                        user=user,
                        rol=rol if rol is not None else None,
                        # passcifrado=passcifrado if passcifrado is not None else None
                    )

                    if respuesta:
                        print("Usuario modificado")
                        return jsonify({"mensaje": "Usuario modificado"})
                    else:
                        return jsonify({"mensaje": "Usuario no encontrado"}), 404

                except Exception as e:
                    return jsonify({"mensaje": "Error al modificar usuario", "error": str(e)}), 500
            else:
                print("aleluya")
                return jsonify({"mensaje": "No tiene permisos para hacer esta operacion"}), 404
        else:
            return jsonify({"mensaje": "No hay rol asignado"}), 401

    else:
        return jsonify({"mensaje": "Token inválido"}), 401

@app.route('/listar_usuarios', methods=['GET'])
def listar_usuarios():
    token = request.args.get('token')  # Obtiene el parámetro 'user' de la URL

    if not token :
        return jsonify({"mensaje": "Faltan parámetros"}), 400

    if compruebotoken(token):
        rol = comprueborol(token)
        if rol == 1:
            try:
                respuesta = listo_usuarios()

                if respuesta:
                    # Agregar la respuesta a la lista de resultados
                    return  respuesta
                else:
                    return jsonify({"mensaje": "error en el listado"}), 404  # Usuario no encontrado

            except Exception as e:
                return jsonify({"mensaje": "Error al listar", "error": str(e)}), 500

        elif rol == 2:
            return jsonify({"mensaje": "No tiene permisos para hacer esta operacion"}), 404
        else:
            return jsonify({"mensaje": "No hay rol asignado"}), 401

    else:
        return jsonify({"mensaje": "Token inválido"}), 401


# Ruta para obtener un usuario específico por ID
# @app.route('/loguin', methods=['POST'])
# def loguin():
#
#     print("peticion recibida", request.url)
#     userloguer = request.get_json()
#
#     # Extraer las variables del JSON
#     usuario = userloguer.get('usuario')
#     password = userloguer.get('password')
#     passcifrado = hashlib.sha256(str(password).encode()).hexdigest()
#
#     # Llamar a la función con las variables extraídas
#     respuesta = loguear( usuario, passcifrado)
#
#     return jsonify(respuesta), 201 if 'mensaje' in respuesta else 400

if __name__ == '__main__':
    print('PyCharm')
    app.run(port=4030,debug=True)



