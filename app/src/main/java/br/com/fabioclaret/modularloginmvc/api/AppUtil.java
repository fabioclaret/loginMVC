package br.com.fabioclaret.modularloginmvc.api;

import android.content.Context;
import android.widget.Toast;

public class AppUtil {

    public static void mensagem(Context context, String texto){
        Toast.makeText(context , texto, Toast.LENGTH_SHORT).show();
    }
    public static void mensagem2(Context context, String texto){
        Toast.makeText(context , texto, Toast.LENGTH_SHORT).show();
    }

}
