from flask import Flask, jsonify, request

app = Flask(__name__)

# Datos de ejemplo (simulando una base de datos)
usuarios = [
    {"id": 1, "nombre": "Juan", "edad": 30},
    {"id": 2, "nombre": "Maria", "edad": 25},
    {"id": 3, "nombre": "Pedro", "edad": 40}
]

# Ruta para obtener todos los usuarios
@app.route('/usuarios', methods=['GET'])
def obtener_usuarios():
    return jsonify(usuarios)

# Ruta para obtener un usuario específico por ID
@app.route('/usuarios/<int:id>', methods=['GET'])
def obtener_usuario(id):
    usuario = next((usuario for usuario in usuarios if usuario["id"] == id), None)
    if usuario:
        return jsonify(usuario)
    else:
        return jsonify({"mensaje": "Usuario no encontrado"}), 404

# Ruta para agregar un nuevo usuario.
# Esto lo que hace con el run es como usar py apisetas/principal/ y el metodo que quiero usar
# como el puerto que tengo está siempre escuchando, lo que le llega lo rutea directamente
@app.route('/usuarios', methods=['POST'])
def agregar_usuario():
    nuevo_usuario = request.get_json()
    nuevo_usuario["id"] = len(usuarios) + 1
    usuarios.append(nuevo_usuario)
    return jsonify(nuevo_usuario), 201

# Ruta para actualizar un usuario existente
@app.route('/usuarios/<int:id>', methods=['PUT'])
def actualizar_usuario(id):
    usuario = next((usuario for usuario in usuarios if usuario["id"] == id), None)
    if usuario:
        datos = request.get_json()
        usuario.update(datos)
        return jsonify(usuario)
    else:
        return jsonify({"mensaje": "Usuario no encontrado"}), 404

# Ruta para eliminar un usuario
@app.route('/usuarios/<int:id>', methods=['DELETE'])
def eliminar_usuario(id):
    global usuarios
    usuarios = [usuario for usuario in usuarios if usuario["id"] != id]
    return jsonify({"mensaje": "Usuario eliminado"})

if __name__ == '__main__':
    app.run(debug=True)

