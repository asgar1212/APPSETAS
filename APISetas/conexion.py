import mysql.connector
# from MySQLdb.constants.FLAG import PRI_KEY
# from Tools.scripts.verify_ensurepip_wheels import print_notice
from mysql.connector import Error
import random
import string
import json

def crear_token(length=16):

    characters = string.ascii_letters + string.digits
    return ''.join(random.choice(characters) for _ in range(length))

def crear_conexion():
    try:
        conexion= mysql.connector.connect(host='127.0.0.1',user='root',database='appandroidsetas')
        if conexion.is_connected():
            print('Conexion a base de datos')
            return conexion
    except  Error as e:
        print(f"Error: {e}")
        return None


def usuario_existe(user):
    conexion = crear_conexion()

    if conexion:
        cursor = conexion.cursor()
        try:
            cursor.execute(f"SELECT IDUSUARIO FROM usuario WHERE USER='{user}'")
            resultado = cursor.fetchone()
            print("resultado", resultado)

        finally:
            cursor.close()
            conexion.close()

        if resultado is not None:
            resultado = resultado[0]
        return resultado

    else:
        return {"error": "No se pudo establecer conexión con la base de datos."}

def guardar_usuario( nombre,apellido,user,password):
    conexion=crear_conexion()

    if conexion:
        cursor = conexion.cursor()
        try:
            resultado=usuario_existe(user)

            if resultado is None:
                print("No existe")
                rol=2
                sql = f"INSERT INTO usuario (nombre, apellido, user, password,rol) VALUES ('{nombre}', '{apellido}', '{user}', '{password}','{rol}')"
                cursor.execute(sql)
                conexion.commit()
                return {"mensaje": "Usuario agregado exitosamente."}
            else:
                print("existe")
                return {"error": "Ya existe este usuario, si eres tú loguéate; si no, escoge otro."}

        except mysql.connector.Error as e:
            return {"error": f"Error al agregar usuario: {e}"}

        finally:
            cursor.close()
            conexion.close()
    else:
        return {"error": "No se pudo establecer conexión con la base de datos."}

def loguear(user, password):
    conexion = crear_conexion()

    if conexion:
        cursor = conexion.cursor()
        try:
            iduser = usuario_existe(user)

            if iduser is not None:
                cursor.execute(f"SELECT IDUSUARIO,USER,PASSWORD FROM usuario WHERE USER = '{user}' AND PASSWORD ='{password}'")
                print(f"SELECT IDUSUARIO,USER,PASSWORD FROM usuario WHERE USER = '{user}' AND PASSWORD ='{password}'")
                devuelve = cursor.fetchone()
                print("aa",devuelve)
                if devuelve is not None:

                    idusuario = devuelve[0]
                    userlogueado = devuelve[1]
                    passwordlogueado = devuelve[2]
                    token = crear_token()
                    print("11 " ,idusuario, userlogueado,passwordlogueado)

                    if userlogueado==user and passwordlogueado==password:

                        cursor.execute(f"UPDATE usuario SET TOKEN = '{token}' WHERE IDUSUARIO ='{idusuario}'")
                        conexion.commit()
                        return {"mensaje": "Inicio de sesion correctos", "token": token}

                    else:
                        return {"error": "Usuario o contraseña incorrectos."}
                else:
                    return {"error": "contraseña incorrectos."}
            else:
                return {"error":"Este usuario no existe"}

        except mysql.connector.Error as e:
            return {"error": f"Error al verificar usuario: {e}"}

        finally:
            cursor.close()
            conexion.close()
    else:
        return {"error": "No se pudo establecer conexión con la base de datos."}

def compruebotoken(token):
    conexion = crear_conexion()

    if conexion:
        cursor = conexion.cursor()
        try:
            cursor.execute(f"SELECT TOKEN FROM usuario WHERE TOKEN = '{token}'")
            print(f"SELECT token FROM usuario WHERE token = '{token}'" , token)
            devuelve = cursor.fetchone()

            if devuelve is not None:
                return True
            else:
                 return {"error": f"Este usuario no existe: {False}"}

        except mysql.connector.Error as e:
            return {"error": f"Error al verificar usuario: {e}"}

        finally:
            cursor.close()
            conexion.close()
    else:
         return {"error": "No se pudo establecer conexión con la base de datos."}

def soyuser(token):
    conexion = crear_conexion()

    if conexion:
        cursor = conexion.cursor()
        try:
            cursor.execute(f"SELECT USER FROM usuario WHERE TOKEN = '{token}'")
            print(f"SELECT USER FROM usuario WHERE token = '{token}'")
            devuelve = cursor.fetchone()[0]

            if devuelve is not None:
                return devuelve
            else:
                 return {"error": f"Este usuario no existe: {False}"}

        except mysql.connector.Error as e:
            return {"error": f"Error al verificar usuario: {e}"}

        finally:
            cursor.close()
            conexion.close()
    else:
         return {"error": "No se pudo establecer conexión con la base de datos."}


