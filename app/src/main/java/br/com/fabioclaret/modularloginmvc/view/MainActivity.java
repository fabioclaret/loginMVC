package br.com.fabioclaret.modularloginmvc.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.fabioclaret.modularloginmvc.R;
import br.com.fabioclaret.modularloginmvc.api.AppUtil;
import br.com.fabioclaret.modularloginmvc.controller.UsuarioController;
import br.com.fabioclaret.modularloginmvc.datamodel.UsuarioDataModel;
import br.com.fabioclaret.modularloginmvc.model.Usuario;

public class MainActivity extends AppCompatActivity {
    EditText email, senha;
    Button criar, entrar;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initComponents();

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean camposValidados = validaCampos();
                if( validaCampos() ){
                    Usuario usuario = new Usuario();
                    UsuarioController controller = new UsuarioController(getApplicationContext());

                    String user = email.getText().toString();
                    String pass = senha.getText().toString();

                    usuario.setNome(user);
                    usuario.setSenha(String.valueOf(senha));

                    String msg = UsuarioDataModel.TABELA;
                    AppUtil.mensagem2(getApplicationContext(), msg);
                    boolean isCheckUser = controller.usuario(user);
                    if(!isCheckUser){
                        AppUtil.mensagem(getApplicationContext(), "Usuario Ainda Nao Cadastrado");
                    }else{
                       boolean isChkPass = controller.usuarioSenha(user, pass);
                       if( isChkPass ){
                           Intent intent = new Intent(MainActivity.this, GeralActivity.class );
                           startActivity(intent);
                       }else{
                           AppUtil.mensagem(getApplicationContext(), "Usuario ou Senha incorretos");
                       }
                    }
                }else{
                    AppUtil.mensagem(getApplicationContext(), "Preencha todos os campos");
                }
            }
        });

        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //boolean camposValidados = validaCampos();

               // if(camposValidados){
                    Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                    startActivity(intent);
               // }else{
               //     AppUtil.mensagem( context, "Digite todos os campos!" );
                //}
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void initComponents() {
        email  = findViewById(R.id.edt_email);
        senha  = findViewById(R.id.edt_senha);
        criar  = findViewById(R.id.btn_cadastrar);
        entrar = findViewById(R.id.btn_entrar);
    }

    private boolean validaCampos(){
        boolean camposValidados = true;

        if(email.getText().toString().isEmpty()){
            camposValidados = false;
            email.setError("Digite o Email!");
            email.requestFocus();
        }

        if(senha.getText().toString().isEmpty()){
            camposValidados = false;
            senha.setError("Digite a Senha!");
            senha.requestFocus();
        }

        return camposValidados;
    }

}