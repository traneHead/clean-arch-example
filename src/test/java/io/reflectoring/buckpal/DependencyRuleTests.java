package io.reflectoring.buckpal;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.reflectoring.buckpal.archunit.HexagonalArchitecture;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DependencyRuleTests {

  @Disabled("Probably ArchRuleDefinition api change, TODO: investigate")
  @Test
  void validateRegistrationContextArchitecture() {

    HexagonalArchitecture.boundedContext("io.reflectoring.buckpal.account")

        .withDomainLayer("domain")

        .withAdaptersLayer("adapter")
        .incoming("in.web")
        .outgoing("out.persistence")
        .and()

        .withApplicationLayer("application")
        .services("service")
        .incomingPorts("port.in")
        .outgoingPorts("port.out")
        .and()

        .withConfiguration("configuration")
        .check(new ClassFileImporter()
            .importPackages("io.reflectoring.buckpal.."));
  }

  @Disabled("Probably ArchRuleDefinition api change, TODO: investigate")
  @Test
  void testPackageDependencies() {
    ArchRuleDefinition.noClasses()
        .that()
        .resideInAPackage("io.reflectoring.reviewapp.domain..")
        .should()
        .dependOnClassesThat()
        .resideInAnyPackage("io.reflectoring.reviewapp.application..")
        .check(new ClassFileImporter()
            .importPackages("io.reflectoring.reviewapp.."));
  }

}
