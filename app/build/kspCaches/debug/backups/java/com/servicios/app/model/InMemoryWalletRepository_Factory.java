package com.servicios.app.model;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class InMemoryWalletRepository_Factory implements Factory<InMemoryWalletRepository> {
  @Override
  public InMemoryWalletRepository get() {
    return newInstance();
  }

  public static InMemoryWalletRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static InMemoryWalletRepository newInstance() {
    return new InMemoryWalletRepository();
  }

  private static final class InstanceHolder {
    static final InMemoryWalletRepository_Factory INSTANCE = new InMemoryWalletRepository_Factory();
  }
}
