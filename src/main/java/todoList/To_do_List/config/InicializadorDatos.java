package todoList.To_do_List.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import todoList.To_do_List.model.Rol;
import todoList.To_do_List.model.Usuario;
import todoList.To_do_List.repository.RolRepositorio;
import todoList.To_do_List.repository.UsuarioRepositorio;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class InicializadorDatos implements CommandLineRunner{
    
    private final RolRepositorio rolRepositorio;

    private final UsuarioRepositorio usuarioRepositorio;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception{
        inicializarRoles();
        inicializarAdministrador();
    }

    private void inicializarRoles(){
        if(!rolRepositorio.existsByNombre("ROLE_USER")){
            rolRepositorio.save(new Rol("ROLE_USER"));
        }

        if(!rolRepositorio.existsByNombre("ROLE_ADMIN")){
            rolRepositorio.save(new Rol("ROLE_ADMIN"));
        }
    }

    private void inicializarAdministrador(){
        if(!usuarioRepositorio.existsByNombreUsuario("admin")){
            Collection<Rol> roles = new ArrayList<>();
            Rol rolUsuario = rolRepositorio.findByNombre("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Rol de usuario no encontrado"));

            Rol rolAdmin = rolRepositorio.findByNombre("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Rol de administrador no encontrado"));

            roles.add(rolUsuario);
            roles.add(rolAdmin);

            Usuario admin = new Usuario();
            admin.setNombre("Administrador");
            admin.setApellido("del sistema");
            admin.setNombreUsuario("admin");
            admin.setCorreo("admin@sistema.com");

            admin.setContrasena(passwordEncoder.encode("admin"));
            admin.setEsAdmin(true);
            admin.setRoles(roles);
            usuarioRepositorio.save(admin);
        }
    }


}

