package com.example.habitossaude.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.habitossaude.UsuarioDao;
import com.example.habitossaude.model.Usuario;
import com.example.habitossaude.model.Meta;

@Database(entities = {Usuario.class, Meta.class}, version = 4, exportSchema = false)  // Atualizando a versão para 4
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    // Métodos abstratos para acessar os DAOs
    public abstract UsuarioDao usuarioDao();

    // Definindo a migração da versão 3 para 4
    public static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Adiciona a coluna "metaAgua" à tabela "metas" caso não exista
            // A nova coluna 'metaAgua' será inserida com valor padrão 0
            database.execSQL("ALTER TABLE metas ADD COLUMN metaAgua INTEGER DEFAULT 0 NOT NULL");
        }
    };

    // Método para obter a instância do banco de dados
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Utilizando o databaseBuilder com a migração
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "habitos_saude_database")
                            .addMigrations(MIGRATION_3_4)  // Adiciona a migração
                            .fallbackToDestructiveMigration()  // Recria o banco se não houver migração para alguma versão
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
