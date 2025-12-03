/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.*;
import java.util.*;

public class AuthUtil {

    private static final String RUTA_USUARIOS = "users.csv"; // ruta en tu proyecto

    public static boolean validarUsuario(String usuario, String clave) {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_USUARIOS))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(";");
                if (arr.length >= 2) {
                    if (arr[0].equals(usuario) && arr[1].equals(clave)) return true;
                }
            }
        } catch (FileNotFoundException e) {
            // si no existe archivo todavía, no hay usuarios
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean registrarUsuario(String usuario, String clave) {
        if (usuario == null || clave == null || usuario.trim().isEmpty()) return false;
        // comprobar si ya existe
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_USUARIOS))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(";");
                if (arr.length >= 1 && arr[0].equals(usuario)) return false;
            }
        } catch (FileNotFoundException e) {
            // no pasa nada
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA_USUARIOS, true))) {
            pw.println(usuario + ";" + clave);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
