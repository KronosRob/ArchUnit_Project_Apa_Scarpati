package alexp.blog.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "alexp.blog")
public class LayerContainmentTest {
    @ArchTest
    public static final ArchRule controllers_reside_in_the_controller_layer = classes()
            .that().haveSimpleNameEndingWith("Controller")
            .should().resideInAPackage("alexp.blog.controller");

    @ArchTest
    public static final ArchRule controllers_only_reside_in_the_controller_layer = noClasses()
            .that().haveSimpleNameNotEndingWith("Controller")
            .should().resideInAPackage("alexp.blog.controller");

    @ArchTest
    public static final ArchRule annotated_controllers_reside_in_the_controller_layer = classes()
            .that().areAnnotatedWith(Controller.class)
            .should().resideInAPackage("alexp.blog.controller");

    @ArchTest
    public static final ArchRule services_reside_in_the_service_layer = classes()
            .that().haveSimpleNameEndingWith("ServiceImpl")
            .should().resideInAPackage("alexp.blog.service");

    @ArchTest
    public static final ArchRule services_only_reside_in_the_service_layer = noClasses()
            .that().haveSimpleNameNotEndingWith("ServiceImpl")
            .should().resideInAPackage("alexp.blog.service");

    @ArchTest
    public static final ArchRule annotated_services_reside_in_the_service_layer = classes()
            .that().areAnnotatedWith(Service.class)
            .should().resideInAPackage("alexp.blog.service");

    @ArchTest
    public static final ArchRule repositories_reside_in_the_repository_layer = classes()
            .that().haveSimpleNameEndingWith("Repository")
            .should().resideInAPackage("alexp.blog.repository");

    @ArchTest
    public static final ArchRule repositories_only_reside_in_the_repository_layer = noClasses()
            .that().haveSimpleNameNotEndingWith("Repository")
            .should().resideInAPackage("alexp.blog.repository");

}
