package com.example.habitossaude;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.habitossaude.model.DateConverter; // Certifique-se de que este é o caminho correto
import com.example.habitossaude.model.Usuario;
import com.example.habitossaude.UsuarioDao;

@Database(entities = {Usuario.class}, version = 4, exportSchema = false) // Inclui a entidade Usuario
@TypeConverters(DateConverter.class) // Referência correta para o DateConverter
public abstract class AppDatabase extends RoomDatabase {

    // Singleton para garantir uma única instância do banco de dados
    private static volatile AppDatabase instance;

    // Método abstrato para acessar o DAO de usuários
    public abstract UsuarioDao usuarioDao();

    // Método para obter a instância do banco de dados
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "habitos_saude_database") // Nome do banco
                    .fallbackToDestructiveMigration() // Recria o banco se houver mudanças incompatíveis no esquema
                    .build();
        }
        return instance;
    }
}
