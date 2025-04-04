package br.com.fabioclaret.modularloginmvc.view;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.fabioclaret.modularloginmvc.R;
import br.com.fabioclaret.modularloginmvc.api.AppUtil;
import br.com.fabioclaret.modularloginmvc.controller.UsuarioController;
import br.com.fabioclaret.modularloginmvc.databinding.ActivityCadastroBinding;
import br.com.fabioclaret.modularloginmvc.datamodel.UsuarioDataModel;
import br.com.fabioclaret.modularloginmvc.datasource.AppDataBase;
import br.com.fabioclaret.modularloginmvc.model.Usuario;

public class CadastroActivity extends AppCompatActivity {
    ActivityCadastroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityCadastroBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean camposValidados = validaCampos();
                if( validaCampos() ){
                    Usuario usuario = new Usuario();
                    UsuarioController controller = new UsuarioController(getApplicationContext());

                    String user  = binding.edtNome.getText().toString();
                    String email = binding.edtEmail.getText().toString();
                    String pass  = binding.edtSenha.getText().toString();

                    usuario.setNome(user);
                    usuario.setEmail(email);
                    usuario.setSenha(pass);

                    boolean isCheckUser = controller.usuario(email);
                    if(isCheckUser){
                        AppUtil.mensagem(getApplicationContext(), "Usuario ja Cadastrado");
                    }else{
                        boolean isSuccess = controller.incluir( usuario );
                        if( isSuccess ){
                            binding.edtNome.setText("");
                            binding.edtEmail.setText("");
                            binding.edtSenha.setText("");
                            AppUtil.mensagem(getApplicationContext(), "Registro incluido com sucesso" );
                        }else{
                            AppUtil.mensagem(getApplicationContext(), "ERRO AO INCLUIR O REGISTRO" );
                        }
                    }
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean validaCampos(){
        boolean camposValidados = true;
        if(binding.edtEmail.getText().toString().isEmpty()){
            camposValidados = false;
            binding.edtEmail.setError("Digite o Email!");
            binding.edtEmail.requestFocus();
        }

        if(binding.edtSenha.getText().toString().isEmpty()){
            camposValidados = false;
            binding.edtSenha.setError("Digite a Senha!");
            binding.edtSenha.requestFocus();
        }

        if(binding.edtNome.getText().toString().isEmpty()){
            camposValidados = false;
            binding.edtNome.setError("Digite o Nome!");
            binding.edtNome.requestFocus();
        }

        return camposValidados;
    }
}