def consultasetas(tipo, habitat, temporada, tamaño_seta, tamaño_pie, color_pie, anillo, volva, tamaño_sombrero, color_sombrero, color_laminas, esporada, tipo_laminas):
    print(
        f"Recibiendo parámetros: {tipo}, {habitat}, {temporada}, {tamaño_seta}, {tamaño_pie}, {color_pie}, {anillo}, {volva}, {tamaño_sombrero}, {color_sombrero}, {color_laminas}, {esporada}, {tipo_laminas}")
    conexion= crear_conexion()


    if conexion:
        cursor= conexion.cursor()
        try:
            sql=f"""
                SELECT s.nombre
                FROM SETA s
                LEFT JOIN FORMADA f ON s.IDseta = f.IDseta
                LEFT JOIN PIE p ON f.IDpie = p.IDpie
                LEFT JOIN TIENE ti ON s.IDseta = ti.IDseta
                LEFT JOIN SOMBRERO so ON ti.IDsombrero = so.IDsombrero
                LEFT JOIN CONTIENE c ON so.IDsombrero = c.IDsombrero
                LEFT JOIN LAMINAS l ON c.IDlaminas = l.IDlaminas
                WHERE 
                  s.tipo = '{tipo}' AND
                  s.habitat = '{habitat}' AND
                  s.temporada = '{temporada}' AND
                  s.tamaño = '{tamaño_seta}' AND
                  p.tamaño = '{tamaño_pie}' AND
                  p.color = '{color_pie}' AND
                  p.anillo = '{anillo}' AND
                  p.volva = '{volva}' AND
                  so.tamaño = '{tamaño_sombrero}' AND
                  so.color = '{color_sombrero}' AND
                  l.color = '{color_laminas}' AND
                  l.esporada = '{esporada}' AND
                  l.tipo = '{tipo_laminas}';
            """
            print("sql",sql)
            cursor.execute(sql)
            coincidencias=cursor.fetchall()
            print("lo que recibo la consulta",len(coincidencias))
            # Convertir a una lista de setas
            lista_setas = [coincidencia[0] for coincidencia in coincidencias]

            # Imprimir la lista de setas
            print("Lista de usuarios:", lista_setas)

            return json.dumps(lista_setas)

        except mysql.connector.Error as e:
            return {"error": f"Error al hacer la consulta: {e}"}

        finally:
            cursor.close()
            conexion.close()
    else:
        return {"error": "No se pudo establecer conexión con la base de datos."}

def comprueborol(token):
    conexion= crear_conexion()

    if conexion:
        cursor= conexion.cursor()
        try:
            cursor.execute(f"SELECT ROL FROM usuario  WHERE TOKEN = '{token}'")
            rol=cursor.fetchone()

            if rol is not None:
                return rol[0]
            else:
                return 0

        except mysql.connector.Error as e:
            return {"error": f"Error al hacer la consulta: {e}"}

        finally:
            cursor.close()
            conexion.close()
    else:
        return {"error": "No se pudo establecer conexión con la base de datos."}

def elimino_usuario(user):
    conexion = crear_conexion()

    if conexion:
        cursor = conexion.cursor()
        try:
            usuario = usuario_existe(user)
            print("usuario",usuario, "user",user)
            if usuario is not None:
                cursor.execute(f"DELETE  FROM usuario WHERE IDUSUARIO = {usuario}")
                print(f"DELETE FROM usuario WHERE IDUSUARIO = {usuario}")
                conexion.commit()
                return True
            return False

        except mysql.connector.Error as e:
            print(f"Error al borrar usuario: {e}")
            return False


        finally:
            cursor.close()
            conexion.close()
    else:
        print("No se pudo establecer conexión con la base de datos.")
        return False

def modifico_usuario(nombre=None, apellido=None, user=None, passcifrado=None, rol=None, nuevouser=None):
    conexion = crear_conexion()

    if conexion:
        cursor = conexion.cursor()
        try:
            usuario = usuario_existe(user)

            if usuario is not None:
                updates = []

                if nombre is not None:
                    updates.append(f"NOMBRE = '{nombre}'")
                if apellido is not None:
                    updates.append(f"APELLIDO = '{apellido}'")
                if passcifrado is not None:
                    updates.append(f"PASSWORD = '{passcifrado}'")
                if nuevouser is not None:
                    updates.append(f"USER = '{nuevouser}'")

                print("a vaer que llega aqui",nombre,apellido,apellido,user,passcifrado,rol)
                if updates:
                    update_query = ", ".join(updates)
                    cursor.execute(f"UPDATE usuario SET {update_query} WHERE IDUSUARIO = '{usuario}'")
                    conexion.commit()
                    return True
            return False

        except mysql.connector.Error as e:
            print(f"Error al Modificar usuario: {e}")
            return False


        finally:
            cursor.close()
            conexion.close()
    else:
        print("No se pudo establecer conexión con la base de datos.")
        return False

def listo_usuarios():
    conexion = crear_conexion()

    if conexion:
        cursor = conexion.cursor()
        try:
            cursor.execute("SELECT DISTINCT user FROM usuario")  # Ajusta la consulta según tu esquema
            usuarios = cursor.fetchall()

            lista_usuarios = [usuario[0] for usuario in usuarios]  # Suponiendo que cada usuario es una tupla

            print("Lista de usuarios:", lista_usuarios)

            return json.dumps(lista_usuarios)
        except Exception as e:
            print("Error al listar usuarios:", e)
            return json.dumps({"error": str(e)})
        finally:
            cursor.close()
            conexion.close()
    return json.dumps({"error": "No se pudo conectar a la base de datos"})





