package com.servicios.app.di;

import com.servicios.app.model.WalletRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AppModule_ProvideWalletRepositoryFactory implements Factory<WalletRepository> {
  @Override
  public WalletRepository get() {
    return provideWalletRepository();
  }

  public static AppModule_ProvideWalletRepositoryFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WalletRepository provideWalletRepository() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideWalletRepository());
  }

  private static final class InstanceHolder {
    static final AppModule_ProvideWalletRepositoryFactory INSTANCE = new AppModule_ProvideWalletRepositoryFactory();
  }
}
